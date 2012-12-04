package com.bakatz.chess.model;

import javax.swing.ImageIcon;

import com.bakatz.chess.view.ChessGameBoard;


import java.util.ArrayList;


// -------------------------------------------------------------------------
/**
 * Represents a Rook game piece.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class Rook
    extends ChessGamePiece
{

    // private ArrayList<String> possibleMoves;

    // ----------------------------------------------------------
    /**
     * Create a new Rook object.
     *
     * @param board
     *            the board to create the rook on
     * @param row
     *            the row to create the rook on
     * @param col
     *            the column to create the rook on
     * @param color
     *            either GamePiece.WHITE, BLACK, or UNASSIGNED
     */
    public Rook( ChessGameBoard board, int row, int col, int color )
    {
        super( board, row, col, color );
    }


    /**
     * Calculates the possible moves for this Rook.
     * @param board the board to check on
     * @return ArrayList<String> the list of moves
     */
    @Override
    protected ArrayList<ChessLocation> calculatePossibleMoves( ChessGameBoard board )
    {
        ArrayList<ChessLocation> northMoves = calculateNorthMoves( board, 8 );
        ArrayList<ChessLocation> southMoves = calculateSouthMoves( board, 8 );
        ArrayList<ChessLocation> westMoves = calculateWestMoves( board, 8 );
        ArrayList<ChessLocation> eastMoves = calculateEastMoves( board, 8 );

        ArrayList<ChessLocation> allMoves = new ArrayList<ChessLocation>();
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
            return new ImageIcon( "chessImages/WhiteRook.gif" );
        }
        else if ( getColorOfPiece() == ChessGamePiece.BLACK )
        {
            return new ImageIcon( "chessImages/BlackRook.gif" );
        }
        else
        {
            return new ImageIcon( "chessImages/default-Unassigned.gif" );
        }
    }
}
