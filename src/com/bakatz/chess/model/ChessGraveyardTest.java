package com.bakatz.chess.model;
import com.bakatz.chess.view.ChessGameBoard;
import com.bakatz.chess.view.ChessGraveyard;

import student.GUITestCase;


// -------------------------------------------------------------------------
/**
 * Tests the ChessGraveyard class.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class ChessGraveyardTest
    extends GUITestCase
{
    private ChessGraveyard grave;


    /**
     * Sets up a default graveyard for use in testing.
     */
    public void setUp()
    {
        grave = new ChessGraveyard( "test graveyard" );
    }


    // ----------------------------------------------------------
    /**
     * Creates a new TestChessGraveyard object.
     */
    public ChessGraveyardTest()
    {
        // unused
    }


    // ----------------------------------------------------------
    /**
     * Tests to make sure the JLabel components are added properly in the
     * addToGraveyard method.
     */
    public void testAddToGraveyard()
    {
        assertEquals( grave.getComponents().length, 1 );
        grave.addPiece( new Pawn( new ChessGameBoard(), 1, 1, 1 ) );
        assertEquals( grave.getComponents().length, 2 );
    }


    // ----------------------------------------------------------
    /**
     * Tests to make sure the JLabel components are cleared properly in the
     * addToGraveyard method.
     */
    public void testClearGraveyard()
    {
        assertEquals( grave.getComponents().length, 1 );
        grave.addPiece( new Pawn( new ChessGameBoard(), 1, 1, 1 ) );
        assertEquals( grave.getComponents().length, 2 );
        grave.clearGraveyard();
        assertEquals( grave.getComponents().length, 1 );
    }

    // ----------------------------------------------------------
    /**
     * Makes sure a piece in the graveyard cannot move out of the graveyard.
     */
    public void testPieceInGraveyard()
    {
        ChessGameBoard cgboard = new ChessGameBoard();
        Queen badQueen = new Queen( cgboard, 1, 1, -1 );
        Pawn badPawn = new Pawn( cgboard, 2, 2, -1 );
        cgboard.clearCell( 1, 1 );
        cgboard.clearCell( 2, 2 );
        grave.addPiece( badQueen );
        grave.addPiece( badPawn );

        badQueen.showLegalMoves( cgboard );
        assertFalse( badQueen.hasLegalMoves( cgboard ) );
        assertFalse( badQueen.canMove( cgboard, 1, 1 ) );
        assertFalse( badQueen.move( cgboard, 1, 1 ) );
        assertEquals( badQueen.calculatePossibleMoves( cgboard ).size(), 0 );

        badPawn.showLegalMoves( cgboard );
        assertFalse( badPawn.hasLegalMoves( cgboard ) );
        assertFalse( badPawn.canMove( cgboard, 1, 1 ) );
        assertFalse( badPawn.move( cgboard, 1, 1 ) );
        assertEquals( badPawn.calculatePossibleMoves( cgboard ).size(), 0 );
    }
}
