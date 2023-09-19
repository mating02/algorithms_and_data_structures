import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Stack;

import static java.lang.Math.abs;
/**
 * This class represents a generic TicTacToe game board.
 */
public class Board {
    private int n;
    //TODO
    private int[][] field;
    public int freefields;
    /**
     *  Creates Board object, am game board of size n * n with 1<=n<=10.
     */
    public Board(int n)
    {
        // TODO
        if(n > 10 || n <= 0){
            throw new InputMismatchException("Not a legitimate input. Board can not be initialised with dimension n x n.");
        }
        else {
            this.n = n;
            this.field = new int[n][n];
            this.freefields = n*n;
            for(int i = 0; i < n; i++) {
                Arrays.fill(field[i], 0);
            }
        }
    }

    /**
     *  @return     length/width of the Board object
     */
    public int getN() { return n; }

    /**
     *  @return     number of currently free fields
     */
    public int nFreeFields() {
        // TODO
        return this.freefields;
    }

    /**
     *  @return     token at position pos
     */
    public int getField(Position pos) throws InputMismatchException
    {
        // TODO
        if(pos.x >= n || pos.x < 0 || pos.y >= n || pos.y < 0){
            throw new InputMismatchException("Ungültige Position.");
        }
        return field[pos.x][pos.y];
    }

    /**
     *  Sets the specified token at Position pos.
     */
    public void setField(Position pos, int token) throws InputMismatchException
    {
        // TODO
        if(pos.x >= n || pos.x < 0 || pos.y >= n || pos.y < 0){
            throw new InputMismatchException("Ungültige Position.");
        }
        else if(token > 1 || token < -1){
            throw new InputMismatchException("Token ungültig.");
        }
        else {
            if(token != 0 && field[pos.x][pos.y] == 0) {
                this.freefields -= 1;
            }
            else if(token == 0 && field[pos.x][pos.y] != 0){
                this.freefields += 1;
            }
            field[pos.x][pos.y] = token;
        }
    }

    /**
     *  Places the token of a player at Position pos.
     */
    public void doMove(Position pos, int player)
    {
        // TODO
        if(field[pos.x][pos.y] == 0){
            setField(pos, player);
        }
        else{
            throw new InputMismatchException("Position is not available");
        }
    }

    /**
     *  Clears board at Position pos.
     */
    public void undoMove(Position pos)
    {
        // TODO
        setField(pos, 0);
    }

    /**
     *  @return     true if game is won, false if not
     */
    public boolean isGameWon() {
        // TODO
        int check3 = 0;
        for(int i = n-1; i>=0; i--){
            check3 += getField(new Position(i, i));
        }
        if(check3 == n || check3 == -n){
            return true;
        }

        int check4 = 0;
        int k = n-1;
        for(int i = 0; i < n; i++){
            check4 += getField(new Position(i, k));
            k--;
        }
        if(check4 == n || check4 == -n){
            return true;
        }

        int check = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                check += getField(new Position(i, j));
            }
            if(check == n || check == -n){
                return true;
            }
            check = 0;
        }
        int check2 = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                check2 += getField(new Position(j, i));
            }
            if(check2 == n || check2 == -n){
                return true;
            }
            check2 = 0;
        }

        return false;
    }

    /**
     *  @return     set of all free fields as some Iterable object
     */
    public Iterable<Position> validMoves()
    {
        // TODO
        // Implementiere Positionenstack für das ganze Board
        // Iteriere über alle Positionen , schaue ob Field an der Stelle = 0 ist -> return field[pos]
        Stack<Position> positions = new Stack<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(field[i][j] == 0){
                    positions.push(new Position(i, j));
                }
            }
        }
        return positions;
    }

    /**
     *  Outputs current state representation of the Board object.
     *  Practical for debugging.
     */
    public void print()
    {
        // TODO
        for(int i = 0; i < n; i++){
            System.out.print("\n");
            for(int j = 0; j < n; j++){
                System.out.print(field[i][j] + "  ");
            }
        }
    }

}
/**
 * int check3 = 1;
 *         for(int i = n-1; i>=0; i--){
 *             check3 *= getField(new Position(i, i));
 *         }
 *         if(check3 != 0){
 *             return true;
 *         }
 *
 *         int check4 = 1;
 *         int k = n-1;
 *         for(int i = 0; i < n; i++){
 *             check4 *= getField(new Position(i, k));
 *             k--;
 *         }
 *         if(check4 != 0){
 *             return true;
 *         }
 *
 *         int check = 1;
 *         for(int i = 0; i < n; i++){
 *             for(int j = 0; j < n; j++){
 *                 check *= getField(new Position(i, j));
 *             }
 *             if(check != 0){
 *                 return true;
 *             }
 *         }
 *         int check2 = 1;
 *         for(int i = 0; i < n; i++){
 *             for(int j = 0; j < n; j++){
 *                 check2 *= getField(new Position(j, i));
 *             }
 *             if(check2 != 0){
 *                 return true;
 *             }
 *         }
 *
 *         return false;
 */

