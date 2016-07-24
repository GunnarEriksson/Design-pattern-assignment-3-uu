/**
 * 
 * The listener interface for receiving file converting requests
 * from the request provider(s).
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 1.0, 2016-01-08
 */
package se.gunnareriksson.tealist.logic;

public interface RequestListener 
{
    /**
     * Receives the convert file request from the
     * request provider
     * 
     * @param args the string of options and paths
     *             to convert files.
     */
    public void updateRequest(String[] args);
}
