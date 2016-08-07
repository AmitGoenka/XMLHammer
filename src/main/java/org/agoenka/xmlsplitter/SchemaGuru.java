package org.agoenka.xmlsplitter;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * Authored by agoenka on 8/6/2016.
 */
class SchemaGuru {

    static void validateSchema (String schemaFilePath, Node document) throws SAXException, IOException {
        loadSchema(schemaFilePath)
                .newValidator()
                .validate(new DOMSource(document));
    }

    private static Schema loadSchema(String filePath) throws SAXException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        return SchemaFactory
                .newInstance(language)
                .newSchema(new File(filePath));
    }

}
