/**
 * 
 */
package com.barun.example.job;

/**
 * @author jokersingh
 *
 */
public enum JobStatus {

	/**
     * The job is starting (opening record reader/writer).
     */
    STARTING,

    /**
     * The job has started.
     */
    STARTED,

    /**
     * The job is stopping (closing record reader/writer)
     */
    STOPPING,

    /**
     * The job has failed (due to read/write errors or errorThreshold exceeded).
     */
    FAILED,

    /**
     * The job has completed normally without any exception.
     */
    COMPLETED,

    /**
     * The job has been interrupted.
     */
    ABORTED
}
