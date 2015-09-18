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
    static Random rnd = new Random();
    public static void initialiseSquares(){
        Integer k=1;
        for(Integer i =9; i>=0;i--){
            if(i%2!=0){
                for(Integer j=0; j<10;j++){
                    Square sq = new Square(k,j,i);
                    sq.setMinSize(50, 50);
                    sq.setAlignment(Pos.CENTER);
                    //squares.add(sq);
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
                    //squares.add(sq);
                    squares[k]=sq;
                    k++;
                }
            }
        }
        for(Integer r=1;r<=100;r++){
            if(r%10==5){
                squares[r].setDestSquare(squares[rnd.nextInt(101)+1]);
            }
        }
    }
}
