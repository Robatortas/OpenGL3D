package robatortas.code.files.math;

import static java.lang.Math.*;

public class Matrix4f {

	public float[] matrix = new float[4*4];
	
	// Makes a row of ones diagonally
	public static Matrix4f identity() {
		Matrix4f result = new Matrix4f();
		
		// Sets every matrix value to 0.0f
		for(int i = 0; i < 4*4; i++) {
			result.matrix[i] = 0.0f;
		}
		
		// Sets a 1.0f in the slot selected
		result.matrix[0+0*4] = 1.0f;
		result.matrix[1+1*4] = 1.0f;
		result.matrix[2+2*4] = 1.0f;
		result.matrix[3+3*4] = 1.0f;
		
		return result;
	}
	
	// Multiplies matrices
	public Matrix4f multiply(Matrix4f matrix) {
		Matrix4f result = new Matrix4f();
		
		for(int y = 0; y < 4; y++) {
			for(int x = 0; x < 4; x++) {
				float sum = 0.0f;
				for(int e = 0; e < 4; e++) {
					sum += this.matrix[e+y*4] * matrix.matrix[x+e*y];
				}
				result.matrix[x+y*4] = sum;
			}
		}
		return result;
	}
	
	// To move an object between 2D or 3D space
	// OpeGL takes Column Major Ordering
	public Matrix4f translate(Vector3f vector) {
		Matrix4f result = new Matrix4f();
		
		result.matrix[0+3*4] = vector.x;
		result.matrix[1+3*4] = vector.y;
		result.matrix[2+3*4] = vector.z;
		
		return result;
	}
	
	// Rotates matrices in 3D space
	public static Matrix4f rotate(float angle, float x, float y, float z) {
		Matrix4f result = identity();
		float r = (float) toRadians(angle);
		float cos = (float) cos(r);
		float sin = (float) sin(r);
		float omc = 1.0f - cos;
		
		result.matrix[0+0*4] = x*omc+cos;
		result.matrix[1+0*4] = y*x*omc+z*sin;
		result.matrix[2+0*4] = x*z*omc-y*sin;

		result.matrix[0+1*4] = x*y*omc-z*sin;
		result.matrix[1+1*4] = y*omc+cos;
		result.matrix[2+1*4] = y*z*omc+x*sin;
		
		result.matrix[0+2*4] = x*z*omc+y*sin;
		result.matrix[1+2*4] = y*z*omc-x*sin;
		result.matrix[2+2*4] = z*omc+cos;
		
		return result;
	}
	
	public static Matrix4f orthographics(float left, float right, float bottom, float top, float near, float far) {
		Matrix4f result = identity();
		
		result.matrix[0+0*4] = 2.0f / (right-left);

		result.matrix[1+1*4] = 2.0f / (top-bottom);
		
		result.matrix[2+2*4] = 2.0f / (near-far);

		result.matrix[0+3*4] = 2.0f / (left+right) / (left-right);
		result.matrix[1+3*4] = 2.0f / (top+bottom) / (top-bottom);
		result.matrix[2+3*4] = 2.0f / (near+far) / (near-far);
		
		return result;
	}
	
	public static Matrix4f perspective(float left, float right, float bottom, float top, float near, float far) {
		Matrix4f result = identity();
		
		result.matrix[0+0*4] = 2.0f / (right-left);
		result.matrix[1+1*4] = 2.0f / (top-bottom);
		
		result.matrix[0+2*4] = 2.0f / (right+left) / (right-left);
		result.matrix[1+2*4] = 2.0f / (top+bottom) / (top-bottom);
		result.matrix[2+2*4] = 2.0f / -(far+near) / (far-near);
		result.matrix[2+2*4] = 2.0f / -1;
		
		
		return result;
	}
}
