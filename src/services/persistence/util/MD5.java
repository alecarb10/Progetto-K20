package services.persistence.util;

import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A Pure Fabrication class to crypt passwords using MD5 algorythm
 */

public class MD5 {
	
	/**
	 * Given a password, crypts it using MD5
	 * @param password
	 * @return crypted password
	 */
	public static String getMd5(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			byte[] messageDigest = md.digest(password.getBytes());

			BigInteger no = new BigInteger(1, messageDigest);

			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}

		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Compares a password with his digest
	 * @param password uncrypted password
	 * @param passwordMd5 crypted password
	 * @return a boolean that indicates success / insuccess
	 */
	public static boolean compareMd5(String password, String passwordMd5) {
		byte[] messageDigest1 = getMd5(password).getBytes();
		byte[] messageDigest2 = passwordMd5.getBytes();
		
		return MessageDigest.isEqual(messageDigest1, messageDigest2);
	}
}
