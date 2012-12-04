package com.bakatz.chess.model;
import com.bakatz.chess.view.ChessGameBoard;
import com.bakatz.chess.view.ChessPanel;

import student.TestCase;


// -------------------------------------------------------------------------
/**
 * Tests the Pawn class.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class PawnTest
    extends TestCase
{
    private Pawn           pawn;

    private ChessGameBoard board;

    private ChessPanel     panel;


    // ----------------------------------------------------------
    /**
     * Create a new TestPawn object.
     */
    public PawnTest()
    {
        // not used
    }


    /**
     * Performs initial setup.
     */
    public void setUp()
    {
        panel = new ChessPanel();
        board = panel.getGameBoard();
        pawn = (Pawn)board.getCells()[1][0].getPieceOnSquare();
    }


    // ----------------------------------------------------------
    /**
     * Tests if the pawn moves around the board properly.
     */
    public void testMovementOfPawn()
    {
        assertTrue( pawn.isPieceOnScreen() );
        assertTrue( pawn.hasLegalMoves( board ) );
        Pawn p = (Pawn)board.getAllWhitePieces().get( 4 );
        p.move( board, p.getRow() - 2, p.getColumn() );
        assertTrue( pawn.hasLegalMoves( board ) );
        assertEquals( pawn.getCurrentAttackers( board ).size(), 0 );
        assertTrue( pawn.move( board, pawn.getRow() + 1, pawn.getColumn() ) );
        assertEquals( pawn.getCurrentAttackers( board ).size(), 1 );
        assertTrue( pawn.hasLegalMoves( board ) );
        assertTrue( pawn.isPieceOnScreen() );
        assertTrue( panel.getGameLog().getLastLog().contains( "->" ) );
    }


    // ----------------------------------------------------------
    /**
     * Tests if the pawn gets promoted to a queen when it goes to the end of the
     * board.
     */
    public void testPawnPromotion()
    {
        board.resetBoard( true );
        board.getCells()[1][0].setPieceOnSquare( new Pawn(
            board,
            1,
            0,
            ChessGamePiece.WHITE ) );
        board.getCells()[1][0].getPieceOnSquare().move( board, 0, 0 );
        assertTrue(
            board.getCells()[0][0].getPieceOnSquare() instanceof Queen );
    }


    // ----------------------------------------------------------
    /**
     * Make sure the pawn's image is correct.
     */
    public void testGetImage()
    {
        Pawn pawn1 = (Pawn)board.getAllWhitePieces().get( 7 );
        assertEquals( pawn1.getImage().toString(),
            "chessImages/WhitePawn.gif" );

        Pawn pawn2 = (Pawn)board.getAllBlackPieces().get( 11 );
        assertEquals( pawn2.getImage().toString(),
            "chessImages/BlackPawn.gif" );

        Pawn pawn3 = new Pawn(board, 1, 2, -1);
        assertEquals( pawn3.getImage().toString(),
            "chessImages/default-Unassigned.gif" );
    }
}
