/**
 * Handles parsing of options and conversion between different files 
 * containing information about tea.
 * 
 * @author Thomas Aggesjö
 * @version 2011-10-24
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-03
 */
package se.gunnareriksson.tealist.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.gunnareriksson.tealist.optionhandler.OptionHandler;
import se.gunnareriksson.tealist.optionhandler.OptionHandlerFactory;
import se.gunnareriksson.tealist.options.Options;
import se.gunnareriksson.tealist.requesthandler.RequestHandler;
import se.gunnareriksson.tealist.requesthandler.RequestHandlerFactory;
import se.gunnareriksson.tealist.requestvalidator.ConvertFileValidator;
import se.gunnareriksson.tealist.ui.RequestProvider;

public class TeaListLogic implements TeaListLogicInterface
{	
    private Map<String, OptionHandler> optionHandlers;
    private RequestHandler requestHandlers;
    
	/**
	 * Constructor
	 * Creates the logic to parse and convert files containing information
	 * about tea.
	 * 
	 * @param requestProvider the provider of file conversion requests
	 */
	public TeaListLogic(List<RequestProvider> requestProviders) 
	{
	    registerLogicToRequestProviders(requestProviders);
	    optionHandlers = OptionHandlerFactory.createOptionHandlers();
	    requestHandlers = RequestHandlerFactory.createRequestHandlers();
	}
	
	/**
	 * Register logic to request providers. Providers that sends requests
	 * to logic.
	 * 
	 * @param requestProviders the list of request providers
	 */
	private void registerLogicToRequestProviders(List<RequestProvider> requestProviders)
	{
	    for (RequestProvider requestProvider : requestProviders)
	    {
	        requestProvider.registerRequestListeners(this);
	    }
	}
	
	/**
	 * Helper method to process a request containing one or more options.
	 * For information on valid options see the readme file.
	 * 
	 * @param args string of options and values, if needed.
	 */
	private void processRequest(String[] args) 
	{
	    Map<Options, String> request = new HashMap<Options, String>();
		request = parseRequest(args, request);
		request = validateRequest(request);
		requestHandlers.processRequest(request);
	}

	/**
     * Helper method to parse the request to check if the request contains
     * the correct number of different options and the options are valid. 
     * 
     * @param args the options given to the program
     * @return the map with the options and values, if needed.
     */
    private Map<Options, String> parseRequest(String[] args, Map<Options, String> request) 
    {
        OptionHandler optionHandler = optionHandlers.get(args[0]);
        if (optionHandler == null)
        {
            request.put(Options.ERROR, "Invalid option: " + args[0]);
        }
        else
        {
            request = optionHandler.parseRequest(args, 0, request);
        }
        
        return request;
    }
	
    /**
     * Helper method to validate the request if all mandatory options are included
     * to convert a file.
     * 
     * @param request the options given to the program
     * @return the map with an error option inserted if not all mandatory
     *         options to convert a file was included.
     */
	private Map<Options, String> validateRequest(Map<Options, String> request) 
	{
	    if (!request.containsKey(Options.ERROR))
	    {
	        if (!ConvertFileValidator.isNoneOrAllFileFormatOptionsIncluded())
	        {
	            request.put(Options.ERROR, "-f -t and -i must all be used when converting files");
	        }
	    }
	    
	    return request;
    }

	/**
     * {@inheritDoc}
     */
    @Override
    public void updateRequest(String[] args) 
    {
        processRequest(args);
    }
}