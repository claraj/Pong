import javax.swing.*;
import java.awt.*;

/**
 * Created by Malcolm on 11/4/2015.
 */
    public class GameDisplay extends JPanel {

    static GameDisplay gamePanel;
    static int screenSize = 300;    //and width - screen is square
    static int paddleSize = 25;     //Actually half the paddle size - how much to draw on each side of center
    static int paddleDistanceFromSide = 10;  //How much space between each paddle and side of screen
    static boolean gameOver;
    static boolean removeInstructions = false;
    static int computerPaddleY = GameDisplay.screenSize / 2 ;    //location of the center of the paddles on the Y-axis of the screen
    static int humanPaddleY = GameDisplay.screenSize / 2 ;
    @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //System.out.println("* Repaint *");

            if (gameOver == true) {
                g.drawString( "Game over!", 20, 30 );
                g.drawString(String.format("Computer Score: %d           Human Score: %d",Main.compScore,Main.humanScore),20,60);
                return;
            }

            if (removeInstructions == false ) {
                g.drawString("Pong! Press up or down to move", 20, 30);
                g.drawString("Press q to quit", 20, 60);

            }



            //While game is playing, these methods draw the ball, paddles, using the global variables
            //Other parts of the code will modify these variables

            //Ball - a circle is just an oval with the height equal to the width

            Color CLR= New_Color.get_new_Color();
            g.setColor(CLR);
            g.fillOval((int) Ball.ballX, (int) Ball.ballY, Ball.ballSize, Ball.ballSize);
            g.setColor(Color.blue);
            g.drawLine(paddleDistanceFromSide, computerPaddleY - paddleSize, paddleDistanceFromSide, computerPaddleY + paddleSize);
            g.setColor(Color.red);
            g.drawLine(screenSize - paddleDistanceFromSide, humanPaddleY - paddleSize, screenSize - paddleDistanceFromSide, humanPaddleY + paddleSize);

        }
    }

