/**
 * Interface for classes that handles writing to file and reading
 * from file.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-08
 */
package se.gunnareriksson.tealist.files;

import java.io.IOException;
import java.util.List;

import se.gunnareriksson.tealist.tea.Tea;

public interface FileIO 
{
    /**
     * Reads information from a file and store the information in a list of
     * Tea objects
     * 
     * @param fileName the path and name of the file to read from.
     * @return a list of Tea objects
     * @throws IOException if the file does not exist or have not correct
     *                     format.
     */
    public List<Tea> readFile(String fileName) throws IOException;
    
    /**
     * Writes a list of Tea object to a file.
     * 
     * @param teaList the list of Tea objects that should be stored to a file.
     * @param fileName path and file name were the information should be stored.
     * @throws Exception if file could not be created or fails to write
     *                   information to the file.
     */
    public void writeFile(List<Tea> teaList, String fileName) throws Exception;
}
