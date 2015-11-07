
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//TODO have paddle speed affect ball's direction
//TODO known issue - sometimes ball gets stuck behind human paddle

public class Main {

    static int gameSpeed = 75;  //How many milliseconds between clock ticks? Reduce this to speed up game
    static int computerPaddleMaxSpeed = 3;   //Max number of pixels that computer paddle can move clock tick. Higher number = easier for computer
    static int humanPaddleMaxSpeed = 5;   //This doesn't quite do the same thing... this is how many pixels human moves per key press TODO use this in a better way
    static int humanPaddleSpeed = 0;      // "speed" is pixels moved up or down per clock tick
    static int computerPaddleSpeed = 0;   // same
    static double ballSpeed = 5;   //Again, pixels moved per clock tick
    //An angle in radians (which range from 0 to 2xPI (0 to about 6.3).
    //This starts the ball moving down toward the human. Replace with some of the other
    //commented out versions for a different start angle
    //set this to whatever you want (but helps if you angle towards a player)
    static double ballDirection = Math.PI + 1;   //heading left and up
    //static double ballDirection = 1;
    //static double ballDirection = 0;   //heading right
    //static double ballDirection = Math.PI;   //heading left

    static Timer timer;    //Ticks every *gameSpeed* milliseconds. Every time it ticks, the ball and computer paddle move

       //draw the game components here

         //Used to work out what message, if any, to display on the screen
     // Same as above


    public static void main(String[] args) {

        GameDisplay.gamePanel = new GameDisplay();
        final Ball ball = new Ball();
        Points_Observer pointsObserver= new Points_Observer();
        ball.addObserver(pointsObserver);
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(GameDisplay.gamePanel, BorderLayout.CENTER);

        JFrame window = new JFrame();
        window.setUndecorated(true);   //Hides the title bar.

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //Quit the program when we close this window
        window.setContentPane(content);
        window.setSize(GameDisplay.screenSize, GameDisplay.screenSize);
        window.setLocation(100,100);    //Where on the screen will this window appear?
        window.setVisible(true);

        KeyHandler listener = new KeyHandler();
        window.addKeyListener(listener);

        //Below, we'll create and start a timer that notifies an ActionListener every time it ticks
        //First, need to create the listener:
        ActionListener gameUpdater = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //gameUpdater is an inner class
                //It's containing class is Main
                //moveBall() and moveComputerPaddle belong to the outer class - Main
                //So we have to say Main.moveBall() to refer to these methods
                ball.moveBall();
                moveComputerPaddle.moveComputerPaddle();

                if (GameDisplay.gameOver) {
                    ball.pointScored(ball);
                    timer.stop();
                }
                GameDisplay.gamePanel.repaint();
            }
        };

        timer = new Timer(gameSpeed, gameUpdater);
        timer.start();    //Every time the timer ticks, the actionPerformed method of the ActionListener is called
    }
     static int compScore=0;
     static int humanScore=0;
    //Uses the current position of ball and paddle to move the computer paddle towards the ball
    public int getHumanScore() {
        return humanScore;
    }

    public static void setHumanScore() {
        humanScore = humanScore+1;
    }

    public int getCompScore() {
        return compScore;
    }

    public static void setCompScore() {
        compScore = compScore+1;
    }




}



