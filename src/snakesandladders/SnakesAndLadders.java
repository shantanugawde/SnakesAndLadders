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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
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
    Integer currentPlayerIndex=0;
    static Label prompt = new Label();
    static ImageView imageView = new ImageView();
    
    static Spinner snakecounter= new Spinner(2, 6, 1,1);
    static Spinner laddercounter= new Spinner(2, 6, 1,1);
    
    static Paint[] fills = new Paint[4];
    static Circle playerCircle = new Circle(15.0);
    static Label winnerLbl = new Label();
        
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        
        Button rollDice = new Button("Roll Dice");
        HBox hbox = new HBox(5.0,rollDice);
        hbox.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(5.0,prompt);
        
        VBox vbox = new VBox(5.0, grid,hbox,hbox2);
        StackPane root = new StackPane();
        
        
        //create init window
        GridPane initG = new GridPane();
        initG.alignmentProperty().set(Pos.CENTER);
        initG.setHgap(10);
        initG.setVgap(10);
        initG.setPadding(new Insets(10,10,10,10));
        primaryStage.setAlwaysOnTop(true);
        Scene initScene = new Scene(initG,300,200);
        Button initBtn = new Button("Play");
        Label l1 = new Label("Snakes: ");
        Label l2 = new Label("Ladders: ");
        initG.add(l1,0,0);
        initG.add(snakecounter, 1, 0);
        initG.add(l2,0,1);
        initG.add(laddercounter,1,1);
        initG.add(initBtn,1,2);
        primaryStage.centerOnScreen();
        
        //end
        
        Canvas canvas = new Canvas(500, 650);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.setLineWidth(5);
        
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
        
        
        
        playerCircle.setFill(new Color(0, 0, 0, 0));
        Button addPlayer = new Button("Add Player");
        addPlayer.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                if(Player.NumberOfPlayers<4){
                    Player pl = new Player();
                    players.add(pl);
                    grid.add(pl,0,9);
                    fills[Player.NumberOfPlayers-1]=pl.getFill();
                    playerCircle.setFill(fills[0]);
                    prompt.setText("Player 1 to play");
                }
                else{
                    prompt.setText("Cannot add more than 4 players.");
                }
            }
        });
        grid.setGridLinesVisible(true);
        
        root.getChildren().add(canvas);
        root.getChildren().add(vbox);
        
        //winner scene
        Rectangle winRect = new Rectangle(500, 650);
        Rectangle winRect2 = new Rectangle(500, 650);
        
        //Scene winSc = new Scene(winnerLbl,500,500);
        //end
        hbox.getChildren().add(imageView);
        hbox.getChildren().add(addPlayer);
        hbox2.getChildren().add(playerCircle);
        hbox2.setAlignment(Pos.CENTER);
        rollDice.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    if(Player.NumberOfPlayers==0){
                        prompt.setText("Add players");
                    }else{
                        prompt.setText("");
                        Integer num = rnd.nextInt(6)+1;
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
                        
                         
                        //repeat chance if die shows six
                        if(num==6)
                            currentPlayerIndex--;
                        
                       
                        if(currentPlayerIndex>=Player.NumberOfPlayers)
                            currentPlayerIndex-=Player.NumberOfPlayers;
                        
                        playerCircle.setFill(fills[currentPlayerIndex]);
                       
                        
                        prompt.setText("Player "+new Integer(currentPlayerIndex+1).toString()+" to play");
                        
                        if(Player.winnerNum>=0){
                            winnerLbl.setText("Player "+Player.winnerNum+" wins!");
                            prompt.setText("Congratulations Player "+Player.winnerNum+"!");
                            winRect.setFill(fills[Player.winnerNum-1]);
                            winRect2.setFill(fills[Player.winnerNum-1]);
                            winnerLbl.setBorder(Border.EMPTY);
                            root.getChildren().add(winRect);
                            root.getChildren().add(winRect2);
                            root.getChildren().add(winnerLbl);
                        }
                    }
                }
        });
        
        initBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Board.snakes=(Integer)snakecounter.getValue();
                Board.ladders=(Integer)laddercounter.getValue();
                gc.clearRect(0, 0, 500, 650);
                Board.initialiseSnL();
                
                for(Integer i=1;i<101;i++){
                    if(Board.squares[i].getDestSquare()!=null){
                        if(Board.squares[i].getDestSquare().getSqNumber()<Board.squares[i].getSqNumber())
                            gc.setStroke(Color.RED);
                        else if(Board.squares[i].getDestSquare().getSqNumber()>Board.squares[i].getSqNumber())
                            gc.setStroke(Color.GREEN);
                        gc.strokeLine((Board.squares[i].getGridX())*50+25, (Board.squares[i].getGridY())*50+25, 
                                (Board.squares[i].getDestSquare().getGridX())*50+25, 
                                (Board.squares[i].getDestSquare().getGridY())*50+25);

                    }
                }
                primaryStage.setScene(scene);
                primaryStage.centerOnScreen();
        
            }
        });
        
        initScene.getStylesheets().add(SnakesAndLadders.class.getResource("SnakesAndLadders.css").toExternalForm());
        //winSc.getStylesheets().add(SnakesAndLadders.class.getResource("SnakesAndLadders.css").toExternalForm());
        
        scene.getStylesheets().add(SnakesAndLadders.class.getResource("SnakesAndLadders.css").toExternalForm());
        primaryStage.setTitle("Snakes and Ladders");
        primaryStage.setScene(initScene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    
    
}