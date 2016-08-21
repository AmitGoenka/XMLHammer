package org.agoenka.xmlhammer.runtime;

import org.agoenka.xmlhammer.runtime.model.Parameters;
import org.agoenka.xmlhammer.runtime.model.ParametersBuilder;

import static org.agoenka.xmlhammer.runtime.ParamValidator.*;
import static org.agoenka.xmlhammer.runtime.model.ParamMarkers.*;
import static org.agoenka.xmlhammer.util.Functions.has;
import static org.agoenka.xmlhammer.util.Functions.next;

/**
 * Authored by agoenka on 8/20/2016.
 */
public class ParamInitializer {

    public static Parameters initialize(String... args) {
        Parameters parameters = null;
        validateNoArgs(args);

        String mode = getMode(args);
        String srcDir = next(args, SRCDIR).orElse("");
        String inDir = next(args, INDIR).orElse("");
        String outDir = next(args, OUTDIR).orElse("");
        String fileName = next(args, FILENAME).orElse("");
        String pivot = next(args, PIVOT).orElse("");
        String fileNamePrefix = next(args, FILENAME_PREFIX).orElse("");
        int start = Integer.valueOf(next(args, START).orElse("-1"));
        int end = Integer.valueOf(next(args, END).orElse("-1"));

        validateMandatory(pivot, "Pivot Element Name");

        if (mode.equals(SPLIT)) {
            validateMandatory(fileName, "File Name");
            parameters = new ParametersBuilder()
                    .mode(mode)
                    .srcDir(srcDir)
                    .inDir(inDir)
                    .outDir(outDir)
                    .fileName(fileName)
                    .pivot(pivot)
                    .build();
        } else if (mode.equals(MERGE)) {
            validateMandatory(fileNamePrefix, "File Name Prefix");
            parameters = new ParametersBuilder()
                    .mode(mode)
                    .srcDir(srcDir)
                    .inDir(inDir)
                    .outDir(outDir)
                    .fileNamePrefix(fileNamePrefix)
                    .pivot(pivot)
                    .start(start)
                    .end(end)
                    .build();
        }

        return parameters;
    }

    private static String getMode(String... args) {
        boolean split = has(args, SPLIT);
        boolean merge = has(args, MERGE);
        validateModes(split, merge);
        if (split) return SPLIT;
        else return MERGE;
    }

}