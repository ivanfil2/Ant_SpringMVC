package com.epam.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.epam.exception.TechnicalException;
import com.epam.model.ImageModel;
import com.epam.service.SaveValidateService;

/**
 * This controller handles REST requests to resource:
 * http://localhost:8080/AntSpringMVC1/images/
 * 
 * @author Ivan_Filimonau
 *
 */
@Controller
@RequestMapping("/images")
public class ImageController {
	@Autowired
	private SaveValidateService saveValidateService;

	/**
	 * This method execute POST requests for CREATING new image
	 * 
	 * @param imageModel
	 * @param bindingResult
	 * @param imageFile
	 * @param model
	 * @return String mapping to the createForm.jsp
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String createImage(
			@Valid ImageModel imageModel,
			BindingResult bindingResult,
			@RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
			Model model, HttpServletRequest req) {
		// If validation failed - return to create-form
		if (bindingResult.hasErrors()) {
			System.out.println("errors!!!");
			return "createForm";
		}
		// Else (if validation is correct) - creating new file and write to the
		// file system
		model.addAttribute("description", imageModel.getDescription());
		System.out.println("Description: " + imageModel.getDescription());
		// ---- Start of file receiving, validating, writing...
		String realPathToFile = req.getSession().getServletContext()
				.getRealPath("/")
				+ "resources/images/" + imageModel.getDescription() + ".jpg";
		try {
			// Validate file - should be *.jpg
			saveValidateService.validateJPG(imageFile);
			// Validate name of file - should not be duplicated
			saveValidateService.validateDuplicateFile(realPathToFile);
		} catch (TechnicalException e) {
			bindingResult.addError(new FieldError("imageModel", "description",
					e.getMessage()));
			// If image not JPG, or image already exist - return to create-form
			return "createForm";
		}
		String realUrlToFile = req.getScheme() + "://" + req.getServerName()
				+ ":" + req.getServerPort() + req.getContextPath()
				+ "/resources/images/" + imageModel.getDescription() + ".jpg";
		saveValidateService.saveImage(realPathToFile, imageFile);
		model.addAttribute("success", " Image successfuly saved");
		model.addAttribute("linkToImage", realUrlToFile);

		// --- Finish of file receiving, validating, writing...
		return "createForm";
	}

	/**
	 * This method execute GET requests for shows the list of images
	 * 
	 * @param model
	 * @param imageModel
	 * @return mapping to list.jsp, and Map of filenames(in the model`s
	 *         attribute "mapNames")
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showListOfImages(Model model, ImageModel imageModel,
			HttpServletRequest req) {
		Map<String, String> listMap;
		System.out.println("GET: " + imageModel.getDescription());
		String realPathToFolder = req.getSession().getServletContext()
				.getRealPath("/")
				+ "resources/images/";
		String realUrlToFolder = req.getScheme() + "://" + req.getServerName()
				+ ":" + req.getServerPort() + req.getContextPath()
				+ "/resources/images/";
		listMap = saveValidateService.getMap(realPathToFolder, realUrlToFolder);
		// put Map to the attribute "mapNames"
		model.addAttribute("mapNames", listMap);
		return "list";
	}


	/**
	 * This method execute GET requests for show images by id
	 * 
	 * @param model
	 * @param imageId
	 * @return String mapping to the showImage.jsp
	 */
	// @RequestMapping(method = RequestMethod.GET, value = "${id}")
	@RequestMapping(method = RequestMethod.GET, value = "/showImageById")
	public String showImageById(Model model,
			@RequestParam(value = "imageId", required = true) String imageId) {
		System.out.println(imageId);
		model.addAttribute("imageURL", imageId);
		return "showImage";
	}

	/**
	 * This method execute GET requests for show create-form
	 * 
	 * @param model
	 * @param imageModel
	 * @return mapping to the createForm.jsp
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/showCreateForm")
	public String showCreateForm(ImageModel imageModel) {
		return "createForm";
	}
}
