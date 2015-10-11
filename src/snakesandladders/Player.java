/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakesandladders;

import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * Objects of this class represent the players on the board.
 * These objects are initialized by {@link snakesandladders.SnakesAndLadders}.
 * 
 * It inherits from {@link javafx.scene.shape.Circle} in order to be rendered on the screen as a Node.
 * @author Shantanu
 */
public class Player extends Circle{
    /**Keeps of count of the number of Player objects created.*/
    public static Integer NumberOfPlayers=0;
    
    /**Unique ID for each Player. 
     * This ranges from 1 to 4(inclusive).
     */
    private Integer PlayerID;
    
    /**{@link snakesandladders.Square} on which the Player currently is.
     * This is used to change the position({@link #move move}) the player.
     */
    private Square currentSquare=Board.squares[1];
    
    /**Array of Colors to be applied to the created objects.
     * The Colors are Red, Green and Blue, each with an {@link javafx.scene.paint.Color#opacity opacity} of 0.6
     */
    private static Color[] colors={new Color(1, 0, 0, 0.6),new Color(0, 1, 0, 0.6),new Color(0, 0, 1, 0.6),new Color(1, 1, 0, 0.6)};
    
    /**
     * Is set to the PlayerID of the Player who wins.
     */
    static Integer winnerNum=-1;
    
    /**The default constructor which initializes the Player object.
     * It calls the constructor of {@link javafx.scene.shape.Circle#Circle(double, javafx.scene.paint.Paint) Circle} 
     * with a radius as 20.0 and Fill as from {@link #colors}
     */
    public Player(){
        super(20.0,colors[Player.NumberOfPlayers]);
        this.centerXProperty().setValue(-5);
        this.PlayerID=Player.NumberOfPlayers+1;
        Player.NumberOfPlayers++;
    }
    
    /**
     * @return  Returns the {@link #currentSquare} of the Player object.    
     */
    public Square getCurrentSquare(){
        return this.currentSquare;
    }
    
    /**
     * Modifies {@link #currentSquare} attribute of the Player object.
     * @param sq Sets the {@link #currentSquare} to sq.
     */
    public void setCurrentSquare(Square sq){
        this.currentSquare=sq;
    }
    
    /**
     * @return  Returns {@link #PlayerID} attribute of the Player object.    
     */
    public Integer getPlayerID(){
        return this.PlayerID;
    }
    
    /**
     * This method is used to change {@link #currentSquare} attribute.
     * The Player object is moved according to the number displayed on the dice.
     * It also sets the winner by changing the static {@link #winnerNum} attribute.
     * 
     * @param diceNum   The number displayed by the dice.
     */
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
                Player.winnerNum=this.getPlayerID();
            }
        }
        if(getCurrentSquare().getDestSquare()!=null){
                Path path = new Path();
                path.getElements().add(new MoveTo(getCurrentSquare().getGridX()*50,-(9-getCurrentSquare().getGridY())*50));
            
                setCurrentSquare(getCurrentSquare().getDestSquare());
                path.getElements().add(new LineTo(getCurrentSquare().getGridX()*50,-(9-getCurrentSquare().getGridY())*50));
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.seconds(1));
            pathTransition.setPath(path);
            pathTransition.setNode(this);
            pathTransition.play();
        }
    }
}
