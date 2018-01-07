/**
 * 
 */
package com.barun.example.job;

import static java.lang.String.format;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * @author jokersingh
 *
 */
public class FileRecordWriter implements RecordWriter {

    private String lineSeparator;
    private OutputStreamWriter outputStreamWriter;
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * 
     *
     * @param outputStreamWriter the output stream to write records to.
     */
    public FileRecordWriter(final OutputStreamWriter outputStreamWriter) {
        this(outputStreamWriter, LINE_SEPARATOR);
    }

    /**
     * 
     *
     * @param outputStreamWriter the output stream to write records to.
     * @param lineSeparator      the line separator.
     */
    public FileRecordWriter(final OutputStreamWriter outputStreamWriter, final String lineSeparator) {
        checkNotNull(outputStreamWriter, "output stream writer");
        checkNotNull(lineSeparator, "line separator");
        this.outputStreamWriter = outputStreamWriter;
        this.lineSeparator = lineSeparator;
    }

    @Override
    public void open() throws Exception {
        // no op
    }

    @Override
    public void writeRecords(Batch batch) throws Exception {
        for (Record record : batch) {
        	@SuppressWarnings("unchecked")
			List<String> values = (List<String>) record.getPayload();
        	int i = 1;
        	for(String value : values) {
        		outputStreamWriter.write(value);
        		if(i != values.size()) {
        			outputStreamWriter.write("|");
        		}
        		i++;
        	}
            outputStreamWriter.write(lineSeparator);
        }
        outputStreamWriter.flush();

    }

    @Override
    public void close() throws Exception {
        outputStreamWriter.close();
    }
    public static void checkNotNull(Object argument, String argumentName) {
        if (argument == null) {
            throw new IllegalArgumentException(format("The %s must not be null", argumentName));
        }
    }

	@Override
	public void writeHeader(FileHeader fileHeader) throws IOException {
		for(String value : fileHeader.getParts()) {
			outputStreamWriter.write(value);
			outputStreamWriter.write(lineSeparator);
		}
		
	}

	@Override
	public void writeFooter(FileFooter fileFooter) {
		outputStreamWriter.write(value);
		outputStreamWriter.write(lineSeparator);
		
	}
}