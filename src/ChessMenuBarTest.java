import javax.swing.JOptionPane;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import student.GUITestCase;


// -------------------------------------------------------------------------
/**
 * Tests the ChessMenuBar class.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class ChessMenuBarTest
    extends GUITestCase
{
    private ChessPanel panel;


    /**
     * Sets up a default panel for use in testing.
     */
    public void setUp()
    {
        panel = new ChessPanel();
    }


    // ----------------------------------------------------------
    /**
     * Creates a new TestChessMenuBar object.
     */
    public ChessMenuBarTest()
    {
        // not used
    }


    // ----------------------------------------------------------
    /**
     * Tests the file menu.
     */
    public void testFileMenu()
    {
        showInFrame( panel );
        ChessGameBoard board = panel.getGameBoard();
        JMenu file = this.getComponent( JMenu.class, where.textIs( "File" ) );
        this.click( board.getCells()[6][0] );
        this.click( board.getCells()[4][0] );
        this.click( file );
        JMenuItem newGame =
            this.getComponent(
                JMenuItem.class,
                where.textIs( "New game/restart" ) );
        this.click( newGame );
        assertTrue( panel.getGameLog().getLastLog().contains( "chess game" ) );

        JMenuItem exit = this.getComponent( JMenuItem.class,
            where.textIs( "Exit" ) );
        this.click( file );
        this.click( exit );
        assertNotNull( this.getComponent( JOptionPane.class ) );
    }


    // ----------------------------------------------------------
    /**
     * Tests the options menu.
     */
    public void testOptionsMenu()
    {
        showInFrame( panel );
        ChessGameBoard board = panel.getGameBoard();
        JMenu options =
            this.getComponent( JMenu.class, where.textIs( "Options" ) );
        this.click( board.getCells()[6][0] );
        this.click( board.getCells()[4][0] );
        this.click( options );
        JMenuItem toggleGraveyard =
            this.getComponent(
                JMenuItem.class,
                where.textIs( "Toggle graveyard" ) );
        JMenuItem toggleGameLog =
            this.getComponent(
                JMenuItem.class,
                where.textIs( "Toggle game log" ) );
        this.click( toggleGraveyard );
        assertFalse( panel.getGraveyard( 1 ).isVisible() );
        assertFalse( panel.getGraveyard( 2 ).isVisible() );
        this.click( options );
        this.click( toggleGameLog );
        assertFalse( panel.getGameLog().isVisible() );
    }


    // ----------------------------------------------------------
    /**
     * Tests the help menu.
     */
    public void testHelpMenu()
    {
        showInFrame( panel );
        JMenu help = this.getComponent( JMenu.class, where.textIs( "Help" ) );
        this.click( help );
        JMenuItem about =
            this.getComponent( JMenuItem.class, where.textIs( "About" ) );
        this.click( about );
        assertTrue( this.getComponent( JOptionPane.class ).isVisible() );
    }
}
