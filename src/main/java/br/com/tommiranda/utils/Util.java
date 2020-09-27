package br.com.tommiranda.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wwthi
 */
public class Util {

    public static <T> List<T> safeList(List<T> list) {
        return list != null ? list : Collections.emptyList();
    }

    public static <T> Set<T> safeSet(Set<T> set) {
        return set != null ? set : Collections.emptySet();
    }

    public static <T, R> Map<T, R> safeMap(Map<T, R> map) {
        return map != null ? map : Collections.emptyMap();
    }

    public static boolean isNullOrEmpty(String string) {
        return (string == null || string.isEmpty());
    }

    public static boolean isNullOrEmpty(List<?> list) {
        return (list == null || list.isEmpty());
    }

    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    public static boolean stringOK(String string) {
        return !Util.isNullOrEmpty(string);
    }

    public static boolean equalStrings(String thisString, String thatString) {
        if (thisString == null || thatString == null) {
            return false;
        }

        return thisString.equals(thatString);
    }
}
