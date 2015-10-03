/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakesandladders;

import java.util.Random;
import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 *
 * @author Shantanu
 */
public class Player extends Circle{
    public static Integer NumberOfPlayers=0;
    private Integer PlayerID;
    private Square currentSquare=Board.squares[1];
    static Random rnd = new Random();
    //static Integer[] winners=new Integer[4];
    static Integer winnerNum=-1;
    public Player(){
        super(20.0,new Color(rnd.nextDouble(),rnd.nextDouble(),rnd.nextDouble(),0.6));
        this.centerXProperty().setValue(-5);
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
            Path path = new Path();
            
            path.getElements().add(new MoveTo(getCurrentSquare().getGridX()*50,-(9-getCurrentSquare().getGridY())*50));
            setCurrentSquare(Board.squares[getCurrentSquare().getSqNumber()+diceNum]);
            
            path.getElements().add(new LineTo(getCurrentSquare().getGridX()*50,-(9-getCurrentSquare().getGridY())*50));
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.seconds(1));
            pathTransition.setPath(path);
            pathTransition.setNode(this);
            pathTransition.play();
            
            if(getCurrentSquare().getSqNumber()==100){
                //Player.winners[++Player.winnerNum]=this.getPlayerID();
                Player.winnerNum=this.getPlayerID();
            }
        }
        if(getCurrentSquare().getDestSquare()!=null){
                Path path = new Path();
                path.getElements().add(new MoveTo(getCurrentSquare().getGridX()*50,-(9-getCurrentSquare().getGridY())*50));
            
                setCurrentSquare(getCurrentSquare().getDestSquare());
                //this.setTranslateX(getCurrentSquare().getGridX()*50);
                //this.setTranslateY(-(9-getCurrentSquare().getGridY())*50);
                 path.getElements().add(new LineTo(getCurrentSquare().getGridX()*50,-(9-getCurrentSquare().getGridY())*50));
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.seconds(1));
            pathTransition.setPath(path);
            pathTransition.setNode(this);
            pathTransition.play();
        }
    }
}
