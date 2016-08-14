package org.agoenka.xmlsplitter.service;

import static org.agoenka.xmlsplitter.util.Constants.FILENAME_SEPARATOR;
import static org.agoenka.xmlsplitter.util.Constants.FILE_EXTENSION_DEFAULT;
import static org.agoenka.xmlsplitter.util.FileUtility.hasXMLExtension;
import static org.agoenka.xmlsplitter.util.FileUtility.lintDirectoryName;

/**
 * Authored by agoenka on 8/10/2016.
 */
public class FileManager {

    private static String SRC_DIR = "";
    private static String IN_DIR = SRC_DIR;
    private static String OUT_DIR = SRC_DIR;

    static String getSourcePath(String srcName) {
        return hasXMLExtension(srcName) ? IN_DIR + srcName : IN_DIR + srcName + ".xml";
    }

    static String getFileName(String nodeName, int counter) {
        return OUT_DIR + nodeName + FILENAME_SEPARATOR + counter + FILE_EXTENSION_DEFAULT;
    }

    public static void setSrcDir (String srcDir) {
        SRC_DIR = lintDirectoryName(srcDir);
    }

    public static void setInputDir (String inDir, boolean relative) {
        inDir = lintDirectoryName(inDir);
        IN_DIR = relative ? SRC_DIR + inDir : inDir;
    }

    public static void setOutputDir (String outDir, boolean relative) {
        outDir = lintDirectoryName(outDir);
        OUT_DIR = relative ? SRC_DIR + outDir : outDir;
    }

}
