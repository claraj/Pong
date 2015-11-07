/**
 * Created by Malcolm on 11/4/2015.
 */
public class moveComputerPaddle {
    protected static void moveComputerPaddle(){

        //if ballY = 100 and paddleY is 50, difference = 50, need to adjust
        //paddleY by up to the max speed (the minimum of difference and maxSpeed)

        //if ballY = 50 and paddleY = 100 then difference = -50
        //Need to move paddleY down by the max speed

        int ballPaddleDifference = GameDisplay.computerPaddleY - (int)Ball.ballY;
        int distanceToMove = Math.min(Math.abs(ballPaddleDifference), Main.computerPaddleMaxSpeed);

        System.out.println("computer paddle speed = " + Main.computerPaddleSpeed);

        if (ballPaddleDifference > 0 ) {   //Difference is positive - paddle is below ball on screen
            GameDisplay.computerPaddleY -= distanceToMove;

        } else if (ballPaddleDifference < 0){
            GameDisplay.computerPaddleY += distanceToMove;

        } else {
            //Ball and paddle are aligned. Don't need to move!
            Main.computerPaddleSpeed = 0;
        }

    }

}
