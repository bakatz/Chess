import student.GUITestCase;


// -------------------------------------------------------------------------
/**
 * Tests the Queen class.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class QueenTest
    extends GUITestCase
{
    private ChessPanel     panel;

    private ChessGameBoard board;


    /**
     * Sets up the test environment.
     */
    public void setUp()
    {
        panel = new ChessPanel();
        board = panel.getGameBoard();
    }


    // ----------------------------------------------------------
    /**
     * Creates a new TestQueen object.
     */
    public QueenTest()
    {
        // not used
    }


    // ----------------------------------------------------------
    /**
     * Tests if the queen moves around the board properly.
     */
    public void testMovementOfQueen()
    {
        Queen queen = (Queen)board.getAllWhitePieces().get( 12 );
        assertTrue( queen.isPieceOnScreen() );
        assertFalse( queen.hasLegalMoves( board ) );
        Pawn p = (Pawn)board.getAllWhitePieces().get( 4 );
        p.move( board, p.getRow() - 2, p.getColumn() );
        assertTrue( queen.hasLegalMoves( board ) );
        assertEquals( queen.getCurrentAttackers( board ).size(), 0 );
        assertTrue( queen.move( board, queen.getRow() - 1,
            queen.getColumn() ) );
        assertEquals( queen.getCurrentAttackers( board ).size(), 0 );
        assertTrue( queen.hasLegalMoves( board ) );
        assertTrue( queen.isPieceOnScreen() );
        assertTrue( panel.getGameLog().getLastLog().contains( "->" ) );
    }


    // ----------------------------------------------------------
    /**
     * Make sure the queen's image is correct.
     */
    public void testGetImage()
    {
        Queen queen1 = (Queen)board.getAllWhitePieces().get( 12 );
        assertEquals(
            queen1.getImage().toString(),
            "chessImages/WhiteQueen.gif" );

        Queen queen2 = (Queen)board.getAllBlackPieces().get( 4 );
        assertEquals(
            queen2.getImage().toString(),
            "chessImages/BlackQueen.gif" );
    }
}
