package com.oscar.healtry.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomString {
    private static final int CODE_SIZE = 8; 
    private static final String READABLE_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";

    private RandomString() {
        throw new IllegalStateException("Clase de utilidades");
      }
    public static String generarCodigo() {
    	return RandomStringUtils.secure().next(CODE_SIZE, READABLE_CHARS);
    }
}