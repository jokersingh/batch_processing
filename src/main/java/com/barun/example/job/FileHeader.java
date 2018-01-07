/**
 * 
 */
package com.barun.example.job;

import java.util.List;

/**
 * @author jokersingh
 *
 */
public class FileHeader {

	private List<String> parts;
	private String seperator;
	/**
	 * @return the parts
	 */
	public List<String> getParts() {
		return parts;
	}
	/**
	 * @param parts the parts to set
	 */
	public void setParts(List<String> parts) {
		this.parts = parts;
	}
	/**
	 * @return the seperator
	 */
	public String getSeperator() {
		return seperator;
	}
	/**
	 * @param seperator the seperator to set
	 */
	public void setSeperator(String seperator) {
		this.seperator = seperator;
	}
	
}
