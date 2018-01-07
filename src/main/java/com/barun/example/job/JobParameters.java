/**
 * 
 */
package com.barun.example.job;

import java.io.Serializable;

/**
 * @author jokersingh
 *
 */
public class JobParameters implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6650735097324187079L;

	/**
     * Default job name.
     */
    public static final String DEFAULT_JOB_NAME = "job";

    /**
     * Default error threshold.
     */
    public static final long DEFAULT_ERROR_THRESHOLD = Long.MAX_VALUE;

    /**
     * Default batch size.
     */
    public static final int DEFAULT_BATCH_SIZE = 100;

    private long errorThreshold;

    private boolean jmxMonitoring;

    private int batchSize;

    public JobParameters() {
        this.errorThreshold = DEFAULT_ERROR_THRESHOLD;
        this.batchSize = DEFAULT_BATCH_SIZE;
    }

    public long getErrorThreshold() {
        return errorThreshold;
    }

    public void setErrorThreshold(long errorThreshold) {
        this.errorThreshold = errorThreshold;
    }

    public boolean isJmxMonitoring() {
        return jmxMonitoring;
    }

    public void setJmxMonitoring(boolean jmxMonitoring) {
        this.jmxMonitoring = jmxMonitoring;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }
}
