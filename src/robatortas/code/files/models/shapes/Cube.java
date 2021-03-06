package robatortas.code.files.models.shapes;

public class Cube {
	// Vertices
	public float[] vertices = {
//			// 0
//			-0.5f, 0.5f, 0,
//			// 1
//			-0.5f, -0.5f, 0,
//			// 3
//			0.5f, -0.5f, 0,
//			// 1 && 2 (connects 1 with 2)
//			0.5f, 0.5f, 0
			
			// X    Y    Z
			-0.5f,0.5f,0,
			-0.5f,-0.5f,0,
			0.5f,-0.5f,0,
			0.5f,0.5f,0,
			
			-0.5f,0.5f,1,
			-0.5f,-0.5f,1,
			0.5f,-0.5f,1,
			0.5f,0.5f,1,
			
			0.5f,0.5f,0,
			0.5f,-0.5f,0,
			0.5f,-0.5f,1,	
			0.5f,0.5f,1,
			
			-0.5f,0.5f,0,
			-0.5f,-0.5f,0,
			-0.5f,-0.5f,1,
			-0.5f,0.5f,1,
			
			-0.5f,0.5f,1,
			-0.5f,0.5f,0,
			0.5f,0.5f,0,
			0.5f,0.5f,1,
			
			// X    Y    Z
			-0.5f,-0.5f,1, // 0
			-0.5f,-0.5f,0, // 1
			0.5f,-0.5f,0,// 2
			0.5f,-0.5f,1 // 3
	};
	
	public int[] indices = {
//			0, 1, 3,
//			3, 1, 2
			
			0,1,3,
			3,1,2,
			
			4,5,7,
			7,5,6,
			
			8,9,11,
			11,9,10,
			
			12,13,15,
			15,13,14,
			
			16,17,19,
			19,17,18,
			
			20,21,23,
			23,21,22
	};
	
	public float[] uvMapping = {
//			0, 0, // V0
//			0, 1, // V1
//			1, 1, // V2
//			1, 0 // V3
			
//			0,0,
//			0,1,
//			1,1,
//			1,0,
//			
//			0,0,
//			0,1,
//			1,1,
//			1,0,
//			
//			0,0,
//			0,1,
//			1,1,
//			1,0,
//			
//			0,0,
//			0,1,
//			1,1,
//			1,0,
//			
//			0,0,
//			0,1,
//			1,1,
//			1,0,
//			
//			0,0,
//			0,1,
//			1,1,
//			1,0
			
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
			0f,0f,
			0f,0.5f,
			0.5f,0.5f,
			0.5f,0f,
			
			// Bottom
			0.5f,0f,
			0.5f,0.5f,
			1,0.5f,
			1f,0,
	};
	
	// TODO: FINISH THIS BULLSHIT!
	public float[] normals = {
			// Sides
			0f,1f,0f,
			0f,1f,0f
	};
}
