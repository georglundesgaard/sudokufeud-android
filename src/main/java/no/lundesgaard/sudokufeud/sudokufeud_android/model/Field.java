package no.lundesgaard.sudokufeud.sudokufeud_android.model;

public class Field {

    private Integer value;
    private boolean locked;
    private int position;

    public Field(Integer value, boolean locked, int position) {
        this.value = value;
        this.locked = locked;
        this.position = position;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isLocked() {
        return locked;
    }

    public int getPosition() {
        return position;
    }
}
