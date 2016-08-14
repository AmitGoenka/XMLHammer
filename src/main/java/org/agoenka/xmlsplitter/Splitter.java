package org.agoenka.xmlsplitter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import static org.agoenka.xmlsplitter.FileManager.getFileName;
import static org.agoenka.xmlsplitter.FileManager.getSourcePath;
import static org.agoenka.xmlsplitter.Parser.*;

/**
 * Authored by agoenka on 8/6/2016.
 */
class Splitter {

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

    static void split (String srcName, String parentElementName) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        String srcPath = getSourcePath(srcName);
        Document container = load(srcPath);
        Node pivot = pluck(container, parentElementName);
        Document filler = load(srcPath);
        NodeList fillerNodes = findChildren(filler, parentElementName);

        LOGGER.info("Split operation started: Hang in there! Current local time is: " + LocalTime.now());
        fill(container, pivot, fillerNodes);
        LOGGER.info("Split operation finished. Current local time is: " + LocalTime.now());
        LOGGER.info("Total number of node elements filled: " + Counter::report);
    }

    private static void fill (Document container, Node pivot, NodeList fillers) throws TransformerException, IOException {
        if (hasStuff(fillers)) {
            for (int index = 0; index < fillers.getLength(); index++) {
                fill(container, pivot, fillers.item(index));
            }
        }
    }

    private static void fill(Document container, Node pivot, Node filler) throws TransformerException, IOException {
        if (isPure(filler)) {
            Node extract = container.importNode(filler, true);
            pivot.appendChild(extract);
            Transformer.stream(getFileName(filler.getNodeName(), Counter.get()), container);
            pivot.removeChild(extract);
        }
    }

}
