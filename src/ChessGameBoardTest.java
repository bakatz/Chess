import java.awt.Color;
import student.GUITestCase;


/**
 * Tests the BoardSquare class.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class ChessGameBoardTest
    extends GUITestCase
{
    private ChessGameBoard  board;

    private BoardSquare[][] cells;


    // ----------------------------------------------------------
    /**
     * Create a new TestChessGameBoard object.
     */
    public ChessGameBoardTest()
    {
        // unused
    }


    /**
     * Performs initial setup.
     */
    public void setUp()
    {
        board = new ChessGameBoard();
        cells = board.getCells();
    }


    // ----------------------------------------------------------
    /**
     * Tests the constructor and the initial setup.
     */
    public void testInitialSetupPerformedCorrectly()
    {
        assertTrue( cells[0][0].getPieceOnSquare() instanceof Rook );
        assertTrue( cells[1][0].getPieceOnSquare() instanceof Pawn );
        assertTrue( cells[1][0].getPieceOnSquare() instanceof Pawn );


        int numBlacks = board.getAllBlackPieces().size();
        int numWhites = board.getAllWhitePieces().size();



        assertEquals( 32, numBlacks + numWhites );
        assertEquals( 16, numWhites );
        assertEquals( 16, numBlacks );
    }


    // ----------------------------------------------------------
    /**
     * Tests the clearColorsOnBoard method.
     */
    public void testClearColorsOnBoard()
    {
        for ( int i = 0; i < cells.length; i++ )
        {
            for ( int j = 0; j < cells[0].length; j++ )
            {
                assertTrue( cells[i][j].getBackground().equals( Color.BLACK ) ||
                    cells[i][j].getBackground().equals( Color.WHITE ) );
            }
        }

        cells[0][0].setBackground( Color.PINK );
        cells[5][4].setBackground( Color.GREEN );
        board.clearColorsOnBoard();

        for ( int i = 0; i < cells.length; i++ )
        {
            for ( int j = 0; j < cells[0].length; j++ )
            {
                assertTrue( cells[i][j].getBackground().equals( Color.BLACK ) ||
                    cells[i][j].getBackground().equals( Color.WHITE ) );
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Tests the click listener to make sure bad clicks are handled properly
     */
    public void testMouseListenerBadClicks()
    {
        showInFrame( board );
        assertNotSame( cells[7][7].getBackground(), Color.GREEN );
        this.click( board, 50, 50 ); // bad move
        assertNotSame( cells[0][0].getBackground(), Color.GREEN );
    }


    // ----------------------------------------------------------
    /**
     * Tests the getAllWhitePieces method.
     */
    public void testGetWhitePieces()
    {
        assertEquals( board.getAllWhitePieces().size(), 16 );
        board.getCells()[7][7].clearSquare();
        assertEquals( board.getAllWhitePieces().size(), 15 );
    }


    // ----------------------------------------------------------
    /**
     * Tests the getAllBlackPieces method.
     */
    public void testGetBlackPieces()
    {
        assertEquals( board.getAllBlackPieces().size(), 16 );
        board.getCells()[0][0].clearSquare();
        assertEquals( board.getAllBlackPieces().size(), 15 );
    }


    // ----------------------------------------------------------
    /**
     * Tests the clearBoard method.
     */
    public void testClearBoard()
    {
        assertEquals( board.getAllBlackPieces().size(), 16 );
        assertEquals( board.getAllBlackPieces().size(), 16 );
        board.resetBoard( false );
        assertEquals( board.getAllBlackPieces().size(), 0 );
        assertEquals( board.getAllBlackPieces().size(), 0 );
    }

    // ----------------------------------------------------------
    /**
     * Tests to make sure getCell returns null if bad arguments are given,
     * and that it returns the right cell if valid arguments are given.
     */
    public void testGetCell()
    {
        assertNull ( board.getCell( -1, -1 ) );
        assertNull ( board.getCell( 9, 9 ) );
        assertNull ( board.getCell( 8, 8 ) );
        assertNotNull ( board.getCell( 0, 0 ) );
        assertNotNull ( board.getCell( 7, 7 ) );
        assertNotNull ( board.getCell( 2, 3 ) );
    }

    // ----------------------------------------------------------
    /**
     * Tests to make sure clearCell returns null if bad arguments are given,
     * and that it returns the right cell if valid arguments are given.
     */
    public void testClearCell()
    {
        Exception caughtEx = null;
        try
        {
            board.clearCell( -1, -1 );
        }
        catch ( Exception ex)
        {
            caughtEx = ex;
        }
        assertTrue( caughtEx instanceof IllegalStateException );

        caughtEx = null;
        try
        {
            board.clearCell( 9, 9 );
        }
        catch ( Exception ex )
        {
            caughtEx = ex;
        }
        assertTrue( caughtEx instanceof IllegalStateException );

        assertNotNull( board.getCell( 0, 0 ).getPieceOnSquare() );
        assertNotNull( board.getCell( 7, 7 ).getPieceOnSquare() );
        assertNull( board.getCell( 2, 3 ).getPieceOnSquare() );

        board.clearCell( 0, 0 );
        board.clearCell( 7, 7 );
        board.clearCell( 2, 3 );

        assertNull( board.getCell( 0, 0 ).getPieceOnSquare() );
        assertNull( board.getCell( 7, 7 ).getPieceOnSquare() );
        assertNull( board.getCell( 2, 3 ).getPieceOnSquare() );
    }
}
