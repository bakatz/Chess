package com.bakatz.chess.model;
import com.bakatz.chess.view.ChessGameBoard;
import com.bakatz.chess.view.ChessPanel;

import student.GUITestCase;


// -------------------------------------------------------------------------
/**
 * Tests the Rook class.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class RookTest
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
     * Creates a new TestRook object.
     */
    public RookTest()
    {
        // not used
    }


    // ----------------------------------------------------------
    /**
     * Tests if the Rook moves around the board properly.
     */
    public void testMovementOfRook()
    {
        Rook rook = (Rook)board.getAllWhitePieces().get( 8 );
        assertTrue( rook.isPieceOnScreen() );
        assertFalse( rook.hasLegalMoves( board ) );
        Pawn p = (Pawn)board.getAllWhitePieces().get( 0 );
        p.move( board, p.getRow() - 2, p.getColumn() );
        assertTrue( rook.hasLegalMoves( board ) );
        assertEquals( rook.getCurrentAttackers( board ).size(), 0 );
        assertTrue( rook.move( board, rook.getRow() - 1, rook.getColumn() ) );
        assertEquals( rook.getCurrentAttackers( board ).size(), 0 );
        assertTrue( rook.hasLegalMoves( board ) );
        assertTrue( rook.isPieceOnScreen() );
        assertTrue( panel.getGameLog().getLastLog().contains( "->" ) );
    }


    // ----------------------------------------------------------
    /**
     * Make sure the Rook's image is correct.
     */
    public void testGetImage()
    {
        Rook rook1 = (Rook)board.getAllWhitePieces().get( 8 );
        assertEquals( rook1.getImage().toString(),
            "chessImages/WhiteRook.gif" );

        Rook rook2 = (Rook)board.getAllBlackPieces().get( 0 );
        assertEquals( rook2.getImage().toString(),
            "chessImages/BlackRook.gif" );

        Rook rook3 = new Rook( board, -1, -1, -1 );
        assertEquals( rook3.getImage().toString(),
            "chessImages/default-Unassigned.gif" );
    }
}
