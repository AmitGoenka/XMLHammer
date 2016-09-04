package org.agoenka.xmlhammer.service;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static org.agoenka.xmlhammer.runtime.Validator.validateNoFileFound;
import static org.agoenka.xmlhammer.service.FileManager.*;
import static org.agoenka.xmlhammer.service.Parser.findNode;
import static org.agoenka.xmlhammer.service.Parser.load;
import static org.agoenka.xmlhammer.util.CommonUtils.isNotEmpty;
import static org.agoenka.xmlhammer.util.FileUtils.fileCount;

/**
 * Authored by agoenka on 8/10/2016.
 */
public class Merger {

    private static final Logger LOGGER = Logger.getLogger(Merger.class.getName());
    private static LocalTime startTime;

    public static void init (String srcDir, String inDir, String outDir) {
        FileManager.setSrcDir(srcDir);
        FileManager.setInputDir(inDir, true);
        FileManager.setOutputDir(outDir, true);
    }

    public static void merge(String fileNamePrefix, String pivotElementName, int start, int end) throws Exception {
        logStart();
        merge(getInDir(), getOutDirDir(), fileNamePrefix, pivotElementName, start, end);
        logEnd();
    }

    public static void merge(String inDir, String outDir, String fileNamePrefix, String pivotElementName, int start, int end) throws Exception {
        Document container = merge(inDir, fileNamePrefix, pivotElementName, start, end);
        Transformer.stream(getFilePath(outDir, pivotElementName), container);
    }

    public static void merge(String inDir, String outDir, String fileNamePrefix, String pivotElementName, String outFileName, int start, int end) throws Exception {
        Document container = merge(inDir, fileNamePrefix, pivotElementName, start, end);
        Transformer.stream(getFilePath(outDir, outFileName), container);
    }

    private static boolean validate(final String fileNamePrefix, final int start, final int end) {
        return start >= 1 && end >= 1 && end >= start && isNotEmpty(fileNamePrefix);
    }

    private static void logStart() {
        startTime = LocalTime.now();
        LOGGER.info("Merge operation started: Hang in there! Current local time is: " + startTime);
    }

    private static void logEnd() {
        LocalTime endTime = LocalTime.now();
        LOGGER.info("Merge operation finished. Current local time is: " + endTime);
        LOGGER.info("Total time taken: " + Duration.between(startTime, endTime).toMillis() + " milliseconds");
    }

    public static Document merge(String inDir, String fileNamePrefix, String pivotElementName, int start, int end) throws ExecutionException, InterruptedException {
        int fileCount = fileCount(inDir, fileNamePrefix);
        validateNoFileFound(fileCount);
        if (start < 0) start = 1;
        if (end <= 0) end = fileCount;
        return loadDocuments(inDir, fileNamePrefix, fileCount, start, end)
                .thenApplyAsync(documents -> {
                    LOGGER.info("All documents are currently loaded in memory, current time is: " + LocalTime.now() + ", No of documents to process: " + documents.size());
                    if (isNotEmpty(documents)) {
                        final Document container = documents.get(0);
                        if (documents.size() > 1) {
                            Node pivot = findNode(container, pivotElementName);
                            List<Document> fillers = documents.subList(1, documents.size());
                            fillers.forEach(f -> Parser.merge(container, pivot, f));
                            return container;
                        }
                    }
                    return null;
                })
                .join();
    }

    private static CompletableFuture<List<Document>> loadDocuments(final String srcDir, final String fileNamePrefix, int limit, final int start, final int end) {
        return CompletableFuture.supplyAsync(() -> {
            final List<Document> documents = new ArrayList<>();
            if (validate(fileNamePrefix, start, end)) {
                IntStream.range(start, end + 1).forEachOrdered(i -> {
                    int index = i % limit != 0 ? i % limit : limit;
                    String fileName = getFilePath(srcDir, fileNamePrefix, index);
                    documents.add(load(fileName));
                });
            }
            return documents;
        });
    }

}