package robatortas.code.files.shaders.java;

import org.lwjgl.util.vector.Matrix4f;

import robatortas.code.files.entities.Camera;
import robatortas.code.files.entities.Light;
import robatortas.code.files.shaders.ShaderProgram;
import robatortas.code.files.toolbox.Maths;

public class StaticShader extends ShaderProgram {

	// This class is to load stuff to the shader :D
	
	private static final String VERTEX_FILE = "src/robatortas/code/files/shaders/glsl/VertexShader.glsl";
	private static final String FRAGMENT_FILE = "src/robatortas/code/files/shaders/glsl/FragmentShader.glsl";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColor;
	
	private int location_reflectivity;
	private int location_reflecDamper;
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	// binds attrib to a certain variable in the vertex shader
	protected void bindAttribs() {
		// 0 because our VAO is located on 0
		// "position" so OpenGL knows what the in position is on the shader file
		
		// OpenGL knows the data of info because it's binding all the data on VAO 0
		// Hence the 0 on the input
		super.bindAttrib(0, "position");
		super.bindAttrib(1, "textureCoords");
		super.bindAttrib(2, "normal");
	}
	
	// Gets uniform locations by inputting the string name of the exact uniform variable
	protected void getAllUniformLocations() {
		// Stores the uniform's location to the location_transformationMatrix variable
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColor = super.getUniformLocation("lightColor");
		location_reflectivity = super.getUniformLocation("reflectivity");
		location_reflecDamper = super.getUniformLocation("reflecDamper");
	}
	
	// Loads specularLighting to the shader with the inputs given
	public void loadSpecular(float reflectivity, float damper) {
		super.loadFloat(location_reflectivity, reflectivity);
		super.loadFloat(location_reflecDamper, damper);
	}
	
	// Loads viewMatrix to the shader
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

	// Loads light to the shader
	public void loadLight(Light light) {
		// location_lightPosition gets the x,y,z positions from the light.getPosition()
		// and location_lightPosition gives the value to the lightPosition from the vertex shader
		super.loadVector(location_lightPosition, light.getPosition());
		super.loadVector(location_lightColor, light.getColor());
	}
}