/**
 * Handles validation of request. Is the abstract class in
 * a decorator pattern which subclasses implement specific
 * rules for options in the request.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-12
 */
package se.gunnareriksson.tealist.requestvalidator;

import java.util.Map;

import se.gunnareriksson.tealist.optionhandler.OptionHandler;
import se.gunnareriksson.tealist.options.Options;

public abstract class RequestValidator extends OptionHandler
{
    /**
     * Makes it possible to decorate the method.
     * 
     * @param args the string of options and eventual values
     * @param indexCounter the position in the array of arguments.
     * @param requestMap the map with options and values, if needed, to parse.
     * @return the map with the result of options and values, if needed.
     */
    public abstract Map<Options, String> parseRequest(String[] args, int indexCounter, Map<Options, String> requestMap);
}
