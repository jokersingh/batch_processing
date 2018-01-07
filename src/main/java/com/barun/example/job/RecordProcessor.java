package com.barun.example.job;

public interface RecordProcessor <I extends Record, O extends Record> {

    /**
     * Process a record.
     *
     * @param record to process.
     * @return the processed record, may be of another type of the input record, or null to skip next processors
     * @throws Exception if an error occurs during record processing
     */
    O processRecord(I record) throws Exception;

}
