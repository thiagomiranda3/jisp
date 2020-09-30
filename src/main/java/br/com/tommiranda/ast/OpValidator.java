package br.com.tommiranda.ast;

import org.eclipse.collections.api.set.primitive.MutableCharSet;
import org.eclipse.collections.impl.factory.primitive.CharSets;

public class OpValidator {

   private static final MutableCharSet validChars = createValidSet();

    public static boolean isValid(char c) {
        return validChars.contains(c);
    }

    private static MutableCharSet createValidSet() {
        return CharSets.mutable.with("abcdefghijklmnopqrstuvwyxzABCDEFGHIJKLMNOPQRSTUVWYXZ123456789!@#$%&*-_+=.<>/|".toCharArray());
    }
}
