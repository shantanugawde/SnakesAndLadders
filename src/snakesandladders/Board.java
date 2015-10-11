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
