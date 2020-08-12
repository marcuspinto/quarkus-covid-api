package dev.marcuspinto.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

public class CovidUtil {
	
	public static final int KEY_SIZE = 36;

	public static String getKey() throws NoSuchAlgorithmException {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[KEY_SIZE / 2];
		random.nextBytes(bytes);
		return DatatypeConverter.printHexBinary(bytes).toLowerCase();
	}
}
