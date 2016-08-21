package org.agoenka.xmlhammer.runtime;

import java.util.logging.Logger;

import static org.agoenka.xmlhammer.runtime.model.ParamMarkers.isMarker;
import static org.agoenka.xmlhammer.util.CommonUtils.isEmpty;
import static org.agoenka.xmlhammer.util.Errors.*;

/**
 * Authored by agoenka on 8/20/2016.
 */
class ParamValidator {

    private static final Logger LOGGER = Logger.getLogger(ParamValidator.class.getName());

    static void validateNoArgs(String[] args) {
        boolean valid = false;
        if (args.length <= 0) LOGGER.severe(ARGS_NONE.get());
        else valid = true;
        if (!valid) onError();
    }

    static void validateModes(boolean split, boolean merge) {
        boolean valid = false;
        if (split && merge) LOGGER.severe(ARGS_MULTIPLE_MODES.get());
        else if (!split && !merge) LOGGER.severe(ARGS_MISSING_MODE.get());
        else valid = true;
        if (!valid) onError();
    }

    static void validateMandatory(String arg, String parameterName) {
        boolean valid = false;
        if (isMarker(arg)) LOGGER.severe(ARGS_MANDATORY.get(parameterName));
        else if (isEmpty(arg)) LOGGER.severe(ARGS_EMPTY.get(parameterName));
        else valid = true;
        if (!valid) onError();
    }

    private static void onError() {
        System.exit(0);
    }

}
