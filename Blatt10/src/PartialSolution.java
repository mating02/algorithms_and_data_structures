import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * ParitialSolution provides at least the functionality which is required
 * for the use in searching for solutions of the game in a search tree.
 * It can store a game situation (Board) and a sequence of mooves.
 */
public class PartialSolution {

    /* TODO */
    /* Add object variables, constructors, methods as required and desired. */
    public Board current;
    public LinkedList<Move> sequence;

    public PartialSolution(Board board){
        this.current = new Board(board);
        this.sequence = new LinkedList<>();
    }
    public PartialSolution(PartialSolution psol){
        this.current = new Board(psol.current);
        this.sequence = new LinkedList<>();
        for(Move move : psol.sequence){
            this.sequence.push(move);
        }
    }

    public void doMove(Move move){
        this.current.doMove(move);
        sequence.add(move);
    }
    /**
     * Return the sequence of moves which resulted in this partial solution.
     *
     * @return The sequence of moves.
     */
    public LinkedList<Move> moveSequence() {
        /* TODO */
        return sequence;
    }

    @Override
    public String toString() {
        String str = "";
        int lastRobot = -1;
        for (Move move : moveSequence()) {
            if (lastRobot == move.iRobot) {
                str += " -> " + move.endPosition;
            } else {
                if (lastRobot != -1) {
                    str += ", ";
                }
                str += "R" + move.iRobot + " -> " + move.endPosition;
            }
            lastRobot = move.iRobot;
        }
        return str;
    }
}


