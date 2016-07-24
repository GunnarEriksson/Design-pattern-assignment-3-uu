/**
 * Handles the from file format request if the request map
 * contains the option From File Format.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-15
 */
package se.gunnareriksson.tealist.requesthandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import se.gunnareriksson.tealist.files.FileFormats;
import se.gunnareriksson.tealist.files.FileIO;
import se.gunnareriksson.tealist.options.Options;
import se.gunnareriksson.tealist.tea.Tea;

public class FromFileFormatRequest extends RequestHandler
{
    private Map<String, FileIO> fileHandling;
    
    /**
     * Constructor
     * Initiates the next request handler in the chain
     * of request handlers
     * 
     * @param nextRequest the next request handler in the
     *                    chain of request handlers.
     */
    public FromFileFormatRequest(RequestHandler nextRequest) 
    {
        super.nextRequest = nextRequest;
        initalizeFileIOLists();
    }
    
    /**
     * Initiates the map of file formats. The map contains
     * handlers of all supported file types.
     */
    private void initalizeFileIOLists()
    {
        fileHandling = FileFormats.getFileHandlerMap();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processRequest(Map<Options, String> request) 
    {
        if (request.containsKey(Options.FROM_FILE_FORMAT))
        {
            try 
            {
                this.convertFile(request);
            } 
            catch (Exception e) 
            {
                showError(e.getMessage());                 
            }
        }
        else
        {
            nextRequest.processRequest(request);
        }
    }
    
    /**
     * Converts tea data from one file to an other. 
     * 
     * @param options any options given for the conversion
     * @throws Exception if there was an error while converting
     */
    private void convertFile(Map<Options, String> options) throws Exception 
    {
        List<Tea> teaList = new ArrayList<Tea>();       
        
        String inFileName = options.get(Options.INPUT_FILE);
        String inFileFormat = options.get(Options.FROM_FILE_FORMAT);
        String outFileFormat = options.get(Options.TO_FILE_FORMAT);
        String outFileName = options.get(Options.OUTPUT_FILE);
        
        FileIO handling = fileHandling.get(inFileFormat);
        teaList = handling.readFile(inFileName);
        
        FileIO outHandling = fileHandling.get(outFileFormat);
        outHandling.writeFile(teaList, outFileName);
    }
}
