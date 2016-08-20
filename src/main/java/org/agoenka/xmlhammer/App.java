package org.agoenka.xmlhammer;

import org.agoenka.xmlhammer.service.Splitter;

import java.util.Arrays;
import java.util.logging.Logger;

import static org.agoenka.xmlhammer.functions.Validate.validate;
import static org.agoenka.xmlhammer.util.Errors.*;

/**
 * Authored by agoenka on 8/6/2016.
 */
public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main (String... args) throws Exception {
        validateArgs(args);

        String fileName = getArgs(args, 0);
        String pivotElementName = getArgs(args, 1);
        String srcDir = getArgs(args, 2);
        String inDir = getArgs(args, 3);
        String outDir = getArgs(args, 4);

        validateArg(fileName, "File Name");
        validateArg(pivotElementName, "Pivot Element Name");

        Splitter.init(srcDir, inDir, outDir);
        Splitter.split(fileName, pivotElementName);
    }

    private static String getArgs (String[] args, int index) {
        return (args.length > index && args[index] != null && !args[index].trim().equals("")) ? args[index].trim() : "";
    }

    private static void validateArgs(String[] args) {
        boolean valid = false;
        if (validate(args, p -> p.length < 2)) LOGGER.severe(ARGS_MISSING_MANDATORY.get());
        else if (validate(args, p -> p.length > 5)) LOGGER.severe(ARGS_TOO_MANY.get());
        else valid = true;
        if (!valid) onError();
    }

    private static void validateArg(String arg, String parameterName) {
        boolean valid = false;
        if (arg.equals("")) LOGGER.severe(ARGS_EMPTY.get(parameterName));
        else valid = true;
        if (!valid) onError();
    }

    private static void onError() {
        System.exit(0);
    }

    private static String getMode (String... args) {
        boolean merge = Arrays.stream(args).anyMatch(a -> ("-m").equalsIgnoreCase(a));

        return null;
    }

}
