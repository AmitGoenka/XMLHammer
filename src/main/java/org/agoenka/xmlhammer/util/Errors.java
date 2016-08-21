package org.agoenka.xmlhammer.util;

import java.util.Formatter;

/**
 * Authored by agoenka on 8/13/2016.
 */
public enum Errors {

    ARGS_NONE("Error: no arguments: no arguments supplied."),
    ARGS_MULTIPLE_MODES("Error: multiple modes: multiple operations are not supported on a single execution. Select only one operation mode."),
    ARGS_MISSING_MODE("Error: missing mode or operation: operation mode is not supplied in arguments. Provide operation mode."),
    ARGS_MANDATORY("Error: missing parameter: '%s' is mandatory for this mode of operation."),
    ARGS_EMPTY("Error: empty parameter: '%s' cannot be empty for this mode of operation."),
    NO_FILES_FOUND("Error: no files found: No files found in the directory for the merge operation");

    private final String value;

    public String get() {
        return value;
    }

    public String get(String param) {
        return new Formatter().format(value, param).toString();
    }

    Errors (String value) {
        this.value = value;
    }
}
