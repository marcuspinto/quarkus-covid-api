package dev.marcuspinto.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

public class CovidUtilTest {
	
	@Test
	public void testGetKey() throws NoSuchAlgorithmException {
		String key = CovidUtil.getKey();
		assertNotNull(key);
		assertEquals(CovidUtil.KEY_SIZE, key.length());
	}

}
