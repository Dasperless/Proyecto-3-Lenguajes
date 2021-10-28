package sudokux;

import java.util.Arrays;
import org.jpl7.Query;
import org.jpl7.Term;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Darío Vargas
 */
public class SudokuXController {

    private int[][] board;
    private int[][] boardClues;

    public SudokuXController() {
        //Carga el archivo del juego.
        Query load = new Query("consult(game)");
        load.hasSolution();
    }

//    public static void main(String[] args) {
//        SudokuXController x = new SudokuXController();
//        int[][] a = x.genCluesBoard();
//        Term[] b = x.genSolutionBoard(a);
//        int[][] c = x.toIntMatrix(b);
//
//        System.out.println(x.toStringBoard(a));
//        System.out.println(x.toString(c));
//        x.printMatrix(b);
//       
//    }
    
    private void printMatrix(Term[] pBoard){
        for(int i =0; i<9;i++){
            System.out.print(pBoard[i].toString());
        }
        System.out.print("\n");
    }
    
    private String toString(int[][] pBoard){
        String str = "[";
        for(int i =0; i< pBoard.length; i++){
            str+="[";
            for(int j = 0; j < pBoard[0].length; j++){
                str+= String.valueOf(pBoard[i][j]);
                if(j != 8){
                    str+=",";
                }
            }
            str+="]";
            if(i != 8){
                str+=",";
            }            
        }
        str+="]";
        return str;
    }
    
    /**
     * Convierte el resultado de prolog en un matriz de enteros.
     * @param plResult
     * @return Una matriz de enteros
     */
    private int[][] toIntMatrix(Term[] plResult) {
        int[][] intMatrix = new int[9][9];
        for (int i = 0; i< 9; i++) {
            String row = plResult[i].toString();

            //Convierte los strings a arreglos de enteros.
            int[] arr = Arrays.stream(row.substring(1, row.length() - 1).split(","))
                    .map(String::trim).mapToInt(Integer::parseInt).toArray();     
            
            //Copia los elementos en la matris de enteros.
            System.arraycopy(arr, 0, intMatrix[i], 0, arr.length);
        }
        return intMatrix;
    }

    /**
     * Genera un número aleatorio entero
     *
     * @param leftLimit
     * @param rightLimit
     * @return un entero.
     */
    private int randInt(int leftLimit, int rightLimit) {
        int randomNum = leftLimit + (int) (Math.random() * (rightLimit - leftLimit));
        return randomNum;
    }

    /**
     * Obtiene el tablero actual
     *
     * @return Un string con el tablero actual.
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Obtiene el tablero de pistas
     *
     * @return Una matriz con las pistas del tablero.
     */
    public int[][] getBoardClues() {
        return boardClues;
    }

    /**
     * Establece el tablero actual.
     *
     * @param pBoard
     */
    private void setBoard(int[][] pBoard) {
        board = pBoard;
    }

    private void setBoardClues(int[][] pBoard) {
        boardClues = pBoard;
    }

    /**
     * Genera una matriz n cantidad de números aleatorios.
     *
     * @return
     */
    private int[][] genCluesBoard() {
        int[][] clueBoard = new int[9][9];
        String stringBoard;

        //Ingresa números aleatorios.
        for (int i = 0; i < 17; i++) {
            int indexI = randInt(0, 8);
            int indexJ = randInt(0, 8);
            int num = randInt(1, 9);
            clueBoard[indexI][indexJ] = num;

            stringBoard = toStringBoard(clueBoard); //Convierte la matriz a string
            String verify = String.format("getBoard(%s,Board)", stringBoard);
            Query verifyBoard = new Query(verify);

            //verifica si es válida la solución
            if (!verifyBoard.hasSolution()) {
                clueBoard[indexI][indexJ] = 0;
                i--;
            }
        }
        return clueBoard;
    }

    /**
     * Genera un tablero de sudoku en prolog.
     *
     * @param pBoard matriz de enteros que representa el tablero.
     * @return
     */
    public Term[] genSolutionBoard(int[][] pBoard) {
        String clueBoard = toStringBoard(pBoard);
        String queryBoardStr = String.format("getBoard(%s,Board)", clueBoard);  //String de la consulta
        Query queryBoard = new Query(queryBoardStr);                            //Genera la consulta.

        java.util.Map<String, Term> solution;
        solution = queryBoard.oneSolution();                                    // Obtiene la primera solución

        return solution.get("Board").listToTermArray();
    }

    /**
     * Combierte una matriz de enteros a string
     *
     * @param board la matriz de enteros a convertir.
     * @return
     */
    private String toStringBoard(int[][] board) {
        String stringBoard = "["; //Inicia la matriz

        //Genera la matriz.
        for (int i = 0; i < 9; i++) {
            stringBoard += "["; //Inicia la fila
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    stringBoard += "_";//Vacío en prolog
                } else {
                    stringBoard += String.valueOf(board[i][j]);
                }
                if (j != 8) {
                    stringBoard += ",";
                }
            }
            stringBoard += "]";//Cierra la fila
            if (i != 8) {
                stringBoard += ",";//Siguiente fila
            }
        }
        stringBoard += "]";//Finaliza la matriz
        return stringBoard;
    }

    public void newBoard() {
        int[][] clueBoard = genCluesBoard();
        int[][] solutionBoard = toIntMatrix(genSolutionBoard(clueBoard));
        setBoardClues(clueBoard);
        setBoard(solutionBoard);
    }
}
