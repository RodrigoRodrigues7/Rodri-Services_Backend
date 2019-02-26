package com.rodrigo.rodriservices.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rodrigo.rodriservices.services.exceptions.FileException;

@Service
public class ImageService {
	
	public BufferedImage getJpgImageFromFile(MultipartFile uploadFile) {
		
		String ext = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
		if(!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Somente imagens PNG e JPG são permitidas!");
		}
		
		try {
			BufferedImage img = ImageIO.read(uploadFile.getInputStream());
			if("png".equals(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

	private BufferedImage pngToJpg(BufferedImage img) {
		
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), 
				BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			ImageIO.write(img, extension, outStream);
			return new ByteArrayInputStream(outStream.toByteArray());
		} 
		catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}		
	}
	
}
