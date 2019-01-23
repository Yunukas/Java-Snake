
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YunusYurttagul
 */
public class Maze {
    public static final int Max_Maze_Row = 20;
    public static final int Max_Maze_Column = 31;
    public static Entity[][] maze;
    
    public static void create(String filename) throws FileNotFoundException, IOException
    {
        maze = new Entity[Max_Maze_Row][Max_Maze_Column];
        
        BufferedReader br = new BufferedReader(new FileReader(filename));
        
        String line = null;
        int r = 0;
        while((line = br.readLine()) != null)
        {
            for(int i = 0; i < Max_Maze_Column; i++)
            {
                if(line.charAt(i) == Wall.SYMBOL)
                {
                    maze[r][i] = new Wall(r, i);
                }
                
            }
            
            ++r;
        }
        br.close();  
    }
    
    public static void display(Snake sc)
    {
        for(int row = 0; row < Max_Maze_Row; row++)
        {
            for(int col = 0; col < Max_Maze_Column; col++)
            {
                // check if there is an object for each row
                if(maze[row][col] != null)
                {
                   System.out.print((maze[row][col]).getSymbol()); 
                }
                // else it is a free space
                else
                {
                    System.out.print(" "); 
                }
                 
            }
            if(row == 0)
            {
                System.out.print("\t" );
                System.out.printf("%-15s %-5s", "Total Score: ", sc.getTotalPoints());
            }
            if(row == 1)
            {   
                System.out.print("\t" );
                System.out.printf("%-15s %-5s", "Nuts Eaten: ", sc.getTotalNutsEaten());
            }
            
            
            System.out.print("\r\n");
        }
        System.out.print("\r\n");
    }
    
    public static boolean available(int row, int col)
    {
        if(maze[row][col] == null)
        {
           return true;
        }
        
        return false;
    }
}
