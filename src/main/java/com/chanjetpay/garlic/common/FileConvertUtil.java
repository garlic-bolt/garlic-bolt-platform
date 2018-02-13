package com.chanjetpay.garlic.common;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileConvertUtil {

	public String getFileByteString(File file) throws Exception{

		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[(int)file.length()];
		fis.read(buffer);
		fis.close();

		//return new BASE64Encoder().encode(buffer);

		Base64 b64 = new Base64();
		return b64.encodeToString(buffer);
	}

	public void getFileByString(String base64Str, String target) throws Exception{
		Base64 b64 = new Base64();
		byte[] buffer = b64.decode(base64Str);

		//byte[] buffer2 = new BASE64Decoder().decodeBuffer(base64Str);

		FileOutputStream fos = new FileOutputStream(target);
		fos.write(buffer);
		fos.close();
	}
}
