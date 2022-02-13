package robatortas.code.files.toolbov;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Maths {
	
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
		Matrix4f matrix = new Matrix4f();
		// Sets every matrix value to 0.0f
		matrix.identity();
		// Translates the matrix
		matrix.translate(translation, matrix);
		// Rotates each axis
		matrix.rotate((float) Math.toRadians(rx), new Vector3f(1,0,0), matrix);
		matrix.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0), matrix);
		matrix.rotate((float) Math.toRadians(rz), new Vector3f(0,0,1), matrix);
		// Scales each axis
		matrix.scale(new Vector3f(scale,scale,scale), matrix);
		return matrix;
	}
}
