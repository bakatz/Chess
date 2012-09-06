import student.TestCase;


// -------------------------------------------------------------------------
/**
 * Tests the GameLog class.
 *
 * @author Ben Katz (bakatz)
 * @author Danielle Bushrow (dbushrow)
 * @author Myles David (davidmm2)
 * @version 2010.11.17
 */
public class ChessGameLogTest
    extends TestCase
{
    private ChessGameLog g;


    // ----------------------------------------------------------
    /**
     * Create a new TestGameLog object.
     */
    public ChessGameLogTest()
    {
        // not used
    }


    /**
     * Performs initial setup.
     */
    public void setUp()
    {
        g = new ChessGameLog();
    }


    // ----------------------------------------------------------
    /**
     * Tests the addToLog method.
     */
    public void testAddToLog()
    {
        assertEquals( g.getLastLog(), "" );
        g.addToLog( "No more web-cat after this project!" );
        assertEquals(
            g.getLastLog().split( "- " )[1],
            "No more web-cat after this project!" );
        g.addToLog( "This is a cause for celebration." );
        assertEquals(
            g.getLastLog().split( "- " )[1],
            "This is a cause for celebration." );
    }

    // ----------------------------------------------------------
    /**
     * Tests the clearLog method.
     */
    public void testClearLog()
    {
        assertEquals( g.getLastLog(), "" );
        g.addToLog( "No more web-cat after this project!" );
        assertEquals(
            g.getLastLog().split( "- " )[1],
            "No more web-cat after this project!" );
        g.addToLog( "This is a cause for celebration." );
        assertEquals(
            g.getLastLog().split( "- " )[1],
            "This is a cause for celebration." );
        g.clearLog();
        assertEquals( g.getLastLog(), "" );
    }
}
