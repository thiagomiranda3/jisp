package br.com.tommiranda.utils;

public class ErrorMessages {

    public static String wrongParamRequired(String op, int required, int found) {
        String requiredPlural = required > 1 ? "s" : "";
        String foundPlural = found > 1 ? " were" : " was";

        return op + " function has " + required + " required param" + requiredPlural + ". " + found + foundPlural + " found";
    }

    public static String wrongParamAtLeast(String op, int minimum, int found) {
        String requiredPlural = minimum > 1 ? "s" : "";
        String foundPlural = found > 1 ? " were" : " was";

        return op + " function has at least " + minimum + " param" + requiredPlural + ". " + found + foundPlural + " found";
    }

    public static String wrongParamOptional(String op, int optional, int found) {
        String optionalPlural = optional > 1 ? "s" : "";
        String foundPlural = found > 1 ? " were" : " was";

        return op + " function has " + optional + " optional param" + optionalPlural + ". " + found + foundPlural + " found";
    }
}
