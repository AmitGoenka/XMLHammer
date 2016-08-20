package org.agoenka.xmlhammer;

import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static org.agoenka.xmlhammer.util.Errors.ARGS_EMPTY;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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
        String baseString = ARGS_EMPTY.get();
        String formattedString = ARGS_EMPTY.get(elementName);
        LOGGER.info(ARGS_EMPTY.get());
        LOGGER.info(ARGS_EMPTY.get("Pivot Element Name"));
        assertNotEquals(formattedString, "");
        assertNotEquals(baseString, formattedString);
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
