package sudokux;

import org.jpl7.Atom;
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
    public SudokuXController(){}
    
    public String[] getBoard(){return board;}
    public void setBoard(String[] pBoard){board=pBoard;}
    public void genPrologBoard(){
        Query q1 = new Query("consult",new Term[]{new Atom("game.pl")});
        System.out.println("consult "+(q1.hasSolution()?"succed":"failed"));        
    }
    
}
