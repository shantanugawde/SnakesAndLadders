/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakesandladders;

import java.util.Random;
import javafx.geometry.Pos;

/**
 * This class initializes all the {@link snakesandladders.Square squares} from 1 to 100(inclusive).
 * All members of this class are static as an object of this class is not created.
 * @author Shantanu
 */
public class Board {
    
    /**The array which contains the squares.
     * It is given a size of 101 so as to conveniently start using indices 1 through 100.
     */
    public static Square[] squares=new Square[101];
    
    /**The number of snakes on the board.
     * By default, this value is set to 2.
     */
    public static Integer snakes=2;
    
    /**The number of ladders on the board.
     * By default, this value is set to 2.
     */
    public static Integer ladders=2;
    
    /**Object of {@link java.util.Random} which is reused throughout the class.*/
    static Random rnd = new Random();
    
    /**This method initializes the {@link snakesandladders.Square squares} from 1 through 100 using nested for loops.
     * It also assigns them their grid coordinates.
     */
    public static void initialiseSquares(){
        Integer k=1;
        for(Integer i =9; i>=0;i--){
            if(i%2!=0){
                for(Integer j=0; j<10;j++){
                    Square sq = new Square(k,j,i);
                    sq.setMinSize(50, 50);
                    sq.setAlignment(Pos.CENTER);
                    squares[k]=sq;
                    k++;
                }
            }
            else
            {
                for(Integer j=9; j>=0;j--){
                    Square sq = new Square(k,j,i);
                    sq.setMinSize(50, 50);
                    sq.setAlignment(Pos.CENTER);
                    squares[k]=sq;
                    k++;
                }
            }
        }
    }
    
    /**This method simulates the functionality of snakes and ladders on the board.
     * This is done by using a {@link #rnd Random generator} and running multiple checks to ensure that no overlap takes place.
     * The method changes the {@link snakesandladders.Square#DestSquare} attribute of the randomly selected squares.
     */
    public static void initialiseSnL(){
        //initialise snakes
        Square tempSq;
        Square tempDestSq;
        Integer ind;
        for(Integer i =0;i<Board.snakes;i++){
            tempSq=Board.squares[rnd.nextInt(93)+2];
            if(tempSq.getDestSquare()==null&&tempSq.getIsDestSquare()==Boolean.FALSE){
                
                do{
                    ind=rnd.nextInt(93)+2;
                }while(ind>tempSq.getSqNumber());
                
                tempDestSq=Board.squares[ind];
                
                if(tempDestSq.getIsDestSquare()==Boolean.TRUE||tempSq.getSqNumber()==tempDestSq.getSqNumber()
                        ||tempDestSq.getDestSquare()!=null){
                        i--;
                        continue;
                }
                else{
                    tempDestSq.setIsDestSquare(Boolean.TRUE);
                    tempSq.setDestSquare(tempDestSq);
                }
            }
            else{
                i--;
                continue;
            }
        }
        //initialise ladders
        for(Integer i =0;i<Board.ladders;i++){
            tempSq=Board.squares[rnd.nextInt(93)+2];
            if(tempSq.getDestSquare()==null&&tempSq.getIsDestSquare()==Boolean.FALSE){
                
                do{
                    ind=rnd.nextInt(93)+2;
                }while(ind<tempSq.getSqNumber());
                tempDestSq=Board.squares[ind];
                
                if(tempDestSq.getIsDestSquare()==Boolean.TRUE||tempSq.getSqNumber()==tempDestSq.getSqNumber()
                        ||tempDestSq.getDestSquare()!=null){
                        i--;
                        continue;
                }
                else{
                    tempDestSq.setIsDestSquare(Boolean.TRUE);
                    tempSq.setDestSquare(tempDestSq);
                }
                
            }
            else{
                i--;
                continue;
            }
        }
    }
}
