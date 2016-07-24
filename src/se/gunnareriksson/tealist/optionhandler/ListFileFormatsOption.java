/**
 * Handles the List File Formats option in the request.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-12
 */
package se.gunnareriksson.tealist.optionhandler;

import java.util.Map;

import se.gunnareriksson.tealist.options.Options;

public class ListFileFormatsOption extends OptionHandler 
{
    /**
     * Constructor
     * Initiates the list of option handlers, which handles the
     * parsing of the string of options and eventual values.
     * 
     * @param optionHandling the list of option handlers.
     */
    public ListFileFormatsOption(Map<String, OptionHandler> optionHandling) 
    {
        this.optionHandling = optionHandling;
    }

    /**
     * Adds the option List File Formats to the request map.
     * 
     * If the string of arguments contains of more arguments, then
     * next option handler is called. The type of option handler is
     * determined by the next option.
     * 
     * @param args the string of options and eventual values.
     * @param indexCounter the position in the array of arguments.
     * @param requestMap the map with options and values, if needed, to parse.
     * @return the map with the result of options and values, if needed.
     */
    @Override
    public Map<Options, String> parseRequest(String[] args, int indexCounter, Map<Options, String> requestMap) 
    {
        requestMap.put(Options.LIST_FILE_FORMATS, null);
        
        if (hasMoreArguments(args, ++indexCounter))
        {
            requestMap = parseNextArgument(args, indexCounter, requestMap);
        }
        
        return requestMap;
    }
}
