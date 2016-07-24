/**
 * The factory for tea list UI. Provides the request to the
 * tea list logic.
 * 
 * @author Gunnar Eriksson, Gunnar.Eriksson.9208@student.uu.se
 * @version 2016-01-18
 */
package se.gunnareriksson.tealist.ui;

public class TeaListUIFactory 
{
    /**
     * Create the tea list UI that provides the file converting
     * requests.
     * @return the tea list UI
     */
    public static TeaListUIInterface createTeaListUI()
    {
        return new TeaListUI();
    }
}
