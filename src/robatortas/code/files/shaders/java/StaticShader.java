package robatortas.code.files.shaders.java;

import org.lwjgl.util.vector.Matrix4f;

import robatortas.code.files.entities.Camera;
import robatortas.code.files.shaders.ShaderProgram;
import robatortas.code.files.toolbov.Maths;

public class StaticShader extends ShaderProgram {

	// This class is to load stuff to the shader :D
	
	private static final String VERTEX_FILE = "src/robatortas/code/files/shaders/glsl/VertexShader.glsl";
	private static final String FRAGMENT_FILE = "src/robatortas/code/files/shaders/glsl/FragmentShader.glsl";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	protected void bindAttribs() {
		// 0 because our VAO is located on 0
		// "position" so OpenGL knows what the in position is on the shader file
		super.bindAttrib(0, "position");
		super.bindAttrib(1, "textureCoords");
	}
	
	// Gets uniform locations by inputting the string name of the exact uniform variable
	protected void getAllUniformLocations() {
		// Stores the uniform to the location_transformationMatrix variable
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
	// Loads transformation Matrix to the shader
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	// Loads projection Matrix to the shader
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(location_projectionMatrix, matrix);
	}
}