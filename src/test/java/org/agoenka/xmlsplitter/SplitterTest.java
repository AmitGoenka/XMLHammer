package org.agoenka.xmlsplitter;

import org.agoenka.xmlsplitter.service.FileManager;
import org.agoenka.xmlsplitter.service.Splitter;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.logging.Logger;

import static org.agoenka.xmlsplitter.util.Errors.ARGS_EMPTY;

/**
 * Authored by agoenka on 8/6/2016.
 */
public class SplitterTest {

    private static final Logger LOGGER = Logger.getLogger(SplitterTest.class.getName());

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

    @Test
    public void error() {
        String elementName = "PivotElementName";
        String baseString = ARGS_EMPTY.get();
        String formattedString = ARGS_EMPTY.get(elementName);
        LOGGER.severe(ARGS_EMPTY.get());
        LOGGER.severe(ARGS_EMPTY.get("Pivot Element Name"));
        Assert.assertNotEquals(formattedString, "");
        Assert.assertNotEquals(baseString, formattedString);
    }

}