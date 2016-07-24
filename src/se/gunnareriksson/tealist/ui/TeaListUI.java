/**
 * The tea list UI that sends the file converting request to all
 * added listeners.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-18
 */
package se.gunnareriksson.tealist.ui;

import java.util.ArrayList;
import java.util.List;

import se.gunnareriksson.tealist.logic.RequestListener;

public class TeaListUI implements TeaListUIInterface
{
    private List<RequestListener> requestListeners;
    
    /**
     * Constructor
     * Creates the list of request listeners.
     */
    public TeaListUI() 
    {
        requestListeners = new ArrayList<RequestListener>();
    }
    
    /**
     * Class entry point.
     * 
     * @param args the string of options and eventual values
     */
    public void request(String[] args)
    {
        sendRequest(args);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void sendRequest(String[] args) 
    {
        for (RequestListener listener : requestListeners)
        {
            listener.updateRequest(args);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerRequestListeners(RequestListener listener) 
    {
        if (!isListenerIncludedInList(listener))
        {
            requestListeners.add(listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeRequestListeners(RequestListener listener) 
    {
        if (isListenerIncludedInList(listener))
        {
            requestListeners.remove(listener);
        }
    }
    
    /**
     * Helper method to check if the listener is in the list
     * 
     * @param listener the listener to check if it is in the list.
     * @return <code>true</code> if listener exists in the list.
     *         <code>false</code> if listener not exists in the list.
     */
    private boolean isListenerIncludedInList(RequestListener listener) 
    {
        return requestListeners.contains(listener);
    }
}
