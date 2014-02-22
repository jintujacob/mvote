package com.hashin.project.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import sun.misc.BASE64Encoder;


public class EncryptDecryptUtil
{
    String cipherText;
    String decryptText;


    public String decrypt(String textToDecrypt){
	KeyGenerator keyGen = KeyGenerator.getInstance("AES");
	keyGen.init(128);
	SecretKey secretKey = keyGen.generateKey();
	Cipher aesCipher = Cipher.getInstance("AES");

	aesCipher.init(Cipher.DECRYPT_MODE,secretKey,aesCipher.getParameters());
	byte[] byteDataToEncrypt = textToDecrypt.getBytes();
	byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt);
	byte[] byteDecryptedText = aesCipher.doFinal(byteCipherText);
	String plainString = new String(byteDecryptedText);
	
	return plainString;
    }
    
    public void encrypt(String textToEncrypt)
    {
	//String plainData="hello",cipherText,decryptedText;
	KeyGenerator keyGen;
	try
	{
	    keyGen = KeyGenerator.getInstance("AES");
	    keyGen.init(128);
	    SecretKey secretKey = keyGen.generateKey();
	    Cipher aesCipher = Cipher.getInstance("AES");
		
	    aesCipher.init(Cipher.ENCRYPT_MODE,secretKey);
	    byte[] byteDataToEncrypt = textToEncrypt.getBytes();
	    byte[] byteCipherText = aesCipher.doFinal(byteDataToEncrypt);
	    cipherText = new BASE64Encoder().encode(byteCipherText);
	    
	} catch (NoSuchAlgorithmException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InvalidKeyException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalBlockSizeException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (BadPaddingException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NoSuchPaddingException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	

    }
}
