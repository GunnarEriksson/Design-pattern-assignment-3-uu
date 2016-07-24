/**
 * Handles the error request if the request map
 * contains the option Error.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-15
 */
package se.gunnareriksson.tealist.requesthandler;

import java.util.Map;

import se.gunnareriksson.tealist.options.Options;

public class ErrorRequest extends RequestHandler
{
    /**
     * Constructor
     * Initiates the next request handler in the chain
     * of request handlers
     * 
     * @param nextRequest the next request handler in the
     *                    chain of request handlers.
     */
    public ErrorRequest(RequestHandler nextRequest) 
    {
        super.nextRequest = nextRequest;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void processRequest(Map<Options, String> request) 
    {
        if (request.containsKey(Options.ERROR))
        {
            showError(request.get(Options.ERROR));
        }
        else
        {
            nextRequest.processRequest(request);
        }
    }
}
