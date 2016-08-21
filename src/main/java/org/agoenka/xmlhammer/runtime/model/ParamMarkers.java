package org.agoenka.xmlhammer.runtime.model;

import static org.agoenka.xmlhammer.util.Functions.getStaticFieldValues;

/**
 * Authored by agoenka on 8/20/2016.
 */
public class ParamMarkers {

    private ParamMarkers() {}

    public static final String MERGE = "-m";
    public static final String SPLIT = "-s";
    public static final String SRCDIR = "-h";
    public static final String INDIR = "-i";
    public static final String OUTDIR = "-o";
    public static final String FILENAME = "-n";
    public static final String PIVOT = "-p";
    public static final String FILENAME_PREFIX = "-f";
    public static final String START = "-x";
    public static final String END = "-y";

    public static boolean isMarker(String in) {
        return getStaticFieldValues(ParamMarkers.class).contains(in);
    }

}