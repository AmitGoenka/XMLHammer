package org.agoenka.xmlhammer.service;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import static org.agoenka.xmlhammer.service.FileManager.getOutFilePath;
import static org.agoenka.xmlhammer.service.FileManager.getSourcePath;
import static org.agoenka.xmlhammer.service.Parser.*;

/**
 * Authored by agoenka on 8/6/2016.
 */
public class Splitter {

    private static final Logger LOGGER = Logger.getLogger(Splitter.class.getName());

    private static class Counter {
        private static AtomicInteger counter = new AtomicInteger(0);
        private static int get() {
            return counter.addAndGet(1);
        }
        private static int report() {
            return counter.get();
        }
    }

    public static void init (String srcDir, String inDir, String outDir) {
        FileManager.setSrcDir(srcDir);
        FileManager.setInputDir(inDir, true);
        FileManager.setOutputDir(outDir, true);
    }

    public static void split (String srcName, String parentElementName) throws TransformerException, IOException {
        String srcPath = getSourcePath(srcName);
        Document container = load(srcPath);
        Node pivot = pluck(container, parentElementName);
        Document filler = load(srcPath);
        NodeList fillerNodes = findChildren(filler, parentElementName);

        LocalTime startTime = LocalTime.now();
        LOGGER.info("Split operation started: Hang in there! Current local time is: " + startTime);
        fill(container, pivot, fillerNodes);
        LocalTime endTime = LocalTime.now();
        LOGGER.info("Split operation finished. Current local time is: " + endTime);
        LOGGER.info("Total time taken: " + Duration.between(startTime, endTime).toMillis() + " milliseconds");
        LOGGER.info("Total number of node elements filled: " + Counter.report());
    }

    private static void fill (Document container, Node pivot, NodeList fillers) throws TransformerException, IOException {
        if (hasNodes(fillers)) {
            for (int index = 0; index < fillers.getLength(); index++) {
                fill(container, pivot, fillers.item(index));
            }
        }
    }

    private static void fill(Document container, Node pivot, Node filler) throws TransformerException, IOException {
        if (isPure(filler)) {
            Node extract = container.importNode(filler, true);
            pivot.appendChild(extract);
            Transformer.stream(getOutFilePath(filler.getNodeName(), Counter.get()), container);
            pivot.removeChild(extract);
        }
    }

}
