package org.agoenka.xmlhammer.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

/**
 * Authored by agoenka on 8/20/2016.
 */
public interface Functions {

    static <T> boolean validate(final T type, Predicate<T> tester) {
        return tester.test(type);
    }

    static <T> boolean has(T[] args, T param) {
        return Arrays.asList(args).contains(param);
    }

    static <T> int get(T[] args, T param) {
        return Arrays.asList(args).indexOf(param);
    }

    static <T> Optional<T> next(T[] args, T param) {
        int index = get(args, param);
        if (index != -1 && index != args.length - 1)
            return Optional.of(args[index + 1]);
        else
            return Optional.empty();
    }

    static List<String> getStaticFieldValues(Class classT) {
        List<String> markers = new ArrayList<>();
        try {
            for (Field field : classT.getDeclaredFields()) {
                markers.add(field.get(classT).toString());
            }
        } catch (IllegalAccessException e) {
            Logger.getAnonymousLogger(e.getMessage());
        }
        return markers;
    }

}