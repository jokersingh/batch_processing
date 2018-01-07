/**
 * 
 */
package com.barun.example.job;

import java.io.Serializable;

/**
 * @author jokersingh
 *
 */
public class JobReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8474647691416226003L;
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private String reportFormat =
            "Job Report:" + LINE_SEPARATOR +
            "===========" + LINE_SEPARATOR +
            "Name: %s" + LINE_SEPARATOR +
            "Status: %s" + LINE_SEPARATOR ;

    private String jobName;

    private JobParameters parameters;

    private JobMetrics metrics;

    private JobStatus status;

    private Throwable lastError;

	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the parameters
	 */
	public JobParameters getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(JobParameters parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the metrics
	 */
	public JobMetrics getMetrics() {
		return metrics;
	}

	/**
	 * @param metrics the metrics to set
	 */
	public void setMetrics(JobMetrics metrics) {
		this.metrics = metrics;
	}

	/**
	 * @return the status
	 */
	public JobStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(JobStatus status) {
		this.status = status;
	}

	/**
	 * @return the lastError
	 */
	public Throwable getLastError() {
		return lastError;
	}

	/**
	 * @param lastError the lastError to set
	 */
	public void setLastError(Throwable lastError) {
		this.lastError = lastError;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(reportFormat,jobName,status);
	}
    
    
}