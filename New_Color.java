import java.awt.*;
import java.util.Random;

public class New_Color {

    public static Color get_new_Color(){
        Random rand = new Random();

        int redValue = rand.nextInt(255);
        int greenValue = rand.nextInt(255);
        int blueValue = rand.nextInt(255);
        Color NEW_RANDOM_COLOR = new Color(redValue, greenValue, blueValue);
        return NEW_RANDOM_COLOR;
    }

 //   System.out.println("Red: " + redValue +", Green: " + greenValue + ", Blue: " + blueValue);



}

