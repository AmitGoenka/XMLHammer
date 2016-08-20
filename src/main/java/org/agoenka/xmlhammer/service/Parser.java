package org.agoenka.xmlhammer.service;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Authored by agoenka on 8/5/2016.
 */
class Parser {

    private static final Logger log = Logger.getLogger(Parser.class.getName());

    static Document load(String filePath) {
        Document document = null;
        try {
            document = newInstance().parse(new File(filePath));
            document.getDocumentElement().normalize();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            log.severe(e.getMessage());
        }
        return document;
    }

    private static DocumentBuilder newInstance() throws ParserConfigurationException {
        return DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder();
    }

    static NodeList findChildren (Document document, String parentNodeName) {
        Node node = findNode(document, parentNodeName);
        return node != null ? node.getChildNodes() : null;
    }

    static Node findNode (Document document, String nodeName) {
        NodeList nodes = findNodes(document, nodeName);
        return nodes != null ? nodes.item(0) : null;
    }

    private static NodeList findNodes (Document document, String nodeName) {
        return document.getElementsByTagName(nodeName).getLength() > 0 ? document.getElementsByTagName(nodeName) : null;
    }

    static Node pluck(Document document, String pivotElementName) {
        return removeChildren(document, pivotElementName);
    }

    private static Node removeChildren (Document document, String parentElementName) {
        return removeChildren(findNode(document, parentElementName));
    }

    private static Node removeChildren (Node parentNode) {
        while (parentNode.getFirstChild() != null) {
            parentNode.removeChild(parentNode.getFirstChild());
        }
        return parentNode;
    }

    static void merge(Document container, Node pivot, Document filler) {
        NodeList fillerNodes = findChildren(filler, pivot.getNodeName());
        merge(container, pivot, fillerNodes);
    }

    private static void merge(Document container, Node pivot, NodeList fillers) {
        if (hasNodes(fillers)) {
            for (int index = 0; index < fillers.getLength(); index++) {
                merge(container, pivot, fillers.item(index));
            }
        }
    }

    private static void merge(Document container, Node pivot, Node filler) {
        if (isPure(filler)) {
            Node extract = container.importNode(filler, true);
            pivot.appendChild(extract);
        }
    }

    static boolean hasNodes(NodeList nodes) {
        return nodes != null && nodes.getLength() > 0;
    }

    static boolean isPure(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE;
    }

    static int len(NodeList nodes) {
        return nodes != null ? nodes.getLength() : 0;
    }

}
