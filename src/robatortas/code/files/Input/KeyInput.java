package robatortas.code.files.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL30;

public class KeyInput implements KeyListener{

	public boolean[] keys = new boolean[120];
	public boolean w, s, a, d;
	public boolean up, down, left, right;
	public boolean semicolon;

	public void update(long window) {
		w = false;
		s = false;
		a = false;
		d = false;
		
		up = false;
		down = false;
		left = false;
		right = false;
		
		semicolon = false;
		
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_W) == GL30.GL_TRUE) {
			w = true;
		}
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_S) == GL30.GL_TRUE) {
			s = true;
		}
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_A) == GL30.GL_TRUE) {
			a = true;
		}
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_D) == GL30.GL_TRUE) {
			d = true;
		}
		
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_UP) == GL30.GL_TRUE) {
			up = true;
		}
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_DOWN) == GL30.GL_TRUE) {
			down = true;
		}
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT) == GL30.GL_TRUE) {
			left = true;
		}
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT) == GL30.GL_TRUE) {
			right = true;
		}
		
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_SEMICOLON) == GL30.GL_TRUE) {
			semicolon = true;
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
