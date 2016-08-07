package org.agoenka.xmlsplitter;

import org.junit.Test;

/**
 * Authored by agoenka on 8/6/2016.
 */
public class SplitterTest {

    @Test
    public void getDocument() throws Exception {
        Splitter.setSrcDir("src/test/resources/examples/");
        Splitter.setInputDir("in/", true);
        Splitter.setOutputDir("out/", true);
        String fileName = "Company.xml";
        String parentElementName = "Employees";
        Splitter.split(fileName, parentElementName);
    }

}