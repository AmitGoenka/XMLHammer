package org.agoenka.xmlsplitter;

import org.agoenka.xmlsplitter.service.Splitter;
import org.junit.Test;

/**
 * Authored by agoenka on 8/6/2016.
 */
public class SplitterTest {

    @Test
    public void testSplit() throws Exception {
        String fileName = "Company.xml";
        String parentElementName = "Employees";
        Splitter.init("src/test/resources/examples/", "in/", "out/");
        Splitter.split(fileName, parentElementName);
    }

}