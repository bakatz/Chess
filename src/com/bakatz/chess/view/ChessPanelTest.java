package com.bakatz.chess.view;
import student.GUITestCase;


// -------------------------------------------------------------------------
/**
 * Does a basic test of the ChessPanel class.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class ChessPanelTest
    extends GUITestCase
{
    private ChessPanel panel;

    /**
     * Sets up a default panel for use in testing.
     */
    public void setUp()
    {
        panel = new ChessPanel();
    }


    // ----------------------------------------------------------
    /**
     * Creates a new TestChessPanel object.
     */
    public ChessPanelTest()
    {
        // not used
    }


    /**
     * Makes sure all components are accessible. This class is really tested
     * through other classes so there is just a simple test case here.
     */
    public void testMakeSureComponentsNotNull()
    {
        assertNotNull( panel.getGameLog() );
        assertNotNull( panel.getGameEngine() );
        assertNotNull( panel.getGameBoard() );
    }

    /**
     * Makes sure getGraveyard returns the proper value in different situations.
     */
    public void testGetGraveyard()
    {
        assertNotNull( panel.getGraveyard( 1 ) );
        assertNotNull( panel.getGraveyard( 2 ) );
        assertNull( panel.getGraveyard( 3 ) );
    }
}
