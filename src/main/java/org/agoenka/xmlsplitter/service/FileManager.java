package org.agoenka.xmlsplitter.service;

import static org.agoenka.xmlsplitter.util.Constants.FILENAME_SEPARATOR;
import static org.agoenka.xmlsplitter.util.Constants.FILE_EXTENSION_DEFAULT;
import static org.agoenka.xmlsplitter.util.FileUtils.hasXMLExtension;
import static org.agoenka.xmlsplitter.util.FileUtils.lintDirectoryName;

/**
 * Authored by agoenka on 8/10/2016.
 */
public class FileManager {

    private static String SRC_DIR = "";
    private static String IN_DIR = SRC_DIR;
    private static String OUT_DIR = SRC_DIR;

    static String getSourcePath(String srcName) {
        return hasXMLExtension(srcName) ? IN_DIR + srcName : IN_DIR + srcName + FILE_EXTENSION_DEFAULT;
    }

    static String getOutFilePath(String fileNamePrefix, int index) {
        return OUT_DIR + getFileName(fileNamePrefix, index);
    }

    static String getInFilePath(final String fileNamePrefix, final int index) {
        return IN_DIR + getFileName(fileNamePrefix, index);
    }

    static String getFilePath(final String srcDir, final String fileNamePrefix) {
        return srcDir + getFileName(fileNamePrefix);
    }

    static String getFilePath(final String srcDir, final String fileNamePrefix, final int index) {
        return srcDir + getFileName(fileNamePrefix, index);
    }

    private static String getFileName(final String fileNamePrefix) {
        return fileNamePrefix + FILE_EXTENSION_DEFAULT;
    }

    private static String getFileName(final String fileNamePrefix, final int index) {
        return getBaseFileName(fileNamePrefix, index) + FILE_EXTENSION_DEFAULT;
    }

    private static String getBaseFileName(final String fileNamePrefix, final int index) {
        return fileNamePrefix + FILENAME_SEPARATOR + index;
    }

    static void setSrcDir (String srcDir) {
        SRC_DIR = lintDirectoryName(srcDir);
    }

    static void setInputDir (String inDir, boolean relative) {
        inDir = lintDirectoryName(inDir);
        IN_DIR = relative ? SRC_DIR + inDir : inDir;
    }

    static void setOutputDir (String outDir, boolean relative) {
        outDir = lintDirectoryName(outDir);
        OUT_DIR = relative ? SRC_DIR + outDir : outDir;
    }

    static String getInDir () {
        return IN_DIR;
    }

    static String getOutDirDir () {
        return OUT_DIR;
    }

}
