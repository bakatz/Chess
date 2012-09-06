import student.TestCase;


/**
 * Tests the BoardSquare class.
 * 
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class BoardSquareTest
    extends TestCase
{
    private BoardSquare    b;

    private ChessGameBoard c;


    // ----------------------------------------------------------
    /**
     * Create a new TestBoardSquare object.
     */
    public BoardSquareTest()
    {
        // unused
    }


    /**
     * Performs initial setup.
     */
    public void setUp()
    {
        b = new BoardSquare( 0, 0, null );
        c = new ChessGameBoard();
    }


    // ----------------------------------------------------------
    /**
     * Tests the getRow method.
     */
    public void testGetRow()
    {
        assertEquals( b.getRow(), 0 );
        b = new BoardSquare( 1, 5, null );
        assertEquals( b.getRow(), 1 );
    }


    // ----------------------------------------------------------
    /**
     * Tests the getColumn method.
     */
    public void testGetColumn()
    {
        assertEquals( b.getColumn(), 0 );
        b = new BoardSquare( 1, 5, null );
        assertEquals( b.getColumn(), 5 );
    }


    // ----------------------------------------------------------
    /**
     * Tests the getPieceOnSquare() method.
     */
    public void testGetPieceOnSquare()
    {
        assertNull( b.getPieceOnSquare() );
        b = new BoardSquare( 1, 5, new Pawn( c, 1, 5, 0 ) );
        assertTrue( b.getPieceOnSquare() instanceof Pawn );
    }


    // ----------------------------------------------------------
    /**
     * Tests the setPieceOnSquare() method.
     */
    public void testSetPieceOnSquare()
    {
        assertNull( b.getPieceOnSquare() );
        b.setPieceOnSquare( new Pawn( c, 1, 5, 0 ) );
        assertTrue( b.getPieceOnSquare() instanceof Pawn );
        assertTrue( b.getComponents().length == 1 ); // JLabel w/ approp. image
        // should be the only component
    }
}
