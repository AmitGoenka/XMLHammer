package org.agoenka.xmlsplitter.functions;

import java.util.function.Predicate;

/**
 * Authored by agoenka on 8/19/2016.
 */
public interface Validate {
    static <X> boolean validate (final X type, Predicate<X> tester) {
        return tester.test(type);
    }
}
