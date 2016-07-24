/**
 * Handles the help request if the request map
 * contains the option Help.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-15
 */
package se.gunnareriksson.tealist.requesthandler;

import java.util.Map;

import se.gunnareriksson.tealist.options.Options;

public class HelpRequest extends RequestHandler
{
    /**
     * Constructor
     * Initiates the next request handler in the chain
     * of request handlers
     * 
     * @param nextRequest the next request handler in the
     *                    chain of request handlers.
     */
    public HelpRequest(RequestHandler nextRequest) 
    {
        super.nextRequest = nextRequest;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processRequest(Map<Options, String> request) 
    {
        if (request.containsKey(Options.HELP))
        {
            showHelp();
        }
        else
        {
            nextRequest.processRequest(request);
        }
    }
}
