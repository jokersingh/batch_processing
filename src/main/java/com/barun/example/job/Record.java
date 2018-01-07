/**
 * 
 */
package com.barun.example.job;

/**
 * @author jokersingh
 *
 */
public interface Record<P> {

    /**
     * Return the record's header.
     *
     * @return the record's header.
     */
    Header getHeader();

    /**
     * Return the record's payload.
     *
     * @return the record's payload.
     */
    P getPayload();

}
