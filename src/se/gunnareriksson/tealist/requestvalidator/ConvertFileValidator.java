/**
 * Adds the name of the object that calls the method parseRequest 
 * to a list, if not already added.
 * The list is later used to check that all mandatory file
 * converting options are present in the request, if least one 
 * mandatory file converting option is found.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-12
 */
package se.gunnareriksson.tealist.requestvalidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import se.gunnareriksson.tealist.optionhandler.OptionHandler;
import se.gunnareriksson.tealist.options.Options;

public class ConvertFileValidator extends RequestValidator 
{
    private OptionHandler optionHandler;
    private static List<String> convertOptionHandlersList = new ArrayList<String>();
    private static List<String> parsingOptionHandlersList = new ArrayList<String>();
    
    /**
     * Constructor
     * Adds the object it should decorate. The class name of the object
     * is added to a list, which is used to see if none or all file
     * converting options are present.
     * 
     * @param optionHandler the object the class decorates.
     */
    public ConvertFileValidator(OptionHandler optionHandler) 
    {
        this.optionHandler = optionHandler;
        convertOptionHandlersList.add(optionHandler.getClass().getSimpleName());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Options, String> parseRequest(String[] args, int indexCounter, Map<Options, String> requestMap) 
    {
        addParsingHandlerIfNotAdded(optionHandler.getClass().getSimpleName());
        requestMap = optionHandler.parseRequest(args, indexCounter, requestMap);
        
        return requestMap;
    }
    
    /**
     * Helper method to add the class name of the object that calls
     * the method parseRequest, if the name is not already added.
     * 
     * @param parsingHandler the class name of the calling object.
     */
    private void addParsingHandlerIfNotAdded(String parsingHandler)
    {
        if (!parsingOptionHandlersList.contains(parsingHandler))
        {
            parsingOptionHandlersList.add(parsingHandler);
        }
    }
    
    /**
     * Helper method to check if all file conversion options is present
     * if at least one is present. The lists contains handlers that
     * handles the file converting options.
     * Clears list so it will be ready for the next validation.
     * 
     * @return <code>true</code>  if no or all options are present. No
     *                            option present means that no file
     *                            conversion should be done.
     *         <code>false</code> if least one option is present 
     *                            but not all.
     */
    public static boolean isNoneOrAllFileFormatOptionsIncluded()
    {
        if (parsingOptionHandlersList.isEmpty())
        {
            return true;
        }
        else
        {
            List<String> tempList = deepCopy(parsingOptionHandlersList);
            parsingOptionHandlersList.clear();
            
            return tempList.containsAll(convertOptionHandlersList); 
        }
    }
    
    /**
     * Helper method to make a deep copy of a list containing
     * string items.
     * 
     * @param source the list of strings to copy
     * @return the list of strings copy.
     */
    private static List<String> deepCopy(List<String> source)
    {
        List<String> copy = new ArrayList<String>();
        
        for (String item : source)
        {
            copy.add(item);
        }
        
        return copy;
    }
}
