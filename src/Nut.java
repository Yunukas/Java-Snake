/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YunusYurttagul
 */
public abstract class Nut extends Entity {

    public static final int Total_Nuts = 10;
    private int NutritionPoints;
    private String Name;
    
    public Nut(String Name, char symbol, int NutritionPoints)
    {
        super(symbol);
        this.Name = Name;
        this.NutritionPoints = NutritionPoints;
    }

    @Override
    public void create() 
    {
        //  temp variables for row and column
        int r;
        int c;
        
        while(true)
        {
            r = (int)(Math.random() * Maze.Max_Maze_Row);
            c = (int)(Math.random() * Maze.Max_Maze_Column);

            // We need to call Maze.available method here
            // to make sure a nut is placed at a null location
            // otherwise it might be placed over another nut
            if(Maze.available(r,c))
            {
                // if the current position is available
                // place the nut
                put(r,c);
                break;
            }
        }
    }
    public static void createRandomNut()
    {
        Nut nut;

        double rnd = Math.random();
        if (rnd < 0.5) 
        {
            nut = new Almond();
        } 
        else 
        {
            nut = new Peanut();
        }

        nut.create();
         
    }  
    public int getNutritionPoints() {
        return NutritionPoints;
    }

    public String getName() {
        return Name;
    }    
}
