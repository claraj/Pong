package com.clara;

//import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    
    
    
    
    static int screenSize = 300;    //and width
    static int paddleSize = 25;     //Actually half the paddle size - how much to draw on each side of center
    static int paddleDistanceFromSide = 10;
    
    static int gameSpeed = 100;  //How long between clock ticks? Reduce this to speed up game
    
    static int computerPaddleY = screenSize / 2 ;    //center of the paddle
    static int humanPaddleY = screenSize / 2 ;
    
    static int computerPaddleMaxSpeed = 10;   //3 pixels per clock tick
    static int humanPaddleMaxSpeed = 5;   //3 pixels per clock tick
    
    static int humanPaddleSpeed = 0;      // "speed" is pixels moved up or down per clock tick
    static int computerPaddleSpeed = 0;   // same
    
    static double  ballX = screenSize / 2;
    static double  ballY = screenSize / 2;
    static int ballSize = 10;
    
    static double ballSpeed = 5;   //Again, pixels moved per clock tick


  //  static double ballDirection = Math.PI / 2;  //an angle, in radians. MOVING DOWN
  //  static double ballDirection = 0;  //MOVING RIGHT
    static double ballDirection = 1;
//
//    static final int UP = 1;
//    static final int DOWN = 2;
//    static final int LEFT = 3;
//    static final int RIGHT = 4;
//    static int relativeDirection = DOWN;    //Adjust this if ballDirection is changed


    static Timer timer;
    
    static GameDisplay gamePanel;
    
    static boolean gameOver;
    
    private static class GameDisplay extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //System.out.println("* Repaint *");

            if (gameOver) {
                g.drawString( "Game over!", 20, 30 );
                return;
            }
            
            g.drawString( "Pong! Press up or down to move", 20, 30 );
            g.drawString( "Press q to quit", 20, 60 );

            g.setColor(Color.blue);
            //Ball
            g.drawOval((int)ballX, (int)ballY, ballSize, ballSize);
            //Computer paddle
            g.drawLine(paddleDistanceFromSide, computerPaddleY - paddleSize, paddleDistanceFromSide, computerPaddleY + paddleSize);
            //Human paddle
            g.drawLine(screenSize - paddleDistanceFromSide, humanPaddleY - paddleSize, screenSize - paddleDistanceFromSide, humanPaddleY + paddleSize);
            
        }
    }
    
    private static class KeyHandler implements KeyListener {
        
        @Override
        public void keyTyped(KeyEvent ev) {		char keyPressed = ev.getKeyChar();
            char q = 'q';
            if( keyPressed == q){
                System.exit(0);    //quit if user presses the q key.
            }}
        
        @Override
        public void keyReleased(KeyEvent ev) {}
        
        @Override
        public void keyPressed(KeyEvent ev) {

            System.out.println("key event!" + ev);

            if (ev.getKeyCode() == KeyEvent.VK_DOWN) {
                System.out.println("down key");
                moveDown();
            }
            if (ev.getKeyCode() == KeyEvent.VK_UP) {
                System.out.println("up key");
                moveUp();
            }
            
            ev.getComponent().repaint();
        }
        
        private void moveDown() {
            if (humanPaddleY < screenSize - paddleSize) {
                humanPaddleY+=humanPaddleMaxSpeed;
            }
        }
        
        private void moveUp() {

            if (humanPaddleY > paddleSize) {
                humanPaddleY-=humanPaddleMaxSpeed;
            }
        }
        
        
    }
    
    
    
    public static void main(String[] args) {
        
        gamePanel = new GameDisplay();
        

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(gamePanel, BorderLayout.CENTER);
        
        JFrame window = new JFrame("Pong!");
        window.setUndecorated(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(content);
        window.setSize(screenSize, screenSize);
        window.setLocation(100,100);
        window.setVisible(true);

        KeyHandler listener = new KeyHandler();
        window.addKeyListener(listener);

        ActionListener gameUpdater = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Do game stuff
                
                moveBall();
                moveComputerPaddle();
                
                if (gameOver) {
                    
                    //displayGameOver();
                    timer.stop();
                }
                
                gamePanel.repaint();
                
            }
            
            void moveBall() {
                System.out.println("move ball");
                //move in current direction
                //bounce off walls and paddle
                //TODO Take into account speed of paddles

                //Work in double
                
                boolean hitWall = false;
                boolean hitHumanPaddle = false;
                boolean hitComputerPaddle = false;
                
                if (ballX <= 0 || ballX >= screenSize ) {
                    gameOver = true;
                    return;
                }
                if (ballY <= 0 || ballY >= screenSize-ballSize) {
                    hitWall = true;
                }
                
                //If ballX is at a paddle AND ballY is within the paddle size.
                //Hit human paddle?
                if (ballX >= screenSize-(paddleDistanceFromSide+(ballSize)) && (ballY > humanPaddleY-paddleSize && ballY < humanPaddleY+paddleSize))
                    hitHumanPaddle = true;
                
                //Hit computer paddle?
                if (ballX <= paddleDistanceFromSide && (ballY > computerPaddleY-paddleSize && ballY < computerPaddleY+paddleSize))
                    hitComputerPaddle = true;
    
    
                /* Remember: 
                static double ballSpeed = 5;   //Again, pixels moved per clock tick
                static double ballDirection = 0;  //an angle, in radians 
    */


                if (hitWall == true) {
                    //bounce
                    ballDirection = ( (2 * Math.PI) - ballDirection );
                    System.out.println("ball direction " + ballDirection);


                }
                
                if (hitComputerPaddle == true) {
                    //TODO bounce off paddle

                    ballDirection = (Math.PI) - ballDirection;
                    //TODO factor in speed

                    ballDirection += computerPaddleSpeed * 0.01;


                }
                
                if (hitHumanPaddle == true) {
                    //TODO verify actually bounce off paddle
                    //Reverse direction
                    ballDirection = (Math.PI) - ballDirection;
                    //TODO consider speed of paddle



                }
                
               // if (hitWall == false && hitComputerPaddle == false && hitHumanPaddle == false) {
                    //TODO ball is in "mid air"
                    //Continue current trajectory

                    //MATH

                    //distance to move in the X direction is ballspeed * cos(ballDirection)
                    //distance to move in the Y direction is ballspeed * sin(ballDirection)

                    ballX = ballX + (ballSpeed * Math.cos(ballDirection));
                    ballY = ballY + (ballSpeed * Math.sin(ballDirection));


              //  }



            }


            boolean ballGoingUp() {
                    double absBallDirection = ballDirection % (Math.PI * 2);

                    if (absBallDirection > (Math.PI / 2) && absBallDirection < Math.PI / 3 * 4){
                        return false;
                    }
                    return true;
            }

            void moveComputerPaddle(){
                //System.out.println("move computer paddle");
                
                
                //if ballY = 100 and paddleY is 50, difference = 50, need to adjust  
                //paddleY by up to the max speed (the minimum of difference and maxSpeed)
                
                //if ballY = 50 and paddleY = 100 then difference = -50
                //Need to move paddleY down by the max speed
                
                
                int ballPaddleDifference = computerPaddleY - (int)ballY;
                int distanceToMove = Math.min(Math.abs(ballPaddleDifference), computerPaddleMaxSpeed);

                System.out.println("computer paddle speed = " + computerPaddleSpeed);

                if (ballPaddleDifference > 0 ) {   //Difference is positive - paddle is below ball on screen
                    computerPaddleY -= distanceToMove;

                } else if (ballPaddleDifference < 0){
                    computerPaddleY += distanceToMove;

                } else {
                    //Ball and paddle are aligned. Don't need to move!
                    computerPaddleSpeed = 0;
                }
                
                
                
            }
            
            
            
        };
        
        timer = new Timer(gameSpeed, gameUpdater);
        timer.start();
    }
    
}



