package com.clara;

//import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {


    int computerPaddleY = 0;
    int humanPaddleY = 0;

    static int screenSize = 300;    //and width
    static int paddleSize = 50;
    static int paddleDistanceFromSide = 10;

    static int computerPaddleLocation = screenSize / 2 ;
    static int humanPaddleLocation = screenSize / 2 ;

    static int ballX = screenSize / 2;
    static int ballY = screenSize / 2;

    static Timer timer;

    private static class GameDisplay extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawString( "Pong! \n Press up or down to start", 20, 30 );
            g.setColor(Color.blue);
            //Ball
            g.drawOval(ballX, ballY, 10, 10);
            //Computer paddle
            g.drawLine(paddleDistanceFromSide, computerPaddleLocation, paddleDistanceFromSide, computerPaddleLocation + paddleSize);
            //Human paddle
            g.drawLine(screenSize - paddleDistanceFromSide, humanPaddleLocation, screenSize - paddleDistanceFromSide, humanPaddleLocation + paddleSize);

        }
    }

    private static class KeyHandler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent ev) {
            if (ev.getKeyCode() == KeyEvent.VK_DOWN) {
                //System.out.println("down key");
                moveUp();
            }
            if (ev.getKeyCode() == KeyEvent.VK_UP) {
                //System.out.println("up key");
                moveDown();
            }

            ev.getComponent().repaint();
        }

        private void moveDown() {}

        private void moveUp() {}


    }



    public static void main(String[] args) {

        GameDisplay displayPanel = new GameDisplay();

        KeyHandler listener = new KeyHandler();
        displayPanel.addKeyListener(listener);

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(displayPanel, BorderLayout.CENTER);

        JFrame window = new JFrame("Pong!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(content);
        window.setSize(screenSize, screenSize);
        window.setLocation(100,100);
        window.setVisible(true);

        ActionListener gameUpdater = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Do game stuff

                moveBall();
                moveComputerPaddle();

                if (isGameOver()) {

                    //displayGameOver();
                    timer.stop();
                }

                GameDisplay.repaint();    //TODO repaint from here

            }

            void moveBall() {
                System.out.println("move ball");
                //move in current direction
                //bounce off walls and paddle
                //Take into account velocity of paddles

            }

            void moveComputerPaddle(){
                System.out.println("move computer paddle");
                //TODO move in direction of the ball.
            }

            boolean isGameOver() {
                return false;
            }

        };

        timer = new Timer(100, gameUpdater);
        timer.start();
    }

}



