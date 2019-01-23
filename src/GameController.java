
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YunusYurttagul
 */
public class GameController {
//    public class GameController extends TimerTask{
    
//    private LinkedList<Squirrel> scs = new LinkedList<Squirrel>();
    private static boolean gameOver = false;
    
    public static enum GameStatus 
    {
        IN_PROGRESS,
        NEGATIVE_POINTS,
        COLLECTED_ALL_NUTS,
        HIT_THE_WALL,
        CRASHED_HIMSELF

    }
    
//    @Override
    public static void run(char direction) 
    {
        Snake.sqlist.get(0).move(direction);
    }

    public static GameStatus ReceiveStatus() 
    {
        GameStatus result = GameStatus.IN_PROGRESS;

        if (Snake.didItHitTheWall()) 
        {
            result = GameStatus.HIT_THE_WALL;
        }
        
        if (Snake.didCrashHimself()) 
        {
            result = GameStatus.CRASHED_HIMSELF;
        }        

        return result;
    }
    
    public static boolean getGameStatus()
    {
        return gameOver;
    }
    
}
