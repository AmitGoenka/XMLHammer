package org.agoenka.xmlsplitter;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import static org.agoenka.xmlsplitter.Parser.*;

/**
 * Authored by agoenka on 8/6/2016.
 */
class Splitter {

    private static final Logger LOGGER = Logger.getLogger(Splitter.class.getName());

    private static String SRC_DIR = "";
    private static String IN_DIR = SRC_DIR;
    private static String OUT_DIR = SRC_DIR;
    private static class Counter {
        private static int counter = 0;
        private static int get() {
            return ++counter;
        }
        private static int report() {
            return counter;
        }
    }

    static void split (String srcName, String parentElementName) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        String srcPath = getSourcePath(srcName);
        Document container = load(srcPath);
        Node pivot = peel(container, parentElementName);
        Document filler = load(srcPath);
        NodeList fillerNodes = findChildren(filler, parentElementName);

        fill(container, pivot, fillerNodes);
        LOGGER.info("Total number of node elements filled: " + Counter.report());
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
            Transformer.stream(getFileName(filler), container);
            pivot.removeChild(extract);
        }
    }

    private static String getSourcePath(String srcName) {
        if (hasXMLExtension(srcName)) return IN_DIR + srcName;
        else return IN_DIR + srcName + ".xml";
    }

    private static boolean hasXMLExtension (String name) {
        return name != null && (name.endsWith(".xml") || name.endsWith(".XML"));
    }

    private static String getFileName(Node node) {
        return OUT_DIR + node.getNodeName() + "-" + Counter.get() + ".xml";
    }

    static void setSrcDir (String srcDir) {
        SRC_DIR = lintDirectoryName(srcDir);
    }

    static void setInputDir (String inDir, boolean relative) {
        inDir = lintDirectoryName(inDir);
        if (relative) IN_DIR = SRC_DIR + inDir;
        else IN_DIR = inDir;
    }

    static void setOutputDir (String outDir, boolean relative) {
        outDir = lintDirectoryName(outDir);
        if (relative) OUT_DIR = SRC_DIR + outDir;
        else OUT_DIR = outDir;
    }

    private static String lintDirectoryName (String dir) {
        return (missingTrailingSlash(dir)) ? dir += "/" : dir;
    }

    private static boolean missingTrailingSlash (String dir) {
        return dir != null && !dir.trim().equals("") && (!dir.trim().endsWith("/") || !dir.trim().endsWith("\\"));
    }

}
