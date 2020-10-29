package br.com.tommiranda.utils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

    public static <T> T getOrDefault(List<T> list, int idx, T def) {
        if(list.size() > idx) {
            return list.get(idx);
        }

        return def;
    }

    // Itera sobre dois iterables alterando o primeiro passado
    public static <T, Y> Map<T, Y> createMapFromIterables(List<T> list1, List<Y> list2, Supplier<? extends Map<T, Y>> supplier) {
        if(list1.size() != list2.size()) {
            throw new IllegalArgumentException("Lists with diferent sizes");
        }

        Iterator<T> iter1 = list1.iterator();
        Iterator<Y> iter2 = list2.iterator();

        Map<T, Y> map = supplier.get();

        while(iter1.hasNext() && iter2.hasNext()) {
            map.put(iter1.next(), iter2.next());
        }

        return map;
    }
}
