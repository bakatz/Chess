import java.util.ArrayList;
import student.GUITestCase;


// -------------------------------------------------------------------------
/**
 * Tests the Knight class.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class KnightTest
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
     * Creates a new TestKnight object.
     */
    public KnightTest()
    {
        // not used
    }


    // ----------------------------------------------------------
    /**
     * Tests if the knight moves around the board properly.
     */
    public void testMovementOfKnight()
    {
        Knight knight = (Knight)board.getAllWhitePieces().get( 9 );
        assertTrue( knight.isPieceOnScreen() );
        assertTrue( knight.hasLegalMoves( board ) );
        Pawn p = (Pawn)board.getAllWhitePieces().get( 4 );
        p.move( board, p.getRow() - 2, p.getColumn() );
        assertTrue( knight.hasLegalMoves( board ) );
        assertEquals( knight.getCurrentAttackers( board ).size(), 0 );
        ArrayList<String> moves = knight.calculatePossibleMoves( board );
        int moveRow = Integer.parseInt( moves.get( 0 ).split( "," )[0] );
        int moveCol = Integer.parseInt( moves.get( 0 ).split( "," )[1] );
        assertTrue( knight.move( board, moveRow, moveCol ) );
        assertEquals( knight.getCurrentAttackers( board ).size(), 0 );
        assertTrue( knight.hasLegalMoves( board ) );
        assertTrue( knight.isPieceOnScreen() );
        assertTrue( panel.getGameLog().getLastLog().contains( "->" ) );
    }

    // ----------------------------------------------------------
    /**
     * Tests to make sure an invalid knight is not placed on the board.
     */
    public void testBadKnight()
    {
        Knight knight = new Knight(board, -1, -1, -1);
        assertFalse( knight.isPieceOnScreen() );
    }


    // ----------------------------------------------------------
    /**
     * Make sure the knight's image is correct.
     */
    public void testGetImage()
    {
        Knight knight1 = (Knight)board.getAllWhitePieces().get( 9 );
        assertEquals(
            knight1.getImage().toString(),
            "chessImages/WhiteKnight.gif" );

        Knight knight2 = (Knight)board.getAllBlackPieces().get( 1 );
        assertEquals(
            knight2.getImage().toString(),
            "chessImages/BlackKnight.gif" );

        Knight knight3 = new Knight(board, 1, 2, 498734698);
        //fake king
        assertEquals( knight3.getImage().toString(),
            "chessImages/default-Unassigned.gif" );
    }
}
