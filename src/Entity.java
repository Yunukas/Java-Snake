
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
public abstract class Entity {
    private char symbol;
    private int row;
    private int column;
    
    // constructor
    public Entity(char symbol)
    {
        this.symbol = symbol;
    }
    
    // constructor, this is used for creating walls
    public Entity(int row, int column, char symbol)
    {
        this.row = row;
        this.column = column;
        this.symbol = symbol;
    }
    // sub-classes will implemet this method
    public abstract void create();
    
    // this method places an Entity to a new location
    public Entity put(int newRow, int newColumn)
    {
        // if new position is availabe(empty),just place the entity 
        // into the maze and return null    
        if(Maze.available(newRow, newColumn))
        {
            row = newRow;
            column = newColumn;
            Maze.maze[row][column] = this;
            
            return null;
        }
        // else if it is a wall, return the wall
        else if(Maze.maze[newRow][newColumn].getSymbol() == Wall.SYMBOL)
        {
            return Maze.maze[newRow][newColumn];
        }
        // otherwise it is a nut, we have to return it to the squirrel
        // store it with a Entity variable first
        Entity replacedEntity = Maze.maze[newRow][newColumn];
        
        // then return stored variable
        return replacedEntity;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public char getSymbol()
    {
        return symbol;
    }
}
