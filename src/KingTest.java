import student.GUITestCase;


// -------------------------------------------------------------------------
/**
 * Tests the King class.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class KingTest
    extends GUITestCase
{
    private ChessPanel     panel;
    private King king;
    private Pawn pawn;
    private Pawn attackingPawn;
    private ChessGameBoard board;


    /**
     * Performs initial setup.
     */
    public void setUp()
    {
        panel = new ChessPanel();
        board = panel.getGameBoard();
        king = (King)board.getCells()[0][3].getPieceOnSquare();
        pawn = (Pawn)board.getCells()[1][3].getPieceOnSquare();
        attackingPawn = (Pawn)board.getCells()[6][2].getPieceOnSquare();
    }


    // ----------------------------------------------------------
    /**
     * Creates a new TestKing object.
     */
    public KingTest()
    {
        // not used
    }


    // ----------------------------------------------------------
    /**
     * Tests if the King moves around the board properly.
     */
    public void testMovementOfKing()
    {
        king = (King)board.getAllWhitePieces().get( 11 );
        assertTrue( king.isPieceOnScreen() );
        assertFalse( king.hasLegalMoves( board ) );
        Pawn p = (Pawn)board.getAllWhitePieces().get( 4 );
        p.move( board, p.getRow() - 2, p.getColumn() );
        assertTrue( king.hasLegalMoves( board ) );
        assertEquals( king.getCurrentAttackers( board ).size(), 0 );
        assertTrue( king.move( board, king.getRow() - 1,
            king.getColumn() + 1 ) );
        assertEquals( king.getCurrentAttackers( board ).size(), 0 );
        assertTrue( king.hasLegalMoves( board ) );
        assertTrue( king.isPieceOnScreen() );
        assertTrue( panel.getGameLog().getLastLog().contains( "->" ) );
    }


    // ----------------------------------------------------------
    /**
     * Make sure the King's image is correct.
     */
    public void testGetImage()
    {
        King king1 = (King)board.getAllWhitePieces().get( 11 );
        assertEquals( king1.getImage().toString(),
            "chessImages/WhiteKing.gif" );

        King king2 = (King)board.getAllBlackPieces().get( 3 );
        assertEquals( king2.getImage().toString(),
            "chessImages/BlackKing.gif" );

        King king3 = new King(board, -1, -1, 498734698);
        //fake king
        assertEquals( king3.getImage().toString(),
            "chessImages/default-Unassigned.gif" );
    }


    // ----------------------------------------------------------
    /**
     * Tests if the King is on the screen.
     */
    public void testKingIsOnScreen()
    {
        assertTrue(king.isPieceOnScreen());
    }

 // ----------------------------------------------------------
    /**
     * Tests that the moves being shown are valid.
     */
    public void testPossibleMoves()
    {
        pawn.move(board, 3, 3);
        pawn.move(board, 4, 3);
        pawn.move(board, 5, 3);
        attackingPawn.move(board, 5, 3);
        assertTrue(
            king.calculatePossibleMoves(board).indexOf(1 + "," + 3) >= 0);

        king.move(board, 1, 3);
        assertTrue(
            king.calculatePossibleMoves(board).indexOf(2 + "," + 3) >= 0);
        assertTrue(
            king.calculatePossibleMoves(board).indexOf(2 + "," + 2) >= 0);
        assertTrue(
            king.calculatePossibleMoves(board).indexOf(2 + "," + 4) >= 0);

        king.move(board, 2, 3);
        king.move(board, 3, 3);
        king.move(board, 4, 3);

        assertTrue(
            king.calculatePossibleMoves(board).indexOf(5 + "," + 3) >= 0);
        king.move(board, 5, 3);

        assertEquals(king, board.getCells()[5][3].getPieceOnSquare());
    }

    // ----------------------------------------------------------
    /**
     * Tests that the King cannot move on top of another piece
     */
    public void testInvalidMoves()
    {
        assertFalse(
            king.calculatePossibleMoves(board).indexOf(1 + "," + 3) >= 0);
        assertFalse(
            king.calculatePossibleMoves(board).indexOf(1 + "," + 2) >= 0);
        assertFalse(
            king.calculatePossibleMoves(board).indexOf(1 + "," + 4) >= 0);
        assertFalse(
            king.calculatePossibleMoves(board).indexOf(0 + "," + 2) >= 0);
        assertFalse(
            king.calculatePossibleMoves(board).indexOf(0 + "," + 4) >= 0);
    }

    // ----------------------------------------------------------
    /**
     * Tests attacking when enemy is diagonally forward
     */
    public void testMoveWhenAttackingOne()
    {
        pawn.move(board, 3, 3);
        pawn.move(board, 4, 3);
        pawn.move(board, 5, 3);
        attackingPawn.move(board, 5, 3);
        king.move(board, 1, 3);
        king.move(board, 2, 3);
        king.move(board, 3, 3);
        king.move(board, 4, 3);
        king.move(board, 4, 2);
        assertTrue(
            king.calculatePossibleMoves(board).indexOf(5 + "," + 3) >= 0);
        king.move(board, 5, 3);
    }

    // ----------------------------------------------------------
    /**
     * Tests attacking when enemy is straight ahead forward
     */
    public void testMoveWhenAttackingTwo()
    {
        pawn.move(board, 3, 3);
        pawn.move(board, 4, 3);
        pawn.move(board, 5, 3);
        attackingPawn.move(board, 5, 3);
        king.move(board, 1, 3);
        king.move(board, 2, 3);
        king.move(board, 3, 3);
        king.move(board, 4, 3);
        assertTrue(
            king.calculatePossibleMoves(board).indexOf(5 + "," + 3) >= 0);
    }

    // ----------------------------------------------------------
    /**
     * Tests attacking when enemy is diagonally backward
     */
    public void testMoveWhenAttackingThree()
    {
        pawn.move(board, 3, 3);
        pawn.move(board, 4, 3);
        pawn.move(board, 5, 3);
        attackingPawn.move(board, 5, 3);
        king.move(board, 1, 3);
        king.move(board, 2, 3);
        king.move(board, 3, 3);
        king.move(board, 4, 3);
        king.move(board, 4, 2);
        attackingPawn.move(board, 4, 3);
        attackingPawn.move(board, 3, 3);
        assertTrue(
            king.calculatePossibleMoves(board).indexOf(3 + "," + 3) >= 0);
    }

    // ----------------------------------------------------------
    /**
     * Tests attacking when enemy is straight ahead backward
     */
    public void testMoveWhenAttackingFour()
    {
        pawn.move(board, 3, 3);
        pawn.move(board, 4, 3);
        pawn.move(board, 5, 3);
        attackingPawn.move(board, 5, 3);
        king.move(board, 1, 3);
        king.move(board, 2, 3);
        king.move(board, 3, 3);
        king.move(board, 4, 3);
        king.move(board, 4, 2);
        attackingPawn.move(board, 4, 3);
        attackingPawn.move(board, 3, 3);
        king.move(board, 4, 3);
        assertTrue(
            king.calculatePossibleMoves(board).indexOf(3 + "," + 3) >= 0);
    }
}
