package org.agoenka.xmlhammer;

import org.agoenka.xmlhammer.runtime.ParamInitializer;
import org.agoenka.xmlhammer.runtime.model.Parameters;
import org.agoenka.xmlhammer.service.Merger;
import org.agoenka.xmlhammer.service.Splitter;

/**
 * Authored by agoenka on 8/6/2016.
 */
public class HammerApp {

    public static void main (String... args) throws Exception {
        Parameters parameters = ParamInitializer.initialize(args);
        if (parameters != null) {
            if(parameters.isSplit()) {
                Splitter.init(parameters.getSrcDir(), parameters.getInDir(), parameters.getOutDir());
                Splitter.split(parameters.getFileName(), parameters.getPivot());
            }
            else if (parameters.isMerge()) {
                Merger.init(parameters.getSrcDir(), parameters.getInDir(), parameters.getOutDir());
                Merger.merge(parameters.getFileNamePrefix(), parameters.getPivot(), parameters.getStart(), parameters.getEnd());
            }
        }
    }

}