/**
 * Handles the Output File option in the request.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-12
 */
package se.gunnareriksson.tealist.optionhandler;

import java.util.Map;

import se.gunnareriksson.tealist.options.Options;

public class OutputFileOption extends OptionHandler 
{
    /**
     * Constructor
     * Initiates the list of option handlers, which handles the
     * parsing of the string of options and eventual values.
     * 
     * @param optionHandling the list of option handlers.
     */
    public OutputFileOption(Map<String, OptionHandler> optionHandling) 
    {
        this.optionHandling = optionHandling;
    }

    /**
     * Adds the option Output File to the request map with or
     * without values, if no faults found.
     * 
     * If the Output File option is found more than twice in the 
     * string of arguments, the option Error is added to the
     * request map.
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
        if(requestMap.containsKey(Options.OUTPUT_FILE))
        {
            requestMap.put(Options.ERROR, "-o can only be used once");
        }
        else if(args.length > indexCounter + 1 && !args[indexCounter + 1].startsWith("-"))
        {
            requestMap.put(Options.OUTPUT_FILE, args[++indexCounter]);
        }
        else
        {
            requestMap.put(Options.OUTPUT_FILE, null);
        }
        
        if (hasMoreArguments(args, ++indexCounter))
        {
            requestMap = parseNextArgument(args, indexCounter, requestMap);
        }
        
        return requestMap;
    }
}
