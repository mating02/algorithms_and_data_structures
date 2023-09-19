import java.util.Objects;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // copy constructor
    public Position(Position that) {
        this(that.x, that.y);
    }

    /**
     * Displace the position by the specified values.
     *
     * @param xd Displacement in x-direction
     * @param yd Displacement in y-direction
     */
    public void displace(int xd, int yd) { // Verschiebung um xd/xy
        x += xd;
        y += yd;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        /* TODO */
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position pos = (Position) o;
        if(pos.x == x && pos.y == y){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        /* TODO */
        return Objects.hash(x, y);
    }
}



