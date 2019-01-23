
import java.util.LinkedList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YunusYurttagul
 */
public class Snake extends Entity implements IMovable{

    private static int pointsCollected = 0;
    private static int totalNutsEaten = 0;
    private static boolean hitTheWall = false;
    private static short wallHitCount = 0;
    private static boolean crashedHimself = false;
    private static short selfCrashCount = 0;
    private static long SPEED = 280_000_000;
    private static long SPEED_INCREMENT = 20_000_000;
    private static long MAX_SPEED = 100_000_000;
    public static final char SYMBOL = (char)331;
    public static final String IMAGE_LOCATION = "File:image/snakePNG.png";
    public static char lastDirection = 'o';
    public static LinkedList<Snake> sqlist = new LinkedList<Snake>();
    
//    private char currentDirection;
    
    public Snake()
    {
        super(SYMBOL);
    }
    
    public Snake(int row, int col)
    {
        super(row, col, SYMBOL);
    }
    
     @Override
    public void create() {
       
       // temp variable for row and column
       int r, c;
              
       Scanner sc = new Scanner(System.in);
       String input;
       while(true)
       {
           // starting row column of snake, it can be modified
           r = 9; c = 9;
           
           if(r < 0 || r > Maze.Max_Maze_Row - 1
                    || c < 0 || c > Maze.Max_Maze_Column - 1
                    || Maze.maze[r][c] != null) 
           {
               System.out.println("Position unavailable, try again : <row, column>");
               continue;
           }
           
           // all checks have been complete, print message and create the squirrel
           System.out.println("User input accepted.");
           put(r,c);
           break;
       } 
    }

    @Override
    public void move(char direction) 
    {
        // this variable is necessary in case squirrel eats a nut
        // and we have to add one more squirrel to the arraylist
        // because its size will grow
        Snake newSquirrel;
        
        // store current positions to temp variables
        // they will be previous row and col
        int prevRow = getRow();
        int prevCol = getColumn();
        // temp variables for the new position
        int newR = -1;
        int newC = -1;
        

        
        switch(direction)
        {
            case 'u':
                    newR = prevRow - 1;
                    newC = prevCol;
                    break;
                
            case 'd':
                    newR = prevRow + 1;
                    newC = prevCol;                    
                    break;
            case 'l':
                    newC = prevCol - 1;
                    newR = prevRow;               
                    break;
                
                
            case 'r':
                    newC = prevCol + 1;
                    newR = prevRow;                 
                    break;
        }
        

        
         // check for maze range
        if(newC < 0 || (newC > (Maze.Max_Maze_Column - 1)) || newR < 0 || (newR > (Maze.Max_Maze_Row - 1)))
        {
            System.out.println("Invalid position. Try again");
            return;
        }
        else
        {
            // (row, column) is within the proper range
            // lets try to move the squirrel
            Entity replacedEntity = Maze.maze[newR][newC];
            // if a non-null value is returned from put(int, int) method, 
            // squirrel either hit a wall or ate a nut
            if(replacedEntity != null)
            {
                if(replacedEntity.getSymbol() == Wall.SYMBOL)
                {
                   // System.out.println("You can't go through the walls!");
                    ++wallHitCount;
                    
                    if(wallHitCount == 2)
                    hitTheWall = true;
//                    gc.stop();
                    return;
                }
                else if(replacedEntity.getSymbol() == Snake.SYMBOL)
                {
                    ++selfCrashCount;
                    if(selfCrashCount == 2)
                    crashedHimself = true;
                    
                    return;
                }
                // if it is not a wall, it is a nut
                else
                {
                    selfCrashCount = 0;
                    wallHitCount = 0;
                    Nut.createRandomNut();
                    
                    switch(direction)
                    {
                        case 'u':
                            lastDirection = 'u';
                            break;
                        case 'd':
                            lastDirection = 'd';
                            break;
                        case 'l':
                            lastDirection = 'l';
                            break;
                        case 'r':
                            lastDirection = 'r';
                            break;
                    }
                    
                    // add to points, can be negative(cashew) or positive
                    pointsCollected += ((Nut)replacedEntity).getNutritionPoints();
                    
                    // if it was an almond or a peanut 
                    // increment the total number of nuts eaten
                    // if squirrel eats a total of 5 nuts, the game will be over
                    // the nuts that give 
                    // positive nutrition points are almond and peanut
                    if(((Nut)replacedEntity).getNutritionPoints() > 0)
                    {
                        totalNutsEaten++;
                    }

                    System.out.printf
                    (
                        "!!! Squirrel ate %s and %s %s points. (Total %s points) !!!"
                        ,((Nut)replacedEntity).getName()
                        ,(((Nut)replacedEntity).getNutritionPoints() > 0 ? "gained" : "lost")
                        ,(Math.abs(((Nut)replacedEntity).getNutritionPoints()))
                        ,getTotalPoints()
                    );  
                   // append a new line
                    System.out.println();
                    
//                    for(int i = 0; i < sqlist.size(); i++)
//                    {
//                        Snake temp = sqlist.get(i);
//                        Maze.maze[temp.getRow()][temp.getColumn()] = null;
//                    }

                    newSquirrel = new Snake(newR, newC);
                    // if squirrel ate a poisonouse cashew
                    // we need to remove a piece of tail
                    // for this we call remove twice
                    // calling it one time does same as
                    // squirrel moving to a blank location
                    // so size stays same
                    // calling it an additional time, removes one piece
                    if(((Nut)replacedEntity).getNutritionPoints() < 0)
                    {
                        // if squirrel eats a poison cashew
                        // at the beginning of the game
                        // we cant remove two pieces 
                        // so we just remove one to prevent crashing
                        // the game will be over anyways
                        if(sqlist.size() > 1)
                        {

                            sqlist.removeLast();
                            sqlist.removeLast();
                            
                        }
                        else
                        {
                            sqlist.removeLast();
                        }
                    }
                    // add a new head
                    sqlist.addFirst(newSquirrel);

                    

                    for(int i = 0; i < sqlist.size(); i++)
                    {
                        Snake temp = sqlist.get(i);
                        Maze.maze[temp.getRow()][temp.getColumn()] = temp;
                    }
                    
                    // increase the SPEED by 5% after every two nuts
                    if((getTotalNutsEaten() % 2 == 0)  && getSpeed() >= MAX_SPEED )
                    {
                       setSpeed(getSpeed() - SPEED_INCREMENT); 
                    }
                    
                    // additionally, increase SPEED by 25% after every 100 points
                    if((getTotalPoints() % 100 == 0) && getSpeed() >= MAX_SPEED)
                    {
                        setSpeed(getSpeed() - (2 * SPEED_INCREMENT));
                    }
                    
                }
            }
            // else, it was an empty space
            else
            {
                wallHitCount = 0;
                selfCrashCount = 0;
                switch(direction)
                {
                    case 'u':
                        lastDirection = 'u';
                        break;
                    case 'd':
                        lastDirection = 'd';
                        break;
                    case 'l':
                        lastDirection = 'l';
                        break;
                    case 'r':
                        lastDirection = 'r';
                        break;
                }
                
                for(int i = 0; i < sqlist.size(); i++)
                {
                    Snake temp = sqlist.get(i);
                    Maze.maze[temp.getRow()][temp.getColumn()] = null;
                }

                newSquirrel = new Snake(newR, newC);
                sqlist.removeLast();
                sqlist.addFirst(newSquirrel);



                for(int i = 0; i < sqlist.size(); i++)
                {
                    Snake temp = sqlist.get(i);
                    Maze.maze[temp.getRow()][temp.getColumn()] = temp;
                }                        
            }
            
        }
    }
    
    public static int getTotalPoints() {
        return pointsCollected;
    }

    public static int getTotalNutsEaten() {
        return totalNutsEaten;
    }    
    
    public static boolean didItHitTheWall()
    {
        return hitTheWall;
    }
    
    public static boolean didCrashHimself()
    {
        return crashedHimself;
    }

    public static long getSpeed() {
        return SPEED;
    }

    public static void setSpeed(long speed) {
        Snake.SPEED = speed;
    }
    
    
}
