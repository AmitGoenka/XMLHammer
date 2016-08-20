package org.agoenka.xmlsplitter.util;

import java.util.List;

/**
 * Authored by agoenka on 8/19/2016.
 */
public interface CommonUtils {

    static boolean isEmpty(String in) {
        return in == null || in.trim().length() == 0;
    }

    static boolean isNotEmpty (String in) {
        return !isEmpty(in);
    }

    static boolean isEmpty (List<?> list) {
        return list == null || list.size() == 0;
    }

    static boolean isNotEmpty (List<?> list) {
        return !isEmpty(list);
    }

}
