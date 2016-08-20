package org.agoenka.xmlsplitter;

import org.agoenka.xmlsplitter.service.Merger;
import org.agoenka.xmlsplitter.service.Splitter;
import org.junit.Test;

/**
 * Authored by agoenka on 8/20/2016.
 */
public class MergerTest {

    @Test
    public void testMerge() throws Exception {
        String fileName = "Company.xml";
        String parentElementName = "Employees";
        Splitter.init("src/test/resources/examples/", "in/", "out/");
        Splitter.split(fileName, parentElementName);
        Merger.init("src/test/resources/examples/", "out/", "in/");
        Merger.merge("Employee", "Employees", 1, 2);
    }

}
