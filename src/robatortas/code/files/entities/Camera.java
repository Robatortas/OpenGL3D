package robatortas.code.files.entities;

import org.lwjgl.util.vector.Vector3f;

import robatortas.code.files.Input.KeyInput;

public class Camera {
	
	private Vector3f position = new Vector3f(0, 1, 0);
	private float pitch;
	private float yaw;
	private float roll;
	
	private KeyInput input;
	
	public Camera(KeyInput input) {
		this.input = input;
	}

	public void move(long window) {
		input.update(window);
		if(input.w) {
			position.z-= 0.05f;
		}
		if(input.s) {
			position.z += 0.05f;
		}
		if(input.a) {
			position.x -= 0.05f;
		}
		if(input.d) {
			position.x += 0.05f;
		}
		
		if(input.up) {
			pitch -= 1;
		}
		if(input.down) {
			pitch += 1;
		}
		if(input.left) {
			yaw -= 1;
		}
		if(input.right) {
			yaw += 1;
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
