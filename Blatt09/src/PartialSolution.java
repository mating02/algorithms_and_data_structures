import java.util.Stack;

/**
 * PartialSolution is a class which represents a state of the game
 * from its initial state to its solution. It includes the current
 * state of the board and the move sequence from the initial state
 * to the current state.</br>
 * For the use in the A*-algorithm, the class implements Comparable
 * wrt the cost of the number of moves + heuristic.</br>
 * For the heuristic and game functionality, the respective methods
 * of class {@link Board} are used.
 */
public class PartialSolution implements Comparable<PartialSolution> {

    int cost;
    Board current;
    Stack<Move> sequence;

    /**
     * Constructor, generates an empty solution based on the provided
     * <em>board</em> with an empty move sequence.
     *
     * @param board initial state of the board
     */
    public PartialSolution(Board board) {
        // TODO 1.2 PartialSolution(board)
        this.cost = board.manhattan();
        Board bo = new Board(board);
        this.current = bo;
        this.sequence = new Stack<>();
    }

    /**
     * Copy constructor, generates a deep copy of the input
     *
     * @param that The partial solution that is to be copied
     */
    public PartialSolution(PartialSolution that) {
        // TODO 1.2 PartialSolution(PartialSolution)
        this.cost = that.cost;
        Board bo = new Board(that.current);
        this.current = bo;
        this.sequence = new Stack<>();
        for(Move move : that.sequence){
            this.sequence.push(move);
        }
    }

    /**
     * Performs a move on the board of the partial solution and updates
     * the cost.
     *
     * @param move The move that is to be performed
     */
    public void doMove(Move move) {
        // TODO 1.2 doMove
        if(current.checkMove(move)){
            current.doMove(move);
            sequence.add(move);
            cost = current.manhattan() + sequence.size();
        }
    }

    /**
     * Tests whether the solution has been reached, i.e. whether
     * current board is in the goal state.
     *
     * @return {@code true}, if the board is in goal state
     */
    public boolean isSolution() {
        // TODO 1.2 isSolution
        if(current.isSolved()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Return the sequence of moves which leads from the initial board
     * to the current state.
     *
     * @return move sequence leading to this state of solution
     */
    public Iterable<Move> moveSequence() {
        // TODO 1.2 moveSequence
        return sequence;
    }

    /**
     * Generates all possible moves on the current board, <em>except</em>
     * the move which would undo the previous move (if there is one).
     *
     * @return moves to be considered in the current situation
     */
    public Iterable<Move> validMoves() {
        // TODO 1.2 validMoves
        if(sequence.size() == 0){
            return current.validMoves();
        }
        return current.validMoves(sequence.peek());
    }

    /**
     * Compares partial solutions based on their cost.
     * (For performance reasons, the costs should be pre-calculated
     * and stored for each partial solution, rather than computed
     * here each time anew.)
     *
     * @param that the other partial solution
     * @return result of cost comparistion between this and that
     */
    public int compareTo(PartialSolution that) {
        // TODO 1.2 compareTo
        return Integer.compare(this.cost, that.cost);
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder("Partial solution with moves: \n");
        for (Move move : moveSequence()) {
            msg.append(move).append(", ");
        }
        return msg.substring(0, msg.length() - 2);
    }


    public static void main(String[] args) {
        String filename = "src/board-3x3-twosteps.txt";
        Board board = new Board(filename);
        PartialSolution psol = new PartialSolution(board);
        psol.doMove(new Move(new Position(2, 1), 2));
        psol.doMove(new Move(new Position(1, 1), 1));
        psol.doMove(new Move(new Position(0, 1), 1));
        psol.doMove(new Move(new Position(0, 2), 0));
        psol.doMove(new Move(new Position(1, 2), 3));
        psol.doMove(new Move(new Position(1, 1), 2));
        psol.doMove(new Move(new Position(2, 1), 3));
        psol.doMove(new Move(new Position(2, 2), 0));
        AStar15Puzzle.printBoardSequence(board, psol.moveSequence());
    }
}


