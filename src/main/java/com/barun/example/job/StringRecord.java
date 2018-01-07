/**
 * 
 */
package com.barun.example.job;

import java.sql.ResultSet;

/**
 * @author jokersingh
 *
 */
public class StringRecord extends GenericRecord<ResultSet> {
	
	private String record;
	public StringRecord(Header header, ResultSet payload,String records) {
		super(header, payload);
		this.record = records;
	}

	

}
