/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakesandladders;

import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Shantanu
 */
public class SnakesAndLadders extends Application {
    
    static Integer previous=0;
    static ArrayList squares = new ArrayList<Square>();
    static Random rnd = new Random();
    Player currentPlayer;
    ArrayList players = new ArrayList<Player>();
    Integer currentPlayerIndex=-1;
    
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_RIGHT);
        Button rollDice = new Button("Roll Dice");
        HBox hbox = new HBox(5.0,rollDice);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(5.0, grid,hbox);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 500, 600);
        Board.initialiseSquares();
        for(Integer i =1;i<101;i++){
            Square sq = Board.squares[i];
            grid.add(sq,sq.getGridX(),sq.getGridY());
            grid.add(new Rectangle(50, 50, new Color(rnd.nextDouble(), rnd.nextDouble(),rnd.nextDouble() , 0.3)),
                    sq.getGridX(),sq.getGridY());
        }
        Button addPlayer = new Button("Add Player");
        
        addPlayer.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Player pl = new Player();
                players.add(pl);
                grid.add(pl,0,9);
                currentPlayerIndex++;
            }
        });
        
        grid.setGridLinesVisible(true);
        
        Label diceDisplay = new Label("0");
        hbox.getChildren().add(diceDisplay);
        hbox.getChildren().add(addPlayer);
        
        
        rollDice.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    Integer num = rnd.nextInt(6)+1;
                    //System.out.println(num.toString());
                    diceDisplay.setText(num.toString());
                    //Square sq = Board.squares[20];
                    currentPlayer=(Player)players.get(currentPlayerIndex);
                    currentPlayer.move(num);
                    //pl.setTranslateX(sq.getGridX()*50);
                    //pl.setTranslateY(-(9-sq.getGridY())*50);
                    //System.out.println(pl.getLayoutX());
                    currentPlayerIndex++;
                    if(currentPlayerIndex>=Player.NumberOfPlayers)
                        currentPlayerIndex-=Player.NumberOfPlayers;
                }
        });
        
        scene.getStylesheets().add(SnakesAndLadders.class.getResource("SnakesAndLadders.css").toExternalForm());
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}