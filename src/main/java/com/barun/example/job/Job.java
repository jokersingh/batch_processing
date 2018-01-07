/**
 * 
 */
package com.barun.example.job;

import java.util.concurrent.Callable;

/**
 * @author jokersingh
 *
 */
public interface Job extends Callable<JobReport> {

    /**
     * Get the job name.
     *
     * @return the job name
     */
    String getName();

    /**
     * Execute the job.
     *
     * @return execution report
     */
    @Override
    JobReport call();
    /**
     * 
     * @param recordReader
     */
    public void setRecordReader(RecordReader recordReader);
    /**
     * 
     * @param recordWriter
     */
    public void setRecordWriter(RecordWriter recordWriter);
    /**
	 * @param recordProcessor the recordProcessor to set
	 */
	public void setRecordProcessor(RecordProcessor recordProcessor);
}

