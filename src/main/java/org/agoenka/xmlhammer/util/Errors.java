package org.agoenka.xmlhammer.util;

import java.util.Formatter;

/**
 * Authored by agoenka on 8/13/2016.
 */
public enum Errors {

    ARGS_MISSING_MANDATORY("Error: missing arguments: xml file name and pivot element name required."),
    ARGS_TOO_MANY("Error: too many arguments: only five parameters are supported."),
    ARGS_EMPTY("Error: empty parameter: '%s' can not be empty.");

    private String value;

    public String get() {
        return value;
    }

    public String get(String param) {
        return new Formatter().format(value, param).toString();
    }

    private Errors (String value) {
        this.value = value;
    }
}
