/**
 * 
 */
package com.barun.example.job;

/**
 * @author jokersingh
 *
 */
public class BatchJob implements Job {
    
	private static final String DEFAULT_JOB_NAME = "job";

    private String name;

    private RecordReader recordReader;
    private RecordWriter recordWriter;
    @SuppressWarnings("rawtypes")
	private RecordProcessor recordProcessor;
    private RecordTracker recordTracker;
    private JobParameters parameters;
    private JobMetrics metrics;
    private JobReport report;
    private JobListener jobListener;

    BatchJob(JobParameters parameters) {
        this.parameters = parameters;
        this.name = DEFAULT_JOB_NAME;
        metrics = new JobMetrics();
        report = new JobReport();
        report.setParameters(parameters);
        report.setMetrics(metrics);
        report.setJobName(name);
        recordTracker = new RecordTracker();
        jobListener = new CompositeJobListener();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public JobReport call() {
        start();
        try {
            openReader();
            openWriter();
            setStatus(JobStatus.STARTED);
            while (moreRecords() && !isInterrupted()) {
                Batch batch = readAndProcessBatch();
                writeBatch(batch);
            }
            setStatus(JobStatus.STOPPING);
        } catch (Exception exception) {
            fail(exception);
            return report;
        } finally {
            closeReader();
            closeWriter();
        }
        teardown();
        return report;
    }

    /*
     * private methods
     */

    private void start() {
        setStatus(JobStatus.STARTING);
        metrics.setStartTime(System.currentTimeMillis());
    }

    private void openReader() throws Exception {
        try {
            recordReader.open();
        } catch (Exception e) {
            throw new Exception("Unable to open record reader", e);
        }
    }

    private void openWriter() throws Exception {
        try {
            recordWriter.open();
        } catch (Exception e) {
            throw new Exception("Unable to open record writer", e);
        }
    }

    private void setStatus(JobStatus status) {
        if(isInterrupted()) {
            //LOGGER.log(Level.INFO, "Job ''{0}'' has been interrupted, aborting execution.", name);
        }
        report.setStatus(status);
    }

    private boolean moreRecords() {
        return recordTracker.moreRecords();
    }

    private Batch readAndProcessBatch() throws Exception, Exception {
        Batch batch = new Batch();
        for (int i = 0; i < parameters.getBatchSize(); i++) {
            Record record = readRecord();
            if (record == null) {
                recordTracker.noMoreRecords();
                break;
            } else {
                metrics.incrementReadCount();
            }
            processRecord(record, batch);
        }
        return batch;
    }

    private Record readRecord() throws Exception {
        Record record;
        try {
            record = recordReader.readRecord();
            return record;
        } catch (Exception e) {
            throw new Exception("Unable to read next record", e);
        }
    }

    @SuppressWarnings(value = "unchecked")
    private void processRecord(Record record, Batch batch) throws Exception {
        Record processedRecord = null;
        try {
            processedRecord = recordProcessor.processRecord(record);
            if (processedRecord != null) {
            	batch.addRecord(processedRecord);
            }
        } catch (Exception e) {
            metrics.incrementErrorCount();
            report.setLastError(e);
            if (metrics.getErrorCount() > parameters.getErrorThreshold()) {
                throw new Exception("Error threshold exceeded. Aborting execution", e);
            }
        }
    }

    private void writeBatch(Batch batch) throws Exception {
        try {
            if (!batch.isEmpty()) {
                recordWriter.writeRecords(batch);
                metrics.incrementWriteCount(batch.size());
            }
        } catch (Exception e) {
            throw new Exception("Unable to write records", e);
        }
    }

    private boolean isInterrupted() {
        return Thread.currentThread().isInterrupted();
    }

    private void teardown() {
        JobStatus jobStatus = isInterrupted() ? JobStatus.ABORTED : JobStatus.COMPLETED;
        teardown(jobStatus);
    }

    private void teardown(JobStatus status) {
        report.setStatus(status);
        metrics.setEndTime(System.currentTimeMillis());
        jobListener.afterJobEnd(report);
    }

    private void fail(Exception exception) {
        String reason = exception.getMessage();
        Throwable error = exception.getCause();
        report.setLastError(error);
        teardown(JobStatus.FAILED);
    }

    private void closeReader() {
        try {
            recordReader.close();
        } catch (Exception e) {
            report.setLastError(e);
        }
    }

    private void closeWriter() {
        try {
            recordWriter.close();
        } catch (Exception e) {
            report.setLastError(e);
        }
    }


    /*
     * Setters for job components
     */

    /**
     * 
     * @param recordReader
     */
    @Override
    public void setRecordReader(RecordReader recordReader) {
        this.recordReader = recordReader;
    }

    /**
     * 
     * @param recordWriter
     */
    @Override
    public void setRecordWriter(RecordWriter recordWriter) {
        this.recordWriter = recordWriter;
    }
    
    /**
	 * @param recordProcessor the recordProcessor to set
	 */
    @Override
	public void setRecordProcessor(RecordProcessor recordProcessor) {
		this.recordProcessor = recordProcessor;
	}

	public void setName(String name) {
        this.name = name;
        this.report.setJobName(name);
    }
}
