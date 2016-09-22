package br.univel.classes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

	public String hashMD5(String texto) throws NoSuchAlgorithmException{
		MessageDigest m=MessageDigest.getInstance("MD5");
	    m.update(texto.getBytes(),0,texto.length());
	    return new BigInteger(1,m.digest()).toString(16);
	}
	
	public String hashSHA256(String texto) throws NoSuchAlgorithmException{
		MessageDigest m=MessageDigest.getInstance("SHA-256");
	    m.update(texto.getBytes(),0,texto.length());
	    return new BigInteger(1,m.digest()).toString(16);
	}
}
