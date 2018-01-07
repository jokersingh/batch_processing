/**
 * 
 */
package com.barun.example.job;

import static java.lang.String.format;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.DataSource;

/**
 * @author jokersingh
 *
 */
public class JdbcRecordReader implements RecordReader {

    private DataSource dataSource;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;
    private String dataSourceName;
    private long currentRecordNumber;

    // parameters
    private int maxRows;
    private int queryTimeout;
    private int fetchSize;

    /**
     * Create a new {@link JdbcRecordReader}.
     *
     * @param dataSource to read data from
     * @param query      to fetch data
     */
    public JdbcRecordReader(final DataSource dataSource, final String query) {
        checkNotNull(dataSource, "data source");
        checkNotNull(query, "query");
        this.dataSource = dataSource;
        this.query = query;
    }

    @Override
    public void open() throws Exception {
        currentRecordNumber = 0;
        connection = dataSource.getConnection();
        statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        if (maxRows >= 1) {
            statement.setMaxRows(maxRows);
        }
        if (fetchSize >= 1) {
            statement.setFetchSize(fetchSize);
        }
        if (queryTimeout >= 1) {
            statement.setQueryTimeout(queryTimeout);
        }
        resultSet = statement.executeQuery(query);
        dataSourceName = getDataSourceName();
    }

    private boolean hasNextRecord() {
        try {
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public JdbcRecord readRecord() {
        if (hasNextRecord()) {
            Header header = new Header(++currentRecordNumber, dataSourceName, new Date());
            return new JdbcRecord(header, resultSet);
        } else {
            return null;
        }
    }

    private String getDataSourceName() {
        try {
            return "Connection URL: " + connection.getMetaData().getURL() + " | Query string: " + query;
        } catch (SQLException e) {
            return "N/A";
        }
    }

    @Override
    public void close() throws Exception {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Set the maximum number of rows to fetch.
     *
     * @param maxRows the maximum number of rows to fetch
     */
    public void setMaxRows(final int maxRows) {
        checkArgument(maxRows >= 1, "max rows parameter must be greater than or equal to 1");
        this.maxRows = maxRows;
    }

    /**
     * Set the statement fetch size.
     *
     * @param fetchSize the fetch size to set
     */
    public void setFetchSize(final int fetchSize) {
        checkArgument(fetchSize >= 1, "fetch size parameter must be greater than or equal to 1");
        this.fetchSize = fetchSize;
    }

    /**
     * Set the statement query timeout.
     *
     * @param queryTimeout the query timeout in seconds
     */
    public void setQueryTimeout(final int queryTimeout) {
        checkArgument(queryTimeout >= 1, "query timeout parameter must be greater than or equal to 1");
        this.queryTimeout = queryTimeout;
    }
    
    private void checkNotNull(Object argument, String argumentName) {
        if (argument == null) {
            throw new IllegalArgumentException(format("The %s must not be null", argumentName));
        }
    }
    
    private void checkArgument(boolean assertion, String message) {
        if (!assertion) {
            throw new IllegalArgumentException(message);
        }
    }
}

