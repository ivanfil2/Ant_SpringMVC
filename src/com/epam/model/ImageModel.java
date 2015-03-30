package com.epam.model;

import javax.validation.constraints.Size;

/**
 * This class represent entity of image
 * 
 * @author Ivan_Filimonau
 *
 */
public class ImageModel {
	@Size(min = 3, max = 12, message = "Description should be 3-12 char long")
	private String description;

	public ImageModel() {

	}

	/**
	 * This method get description variable.
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method set description variable.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
