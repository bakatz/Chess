import javax.swing.JFrame;
import student.GUITestCase;


// -------------------------------------------------------------------------
/**
 * Tests the ChessMain class.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class ChessMainTest
    extends GUITestCase
{
    /**
     * Used to set default variables. Not used in this case.
     */
    public void setUp()
    {
        // not used
    }


    // ----------------------------------------------------------
    /**
     * Creates a new TestChessMain object.
     */
    public ChessMainTest()
    {
        // not used
    }


    // ----------------------------------------------------------
    /**
     * Tests the main method. Just makes sure the JFrame actually shows up and
     * is visible.
     */
    public void testMain()
    {
        ChessMain.main( null );
        assertTrue( this.getComponent( JFrame.class ).isVisible() );
    }
}
