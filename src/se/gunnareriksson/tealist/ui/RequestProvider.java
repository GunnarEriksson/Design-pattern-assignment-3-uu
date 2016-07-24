/**
 * The provider interface for sending file converting requests
 * to request listener(s).
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 1.0, 2016-01-08
 */
package se.gunnareriksson.tealist.ui;

import se.gunnareriksson.tealist.logic.RequestListener;

public interface RequestProvider 
{
    /**
     * Add listeners.
     * @param listener the listener that should be added.
     */
    public void registerRequestListeners(RequestListener listener);
    
    /**
     * Remove listeners.
     * @param listener the listener that should be removed.
     */
    public void removeRequestListeners(RequestListener listener);
}
