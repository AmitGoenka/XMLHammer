package org.agoenka.xmlhammer.runtime.model;

/**
 * Authored by agoenka on 8/20/2016.
 */
public class Parameters {

    private final String mode;
    private final boolean split;
    private final boolean merge;
    private final String srcDir;
    private final String inDir;
    private final String outDir;
    private final String fileName;
    private final String pivot;
    private final String fileNamePrefix;
    private final int start;
    private final int end;

    public Parameters(String mode, boolean split, boolean merge, String srcDir, String inDir, String outDir, String fileName, String pivot, String fileNamePrefix, int start, int end) {
        this.mode = mode;
        this.split = split;
        this.merge = merge;
        this.srcDir = srcDir;
        this.inDir = inDir;
        this.outDir = outDir;
        this.fileName = fileName;
        this.pivot = pivot;
        this.fileNamePrefix = fileNamePrefix;
        this.start = start;
        this.end = end;
    }

    public String getMode() {
        return mode;
    }

    public boolean isSplit() {
        return split;
    }

    public boolean isMerge() {
        return merge;
    }

    public String getSrcDir() {
        return srcDir;
    }

    public String getInDir() {
        return inDir;
    }

    public String getOutDir() {
        return outDir;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPivot() {
        return pivot;
    }

    public String getFileNamePrefix() {
        return fileNamePrefix;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

}