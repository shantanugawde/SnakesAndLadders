/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakesandladders;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Objects of this class represent the squares in the grid of the board.
 * These objects are initialized by the Board class, which assigns them
 * their SqNumber and grid coordinates.
 * 
 * It inherits from {@link javafx.scene.control.Label} in order to be rendered on the screen as a Node.
 * @author Shantanu
 */
public class Square extends Label{
    /**Number assigned to the square, which is also the text of the Label.*/
    private Integer SqNumber;
    
    /**Row number of the Square object, as to be added in the Grid layout control.*/
    private Integer GridX;
    
    /**Column number of the Square object, as to be added in the Grid layout control.*/
    private Integer GridY;
    
    /**Points to the Square object the {@link snakesandladders.Player} will reach if it moves to this current square.
     * This is used to simulate Snakes and Ladders.
     */
    private Square DestSquare=null;
    
    /**Set to true if this Square object is the DestSquare of some other Square object on the board.*/
    private Boolean isDestSquare=false;
    
    /**
     * Constructor to initialize the Square object.
     * 
     * @param num   Initializes the {@link #SqNumber} attribute
     * @param x     Initializes the {@link #GridX} attribute
     * @param y     Initializes the {@link #GridY} attribute
     */
    public Square(Integer num, Integer x,Integer y){
        super(num.toString());
        this.SqNumber=num;
        this.fontProperty().set(Font.font("Calibri", FontWeight.BOLD, 32));
        this.GridX=x;
        this.GridY=y;
    }
    
    /**
     * @return Returns {@link #GridX}
     */
    public Integer getGridX(){
        return this.GridX;
    }
    /**
     * @return Returns {@link #GridY}
     */
    public Integer getGridY(){
        return this.GridY;
    }
    /**
     * @return Returns {@link #SqNumber}
     */
    public Integer getSqNumber(){
        return this.SqNumber;
    }
    /**
     * Sets the value of the {@link #isDestSquare} attribute.
     * @param b A boolean value which modifies the isDestSquare attribute.
     */
    public void setIsDestSquare(Boolean b){
        this.isDestSquare=b;
    }
    /**
     * @return Returns the value of {@link #isDestSquare}
     */
    public Boolean getIsDestSquare(){
        return this.isDestSquare;
    }
    /**
     * Modifies the {@link #DestSquare} attribute.
     * @param sq    Square to set the DestSquare attribute to
     */
    public void setDestSquare(Square sq){
        this.DestSquare=sq;
    }
    /**
     * @return Returns {@link #DestSquare}
     */
    public Square getDestSquare(){
        return this.DestSquare;
    }
}
