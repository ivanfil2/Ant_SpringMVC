package com.epam.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.epam.exception.TechnicalException;

public class SaveValidateServiceImpl implements SaveValidateService {
	@Override
	public Map<String, String> getMap(String realPathToFolder,
			String realUrlToFolder) {
		// scan resources/images/ folder and create an array of existing files
		File file = new File(realPathToFolder);
		File[] listFiles = file.listFiles();
		// Create a map (key=fileName, value=URL to file)
		HashMap<String, String> listMap = new HashMap<String, String>();
		for (File file2 : listFiles) {
			// listMap.put(file2.getName(), req.getSession().getServletContext()
			// .getRealPath("/")
			// + "resources/images/" + file2.getName());
			listMap.put(file2.getName(), realUrlToFolder + file2.getName());
		}

		return listMap;
	}

	@Override
	public void saveImage(String filename, MultipartFile imageFile) {
		File file = new File(filename);
		System.out.println(file.getAbsolutePath().toString());
		try {
			FileUtils.writeByteArrayToFile(file, imageFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void validateDuplicateFile(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			throw new TechnicalException("Image with this name already exist");
		}
	}

	@Override
	public void validateJPG(MultipartFile image) {
		if (!image.getContentType().equals("image/jpeg")) {
			throw new TechnicalException("Only JPG images are acceptable");
		}
	}

}
