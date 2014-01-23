package com.hashin.project.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

// import com.wellpoint.iam.common.util.BASE64Encoder;
// import com.wellpoint.iam.common.util.BASE64Decoder;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encryption {
	static String secret = "Sn43R28*893bnheDJf79L43_";
//	static String DESEDEALG = "DESede/CBC/PKCS5Padding";
	static String IVstring = "72&r_SwC";
	IvParameterSpec paramspec;
	SecretKey desedekey;

	static String DESALG = "DES";
	SecretKey deskey;

	private static Encryption instance;

	private Encryption() throws InvalidKeyException,
			UnsupportedEncodingException, NoSuchAlgorithmException,
			InvalidKeySpecException {
		DESedeKeySpec desedekeyspec = new DESedeKeySpec(secret.getBytes("UTF8"));
		SecretKeyFactory desedefactory = SecretKeyFactory.getInstance("DESede");
		desedekey = desedefactory.generateSecret(desedekeyspec);
		paramspec = new IvParameterSpec(IVstring.getBytes("UTF8"));

		SecretKeyFactory desfactory = SecretKeyFactory.getInstance("DES");
		DESKeySpec deskeyspec = new DESKeySpec(secret.getBytes("UTF8"));
		deskey = desfactory.generateSecret(deskeyspec);
	}

	public static Encryption getInstance() throws InvalidKeyException,
			UnsupportedEncodingException, NoSuchAlgorithmException,
			InvalidKeySpecException {
		if (instance == null) {
			instance = new Encryption();
		}
		return instance;
	}

	public String encrypt(String text)
			throws UnsupportedEncodingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException {
		byte[] plainText = text.getBytes("UTF8");

		Cipher cipher = null;
		cipher = Cipher.getInstance(DESALG);
		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		
		byte[] cipherText = cipher.doFinal(plainText);

		BASE64Encoder encoder = new BASE64Encoder();
		String base64Text = encoder.encode(cipherText);
		return base64Text;

	}

	public String decrypt(String text)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, InvalidAlgorithmParameterException,
			IOException {
		BASE64Decoder base64decoder = new BASE64Decoder();
		byte[] encryptedText = base64decoder.decodeBuffer(text);

		Cipher cipher = null;
		cipher = Cipher.getInstance(DESALG);
		cipher.init(Cipher.DECRYPT_MODE, deskey);
		byte[] cipherText = cipher.doFinal(encryptedText);

		return (new String(cipherText));
	}
	
	
	//testing the encrypt / decrypt functionalities
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {

		String toEncrypt = "A67776";
		
		String encrypted= Encryption.getInstance().encrypt(toEncrypt);
		String decrypted = Encryption.getInstance().decrypt(encrypted);
		
		System.out.println(toEncrypt + " >> "+ encrypted + " >> "+decrypted);
	}
	
}
