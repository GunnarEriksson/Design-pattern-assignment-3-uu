/**
 * Parses the request to check for invalid options, invalid options
 * and invalid request structure.
 * Is the abstract class in a state design pattern. The pattern
 * decides which next option handler that should be called
 * depending of the next option.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-12
 */
package se.gunnareriksson.tealist.optionhandler;

import java.util.Map;

import se.gunnareriksson.tealist.options.Options;

public abstract class OptionHandler
{
    protected Map<String, OptionHandler> optionHandling;
    
    /**
     * Parses the request depending of the option.
     * 
     * @param args the string of options and eventual values.
     * @param indexCounter the position in the array of arguments.
     * @param requestMap the map with options and values, if needed, to parse.
     * @return the map with the result of options and values, if needed.
     */
    public abstract Map<Options, String> parseRequest(String[] args, int indexCounter, Map<Options, String> requestMap);
    
    /**
     * Checks if there are more arguments in the string of options and
     * eventual values.
     * 
     * @param args the string of options and eventual values.
     * @param indexCounter the position in the array of arguments.
     * @return <code>true</code>  if the string contains more arguments.
     *         <code>false</code> if the string contains no arguments.
     */
    protected boolean hasMoreArguments(String[] args, int indexCounter)
    {
        if (indexCounter < args.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Parse the next argument in the string of options and eventual values.
     * 
     * @param args the string of options and eventual values.
     * @param indexCounter indexCounter the position in the array of arguments.
     * @param requestMap the map with options and values, if needed, to parse.
     * @return the map with options and values, if needed.
     */
    protected Map<Options, String> parseNextArgument(String[] args, int indexCounter, Map<Options, String> requestMap)
    {
        OptionHandler optionHandler = optionHandling.get(args[indexCounter]);
        if (optionHandler != null)
        {
            requestMap = optionHandler.parseRequest(args, indexCounter, requestMap);
        }
        else
        {
            requestMap.put(Options.ERROR, "Invalid option: " + args[indexCounter]);
        }
        
        return requestMap;
    }
}
