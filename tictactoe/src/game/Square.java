package game;

/*
 *Immutable datatype representing place(game.X or game.O) on board
 * game.Square = oPlace() + xPlace() + BlankPlace()
 */
public interface Square {
    /*
     * returns game.X object
     */
     Square beX();

    /*
     *return game.O object
     */
     Square beO();


    @Override
    public String toString();

    boolean isitO();

    boolean isitX();

    boolean isitBlank();

}
