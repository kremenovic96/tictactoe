package game;

public class O implements Square {
    @Override
    public Square beX() {
        return this;
    }

    @Override
    public Square beO() {
        return this;
    }

    @Override
    public boolean isitO(){
        return true;
    }

    @Override
    public boolean isitX(){
        return false;
    }

    @Override
    public boolean isitBlank(){
        return false;
    }

    @Override
    public String toString(){
        return "O";
    }
}
