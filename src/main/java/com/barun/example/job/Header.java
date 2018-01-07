/**
 * 
 */
package com.barun.example.job;

import java.util.Date;

/**
 * @author jokersingh
 *
 */
public class Header {

    private Long number;
    private String source;
    private Date creationDate;

    /**
     * @param number       physical record number in the data source (if defined).
     * @param source       data source name from which this record has been read.
     * @param creationDate date at which the record has been read.
     */
    public Header(Long number, String source, Date creationDate) {
        this.number = number;
        this.source = source;
        this.creationDate = creationDate;
    }

    /**
     * Return the physical record number in the data source (if defined).
     */
    public Long getNumber() {
        return number;
    }

    /**
     * Return the data source name from which this record has been read.
     */
    public String getSource() {
        return source;
    }

    /**
     * Return the date at which the record has been read.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("number=").append(number);
        sb.append(", source=\"").append(source).append('\"');
        sb.append(", creationDate=\"").append(creationDate).append('\"');
        return sb.toString();
    }
}
