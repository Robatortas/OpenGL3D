package robatortas.code.files.entities;

import org.lwjgl.util.vector.Vector3f;

import robatortas.code.files.Input.KeyInput;

public class Camera {
	
	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw;
	private float roll;
	
	private KeyInput input;
	
	public Camera(KeyInput input) {
		this.input = input;
	}

	public void move(long window) {
		input.update(window);
		if(input.up) {
			position.z -= 0.02f;
		}
		if(input.down) {
			position.z += 0.02f;
		}
		if(input.left) {
			position.x += 0.02f;
		}
		if(input.right) {
			position.x -= 0.02f;
		}
	}
	
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
