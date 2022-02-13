package robatortas.code.files.shaders.java;

import robatortas.code.files.shaders.ShaderProgram;

public class StaticShader extends ShaderProgram {

	private static final String VERTEX_FILE = "./src/robatortas/code/files/shaders/glsl/VertexShader.glsl";
	private static final String FRAGMENT_FILE = "./src/robatortas/code/files/shaders/glsl/FragmentShader.glsl";
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	protected void bindAttribs() {
		// 0 because our VAO is located on 0
		// "position" so OpenGL knows what the in position is on the shader file
		super.bindAttrib(0, "position");
		super.bindAttrib(1, "textureCoords");
	}
	
	protected void getAllUniformLocations() {
		
	}
}