package com.mastercard.security;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import jakarta.persistence.AttributeConverter;
import lombok.SneakyThrows;

@Configuration
public class AES_Encryptor implements AttributeConverter<Object, String>{

	
	@Value("${aes.encryption.key}")
	private String encryptionKey;
	
	private String encryptionCipher = "AES";
	
	private  Key key;
	
	private  Cipher cipher;
	
	private  Key getKey() {
		if(key == null)
		{
			key = new SecretKeySpec(encryptionKey.getBytes(),encryptionCipher);
		}
		return key;
	}


	public  Cipher getCipher() throws GeneralSecurityException {
		if(cipher == null)
		{
			cipher = Cipher.getInstance(encryptionCipher);
		}
		return cipher;
	}
	
	
	private void initCipher(int encryptMode) throws GeneralSecurityException
	{
		getCipher().init(encryptMode, getKey());
	}
	

	//Used to convert the plain text into encrypted format
	@SneakyThrows
	@Override
	public String convertToDatabaseColumn(Object attribute){
		if(attribute == null)
		{
			return null;
		}
			initCipher(Cipher.ENCRYPT_MODE);
		
		
		byte [] bytes = SerializationUtils.serialize(attribute);
		
		return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));
	}

	//Used to convert the encrypted format into plain text
	@SneakyThrows
	@Override
	public Object convertToEntityAttribute(String dbData) {
		if(dbData == null)
		{
			return null;
		}
		
		initCipher(Cipher.DECRYPT_MODE);
		byte [] bytes = getCipher().doFinal(Base64.getDecoder().decode(dbData));
		return SerializationUtils.deserialize(bytes);
	}

}
