package com.chanjetpay.garlic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * 附件处理
 */
@Controller
public class AttachController {

	@RequestMapping("upload")
	public String file(){
		return "/uploadtest";
	}

	@Value("${upload.dir}")
	private String uploadDir;

	@Autowired
	private ServletContext servletContext;

	/**
	 * 实现文件上传
	 * */
	@RequestMapping("fileUpload")
	@ResponseBody
	public String fileUpload(@RequestParam("fileName") MultipartFile file){
		if(file.isEmpty()){
			return "false";
		}
		String fileName = file.getOriginalFilename();
		int size = (int) file.getSize();
		System.out.println(fileName + "-->" + size);

		String path = servletContext.getRealPath("/WEB-INF/tmp");
		path = "d:/test";
		System.out.println(uploadDir);
		File dest = new File(path + "/" + fileName);

		if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
			dest.getParentFile().mkdir();
		}
		try {
			file.transferTo(dest); //保存文件
			return "true";
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
	}

	/**
	 * 实现多文件上传
	 * */
	@RequestMapping(value="multifileUpload",method= RequestMethod.POST)
	public @ResponseBody String multifileUpload(@RequestParam("fileName")List<MultipartFile> files){
		if(files.isEmpty()){
			return "false";
		}

		String path = "d:/test" ;

		for(MultipartFile file:files){
			String fileName = file.getOriginalFilename();
			int size = (int) file.getSize();
			System.out.println(fileName + "-->" + size);

			if(file.isEmpty()){
				return "false";
			}else{
				File dest = new File(path + "/" + fileName);
				if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
					dest.getParentFile().mkdir();
				}
				try {
					file.transferTo(dest);
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "false";
				}
			}
		}
		return "true";
	}

	@RequestMapping("download")
	public String downLoad(HttpServletResponse response){
		String filename="2.jpg";
		String filePath = "d:/test" ;
		File file = new File(filePath + "/" + filename);
		if(file.exists()){ //判断文件父目录是否存在
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

			byte[] buffer = new byte[1024];
			FileInputStream fis = null; //文件输入流
			BufferedInputStream bis = null;

			OutputStream os = null; //输出流
			try {
				os = response.getOutputStream();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				int i = bis.read(buffer);
				while(i != -1){
					os.write(buffer);
					i = bis.read(buffer);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("----------file download" + filename);
			try {
				bis.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
