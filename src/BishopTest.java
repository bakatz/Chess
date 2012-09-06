import student.GUITestCase;


// -------------------------------------------------------------------------
/**
 * Tests the Bishop class
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class BishopTest
    extends GUITestCase
{
    private ChessPanel     panel;

    private ChessGameBoard board;


    /**
     * Sets up the test harness by initializing the panel and board.
     */
    public void setUp()
    {
        panel = new ChessPanel();
        board = panel.getGameBoard();
    }


    // ----------------------------------------------------------
    /**
     * Creates a new TestBishop object.
     */
    public BishopTest()
    {
        // not used
    }


    // ----------------------------------------------------------
    /**
     * Tests if the bishop moves around the board properly.
     */
    public void testMovementOfBishop()
    {
        Bishop bish = (Bishop)board.getAllWhitePieces().get( 10 );
        assertTrue( bish.isPieceOnScreen() );
        assertFalse( bish.hasLegalMoves( board ) );
        Pawn p = (Pawn)board.getAllWhitePieces().get( 3 );
        p.move( board, p.getRow() - 2, p.getColumn() );
        assertTrue( bish.hasLegalMoves( board ) );
        assertEquals( bish.getCurrentAttackers( board ).size(), 0 );
        assertTrue( bish.move( board, bish.getRow() - 5,
            bish.getColumn() + 5 ) );
        assertEquals( bish.getCurrentAttackers( board ).size(), 2 );
        assertTrue( bish.hasLegalMoves( board ) );
        assertTrue( bish.isPieceOnScreen() );
        assertTrue( panel.getGameLog().getLastLog().contains( "->" ) );
    }


    // ----------------------------------------------------------
    /**
     * Make sure the bishop's image is correct.
     */
    public void testGetImage()
    {
        Bishop bish1 = (Bishop)board.getAllWhitePieces().get( 10 );
        assertEquals(
            bish1.getImage().toString(),
            "chessImages/WhiteBishop.gif" );

        Bishop bish2 = (Bishop)board.getAllBlackPieces().get( 2 );
        assertEquals(
            bish2.getImage().toString(),
            "chessImages/BlackBishop.gif" );

        Bishop bish3 = new Bishop( board, 1, 2, 4 );
        assertEquals(
            bish3.getImage().toString(),
            "chessImages/default-Unassigned.gif" );
    }
}
