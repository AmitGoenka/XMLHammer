package org.agoenka.xmlsplitter;

import org.junit.Test;

/**
 * Authored by agoenka on 8/6/2016.
 */
public class SplitterTest {

    @Test
    public void splitTest() throws Exception {
        FileManager.setSrcDir("src/test/resources/examples/");
        FileManager.setInputDir("in/", true);
        FileManager.setOutputDir("out/", true);
        String fileName = "Company.xml";
        String parentElementName = "Employees";
        Splitter.split(fileName, parentElementName);
    }

}