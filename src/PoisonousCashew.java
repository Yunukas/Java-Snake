/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YunusYurttagul
 */
public class PoisonousCashew extends Nut{
    private static final int NUTRITION_POINTS = -15;
    private static final String NAME = "Poisonous Cashew";
    public static final int COUNT = 5;
    public static final char SYMBOL = 'C';
    public static final String IMAGE_LOCATION = "File:cashewPNG.png";
    
    
    public PoisonousCashew()
    {
        super(NAME, SYMBOL, NUTRITION_POINTS);
    }
}
