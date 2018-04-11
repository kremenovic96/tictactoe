package game;

public class Blank implements Square {
    @Override
    public Square beX() {
        return new X();
    }

    @Override
    public Square beO() {
        return new O();
    }


    @Override
    public boolean isitO(){
        return false;
    }

    @Override
    public boolean isitX(){
        return false;
    }

    @Override
    public boolean isitBlank(){
        return true;
    }

    @Override
    public String toString(){
        return "_";
    }
}
