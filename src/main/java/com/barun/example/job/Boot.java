/**
 * 
 */
package com.barun.example.job;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * @author jokersingh
 *
 */
public class Boot {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		JobParameters parameters = new JobParameters();
		Job job = new BatchJob(parameters);
		MysqlDataSource dataSource= new MysqlDataSource();
		dataSource.setURL("jdbc:mysql://localhost:3306/sys");
		dataSource.setPort(3306);
		dataSource.setUser("root");
		dataSource.setPassword("Password#123");
		List<String> fields = new ArrayList<>();
		fields.add("x");
		job.setRecordReader(new JdbcRecordReader(dataSource, "select * from t1"));
		OutputStream outputstream = new FileOutputStream(new File("c:\\data\\output-text.txt"));
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputstream );
		job.setRecordWriter(new FileRecordWriter(outputStreamWriter ));
		RecordProcessor<JdbcRecord, Record> recordProcessor = new JdbcRecordProcessor<Record>(fields);
		job.setRecordProcessor(recordProcessor );
		JobExecutor jobExecutor = new JobExecutor(4);
	    JobReport jobReport = jobExecutor.execute(job);
	    System.out.println(jobReport);
	    jobExecutor.shutdown();
	}

}
