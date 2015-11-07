import java.util.Observable;
import java.util.Observer;

/**
 * Created by Malcolm on 11/1/2015.
 */
public  class Points_Observer implements Observer{

    public void update (Observable ball,Object arg){
        if((( Ball)ball).getPointScored()){
            if(Ball.getBallX()<=0){
                System.out.println("Human Scored!");
                Main.setHumanScore();
            }
            else if(Ball.getBallX()>=GameDisplay.screenSize){
                System.out.println("Computer Scored!");
                Main.setCompScore();
            }
        }
        //TODO put a observer here. This looks at the ballX Varioable
    }
}
