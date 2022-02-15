package robatortas.code.files.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL30;

public class KeyInput implements KeyListener{

	public boolean[] keys = new boolean[120];
	public boolean up, down, left, right;

	public void update(long window) {
		up = false;
		down = false;
		left = false;
		right = false;
		
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_W) == GL30.GL_TRUE) {
			up = true;
		}
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_S) == GL30.GL_TRUE) {
			down= true;
		}
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_A) == GL30.GL_TRUE) {
			left = true;
		}
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_D) == GL30.GL_TRUE) {
			right = true;
		}
	}
	
	public void keyTyped(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}
}
