/**
 * Interface for classes that handles file converting requests
 * to the logic. Files that contains tea information.
 * Extends request provider interface for classes that
 * provides requests to requests listeners.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-08
 */
package se.gunnareriksson.tealist.ui;

public interface TeaListUIInterface extends RequestProvider
{
    public void sendRequest(String[] args);
}
