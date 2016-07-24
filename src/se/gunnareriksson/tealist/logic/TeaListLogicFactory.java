/**
 * Factory to create tea list logic to handle requests for file converting.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-08
 */
package se.gunnareriksson.tealist.logic;

import java.util.List;

import se.gunnareriksson.tealist.ui.RequestProvider;

public class TeaListLogicFactory 
{
    /**
     * Create tea list logic
     * 
     * @param requestProvider the list of request providers that provides file converting
     *                        requests.  
     * @return the tea list logic.
     */
    public static TeaListLogicInterface createTeaListLogic(List<RequestProvider> requestProvider)
    {
        return new TeaListLogic(requestProvider);
    }
}
