package robatortas.code.files.models.shapes;

public class Pyramid {
	
	public float[] vertices = {
			// Sides
			0.0f,0.5f,0.5f,
			-0.5f,-0.5f,0,
			0.5f,-0.5f,0,
			0.0f,0.5f,0.5f,
			
			-0.0f,0.5f,1,
			-0.5f,-0.5f,1,
			0.5f,-0.5f,1,
			0.0f,0.5f,1,
			
			0.0f,0.5f,0,
			0.5f,-0.5f,0,
			0.5f,-0.5f,1,	
			0.0f,0.5f,1,
			
			0.0f,0.5f,0,
			-0.5f,-0.5f,0,
			-0.5f,-0.5f,1,
			0.0f,0.5f,1,
			
			// Bottom
			-0.5f,-0.5f,1,
			-0.5f,-0.5f,0,
			0.5f,-0.5f,0,
			0.5f,-0.5f,1
	};
	
	public int[] indices = {
			0,1,3,
			3,1,2,
			
			3,5,3,
			3,5,6,
			
			3,9,3,
			3,9,10,
			
			3,13,3,
			3,13,14,
			
			16,17,19,
			19,17,18,
	};
	
	public float[] uvMapping = {
			// Sides
			0f,0.5f,
			0f,1f,
			1f/2f,1f,
			1f/2f,0.5f,
			
			0f,0.5f,
			0f,1f,
			1f/2f,1f,
			1f/2f,0.5f,
			
			0f,0.5f,
			0f,1f,
			1f/2f,1f,
			1f/2f,0.5f,
			
			0f,0.5f,
			0f,1f,
			1f/2f,1f,
			1f/2f,0.5f,
			
			// Top
			0.5f,0f,
			0.5f,0.5f,
			1,0.5f,
			1f,0,
	};
	
}
