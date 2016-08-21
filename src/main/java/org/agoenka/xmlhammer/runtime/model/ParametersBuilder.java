package org.agoenka.xmlhammer.runtime.model;

import static org.agoenka.xmlhammer.runtime.model.ParamMarkers.MERGE;
import static org.agoenka.xmlhammer.runtime.model.ParamMarkers.SPLIT;

public class ParametersBuilder {

    private String mode = "";
    private boolean split;
    private boolean merge;
    private String srcDir = "";
    private String inDir = "";
    private String outDir = "";
    private String fileName = "";
    private String pivot = "";
    private String fileNamePrefix = "";
    private int start = 1;
    private int end;

    public ParametersBuilder mode(String mode) {
        this.mode = mode;
        switch(mode) {
            case SPLIT :
                this.split = true;
                this.merge = false;
                break;
            case MERGE :
                this.split = false;
                this.merge = true;
                break;
            default:
                this.split = false;
                this.merge = false;
                break;
        }
        return this;
    }

    public ParametersBuilder srcDir(String srcDir) {
        this.srcDir = srcDir;
        return this;
    }

    public ParametersBuilder inDir(String inDir) {
        this.inDir = inDir;
        return this;
    }

    public ParametersBuilder outDir(String outDir) {
        this.outDir = outDir;
        return this;
    }

    public ParametersBuilder fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ParametersBuilder pivot(String pivot) {
        this.pivot = pivot;
        return this;
    }

    public ParametersBuilder fileNamePrefix(String fileNamePrefix) {
        this.fileNamePrefix = fileNamePrefix;
        return this;
    }

    public ParametersBuilder start(int start) {
        this.start = start;
        return this;
    }

    public ParametersBuilder end(int end) {
        this.end = end;
        return this;
    }

    public Parameters build() {
        return new Parameters(mode, split, merge, srcDir, inDir, outDir, fileName, pivot, fileNamePrefix, start, end);
    }
}