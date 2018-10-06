package com.bib404.system_bib404.service.impl;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.bib404.system_bib404.service.EncriptadoPass;
@Service("encriptadoPass")
public class EncriptadoImp implements EncriptadoPass{
	private static final String Skey = "DixonAndKike"; 
	
	@Override
	public String Encriptar(String password) {
		String texto ="";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] dPass = md.digest(Skey.getBytes("utf-8"));
			byte[] kBy = Arrays.copyOf(dPass, 24);
			SecretKey k = new SecretKeySpec(kBy, "DESede");
			Cipher cip = Cipher.getInstance("DESede");
			cip.init(Cipher.ENCRYPT_MODE, k);
			byte[] textPl = password.getBytes("utf-8");
			byte[] buf = cip.doFinal(textPl);
			byte[] base64Bytes = Base64.encodeBase64(buf);
			texto = new String(base64Bytes);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return texto;
	}

	@Override
	public String Desencriptar(String password) {
		String texto ="";
        try {
            byte[] message = Base64.decodeBase64(password.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] dpass = md.digest(Skey.getBytes("utf-8"));
            byte[] kBy = Arrays.copyOf(dpass, 24);
            SecretKey key = new SecretKeySpec(kBy, "DESede");
 
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
 
            byte[] textPl = decipher.doFinal(message);
 
            texto = new String(textPl, "UTF-8");
 
        } catch (Exception ex) {
        }
        return texto;
	}
	

}
