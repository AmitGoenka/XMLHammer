package org.agoenka.xmlsplitter;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;

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

    @Test
    public void time() {
        LocalTime timeStart = LocalTime.now();
        LocalTime timeEnd = LocalTime.now();
        Long millis = Duration.between(timeStart, timeEnd).toMillis();
        System.out.println(millis);
    }

}