import java.util.Observable;
import java.util.Observer;

/**
 * Created by Malcolm on 11/5/2015.
 */
public  class Points_Observer implements Observer{

    public void update (Observable ball,Object arg){
        if((( Ball)ball).getPointScored()){
            if(Ball.getBallX()<=0){
                System.out.println("Human Scored!");
                ((Ball) ball).setBallY(GameDisplay.screenSize/2);
                ((Ball) ball).setBallX(GameDisplay.screenSize/2);
                Main.setHumanScore();
                
            }
            else if(Ball.getBallX()>=GameDisplay.screenSize){
                System.out.println("Computer Scored!");
                ((Ball) ball).setBallY(GameDisplay.screenSize/2);
                ((Ball) ball).setBallX(GameDisplay.screenSize/2);
                Main.setCompScore();
                
            }
        }
        //TODO put a observer here. This looks at the ballX Varioable
    }






    /*class EggAlert implements Observer {

    public void update(Observable chicken, Object arg) {
        if (((Chicken)chicken).laidEgg() ){
            System.out.println(chicken + " has laid an egg");
        }

    }*/
}
