package org.agoenka.xmlsplitter.service;

import org.w3c.dom.Node;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Authored by agoenka on 8/6/2016.
 */
class Transformer {

    private static javax.xml.transform.Transformer newInstance() throws TransformerConfigurationException {
        javax.xml.transform.Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        return transformer;
    }

    private static StreamResult newStreamResult(String filePath) throws IOException {
        return new StreamResult(new FileWriter(filePath));
    }

    static void stream (String filePath, Node node) throws TransformerException, IOException {
        newInstance().transform(new DOMSource(node), newStreamResult(filePath));
    }

}