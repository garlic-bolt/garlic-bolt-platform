package com.chanjetpay.garlic.web;

//import com.chanjetpay.garlic.api.BlockService;
//import com.chanjetpay.garlic.dto.UserDto;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import net.glxn.qrgen.core.AbstractQRCode;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by libaoa on 2017/11/7.
 */
@Controller
@RequestMapping("qrcode")
public class QRCodeController {

	private static final Logger logger = LoggerFactory.getLogger(QRCodeController.class);

	@RequestMapping("/{merchantid}/cashier")
	public String orderQRCode(@PathVariable(value="merchantid") String merchantId) {

		AbstractQRCode qrCode = QRCode.from("http://www.baidu.com");
		// 设置字符集，支持中文
		qrCode.withCharset("utf-8");
		// 设置生成的二维码图片大小
		qrCode.withSize(260, 260);
		ByteArrayOutputStream out = qrCode.to(ImageType.PNG).stream();

		File file = new File("D:\\qrCode.png");
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(file);
			fout.write(out.toByteArray());
			fout.flush();
			System.out.println("***********二维码生成成功！**********");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fout.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "";
	}

	@RequestMapping(value = {"/img/render"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	public 	@ResponseBody String execute(HttpServletRequest httpServletRequest,
						  HttpServletResponse httpServletResponse) throws IOException {
		// img为图片的二进制流

		AbstractQRCode qrCode = QRCode.from("http://www.baidu.com");
		// 设置字符集，支持中文
		qrCode.withCharset("utf-8");
		// 设置生成的二维码图片大小
		qrCode.withSize(260, 260);
		ByteArrayOutputStream out = qrCode.to(ImageType.PNG).stream();

		byte[] img = out.toByteArray();
		httpServletResponse.setContentType("image/png");
		OutputStream os = httpServletResponse.getOutputStream();
		os.write(img);
		os.flush();
		os.close();
		return "success";
	}

	/**
	 * 生成并下载二维码
	 * @param url 二维码对于URL
	 * @param width 二维码宽
	 * @param height 二维码高
	 * @param format  二维码格式
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
	public static ResponseEntity<byte[]> getResponseEntity(String url, int width, int height, String format) throws WriterException, IOException {
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(url,
				BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
		//将矩阵转为Image
		BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, format, out);//将BufferedImage转成out输出流
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.CREATED);
	}

	@RequestMapping("/downloadIOSAPPQRCode")
	public ResponseEntity<byte[]> downloadIOSAPPController(@RequestParam(required = true)String type)
			throws WriterException, IOException{
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("app.properties");
		return getResponseEntity("http://www.baidu.com", 150, 150, "png");
	}

}
