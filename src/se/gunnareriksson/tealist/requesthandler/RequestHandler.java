/**
 * Handles the request after the request is parsed and validated.
 * Is the abstract class in a chain of responsibility pattern, which
 * subclasses determines which class should handle the request.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-12
 */
package se.gunnareriksson.tealist.requesthandler;

import java.util.Map;

import se.gunnareriksson.tealist.options.Options;

public abstract class RequestHandler 
{
    protected RequestHandler nextRequest;
    
    /**
     * Processes the request. If the request should not
     * be handled by the current request handler, the request 
     * is forwarded to the next request handler in the chain.
     * 
     * @param requests the map with the options and values, if needed.
     */
    public abstract void processRequest(Map<Options, String> requests);
    
    /**
     * Prints any errors to standard out
     * 
     * @param error the error that happened
     */
    protected void showError(String error)
    {
        System.out.println("Error: " + error);
        this.showHelp();        
    }
    
    /**
     * Prints the help to standard out
     */
    protected void showHelp()
    {
        System.out.println("Usage: java -jar TeaList.jar [OPTION][VALUE]");
        System.out.println("Options can be given in any order");
        System.out.println("");
        System.out.println("Input/output format");
        System.out.println("-f from file format (requires value after)");
        System.out.println("-t to file format (require value after)");
        System.out.println("");
        System.out.println("Input/output file");
        System.out.println("-i input file (requires value after)");
        System.out.println("-o output file (if no value is given standard output will be used)");
        System.out.println("");
        System.out.println("Information");
        System.out.println("-l list avaliable file formats");
        System.out.println("-h print help");
        System.out.println("");
        System.out.println("Example of valid options and values");
        System.out.println("java -jar TeaList.jar -h");
        System.out.println("(prints help)");
        System.out.println("java -jar TeaList.jar -f text -t xml -i tea.txt");
        System.out.println("(reads a tealist in text format in tea.txt and writes it as xml to standard output)");
        System.out.println("java -jar TeaList.jar -o tea.txt -i tea.xml -t txt -f xml");
        System.out.println("(reads a tealist in xml format in tea.xml and writes it as text to tea.txt)");      
    }
}
