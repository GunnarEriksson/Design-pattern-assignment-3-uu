/**
 * The factory for request handler list. The request handlers are
 * responsible to perform the request after the request is parsed
 * and validated.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-15
 */
package se.gunnareriksson.tealist.requesthandler;

public class RequestHandlerFactory 
{
    /**
     * Static method that creates the list of request handlers 
     * which one of them performs the request.
     * 
     * @return the list of option handlers.
     */
    public static RequestHandler createRequestHandlers()
    {
        RequestHandler fromFileFormatRequest = new FromFileFormatRequest(null);
        RequestHandler helpRequest = new HelpRequest(fromFileFormatRequest);
        RequestHandler listFileFormatsRequest = new ListFileFormatsRequest(helpRequest);
        RequestHandler errorRequest = new ErrorRequest(listFileFormatsRequest);
        
        return errorRequest;
    }
}
