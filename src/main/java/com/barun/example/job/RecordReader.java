/**
 * 
 */
package com.barun.example.job;

/**
 * @author jokersingh
 *
 */
public interface RecordReader {
	/**
     * Open the reader.
     *
     * @throws Exception if an error occurs during reader opening
     */
    void open() throws Exception;

    /**
     * Read next record from the data source.
     *
     * @return the next record from the data source or null if the end of the data source is reached
     * @throws Exception if an error occurs during reading next record
     */
    Record readRecord() throws Exception;

    /**
     * Close the reader.
     *
     * @throws Exception if an error occurs during reader closing
     */
    void close() throws Exception;

}