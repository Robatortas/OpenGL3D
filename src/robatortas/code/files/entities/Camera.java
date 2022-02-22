package robatortas.code.files.entities;

import org.lwjgl.util.vector.Vector3f;

import robatortas.code.files.Input.KeyInput;
import robatortas.code.files.Input.MouseInput;

public class Camera {
	
	private Vector3f position = new Vector3f(0, 1, 0);
	private float pitch;
	private float yaw;
	private float roll;
	
	private KeyInput key;
	private MouseInput mouse;
	
	public Camera(KeyInput key, MouseInput mouse) {
		this.key = key;
		this.mouse = mouse;
	}

	public void move(long window) {
		key.update(window);
		mouse.update(window);
		
		if(key.w) {
			position.z-= 0.05f;
		}
		if(key.s) {
			position.z += 0.05f;
		}
		if(key.a) {
			position.x -= 0.05f;
		}
		if(key.d) {
			position.x += 0.05f;
		}
		
		if(key.up) {
			pitch -= 1;
		}
		if(key.down) {
			pitch += 1;
		}
		if(key.left) {
			yaw -= 1;
		}
		if(key.right) {
			yaw += 1;
		}
		
		if(mouse.mouseButton == 0) {
//			yaw += angleChange;
			System.out.println(mouse.mouseDX);
		}
		
	}
	
	// Used primarily to get the camera position in the viewMatrix
	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}	
}
