/**
 * The factory for option handler list. The option handlers are
 * responsible to parse the request of options and values.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-18
 */
package se.gunnareriksson.tealist.optionhandler;

import java.util.HashMap;
import java.util.Map;

import se.gunnareriksson.tealist.options.Options;
import se.gunnareriksson.tealist.requestvalidator.ConvertFileValidator;

public class OptionHandlerFactory 
{
    /**
     * Static method that creates the list of option handlers 
     * that provides the parsing of the request.
     * 
     * @return the list of option handlers.
     */
    public static Map<String, OptionHandler> createOptionHandlers()
    {
        Map<String, OptionHandler> optionHandlers = new HashMap<String, OptionHandler>();
        
        optionHandlers.put(Options.FROM_FILE_FORMAT.toString(), new ConvertFileValidator(new FromFileFormatOption(optionHandlers)));
        optionHandlers.put(Options.TO_FILE_FORMAT.toString(), new ConvertFileValidator(new ToFileFormatOption(optionHandlers)));
        optionHandlers.put(Options.INPUT_FILE.toString(), new ConvertFileValidator(new InputFileOption(optionHandlers)));
        optionHandlers.put(Options.OUTPUT_FILE.toString(), new OutputFileOption(optionHandlers));
        optionHandlers.put(Options.LIST_FILE_FORMATS.toString(), new ListFileFormatsOption(optionHandlers));
        optionHandlers.put(Options.HELP.toString(), new HelpOption(optionHandlers));
        optionHandlers.put(Options.ERROR.toString(), new ErrorOption(optionHandlers));
        
        return optionHandlers;
    }
}
