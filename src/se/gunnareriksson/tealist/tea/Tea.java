/**
 * Class for information regarding tea.
 * 
 * @author Thomas Aggesjö
 * @version 2011-10-25 
 */
package se.gunnareriksson.tealist.tea;

public class Tea 
{
	/* Constants regarding tea */
	public static final String TEA = "tea",
							   TEALIST = "tealist",	
							   CATEGORY = "category",
							   NAME = "name",
							   PRICE = "price",
							   DESCRIPTION = "description";	
	
	public String category;
	public String name;
	public int price;
	public String description;
	
	/**
	 * Default constructor
	 * Creates a default tea object
	 */
	public Tea() 
	{
		category = name = description = "";
	}
	
	/**
	 * Constructs a tea object
	 * 
	 * @param category the tea category (Black, red, herb tea etc)
	 * @param name the name of the tea
	 * @param price the price
	 * @param description a description containing flavors, how it is done etc
	 */
	public Tea(String category, String name, int price, String description) 
	{
		this.category = category;
		this.name = name;
		this.price = price;
		this.description = description;
	}
}