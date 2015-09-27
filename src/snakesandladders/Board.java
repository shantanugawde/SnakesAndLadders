/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakesandladders;

import java.util.Random;
import javafx.geometry.Pos;

/**
 *
 * @author Shantanu
 */
public class Board {
    public static Square[] squares=new Square[101];
    public static Integer snakes=2;
    public static Integer ladders=2;
    static Random rnd = new Random();
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
    public static void initialiseSnL(Integer snakes, Integer ladders){
        //initialise snakes
        Square tempSq;
        Square tempDestSq;
        for(Integer i =0;i<snakes;i++){
            tempSq=Board.squares[rnd.nextInt(98)+2];
            if(tempSq.getDestSquare()==null&&!tempSq.getIsDestSquare()){
                do{
                    tempDestSq=Board.squares[rnd.nextInt(100)+1];
                }while(tempDestSq.getSqNumber()>tempSq.getSqNumber());
                System.out.println(tempDestSq.getIsDestSquare());
                tempDestSq.setIsDestSquare(Boolean.TRUE);
                tempSq.setDestSquare(tempDestSq);
            }
            else{
                i--;
            }
        }
        //initialise ladders
        for(Integer i =0;i<ladders;i++){
            tempSq=Board.squares[rnd.nextInt(95)+1];
            if(tempSq.getDestSquare()==null&&!tempSq.getIsDestSquare()){
                do{
                    tempDestSq=Board.squares[rnd.nextInt(100)+1];
                }while(tempDestSq.getSqNumber()<tempSq.getSqNumber());
                System.out.println(tempDestSq.getIsDestSquare());
                tempDestSq.setIsDestSquare(Boolean.TRUE);
                tempSq.setDestSquare(tempDestSq);
            }
            else{
                i--;
            }
        }
    }
}
