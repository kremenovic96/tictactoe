package game;

import java.util.HashMap;
import java.util.Map;

/*
 * Abstract datatype for representing a group of game.Square objects.
 * Abstraction function:
 * Rep invariant:
 */
public class Board {

    /*
     * Class that keeps info about game.Square objects location
     */

    private class Coordinate{
        private final int row;
        private final int col;

        public Coordinate(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode(){
            int hash = 7;
            hash = 71 * hash + this.row;
            hash = 71 * hash + this.col;
            return hash;
        }

        @Override
        public String toString(){
            return Integer.toString(row) + "," + Integer.toString(col);
        }

        @Override
        public boolean equals(Object thatObject){
            if(!(thatObject instanceof Coordinate)) return false;
            Coordinate c = (Coordinate) thatObject;
            return this.row == c.row && this.col == c.col;
        }
    }

    Map<Coordinate, Square> board;
    private final int boardRows;
    private final int boardColumns;
    public Board(int rows, int columns){
        board = new HashMap<>();
        boardRows = rows;
        boardColumns = columns;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                board.put(new Coordinate(i, j), new Blank());
            }
        }

    }

    /*
     * Puts game.X in given location if its blank
     */
    public String putX(int row, int col){
        Coordinate c = new Coordinate(row, col);
        if(board.get(c).isitBlank()){
            board.put(c, board.get(c).beX());

        }
        return (this.whoWon().equals("no winner")) ? this.toString() : this.whoWon();
    }

    /*
     * Puts game.O in given location if its blank
     */
    public String putO(int row, int col){
        Coordinate c = new Coordinate(row, col);
        if(board.get(c).isitBlank()){
            board.put(c, board.get(c).beO());

        }
        return (this.whoWon().equals("no winner")) ? this.toString() : this.whoWon();
    }

    public String whoWon(){
        //first loop search columns for winner
        String s = "initialwhoWon";
        int counter = 1;
        for(int k = 0; k < this.boardColumns; k++){
            counter = 1;
            s = board.get(new Coordinate(0, k)).toString();
            if(s != "_") {
                for (int j = 1; j < this.boardRows; j++) {
                    if (board.get(new Coordinate(j, k)).toString().equals(s)) counter++;
                }
                if (counter == this.boardRows) return s;
                //counter = 1;
            }
        }

        // horizontalno
        for(int k = 0; k < this.boardRows; k++){
            counter = 1;
            s = board.get(new Coordinate(k, 0)).toString();
            if(s != "_") {
                for (int j = 1; j < this.boardColumns; j++) {
                    if (board.get(new Coordinate(k, j)).toString().equals(s)) counter++;
                }
                if (counter == this.boardColumns) return s;
                //counter = 1;
            }
        }

        //dijagonalno od lijevo(gore) ka desno(dolje)
        s = board.get(new Coordinate(0, 0)).toString();
        if(s != "_") {
            counter = 1;
            for (int k = 1; k < this.boardColumns; k++) {
                //s = board.get(new Coordinate(0, 0)).toString();
                if (board.get(new Coordinate(k, k)).toString().equals(s)) counter++;
            }
            if (counter == this.boardRows) return s;
        }

        //dijagnolano od lijevo(dolje) ka desno(gore)
        s = board.get(new Coordinate(this.boardRows - 1, 0)).toString();
        if (s != "_") {
            counter = 1;
            int column = 1;
            for (int k = this.boardRows - 2; k >= 0; k--) {
                //s = board.get(new Coordinate(this.boardRows - 1, 0)).toString();
                if (board.get(new Coordinate(k, column++)).toString().equals(s)) counter++;
            }
            if (counter == this.boardColumns) return s;
        }
       //System.out.println("whoWin ne treba ovo printati");
        return "no winner";
    }


    @Override
    public String toString(){
        String rep = "";
        for(int i = 0; i < this.boardRows; i++){
            for(int j = 0; j < this.boardColumns; j++){
                rep += board.get(new Coordinate(i, j));
                if(j < this.boardColumns - 1) rep += " ";
            }
            if(i != this.boardRows - 1) rep += "\n";

        }
        return rep;
    }


}
