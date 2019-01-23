/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YunusYurttagul
 */
public class Almond extends Nut {
    public static final char SYMBOL = 'A';
    public static final String IMAGE_LOCATION = "File:image/almondPNG.png";
    private static final String NAME = "Almond";
    private static final int NUTRITION_POINTS = 5;

    public Almond()
    {
        super(NAME, SYMBOL, NUTRITION_POINTS);
    }
}
