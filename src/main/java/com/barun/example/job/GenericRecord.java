/**
 * 
 */
package com.barun.example.job;

/**
 * @author jokersingh
 *
 */
public class GenericRecord<P> implements Record<P> {

    protected Header header;
    protected P payload;

    /**
     * Create a new {@link GenericRecord}.
     *
     * @param header  the record header
     * @param payload the record payload
     */
    public GenericRecord(final Header header, final P payload) {
        this.header = header;
        this.payload = payload;
    }

    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public P getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "Record: {" +
                "header=[" + header +
                "], payload=[" + payload +
                "]}";
    }
}
