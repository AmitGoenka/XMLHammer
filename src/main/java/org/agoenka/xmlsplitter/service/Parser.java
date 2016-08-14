package org.agoenka.xmlsplitter.service;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Authored by agoenka on 8/5/2016.
 */
class Parser {

    static Document load(String filePath) throws ParserConfigurationException, IOException, SAXException {
        Document document = newInstance().parse(new File(filePath));
        document.getDocumentElement().normalize();
        return document;
    }

    static NodeList findChildren (Document document, String parentNodeName) {
        Node node = findNode(document, parentNodeName);
        return node != null ? node.getChildNodes() : null;
    }

    static boolean hasStuff(NodeList nodes) {
        return nodes != null && nodes.getLength() > 0;
    }

    static int len(NodeList nodes) {
        return nodes != null ? nodes.getLength() : 0;
    }

    static boolean isPure(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE;
    }

    static Node pluck(Document document, String pivotElementName) {
        return removeChildren(document, pivotElementName);
    }

    private static Node findNode (Document document, String nodeName) {
        NodeList nodes = findNodes(document, nodeName);
        return nodes != null ? nodes.item(0) : null;
    }

    private static NodeList findNodes(Document document, String nodeName) {
        return document.getElementsByTagName(nodeName).getLength() > 0 ? document.getElementsByTagName(nodeName) : null;
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

    private static DocumentBuilder newInstance() throws ParserConfigurationException {
        return DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder();
    }

}
