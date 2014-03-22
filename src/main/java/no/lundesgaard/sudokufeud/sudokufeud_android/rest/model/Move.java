package no.lundesgaard.sudokufeud.sudokufeud_android.rest.model;

public class Move {
    private int x;
    private int y;
    private int piece;

    public Move(int x, int y, int piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }
}
