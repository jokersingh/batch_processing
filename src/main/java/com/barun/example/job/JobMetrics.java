/**
 * 
 */
package com.barun.example.job;

import java.io.Serializable;

/**
 * @author jokersingh
 *
 */
public class JobMetrics implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1943604441293037824L;

	private long startTime;

    private long endTime;

    private long readCount;

    private long writeCount;

    private long errorCount;

    public void incrementErrorCount() {
        errorCount++;
    }

    public void incrementReadCount() {
        readCount++;
    }

    public void incrementWriteCount(long count) {
        writeCount += count;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getDuration() {
        return getEndTime() - getStartTime();
    }

    public long getErrorCount() {
        return errorCount;
    }

    public long getReadCount() {
        return readCount;
    }

    public long getWriteCount() {
        return writeCount;
    }
}

