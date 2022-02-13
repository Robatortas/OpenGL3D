package robatortas.code.files.shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public abstract class ShaderProgram {
	
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	public ShaderProgram(String vertexFile, String fragmentFile) {
		// Loads Shaders with the file directories
		vertexShaderID = loadShader(vertexFile, GL30.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile, GL30.GL_FRAGMENT_SHADER);
		// Creates Shader program
		programID = GL30.glCreateProgram();
		// Attaches shaders into program
		GL30.glAttachShader(programID, vertexShaderID);
		GL30.glAttachShader(programID, fragmentShaderID);
		// Binds the attributes to the VAO
		bindAttribs();
		// Links program
		GL30.glLinkProgram(programID);
		//Validates the program
		// Checks to see wether the executables contained in program can execute
		GL30.glValidateProgram(programID);
		getAllUniformLocations();
	}
	
	protected abstract void getAllUniformLocations();
	
	// Gets the uniform location from the shader classes
	public int getUniformLocation(String uniformName) {
		return GL30.glGetUniformLocation(programID, uniformName);
	}
	
	// When you want to use the program.
	public void start() {
		GL30.glUseProgram(programID);
	}
	
	// Stops the program
	public void stop() {
		GL30.glUseProgram(0);
	}
	
	public void cleanup() {
		stop();
		// Detach the shaders from the program
		GL30.glDetachShader(programID, vertexShaderID);
		GL30.glDetachShader(programID, fragmentShaderID);
		// Deletes shaders
		GL30.glDeleteShader(vertexShaderID);
		GL30.glDeleteShader(fragmentShaderID);
		// Deletes program
		GL30.glDeleteProgram(programID);
	}
	
	// Link up the inputs to the shader programs to one of the attribs of the VAO
	protected abstract void bindAttribs();
	
	// Binds the variableName in the glsl files with the number where the VAO attribs are stored
	protected void bindAttrib(int attrib, String variableName) {
		GL30.glBindAttribLocation(programID, attrib, variableName);
	}
	
	/* Reads the shaders and appends them into one single line so
	 * OpenGL can read the shader.
	 * Compiles it and checks for any errors
	 */
	private static int loadShader(String file, int type) {
		// Makes each line of the glsl files to be changed
		StringBuilder shaderSource = new StringBuilder();
		
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(file));
			// Temp line
			String buffer;
			// While the file still isn't fully read.
			// readLine() advances to the next line
			while((buffer = reader.readLine()) != null) {
				shaderSource.append(buffer).append("\n");
			}
		} catch (IOException e) {
			System.err.println("Unable to read file");
			e.printStackTrace();
		}
		
		// Creates shader depending on the type
		
		/* TYPES: 
		 * GL_VERTEX_SHADER
		 * GL_FRAGMENT_SHADER
		 */
		int shaderID = GL30.glCreateShader(type);
		// Passes source code to OpenGL
		GL30.glShaderSource(shaderID, shaderSource);
		// Compiles the shader
		GL20.glCompileShader(shaderID);
		// Error handling
		if(GL30.glGetShaderi(shaderID, GL30.GL_COMPILE_STATUS) == GL30.GL_FALSE) {
			System.out.println(GL30.glGetShaderInfoLog(shaderID, 1200));
			System.err.println("Unable to compile shader.");
			System.exit(0);
		}
		return shaderID;
	}
	
	// Loads a float from a shader class for usability in java
	protected void loadFloat(int location, float value) {
		GL30.glUniform1f(location, value);
	}
	
	// Loads a vector from a shader class for usability in java
	protected void loadVector(int location/*, Vector3f vector*/) {
		
	}
}