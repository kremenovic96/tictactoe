package game;

public class X implements Square {
    @Override
    public Square beX() {
        return this;
    }

    @Override
    public Square beO() {
        return this;
    }

    @Override
    public boolean isitX(){
        return true;
    }

    @Override
    public boolean isitO(){
        return false;
    }

    @Override
    public boolean isitBlank(){
        return false;
    }

    @Override
    public String toString(){
        return "X";
    }
}
