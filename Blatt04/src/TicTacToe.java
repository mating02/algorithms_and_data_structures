import java.util.Stack;

/**
 * This class implements and evaluates game situations of a TicTacToe game.
 */
public class TicTacToe {

    /**
     * Returns an evaluation for player at the current board state.
     * Arbeitet nach dem Prinzip der Alphabeta-Suche. Works with the principle of Alpha-Beta-Pruning.
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
     * @return          rating of game situation from player's point of view
     **/
    public static int alphaBeta(Board board, int player)
    {
        // TODO
        // Gebe Bewertung aus Sicht von Player aus
        return player * scorePlayer(board, player, -Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    public static int scorePlayer(Board board, int player, int alpha, int beta){
        if(board.isGameWon() || board.nFreeFields() == 0){
            if(board.isGameWon()){
                return player * (board.nFreeFields() + 1);
            }
            else{
                return 0;
            }
        }
        for(Position move : board.validMoves()){
            board.doMove(move, player);
            int score = - scorePlayer(board, -player, -beta, -alpha);
            board.undoMove(move);
            if(player * score > alpha){
                alpha = player * score;
                if(alpha >= beta) break;
            }
        }
        return alpha;
    }


    /**
     * Vividly prints a rating for each currently possible move out at System.out.
     * (from player's point of view)
     * Uses Alpha-Beta-Pruning to rate the possible moves.
     * formatting: See "Beispiel 1: Bewertung aller Zugmöglichkeiten" (Aufgabenblatt 4).
     *
     * @param board     current Board object for game situation
     * @param player    player who has a turn
     **/
    public static void evaluatePossibleMoves(Board board, int player)
    {
        // TODO
    }

    public static void main(String[] args)
    {
    }
}


