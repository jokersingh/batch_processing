package com.barun.example.job;

import java.io.IOException;

public interface RecordWriter {
	/**
     * Open the writer.
     *
     * @throws Exception if an error occurs during opening the writer
     */
    void open() throws Exception;

    /**
     * Write a batch of records to a data sink.
     *
     * @param batch of records to write.
     * @throws Exception if an error occurs during record writing
     */
    void writeRecords(Batch batch) throws Exception;

    /**
     * Close the writer
     *
     * @throws Exception if an error occurs during closing the writer
     */
    void close() throws Exception;
    
    /**
     * 
     * @param fileHeader
     * @throws IOException 
     */
    void writeHeader(FileHeader fileHeader) throws IOException;
    
    /**
     * 
     * @param fileFooter
     */
    void writeFooter(FileFooter fileFooter);
}
