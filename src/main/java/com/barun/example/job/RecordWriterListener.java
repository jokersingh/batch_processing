/**
 * 
 */
package com.barun.example.job;

/**
 * @author jokersingh
 *
 */
public interface RecordWriterListener {

	/**
     * Called before writing each batch of records.
     *
     * @param batch to be written
     */
    void beforeRecordWriting(Batch batch);

    /**
     * Called after writing each batch of records.
     *
     * @param batch of records that have been written.
     */
    void afterRecordWriting(Batch batch);

    /**
     * Called when an exception occurs during batch writing.
     *
     * @param batch attempted to be written
     * @param throwable the throwable thrown during record writing
     */
    void onRecordWritingException(Batch batch, final Throwable throwable);
}
