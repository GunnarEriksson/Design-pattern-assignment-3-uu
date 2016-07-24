/**
 * Reads and writes a list of Tea objects from and to a text file.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-08
 */
package se.gunnareriksson.tealist.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import se.gunnareriksson.tealist.tea.Tea;

public class TextFile implements FileIO
{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tea> readFile(String fileName) throws IOException 
    {
        File file = new File(fileName);
        if(!file.exists() || !file.isFile())
        {
            throw new IOException("The file " + fileName +  " does not exist");
        }
        
        List<Tea> teaList = new ArrayList<Tea>();
        try (BufferedReader br = createBufferReader(file))
        {
            teaList = createTeaListFromFile(teaList, br);
            br.close();
        }
        catch (Exception e) 
        {
            throw new IOException("Input file (" + fileName + ") not correct format");
        }
        
        return teaList;
    }
    
    /**
     * Helper method to create buffer reader for reading text files.
     * 
     * @param file the file to read from.
     * 
     * @return the buffer reader.
     * @throws FileNotFoundException if file is not found.
     * @throws UnsupportedEncodingException if the named char set is not supported.
     */
    private BufferedReader createBufferReader(File file) throws FileNotFoundException, UnsupportedEncodingException
    {
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        
        return br;
    }
    
    /**
     * Helper method to create a list of teas from text strings in a file.
     * 
     * @param teaList the list of tea.
     * @param br the buffer reader.
     * @return the list of tea.
     * @throws Exception if problem when reading text strings.
     */
    private List<Tea> createTeaListFromFile(List<Tea> teaList, BufferedReader br) throws Exception
    {
        String line;
        while((line = br.readLine()) != null)
        {
            Tea tea = new Tea();
            String[] teaLine = line.split(";");
            tea.category = teaLine[0];
            tea.name = teaLine[1];
            tea.price = Integer.valueOf(teaLine[2]);
            tea.description = teaLine[3];
            teaList.add(tea);

        }
        
        return teaList;
    }

    /**
     * {@inheritDoc}
     * @throws IOException 
     */
    @Override
    public void writeFile(List<Tea> teaList, String fileName) throws IOException 
    {
        if(fileName == null) 
        {
            printTeaListToOut(teaList);
        }
        else 
        {
            File file;
            try 
            {
                file = getFile(fileName);
            }
            catch (IOException e) 
            {
                throw new IOException("File (" + fileName + ") could not be found or created.");
            }
            
            try (BufferedWriter bw = createBufferWriter(file))
            {
                printTeaListToFile(teaList, bw);
                bw.close();
            }
            catch (FileNotFoundException e) 
            {
                throw new IOException("File (" + fileName + ") could not be found.");
            }
            catch (UnsupportedEncodingException e) 
            {
                throw new IOException("File (" + fileName + ") could not be encoded.");
            }
            catch (IOException e) 
            {
                throw new IOException("I/O fault (" + fileName + ").");
            }
        }
    }
    
    /**
     * Helper method to print tea list to out.
     * 
     * @param teaList the tea list to print.
     */
    private void printTeaListToOut(List<Tea> teaList)
    {
        for(Tea tea : teaList)
        {
            System.out.println(tea.category + ";" + tea.name + ";" + tea.price + ";" + tea.description);
        }
    }
    
    /**
     * Helper to get existing file or create a new file if the file
     * does not exits.
     * 
     * @param fileName the name of the file to be found or created.
     * @return the file
     * @throws IOException at problem with file handling.
     */
    private File getFile(String fileName) throws IOException
    {
        File file = new File(fileName);
        if (file.exists())
        {
            return file;
        }
        else
        {
            file.createNewFile();
            return file;
        }
    }
    
    /**
     * Helper method to create buffer writer for writing to text files.
     * 
     * @param file the text file to write to
     * @return the buffer writer
     * @throws FileNotFoundException if the file was not found or could be created.
     * @throws UnsupportedEncodingException if the char set was not supported.
     */
    private BufferedWriter createBufferWriter(File file) throws FileNotFoundException, UnsupportedEncodingException
    {
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);
        
        return bw;
    }
    
    /**
     * Helper method to print tea list to text file.
     * 
     * @param teaList the tea list to be printed.
     * @param bw the buffered writer to be able to print to text file
     * @throws IOException at problem with file handling
     */
    private void printTeaListToFile(List<Tea> teaList, BufferedWriter bw) throws IOException
    {
        for(int i = 0; i < teaList.size(); i ++)  
        {
            Tea tea = teaList.get(i);
            bw.write(tea.category + ";" + tea.name + ";" + tea.price + ";" + tea.description);

            if(i + 1 != teaList.size())
            {
                bw.newLine();
            }
        }
    }
}
