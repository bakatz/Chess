package com.bakatz.chess.model;

import javax.swing.ImageIcon;

import com.bakatz.chess.view.ChessGameBoard;


import java.util.ArrayList;


// import java.awt.Color;

// -------------------------------------------------------------------------
/**
 * Represents a Queen game piece.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class Queen
    extends ChessGamePiece
{

    // ----------------------------------------------------------
    /**
     * Create a new Queen object.
     *
     * @param board
     *            the board the queen is on
     * @param row
     *            the row location of the queen
     * @param col
     *            the column location of the queen
     * @param color
     *            either GamePiece.WHITE, BLACK, or UNASSIGNED
     */
    public Queen( ChessGameBoard board, int row, int col, int color )
    {
        super( board, row, col, color );
    }


    /**
     * Calculates the possible moves for this Queen.
     * @param board the board to check on
     * @return ArrayList<String> the list of moves
     */
    @Override
    protected ArrayList<ChessLocation> calculatePossibleMoves( ChessGameBoard board )
    {
        ArrayList<ChessLocation> northEastMoves = calculateNorthEastMoves( board, 8 );
        ArrayList<ChessLocation> northWestMoves = calculateNorthWestMoves( board, 8 );
        ArrayList<ChessLocation> southEastMoves = calculateSouthEastMoves( board, 8 );
        ArrayList<ChessLocation> southWestMoves = calculateSouthWestMoves( board, 8 );
        ArrayList<ChessLocation> northMoves = calculateNorthMoves( board, 8 );
        ArrayList<ChessLocation> southMoves = calculateSouthMoves( board, 8 );
        ArrayList<ChessLocation> eastMoves = calculateEastMoves( board, 8 );
        ArrayList<ChessLocation> westMoves = calculateWestMoves( board, 8 );

        ArrayList<ChessLocation> allMoves = new ArrayList<ChessLocation>();
        allMoves.addAll( northEastMoves );
        allMoves.addAll( northWestMoves );
        allMoves.addAll( southWestMoves );
        allMoves.addAll( southEastMoves );
        allMoves.addAll( northMoves );
        allMoves.addAll( southMoves );
        allMoves.addAll( westMoves );
        allMoves.addAll( eastMoves );

        return allMoves;
    }


    /**
     * Creates an icon for this piece depending on the piece's color.
     *
     * @return ImageIcon the ImageIcon representation of this piece.
     */
    @Override
    public ImageIcon createImageByPieceType()
    {
        if ( getColorOfPiece() == ChessGamePiece.WHITE )
        {
            return new ImageIcon( "chessImages/WhiteQueen.gif" );
        }
        else if ( getColorOfPiece() == ChessGamePiece.BLACK )
        {
            return new ImageIcon( "chessImages/BlackQueen.gif" );
        }
        else
        {
            return new ImageIcon( "chessImages/default-Unassigned.gif" );
        }
    }

}
