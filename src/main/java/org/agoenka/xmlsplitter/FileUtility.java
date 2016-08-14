package org.agoenka.xmlsplitter;

/**
 * Authored by agoenka on 8/13/2016.
 */
interface FileUtility {

    static boolean hasXMLExtension (String name) {
        return name != null && (name.endsWith(".xml") || name.endsWith(".XML"));
    }

    static String lintDirectoryName (String dir) {
        return missingTrailingSlash(dir) ? dir + "/" : dir;
    }

    static boolean missingTrailingSlash (String dir) {
        return dir != null && !dir.trim().equals("") && (!dir.trim().endsWith("/") || !dir.trim().endsWith("\\"));
    }

}
