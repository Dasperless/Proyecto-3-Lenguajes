package sudokux;

import java.util.ArrayList;
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
    private String[] board;
    
    
    public SudokuXController(){
        //Carga el archivo del juego.
        Query load = new Query("consult(game)");
        load.hasSolution();        
    }    

    public static void main(String[] args) {
        SudokuXController x = new SudokuXController();
        x.genPrologBoard();
    }    
    
    public String[] getBoard(){return board;}
    
    public void setBoard(String[] pBoard){board=pBoard;}
    
    /**
     * Genera un número aleatorio entero
     * @param leftLimit
     * @param rightLimit
     * @return un entero.
     */
    private int randInt(int leftLimit,int rightLimit){
        int randomNum = leftLimit + (int) (Math.random() * (rightLimit - leftLimit));
        return randomNum;
    }
    
    /**
     * Genera una matriz n cantidad de números aleatorios.
     * @return 
     */
    private String boardSeed(){
        ArrayList<ArrayList<String>> emptyBoard =  new ArrayList<>();
        
        //Genera el tablero vacío
        for (int i = 0; i <9;i++){
            ArrayList<String> row =  new ArrayList<>(Arrays.asList("_","_","_","_","_","_","_","_","_"));
            emptyBoard.add(row);
        }
        
        //Ingresa números aleatorios.
        for(int i = 0; i < 5;i++){
            int indexI = randInt(0,8);
            int indexJ = randInt(0,8);
            int num = randInt(1,9);
            emptyBoard.get(indexI).set(indexJ,String.valueOf(num));
        }
        return emptyBoard.toString();
    }
    
    /**
     * Genera un tablero de sudoku en prolog.
     * @return 
     */
    public String genPrologBoard(){
        String seed = boardSeed(); //Semilla para general el tablero
        String queryBoardStr= String.format("getBoard(%s,Board)", seed);
        Query queryBoard = new Query(queryBoardStr);

        java.util.Map<String,Term> solution;
        solution = queryBoard.oneSolution();

        return solution.get("Board").toString();
    }
}