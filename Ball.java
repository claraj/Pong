import java.util.Observable;

/**
 * Created by Malcolm on 11/4/2015.
 */
//Checks to see if the ball has hit a wall or paddle
//If so, bounce off the wall/paddle
//And then move ball in the correct direction
public class Ball extends Observable{
    boolean pointScoredVar=false;
    public void pointScored(Ball ballX){
        this.pointScoredVar=true;
        this.setChanged();
        this.notifyObservers();
        this.pointScoredVar=false;
    }
    public static double getBallX() {
        return ballX;
    }

    static double  ballX = GameDisplay.screenSize / 2;   //Imagine the ball is in a square box. These are the coordinates of the top of that box.
    static double  ballY = GameDisplay.screenSize / 2;   //So this starts the ball in (roughly) the center of the screen
    static int ballSize = 10;

    protected static void moveBall() {
        System.out.println("move ball");
        //move in current direction
        //bounce off walls and paddle
        //TODO Take into account speed of paddles

        //Work in double

        boolean hitWall = false;
        boolean hitHumanPaddle = false;
        boolean hitComputerPaddle = false;

        if (ballX <= 0 || ballX >= GameDisplay.screenSize ) {
            GameDisplay.gameOver = true;
            return;
        }
        if (ballY <= 0 || ballY >=  GameDisplay.screenSize-ballSize) {
            hitWall = true;
        }

        //If ballX is at a paddle AND ballY is within the paddle size.
        //Hit human paddle?
        if (ballX >=  GameDisplay.screenSize-(GameDisplay.paddleDistanceFromSide+(ballSize)) && (ballY > GameDisplay.humanPaddleY-GameDisplay.paddleSize && ballY < GameDisplay.humanPaddleY+GameDisplay.paddleSize))

            hitHumanPaddle = true;


        //Hit computer paddle?
        if (ballX <= GameDisplay.paddleDistanceFromSide && (ballY > GameDisplay.computerPaddleY-GameDisplay.paddleSize && ballY < GameDisplay.computerPaddleY+GameDisplay.paddleSize))
            hitComputerPaddle = true;


        if (hitWall == true) {
            //bounce
            Main.ballDirection = ( (2 * Math.PI) - Main.ballDirection );
            System.out.println("ball direction " + Main.ballDirection);
        }

        //Bounce off computer paddle - just need to modify direction
        if (hitComputerPaddle == true) {
            Main.ballDirection = (Math.PI) - Main.ballDirection;

            //TODO factor in speed into new direction
            //TODO So if paddle is moving down quickly, the ball will angle more down too

        }

        //Bounce off computer paddle - just need to modify direction
        if (hitHumanPaddle == true) {
            Main.ballDirection = (Math.PI) - Main.ballDirection;
            //TODO consider speed of paddle
        }

        //Now, move ball correct distance in the correct direction

        // ** TRIGONOMETRY **

        //distance to move in the X direction is ballSpeed * cos(ballDirection)
        //distance to move in the Y direction is ballSpeed * sin(ballDirection)

        ballX = ballX + (Main.ballSpeed * Math.cos(Main.ballDirection));
        ballY = ballY + (Main.ballSpeed * Math.sin(Main.ballDirection));

        // ** TRIGONOMETRY END **

    }
    //TODO This is the beginning of my points observer.

    public boolean getPointScored(){return pointScoredVar;}
}
