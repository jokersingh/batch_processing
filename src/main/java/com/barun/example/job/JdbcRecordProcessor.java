/**
 * 
 */
package com.barun.example.job;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jokersingh
 *
 */
@SuppressWarnings("rawtypes")
public class JdbcRecordProcessor<P> implements RecordProcessor<JdbcRecord, Record> {

	private List<String> fields;
	public JdbcRecordProcessor(List<String> fields) {
		this.fields = fields;
	}

	@SuppressWarnings("unchecked")
	public Record<P> processRecord(final JdbcRecord record) throws Exception {
        ResultSet resultSet = record.getPayload();
        //initFieldNames(resultSet);
        List<String> values = new ArrayList<>();
       /* for (int i = 0; i < fields.length; i++) {
        	if("TIMESTAMP".equals(columType.get(fields[i]))){
        		values.put(fields.get(i), resultSet.getTimestamp(i + 1).toString());
        	}else {
        		values.put(fields[i], resultSet.getString(i + 1));
        	}
        }*/
        for(String field: fields) {
        	values.add(checkNull(resultSet.getString(field)));
        }
        return (Record<P>) new GenericRecord<>(record.getHeader(), values);
    }

	private String checkNull(String value) {
		if(null == value) {
			return "";
		}
		return value;
	}
}
