package org.agoenka.xmlsplitter;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Authored by agoenka on 8/6/2016.
 */
public class App {

    public static void main (String... args) throws SAXException, TransformerException, ParserConfigurationException, IOException {

        validateArgs(args);

        String fileName = getArgs(args, 0);
        String pivotElementName = getArgs(args, 1);
        String srcDir = getArgs(args, 2);
        String inDir = getArgs(args, 3);
        String outDir = getArgs(args, 4);

        validateArg(fileName, "File Name");
        validateArg(pivotElementName, "Pivot Element Name");

        Splitter.setSrcDir(srcDir);
        Splitter.setInputDir(inDir, true);
        Splitter.setOutputDir(outDir, true);
        Splitter.split(fileName, pivotElementName);
    }

    private static String getArgs (String[] args, int index) {
        return (args.length > index && args[index] != null && !args[index].trim().equals("")) ? args[index].trim() : "";
    }

    private static void validateArgs(String[] args) {
        boolean valid = false;
        if (args.length < 2) System.err.println("Error: missing arguments: xml file name and pivot element name required.");
        else if (args.length > 5) System.err.println("Error: too many arguments: only five parameters are supported.");
        else valid = true;
        if (!valid) onError();
    }

    private static void validateArg(String arg, String parameterName) {
        boolean valid = false;
        if (arg.equals("")) System.err.println("Error: empty parameter: '" + parameterName + "' can not be empty.");
        else valid = true;
        if (!valid) onError();
    }

    private static void onError() {
        System.exit(0);
    }

}
