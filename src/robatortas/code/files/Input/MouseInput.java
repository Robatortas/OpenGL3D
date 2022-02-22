package robatortas.code.files.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.nio.DoubleBuffer;

import org.lwjgl.glfw.GLFW;

import robatortas.code.files.DisplayManager;
import robatortas.code.files.utils.MyBufferUtils;

public class MouseInput implements MouseListener, MouseMotionListener {

	public int mouseDX;
	public int mouseDY;
	
	public int mouseX;
	public int mouseY;
	public boolean moved = false;
	
	public int mousePX;
	public int mousePY;
	public int mouseButton = 0;
	
	public void update(long window) {
//		GLFW.glfwSetCursorPos(window, DisplayManager.HEIGHT/2, DisplayManager.WIDTH/2);
		GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);

		GLFW.nglfwGetCursorPos(window, mouseX, mouseY);
	}
	
	public static double getCursorPosX(long window) {
	    DoubleBuffer posX = MyBufferUtils.createDoubleBuffer(1);
	    GLFW.glfwGetCursorPos(window, posX, null);
	    return posX.get(0);
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		mouseButton = e.getButton();
		mousePX = e.getX();
		mousePY = e.getY();
	}

	public void mouseReleased(MouseEvent e) {
		mouseButton = 0;
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
	
	// MouseMotionListener
	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		moved = true;
	}
	
}
