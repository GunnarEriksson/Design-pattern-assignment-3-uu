/**
 * Program entry point. A file converting program that lets you
 * convert different file types containing tea list.
 * 
 * For more information, see readme file for information about
 * valid options.
 * 
 * @author Thomas Aggesjö
 * @version 2011-10-10
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-18
 */
package se.gunnareriksson.tealist;

import java.util.ArrayList;
import java.util.List;

import se.gunnareriksson.tealist.logic.TeaListLogicFactory;
import se.gunnareriksson.tealist.logic.TeaListLogicInterface;
import se.gunnareriksson.tealist.ui.RequestProvider;
import se.gunnareriksson.tealist.ui.TeaListUIFactory;
import se.gunnareriksson.tealist.ui.TeaListUIInterface;

public class TeaListMain 
{
	/**
	 * Program entry point to set up UI and tea list logic
	 * 
	 * @param args Program parameters 
	 */
	public static void main(String[] args) 
	{
	    TeaListUIInterface teaListUI = TeaListUIFactory.createTeaListUI();
	    List<RequestProvider> requestProviders = new ArrayList<RequestProvider>();
	    requestProviders.add(teaListUI);
	    TeaListLogicInterface  teaListLogic = TeaListLogicFactory.createTeaListLogic(requestProviders);
		teaListLogic.updateRequest(args);
	}
}