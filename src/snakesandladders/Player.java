/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakesandladders;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Shantanu
 */
public class Player extends Circle{
    public static Integer NumberOfPlayers=0;
    private Integer PlayerID;
    private Square currentSquare=Board.squares[1];
    static Random rnd = new Random();
    static Integer[] winners=new Integer[4];
    static Integer winnerNum=-1;
    public Player(){
        super(20.0,new Color(rnd.nextDouble(),rnd.nextDouble(),rnd.nextDouble(),0.5));
        this.PlayerID=Player.NumberOfPlayers+1;
        Player.NumberOfPlayers++;
    }
    public Square getCurrentSquare(){
        return this.currentSquare;
    }
    public void setCurrentSquare(Square sq){
        this.currentSquare=sq;
    }
    public Integer getPlayerID(){
        return this.PlayerID;
    }
    public void move(Integer diceNum){
        if(getCurrentSquare().getSqNumber()+diceNum<=100){
            setCurrentSquare(Board.squares[getCurrentSquare().getSqNumber()+diceNum]);
            this.setTranslateX(getCurrentSquare().getGridX()*50);
            this.setTranslateY(-(9-getCurrentSquare().getGridY())*50);
            if(getCurrentSquare().getSqNumber()==100){
                Player.winners[++Player.winnerNum]=this.getPlayerID();
            }
            try{
            if(getCurrentSquare().getDestSquare()!=null)
                Thread.sleep(2000);
            }
            catch(Exception ex){}
        }
        if(getCurrentSquare().getDestSquare()!=null){
                System.out.println("In!!!!"+getCurrentSquare().getDestSquare().getSqNumber());
                setCurrentSquare(getCurrentSquare().getDestSquare());
                this.setTranslateX(getCurrentSquare().getGridX()*50);
                this.setTranslateY(-(9-getCurrentSquare().getGridY())*50);
            
        }
    }
}
