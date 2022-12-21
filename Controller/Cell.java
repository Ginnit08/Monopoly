package Controller;

public class Cell {
    private int x;
    private int y;
    private int index;
    private int value;
    private String name;

    public Cell(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getIndex() {
        return index;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
