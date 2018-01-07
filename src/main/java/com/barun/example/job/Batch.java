/**
 * 
 */
package com.barun.example.job;

import static java.util.Collections.addAll;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author jokersingh
 *
 */
public class Batch implements Iterable<Record> {

    private List<Record> records = new ArrayList<>();
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * Create a new {@link Batch}.
     */
    public Batch() {
    }

    /**
     * Create a new {@link Batch}.
     *
     * @param records to put in the batch
     */
    public Batch(Record... records) {
        addAll(this.records, records);
    }

    /**
     * Create a new {@link Batch}.
     *
     * @param records to put in the batch
     */
    public Batch(List<Record> records) {
        this.records = records;
    }

    /**
     * Add a record to the batch.
     *
     * @param record to add
     */
    public void addRecord(final Record record) {
        records.add(record);
    }

    /**
     * Remove a record from the batch.
     *
     * @param record to remove
     */
    public void removeRecord(final Record record) {
        records.remove(record);
    }

    /**
     * Check if the batch is empty.
     *
     * @return true if the batch is empty, false otherwise
     */
    public boolean isEmpty() {
        return records.isEmpty();
    }

    /**
     * Get the size of the batch.
     *
     * @return the size of the batch
     */
    public long size() {
        return records.size();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Batch batch = (Batch) o;

        return records.equals(batch.records);

    }

    @Override
    public int hashCode() {
        return records.hashCode();
    }


    @Override
    public Iterator<Record> iterator() {
        return records.iterator();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Batch: {");
        stringBuilder.append(LINE_SEPARATOR);
        for (Record record : records) {
            stringBuilder.append('\t');
            stringBuilder.append(record);
            stringBuilder.append(LINE_SEPARATOR);
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
