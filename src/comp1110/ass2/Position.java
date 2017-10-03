package comp1110.ass2;

/**
 * Created by Alex on 29/9/17.
 */
public class Position {
    boolean ontop = false;
    boolean engaged = false;

    public Position(boolean ontop) {
        this.ontop = ontop;
    }

    public Position(boolean ontop, boolean engaged) {
        this.ontop = ontop;
        this.engaged = engaged;
    }

}
