import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import student.GUITestCase;


/**
 * Tests the ChessGameEngine class.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class ChessGameEngineTest
    extends GUITestCase
{
    private ChessPanel      panel;

    private ChessGameEngine gameEngine;

    private ChessGameBoard  board;


    /**
     * Sets up the test harness by creating a panel and other necessary
     * Chess objects.
     */
    public void setUp()
    {
        panel = new ChessPanel();
        gameEngine = panel.getGameEngine();
        board = panel.getGameBoard();
    }


    // ----------------------------------------------------------
    /**
     * Creates a new TestChessGameEngine object.
     */
    public ChessGameEngineTest()
    {
        // not used
    }


    // ----------------------------------------------------------
    /**
     * Makes sure the ChessGameEngine initialized properly and is waiting for
     * the user to click.
     */
    public void testInitialization()
    {
        assertNotNull( gameEngine );
        assertTrue( panel.getGameLog().getLastLog().contains(
            "new chess" + " game" ) );
        assertEquals( gameEngine.getCurrentPlayer(), 1 );
        assertFalse( gameEngine.playerHasLegalMoves( -1 ) );
        assertFalse( gameEngine.isKingInCheck( false ) );
    }


    // ----------------------------------------------------------
    /**
     * Performs a checkmate and determines if all conditions are met for this
     * end-game state. (for player 2)
     */
    public void testCheckmatePlayerOne()
    {
        assertTrue( gameEngine.playerHasLegalMoves( 1 ) );
        assertTrue( gameEngine.playerHasLegalMoves( 2 ) );
        assertEquals( gameEngine.determineGameLost(), 0 );
        showInFrame( panel );
        this.click( board.getCells()[6][3] );
        this.click( board.getCells()[4][3] ); // move pawn above queen 2 north
        this.click( board.getCells()[1][0] );
        this.click( board.getCells()[2][0] );
        assertEquals( gameEngine.determineGameLost(), 0 );

        this.click( board.getCells()[7][2] );
        this.click( board.getCells()[4][5] ); // move bishop 3 north-east
        this.click( board.getCells()[2][0] );
        this.click( board.getCells()[3][0] );
        assertEquals( gameEngine.determineGameLost(), 0 );

        this.click( board.getCells()[7][4] );
        this.click( board.getCells()[5][2] ); // move queen 2 north-west
        this.click( board.getCells()[3][0] );
        this.click( board.getCells()[4][0] );
        assertEquals( gameEngine.determineGameLost(), 0 );

        this.click( board.getCells()[5][2] );
        this.click( board.getCells()[1][2] );
        assertTrue( gameEngine.playerHasLegalMoves( 1 ) );
        assertFalse( gameEngine.playerHasLegalMoves( 2 ) );
        assertEquals( gameEngine.determineGameLost(), 2 );
        assertEquals( gameEngine.getCurrentPlayer(), 2 );
        selectConfirmDialogOption( JOptionPane.YES_OPTION );
    }

    // ----------------------------------------------------------
    /**
     * Performs a checkmate and determines if all conditions are met for this
     * end-game state. (for player 2)
     */
    public void testCheckmatePlayerTwo()
    {
        assertTrue( gameEngine.playerHasLegalMoves( 1 ) );
        assertTrue( gameEngine.playerHasLegalMoves( 2 ) );
        assertEquals( gameEngine.determineGameLost(), 0 );
        showInFrame( panel );
        this.click( board.getCells()[6][0] );
        this.click( board.getCells()[5][0] );
        this.click( board.getCells()[1][3] );
        this.click( board.getCells()[3][3] ); // move pawn below queen 2 south
        this.click( board.getCells()[5][0] );
        this.click( board.getCells()[4][0] );
        assertEquals( gameEngine.determineGameLost(), 0 );


        this.click( board.getCells()[0][2] );
        this.click( board.getCells()[3][5] ); // move bishop 3
        this.click( board.getCells()[4][0] );
        this.click( board.getCells()[3][0] );
        assertEquals( gameEngine.determineGameLost(), 0 );

        this.click( board.getCells()[0][4] );
        this.click( board.getCells()[2][2] ); // move queen 2
        this.click( board.getCells()[3][0] );
        this.click( board.getCells()[2][0] );
        assertEquals( gameEngine.determineGameLost(), 0 );

        this.click( board.getCells()[2][2] );
        this.click( board.getCells()[6][2] );
        assertFalse( gameEngine.playerHasLegalMoves( 1 ) );
        assertTrue( gameEngine.playerHasLegalMoves( 2 ) );
        assertEquals( gameEngine.determineGameLost(), 1 );
        assertEquals( gameEngine.getCurrentPlayer(), 1 );
        selectConfirmDialogOption( JOptionPane.YES_OPTION );
    }


    /**
     * Performs a check and makes sure all conditions are met for this state.
     */
    public void testCheck()
    {
        assertTrue( gameEngine.playerHasLegalMoves( 1 ) );
        assertTrue( gameEngine.playerHasLegalMoves( 2 ) );
        assertEquals( gameEngine.determineGameLost(), 0 );
        showInFrame( panel );
        this.click( board.getCells()[6][3] );
        this.click( board.getCells()[4][3] ); // move pawn above queen 2 north
        this.click( board.getCells()[1][4] );
        this.click( board.getCells()[3][4] );
        assertEquals( gameEngine.determineGameLost(), 0 );

        this.click( board.getCells()[7][2] );
        this.click( board.getCells()[3][6] ); // move bishop 4 north-east
        assertEquals( gameEngine.determineGameLost(), 0 );
        assertTrue( gameEngine.isKingInCheck( true ) ); // check current king
        assertFalse( gameEngine.isKingInCheck( false ) ); // check other king
        assertTrue( gameEngine.playerHasLegalMoves( 1 ) );
        assertTrue( gameEngine.playerHasLegalMoves( 2 ) );
        assertEquals( gameEngine.getCurrentPlayer(), 2 );
        board.getCells()[1][7].getPieceOnSquare().showLegalMoves( board );
        for ( int i = 0; i < board.getCells().length; i++ )
        {
            for ( int j = 0; j < board.getCells()[0].length; j++ )
            {
                assertTrue( !board.getCells()[i][j].getBackground().equals(
                    Color.PINK )
                    && !board.getCells()[i][j].getBackground().equals(
                        Color.YELLOW ) );
            } //makes sure no illegal moves were shown
        }
        assertEquals( board.getCells()[1][7].getPieceOnSquare()
            .calculatePossibleMoves( board )
            .size(), 2 ); //this includes illegal moves
    }


    // ----------------------------------------------------------
    /**
     * Tests the stalemate end-game condition and makes sure all conditions are
     * met properly.
     */
    public void testStalemate()
    {
        assertTrue( gameEngine.playerHasLegalMoves( 1 ) );
        assertTrue( gameEngine.playerHasLegalMoves( 2 ) );
        assertEquals( gameEngine.determineGameLost(), 0 );
        showInFrame( panel );
        board.resetBoard( true ); //we're forcing a reset, game engine will be
                                  //out of sync
        new King( board, 0, 0, 0 );
        new King( board, 5, 5, 1 );
        click( board.getCells()[5][5] );
        click( board.getCells()[4][5] ); //force the game engine to update
        assertEquals( gameEngine.determineGameLost(), -1 );
        selectConfirmDialogOption( JOptionPane.NO_OPTION );
    }


    /**
     * Makes sure invalid clicks are handled properly.
     */
    public void testIllegalMoves()
    {
        showInFrame( panel );
        this.click( board.getCells()[6][0] );
        this.click( board.getCells()[6][0] );
        this.click( board.getCells()[4][2] );
        assertNotNull( this.getComponent( JOptionPane.class ) );
        click( getComponent( JButton.class, where.textIs( "OK" ) ) );
        this.click( board.getCells()[0][2] );
        assertNotNull( this.getComponent( JOptionPane.class ) );
        click( getComponent( JButton.class, where.textIs( "OK" ) ) );

        this.click( board.getCells()[7][1] );
        this.click( board.getCells()[0][0] );
        assertNotNull( this.getComponent( JOptionPane.class ) );
        click( getComponent( JButton.class, where.textIs( "OK" ) ) );
        this.click( board.getCells()[6][1] );
        this.click( board.getCells()[5][1] );

        this.click( board.getCells()[1][1] );
        this.click( board.getCells()[7][7] );

        assertNotNull( this.getComponent( JOptionPane.class ) );
        click( getComponent( JButton.class, where.textIs( "OK" ) ) );
        this.click( board.getCells()[7][7] );
    }
}
