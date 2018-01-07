/**
 * 
 */
package com.barun.example.job;

import java.sql.ResultSet;

/**
 * @author jokersingh
 *
 */
public class JdbcRecord extends GenericRecord<ResultSet> {

    /**
     * Create a new {@link JdbcRecord}.
     *
     * @param header of the record
     * @param payload of the record
     */
    public JdbcRecord(final Header header, final ResultSet payload) {
        super(header, payload);
    }

}
