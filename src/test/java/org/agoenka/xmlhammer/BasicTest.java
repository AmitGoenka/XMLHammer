package org.agoenka.xmlhammer;

import org.agoenka.xmlhammer.runtime.model.ParamMarkers;
import org.agoenka.xmlhammer.util.Functions;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static org.agoenka.xmlhammer.util.Errors.ARGS_MANDATORY;
import static org.junit.Assert.*;

/**
 * Authored by agoenka on 8/20/2016.
 */
public class BasicTest {

    private static final Logger LOGGER = Logger.getLogger(BasicTest.class.getName());

    @Test
    public void testTime() {
        LocalTime timeStart = LocalTime.now();
        LocalTime timeEnd = LocalTime.now();
        Long millis = Duration.between(timeStart, timeEnd).toMillis();
        LOGGER.info("Millis: " + millis);
        assertTrue(millis > 0);
    }

    @Test
    public void testErrorString() {
        String elementName = "PivotElementName";
        String baseString = ARGS_MANDATORY.get();
        String formattedString = ARGS_MANDATORY.get(elementName);
        LOGGER.info(ARGS_MANDATORY.get());
        LOGGER.info(ARGS_MANDATORY.get("Pivot Element Name"));
        assertNotEquals(formattedString, "");
        assertNotEquals(baseString, formattedString);
    }

    @Test
    public void testParamMarkerFields() {
        List<String> markerValues = Functions.getStaticFieldValues(ParamMarkers.class);
        assertNotNull(markerValues);
        assertTrue(markerValues.size() > 0);
    }

    @Test(expected = NumberFormatException.class)
    public void testParseInteger() {
        String string = "-S";
        int integer = Integer.valueOf(string);
        LOGGER.info("" + integer);
    }

    @Test
    public void testOverflow() {
        int start = 47;
        int end = 100;
        int limit = 17;
        List<Integer> indexList = new ArrayList<>();

        LOGGER.info("Begin:: " + "start: " + start + ", end: " + end + ", limit: " + limit);
        IntStream.range(start, end + 1).forEachOrdered(i -> {
            int index = i%limit != 0 ? i%limit : limit;
            indexList.add(index);
        });

        Assert.assertTrue(indexList.size() == 1 + end - start);
        int max = indexList.stream().max(Integer::compareTo).orElse(0);
        Assert.assertTrue(max == limit);
    }

}