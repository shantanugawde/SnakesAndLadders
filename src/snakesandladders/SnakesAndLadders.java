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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
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
    static Label prompt = new Label();
    static ImageView imageView = new ImageView();
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_RIGHT);
        Button rollDice = new Button("Roll Dice");
        HBox hbox = new HBox(5.0,rollDice);
        hbox.setAlignment(Pos.CENTER);
        
        VBox vbox = new VBox(5.0, grid,hbox);
        StackPane root = new StackPane(vbox);
       
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 500, 650);
        Board.initialiseSquares();
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        //root.getChildren().add(imageView);
        
        Image one = new Image(SnakesAndLadders.class.getResource("one.png").toString());
        Image two = new Image(SnakesAndLadders.class.getResource("two.png").toString());
        Image three = new Image(SnakesAndLadders.class.getResource("three.png").toString());
        Image four = new Image(SnakesAndLadders.class.getResource("four.png").toString());
        Image five = new Image(SnakesAndLadders.class.getResource("five.png").toString());
        Image six = new Image(SnakesAndLadders.class.getResource("six.png").toString());
        
        
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
                if(currentPlayerIndex<3){
                    Player pl = new Player();
                    players.add(pl);
                    grid.add(pl,0,9);
                    currentPlayerIndex++;
                }
                else{
                    prompt.setText("Cannot add more than 4 players.");
                }
            }
        });
        
        grid.setGridLinesVisible(true);
        
        hbox.getChildren().add(imageView);
        hbox.getChildren().add(addPlayer);
        vbox.getChildren().add(prompt);
        
        rollDice.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    if(currentPlayerIndex==-1){
                        prompt.setText("Add players");
                    }else{
                        prompt.setText("");
                    Integer num = rnd.nextInt(6)+1;
                    //System.out.println(num.toString());
                    //diceDisplay.setText(num.toString());
                    //Square sq = Board.squares[20];
                    switch(num){
                        case 1:
                            imageView.setImage(one);
                            break;
                        case 2:
                            imageView.setImage(two);
                            break;
                        case 3:
                            imageView.setImage(three);
                            break;
                        case 4:
                            imageView.setImage(four);
                            break;
                        case 5:
                            imageView.setImage(five);
                            break;
                        case 6:
                            imageView.setImage(six);
                            break;
                    }
                    currentPlayer=(Player)players.get(currentPlayerIndex);
                    currentPlayer.move(num);
                    currentPlayerIndex++;
                    if(currentPlayerIndex>=Player.NumberOfPlayers)
                        currentPlayerIndex-=Player.NumberOfPlayers;
                    if(Player.winnerNum>=0){
                        prompt.setText("Player "+Player.winners[Player.winnerNum]+" wins!");
                    }
                    }
                }
        });
        
        scene.getStylesheets().add(SnakesAndLadders.class.getResource("SnakesAndLadders.css").toExternalForm());
        primaryStage.setTitle("Snakes and Ladders");
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