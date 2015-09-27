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
 *
 * @author Shantanu
 */
public class Square extends Label{
    private Integer SqNumber;
    private Integer GridX;
    private Integer GridY;
    private Square DestSquare=null;
    private Boolean isDestSquare=false;
    /*
    Currently gridX=gridColumn and gridY=-(9-gridRow)
    */
    public Square(Integer num,Square dst){
        super(num.toString());
        this.SqNumber=num;
        this.DestSquare=dst;
        this.GridX=0;
        this.GridY=0;
    }
    public Square(Integer num){
        super(num.toString());
        this.SqNumber=num;
        this.GridX=0;
        this.GridY=0;
    }
    public Square(Integer num, Integer x,Integer y){
        super(num.toString());
        this.SqNumber=num;
        this.fontProperty().set(Font.font("Calibri", FontWeight.BOLD, 32));
        this.GridX=x;
        this.GridY=y;
    }
    public Integer getGridX(){
        return this.GridX;
    }
    public Integer getGridY(){
        return this.GridY;
    }
    public Integer getSqNumber(){
        return this.SqNumber;
    }
    public void setIsDestSquare(Boolean b){
        this.isDestSquare=b;
    }
    public Boolean getIsDestSquare(){
        return this.isDestSquare;
    }
    /*public void setSqNumber(Integer num){
        this.sqNumber=num;
    }*/
    public void setDestSquare(Square sq){
        this.DestSquare=sq;
    }
    public Square getDestSquare(){
        return this.DestSquare;
    }
}
