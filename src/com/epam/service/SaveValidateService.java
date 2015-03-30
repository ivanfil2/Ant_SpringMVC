package com.epam.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * This interface describes methods for saving and validating images
 * 
 * @author Ivan_Filimonau
 *
 */
public interface SaveValidateService {

	/**
	 * This method scan resources/images/ folder and create a Map of existing
	 * files
	 * 
	 * @param realPathToFolder
	 * @return Map (key=fileName, value=URL to file)
	 */
	public Map<String, String> getMap(String realPathToFolder,
			String realUrlToFolder);

	/**
	 * Save image to file system
	 * 
	 * @param filename
	 * @param imageFile
	 */
	public void saveImage(String filename, MultipartFile imageFile);

	/**
	 * Validate on equivalence to JPG
	 * 
	 * @param image
	 */
	public void validateJPG(MultipartFile image);

	/**
	 * Validate on uniqueness of image in target folder
	 * 
	 * @param filename
	 */
	public void validateDuplicateFile(String filename);
}
