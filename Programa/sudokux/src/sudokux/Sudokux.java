/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sudokux;
import org.jpl7.*;
/**
 *
 * @author dvarg
 */
public class Sudokux {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Query q1 = new Query("consult",new Term[]{new Atom("game.pl")});
        System.out.println("consult "+(q1.hasSolution()?"succed":"failed"));
    }
    
}
