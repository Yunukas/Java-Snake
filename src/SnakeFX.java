
import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image ;
import javafx.scene.image.ImageView;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author YunusYurttagul
 */
public class SnakeFX extends Application {

    static char direction;
    static long lastUpdate = 0;
    static Image wall = new Image(Wall.IMAGE_LOCATION);
    static Image squirrel = new Image(Snake.IMAGE_LOCATION);
    static Image almond = new Image(Almond.IMAGE_LOCATION);
    static Image peanut = new Image(Peanut.IMAGE_LOCATION);
      
    boolean goodToGo = false;
    
    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        // create the maze by reading text file
        Maze.create("Maze.txt");
        
        // create the gridpane
        GridPane grid = new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(5, 5, 5, 5));

        // create the snake
        Snake sq = new Snake();
        sq.create();

        // add first head of snake
        Snake.sqlist.addFirst(sq);
        
        // create the game controller
        final GameController gc = new GameController();
        
        // start by creating a random nut
        Nut.createRandomNut();
        
        // then redraw the grid
        updateGrid(grid);
    
        Scene scene = new Scene(grid,820, 570);
        primaryStage.setTitle("Nut Eating Snake");
        

        // handle keyboard presses
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() 
        {
            @Override
            public void handle(KeyEvent event) 
            {
                switch (event.getCode()) 
                {
                    case UP: 
                        if(Snake.lastDirection != 'd')
                        {
                            direction = 'u';
                            goodToGo = true;
                        }
                        break;
                    case DOWN:  
                        if(Snake.lastDirection != 'u')
                        {
                            direction = 'd';
                            goodToGo = true;
                        }  
                        break;
                    case LEFT:
                        if(Snake.lastDirection != 'r')
                        {
                            direction = 'l';
                            goodToGo = true;
                        }   
                        break;
                    case RIGHT:
                        if(Snake.lastDirection != 'l')
                        {
                            direction = 'r';
                            goodToGo = true;
                        } 
                        break;
                }
            }
        });
                
                      
        primaryStage.setScene(scene);
        primaryStage.show();
       
        // start the animation timer
        AnimationTimer timer = new AnimationTimer() 
        {
            @Override
            public void handle(long now) 
            {
                if(now - lastUpdate >= Snake.getSpeed() && goodToGo)
                {
                    gc.run(direction);
                    grid.getChildren().clear();
                    updateGrid(grid);                    
                    

                    lastUpdate = now;
                    
                    GameController.GameStatus gs = gc.ReceiveStatus();
                    
                    String result = " "; 
                    String reason = " ";
                    boolean gameOver = false;
                    switch(gs)
                    {
                        case IN_PROGRESS:
                            break;
                        case HIT_THE_WALL:
                            result = "Game Is Over";
                            reason = "You hit the wall!";
                            gameOver = true;
                            break;
                        case COLLECTED_ALL_NUTS:
                            result = "You have won the game!";
                            reason = "You collected all the nuts!";
                            gameOver = true;
                            break; 
                        case NEGATIVE_POINTS:
                            result = "Game Is Over";
                            reason = "Your score is below zero!";
                            gameOver = true;
                            break; 
                        case CRASHED_HIMSELF:
                            result = "Game Is Over";
                            reason = "You crashed yourself!";
                            gameOver = true;
                            break;                                 
                    }
                    if(gameOver)
                    {
//                        try
//                        {
//                            Thread.sleep(500);
                            this.stop();
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Game Over");
                            alert.setHeaderText(result);
                            alert.setContentText(reason);

                            alert.show();
//                        }
//                        catch(InterruptedException e)
//                        {
//                            System.out.println("InterruptedException: " + e.getMessage());
//                        }
                    }
                }
            }
        };
        timer.start();
    }
    
    // this method will update the grid, update the score and draw entities
    public void updateGrid(GridPane grid)
    {
        
        Text title = new Text("Score: ");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        String s = "" + Snake.getTotalPoints();
        Text scoreText = new Text(s);
        scoreText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        grid.add(title, 0, 0, 3, 1);
        grid.add(scoreText, 3, 0, 2, 1);
        

        for(int row = 0; row < Maze.Max_Maze_Row; row++)
        {
            for(int col = 0; col < Maze.Max_Maze_Column; col++)
            {
                // check if there is an object for each row
                if(Maze.maze[row][col] != null)
                {
                    if(Maze.maze[row][col].getSymbol() == Snake.SYMBOL)
                    {
                        grid.add(new ImageView(squirrel), col, row + 1);
                    }
                                       
                    if((Maze.maze[row][col]).getSymbol() == Almond.SYMBOL)
                    {
                       grid.add(new ImageView(almond), col, row + 1);
                       
                    }
                    if((Maze.maze[row][col]).getSymbol() == Peanut.SYMBOL)
                    {
                       grid.add(new ImageView(peanut), col, row + 1);
                       
                    }
                   
                    if((Maze.maze[row][col]).getSymbol() == Wall.SYMBOL)
                    {
                        grid.add(new ImageView(wall), col, row + 1);
                    }
                }
                // else it is a free space
                else
                {
                    Text tx = new Text(" ");
                    tx.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                    grid.add(tx, col, row, 1, 1);
                }        
            }
        }
    }
    
    public static void main(String[] args) {
        Application.launch(args);        
    }
}
