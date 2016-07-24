/**
 * Handles the list file formats request if the request map
 * contains the option List File Formats.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-15
 */
package se.gunnareriksson.tealist.requesthandler;

import java.util.Map;

import se.gunnareriksson.tealist.options.Options;

public class ListFileFormatsRequest extends RequestHandler
{
    /**
     * Constructor
     * Initiates the next request handler in the chain
     * of request handlers
     * 
     * @param nextRequest the next request handler in the
     *                    chain of request handlers.
     */
    public ListFileFormatsRequest(RequestHandler nextRequest) 
    {
        super.nextRequest = nextRequest;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processRequest(Map<Options, String> request) 
    {
        if (request.containsKey(Options.LIST_FILE_FORMATS))
        {
            showFileFormats();
        }
        else
        {
            nextRequest.processRequest(request);
        }
    }
    
    /**
     * Prints valid file formats to standard out
     */
    private void showFileFormats()
    {
        System.out.println("TeaList 0.1 supports the following file formats");
        System.out.println("text - Text (txt) file where fields are separated with ;");
        System.out.println("xml - Xml (xml) file");
    }
}
