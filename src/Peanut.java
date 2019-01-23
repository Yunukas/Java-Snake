/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YunusYurttagul
 */
public class Peanut extends Nut{
    public static final char SYMBOL = 'P';
    public static final String IMAGE_LOCATION = "File:image/peanutPNG.png";
    private static final String NAME = "Peanut";
    private static final int NUTRITION_POINTS = 10;
    
    public Peanut()
    {
        super(NAME, SYMBOL, NUTRITION_POINTS);
    }   
}
