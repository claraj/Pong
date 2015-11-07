import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Listen for user pressing a key, and moving human paddle in response
public class KeyHandler implements KeyListener {

    @Override
    public void keyTyped(KeyEvent ev) {
        char keyPressed = ev.getKeyChar();
        char q = 'q';
        char r = 'r';
        if (keyPressed == q && GameDisplay.gameOver!=false) {
            System.exit(0);    //quit if user presses the q key after the game has been lost.
        }
        else if (keyPressed==r && GameDisplay.gameOver!=false){
            System.out.println("I hear you typeing that ARRRRRRRRRR");

        }
    }


    @Override
    public void keyReleased(KeyEvent ev) {
    }   //Don't need this one, but required to implement it.


    @Override
    public void keyPressed(KeyEvent ev) {

        GameDisplay.removeInstructions = true;   //game has started

        if (ev.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("down key");
            moveDown();
        }
        if (ev.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("up key");
            moveUp();
        }


        //ev.getComponent() returns the GUI component that generated this event
        //In this case, it will be GameDisplay JPanel
        ev.getComponent().repaint();   //This calls paintComponent(Graphics g) again
    }
    private void moveDown() {
        //Coordinates decrease as you go up the screen, that's why this looks backwards.
        if (GameDisplay.humanPaddleY < GameDisplay.screenSize - GameDisplay.paddleSize) {
            GameDisplay.humanPaddleY+=Main.humanPaddleMaxSpeed;
        }
    }

    private void moveUp() {
        //Coordinates increase as you go down the screen, that's why this looks backwards.
        if (GameDisplay.humanPaddleY > GameDisplay.paddleSize) {
            GameDisplay.humanPaddleY-=Main.humanPaddleMaxSpeed;
        }
    }
}
