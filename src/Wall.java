/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YunusYurttagul
 */
public class Wall extends Entity{

    public static final char SYMBOL = '*';
    public static final String IMAGE_LOCATION = "File:image/wall.jpg";
    
    public Wall(int row, int column)
    {
        super(row, column, SYMBOL);
    }
    
    @Override
    public void create() {
        // do nothing
    }
}
