package org.agoenka.xmlsplitter.service;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.agoenka.xmlsplitter.service.FileManager.*;
import static org.agoenka.xmlsplitter.service.Parser.findNode;
import static org.agoenka.xmlsplitter.service.Parser.load;
import static org.agoenka.xmlsplitter.util.CommonUtils.isNotEmpty;
import static org.agoenka.xmlsplitter.util.FileUtils.fileCount;

/**
 * Authored by agoenka on 8/10/2016.
 */
public class Merger {

    public static void init (String srcDir, String inDir, String outDir) {
        FileManager.setSrcDir(srcDir);
        FileManager.setInputDir(inDir, true);
        FileManager.setOutputDir(outDir, true);
    }

    public static void merge(String fileNamePrefix, String pivotElementName, int start, int end) throws Exception {
        merge(getInDir(), getOutDirDir(), fileNamePrefix, pivotElementName, start, end);
    }

    public static void merge(String inDir, String outDir, String fileNamePrefix, String pivotElementName, int start, int end) throws Exception {
        Document container = merge(inDir, fileNamePrefix, pivotElementName, start, end);
        Transformer.stream(getFilePath(outDir, pivotElementName), container);
    }

    public static Document merge(String inDir, String fileNamePrefix, String pivotElementName, int start, int end) {
        int fileCount = fileCount(inDir, fileNamePrefix);
        List<Document> documents = loadDocuments(inDir, fileNamePrefix, fileCount, start, end);
        if (isNotEmpty(documents)) {
            final Document container = documents.get(0);
            if (documents.size() > 1) {
                Node pivot = findNode(container, pivotElementName);
                List<Document> fillers = documents.subList(1, documents.size());
                fillers.forEach(f -> Parser.merge(container, pivot, f));
            }
            return container;
        }
        return null;
    }

    private static List<Document> loadDocuments(final String srcDir, final String fileNamePrefix, int limit, final int start, final int end) {
        final List<Document> documents = new ArrayList<>();
        if (validate(fileNamePrefix, start, end)) {
            IntStream.range(start, end + 1).forEachOrdered(i -> {
                int index = i%limit != 0 ? i%limit : limit;
                String fileName = getFilePath(srcDir, fileNamePrefix, index);
                documents.add(load(fileName));
            });
        }
        return documents;
    }

    private static boolean validate(final String fileNamePrefix, final int start, final int end) {
        return start >= 1 && end >= 1 && end >= start && isNotEmpty(fileNamePrefix);
    }

}