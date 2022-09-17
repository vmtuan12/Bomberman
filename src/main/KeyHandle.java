package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandle implements KeyListener {
    private boolean up, down, left, right;

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W){
            up = true;
        }
        if(key == KeyEvent.VK_S){
            down = true;
        }
        if(key == KeyEvent.VK_A){
            left = true;
        }
        if(key == KeyEvent.VK_D){
            right = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W){
            up = false;
        }
        if(key == KeyEvent.VK_S){
            down = false;
        }
        if(key == KeyEvent.VK_A){
            left = false;
        }
        if(key == KeyEvent.VK_D){
            right = false;
        }
    }
}
