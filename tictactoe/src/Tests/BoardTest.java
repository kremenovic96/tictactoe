package Tests;

import static org.junit.Assert.assertEquals;

import game.Board;
import org.junit.Test;
public class BoardTest {

    @Test(expected = AssertionError.class)
        public void testAssertionsEnabled(){
            assert false;
        }

/*
 * Testing strategy:
 *  make a 3x3 and 4x4 boards and check their initial toString value
 */
    @Test
    public void testInitialState(){
        Board b = new Board(3,3);
        String actualString = b.toString();
        String coorectString = "_ _ _\n_ _ _\n_ _ _";
        assertEquals(coorectString, actualString);
        b = new Board(4, 4);
        actualString = b.toString();
        coorectString = "_ _ _ _\n_ _ _ _\n_ _ _ _\n_ _ _ _";
        assertEquals(coorectString, actualString);

    }

    @Test
    public void testputX(){
        Board b = new Board(3, 3);
        b.putX(0, 1);
        String actualString = b.toString();
        String correctString = "_ X _\n_ _ _\n_ _ _";
        assertEquals(correctString, actualString);
        actualString = b.putX(0, 0);
        correctString = "X X _\n_ _ _\n_ _ _";
        assertEquals(correctString, actualString);
        actualString = b.putX(2, 1);
        correctString = "X X _\n_ _ _\n_ X _";
        assertEquals(correctString, actualString);
        actualString = b.putX(2, 2);
        correctString = "X X _\n_ _ _\n_ X X";
        assertEquals(correctString, actualString);
        actualString = b.putX(1, 0);
        correctString = "X X _\nX _ _\n_ X X";
        assertEquals(correctString, actualString);
    }

    @Test
    public void testputO(){
        Board b = new Board(3, 3);
        b.putO(0, 1);
        String actualString = b.toString();
        String correctString = "_ O _\n_ _ _\n_ _ _";
        assertEquals(correctString, actualString);
        actualString = b.putO(0, 0);
        correctString = "O O _\n_ _ _\n_ _ _";
        assertEquals(correctString, actualString);
        actualString = b.putO(2, 1);
        correctString = "O O _\n_ _ _\n_ O _";
        assertEquals(correctString, actualString);
        actualString = b.putO(2, 2);
        correctString = "O O _\n_ _ _\n_ O O";
        assertEquals(correctString, actualString);
        actualString = b.putO(1, 0);
        correctString = "O O _\nO _ _\n_ O O";
        assertEquals(correctString, actualString);
    }

    @Test
    public void testWinXVertically(){
        Board b = new Board(3, 3);
        b.putX(0, 0);
        b.putX(1, 0);
        b.putX(2, 0);
        String correctWinner = "X";
        String actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
        b = new Board(3, 3);
        b.putX(0, 1);
        b.putX(1, 1);
        b.putX(2, 1);
        actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
        b = new Board(3, 3);
        b.putX(0, 2);
        b.putX(1, 2);
        b.putX(2, 2);
        actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
        }

    @Test
    public void testWinXHorizontally(){
        Board b = new Board(3, 3);
        b.putX(0, 0);
        b.putX(0, 1);
        b.putX(0, 2);
        String correctWinner = "X";
        String actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
        b = new Board(3, 3);
        b.putX(1, 0);
        b.putX(1, 1);
        b.putX(1, 2);
        actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
        b = new Board(3, 3);
        b.putX(2, 0);
        b.putX(2, 1);
        b.putX(2, 2);
        actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
    }

    /*
     * test 3x3 both diagonals
     */
    @Test
    public void testWinXDiagonally(){
        Board b = new Board(3, 3);
        b.putX(0, 0);
        b.putX(1, 1);
        b.putX(2, 2);
        String correctWinner = "X";
        String actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
        b = new Board(3, 3);
        b.putX(0, 2);
        b.putX(1, 1);
        assertEquals(correctWinner, b.putX(2, 0));
        actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
    }

    @Test
    public void testWinOVertically(){
        Board b = new Board(3, 3);
        b.putO(0, 0);
        b.putO(1, 0);
        b.putO(2, 0);
        String correctWinner = "O";
        String actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
        b = new Board(3, 3);
        b.putO(0, 1);
        b.putO(1, 1);
        b.putO(2, 1);
        actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
        b = new Board(3, 3);
        b.putO(0, 2);
        b.putO(1, 2);
        assertEquals(correctWinner, b.putO(2, 2));
        actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
    }

    @Test
    public void testWinOHorizontally(){
        Board b = new Board(3, 3);
        b.putO(0, 0);
        b.putO(0, 1);
        b.putO(0, 2);
        String correctWinner = "O";
        String actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
        b = new Board(3, 3);
        b.putO(1, 0);
        b.putO(1, 1);
        b.putO(1, 2);
        actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
        b = new Board(3, 3);
        b.putO(2, 0);
        b.putO(2, 1);
        assertEquals(correctWinner, b.putO(2, 2));
        actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
    }

    /*
    *Test 3x3 both diagonals
    */
    @Test
    public void testWinODiagonally(){
        Board b = new Board(3, 3);
        b.putO(0, 0);
        b.putO(1, 1);
        b.putO(2, 2);
        String correctWinner = "O";
        String actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
        b = new Board(3, 3);
        b.putO(0, 2);
        b.putO(1, 1);
        assertEquals(correctWinner, b.putO(2, 0));
        actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
    }

    @Test
    public void testNoWinner(){
        Board b = new Board(3, 3);
        b.putX(0, 1);
        String correctWinner = "no winner";
        String actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
        b.putX(0,2);
        b.putX(2, 2);
        actualWinner = b.whoWon();
        assertEquals(correctWinner, actualWinner);
    }


}
