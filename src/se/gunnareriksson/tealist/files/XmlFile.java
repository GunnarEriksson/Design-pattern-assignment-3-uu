/**
 * Reads and writes a list of Tea objects from and to an XML file.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-08
 */
package se.gunnareriksson.tealist.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import se.gunnareriksson.tealist.tea.Tea;

public class XmlFile implements FileIO 
{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tea> readFile(String fileName) throws IOException 
    {
        List<Tea> teaList = new ArrayList<Tea>();

        File xmlFile = new File(fileName);
        if(!xmlFile.exists() || !xmlFile.isFile())
        {
            throw new IOException("The file " + fileName +  " does not exist");
        }

        try
        {
            Document doc = createDocumentFromXmlFile(xmlFile);
            teaList = createTeaListFromDocument(teaList, doc);
        }
        catch(Exception e) 
        {
            throw new IOException("Input file (" + fileName + ") not correct format");
        }
        return teaList;
    }
    
    /**
     * Helper method to create XML document from an XML file
     * 
     * @param xmlFile the XML file to create the document from.
     * @return the XML document containing the content of the XML file.
     * @throws ParserConfigurationException if document builder can not be created.
     * @throws SAXException If any parse errors occur
     * @throws IOException If any IO errors occur
     */
    private Document createDocumentFromXmlFile(File xmlFile) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        
        return doc;
    }
    
    /**
     * Helper method to create tea list from XML document
     * 
     * @param teaList the tea list to add tea element from XML document
     * @param doc the XML document containing tea information.
     * @return the tea list.
     */
    private List<Tea> createTeaListFromDocument(List<Tea> teaList, Document doc)
    {
        NodeList nodeList = doc.getElementsByTagName(Tea.TEA);
        for(int i = 0; i < nodeList.getLength(); i++) 
        {
            Tea tea = new Tea();
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) 
            {
                Element element = (Element) node;

                tea.category = getTagValue(Tea.CATEGORY, element);
                tea.name = getTagValue(Tea.NAME, element);
                tea.price = Integer.valueOf(getTagValue(Tea.PRICE, element));
                tea.description = getTagValue(Tea.DESCRIPTION, element);     
            }
            teaList.add(tea);
        }
        
        return teaList;
    }
    
    /**
     * Helper method to get a value from an element
     *  
     * @param tag The tag we are looking for
     * @param element The current element
     * @return The value of the tag
     */
    private String getTagValue(String tag, Element element) 
    {
        NodeList nlList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeFile(List<Tea> teaList, String fileName) throws Exception
    {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        doc = writeXmlDocumentFromTeaList(teaList, doc);           

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        
        StreamResult result;
        if(fileName == null) 
        {
            result = new StreamResult(System.out);
        }
        else 
        {
            result = new StreamResult(new File(fileName));
        }

        transformer.transform(source, result);        
    }
    
    private Document writeXmlDocumentFromTeaList(List<Tea> teaList, Document doc)
    {
        Element rootElement = doc.createElement(Tea.TEALIST);
        doc.appendChild(rootElement);
        
        for(Tea tea : teaList) {
            Element teaElement = doc.createElement(Tea.TEA);
            rootElement.appendChild(teaElement);

            Element categoryElement = doc.createElement(Tea.CATEGORY);
            categoryElement.appendChild(doc.createTextNode(tea.category));
            teaElement.appendChild(categoryElement);

            Element nameElement = doc.createElement(Tea.NAME);
            nameElement.appendChild(doc.createTextNode(tea.name));
            teaElement.appendChild(nameElement);

            Element priceElement = doc.createElement(Tea.PRICE);
            priceElement.appendChild(doc.createTextNode("" + tea.price));
            teaElement.appendChild(priceElement);

            Element descriptionElement = doc.createElement(Tea.DESCRIPTION);
            descriptionElement.appendChild(doc.createTextNode(tea.description));
            teaElement.appendChild(descriptionElement);
        }
        
        return doc;
    }
}
