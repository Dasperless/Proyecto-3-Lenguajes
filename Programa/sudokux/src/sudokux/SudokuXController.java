package sudokux;

import java.util.Random;
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
        System.out.println(x.randomNum());
//        x.genPrologBoard();
    }    
    
    public String[] getBoard(){return board;}
    
    public void setBoard(String[] pBoard){board=pBoard;}
    
    private long randomNum(){
        long leftLimit = 1;
        long rightLimit = 10000;
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        return generatedLong;
    }
    
    public void genPrologBoard(){
        String queryBoardStr= "getBoard(Row)";
        Query queryBoard = new Query(queryBoardStr);

        java.util.Map<String,Term> solution;
        solution = queryBoard.oneSolution();
        long randomNum = randomNum();
        for(long i = 0; i<randomNum;i++){
            solution = queryBoard.nextSolution(); 
        }
        System.out.println("X=" + solution.get("Row"));
    }
}

