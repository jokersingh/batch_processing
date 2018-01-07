/**
 * 
 */
package com.barun.example.job;

/**
 * @author jokersingh
 *
 */
public interface JobListener {

    /**
     * Called before starting the job.
     *
     * @param jobParameters the job parameters
     */
    void beforeJobStart(final JobParameters jobParameters);

    /**
     * Called after job end.
     *
     * @param jobReport The job execution report
     */
    void afterJobEnd(final JobReport jobReport);
}
