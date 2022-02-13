package robatortas.code.files;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import robatortas.code.files.models.Loader;
import robatortas.code.files.models.Model;
import robatortas.code.files.models.TexturedModel;
import robatortas.code.files.shaders.java.StaticShader;
import robatortas.code.files.textures.ModelTexture;

public class DisplayManager {
	
	public long window;
	
	private int WIDTH = 720;
	private int HEIGHT = 400;
	
	public Loader loader = new Loader();
	public Renderer renderer = new Renderer();
	
	public void create() {
		window();
	}
	
	public void window() {
		GLFW.glfwInit();
		
		
		window = GLFW.glfwCreateWindow(getWidth(), getHeight(), "OPENGL3D", MemoryUtil.NULL, MemoryUtil.NULL);
		
		if(!GLFW.glfwInit()) {
			try {
				throw new Exception("Unable to initialize GLFW window.");
			} catch(Exception e) {
			}
		}
		
		if(window == MemoryUtil.NULL) {
			try {
				throw new Exception("Unable to create window.");
			} catch(Exception e) {
			}
		}
		
		// Creates OpenGL on window
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
		
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, 1);
		
		/* INDICES
		 * 
		 * (1 connects with 3**)
		 * 
		 * 
		 * 		0	_______	 3
		 * 		|	|    /|	 |
		 * 	    |   |   / |	 |
		 * 	    |   |  /  |	 |
		 *	    V   | /   |	 V
		 *	    1   |/____|  2
		 *		------------>
		 */
		
		// Vertices
		float[] vertices = {
			// 0
			-0.5f, 0.5f, 0,
			// 1
			-0.5f, -0.5f, 0,
			// 3
			0.5f, -0.5f, 0,
			// 1 && 2 (connects 1 with 2)
			0.5f, 0.5f, 0
		};
		
		// Marks where each vertex is (in a weird way to bind them :D)
		int[] indices = {
				0, 1, 3,
				3, 1, 2
		};
		
		float[] uvMapping = {
				0, 0, // V0
				0, 1, // V1
				1, 1, // V2
				1, 0 // V3
		};
		
		GL30.glEnable(GL30.GL_DEPTH_TEST);
		GL30.glActiveTexture(GL30.GL_TEXTURE1);
		GL30.glEnable(GL30.GL_BLEND);
		GL30.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		
		GL30.glEnable(GL30.GL_TEXTURE_2D);
		
		StaticShader shader = new StaticShader();
		
		Model model = loader.loadToVAO(vertices, uvMapping, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("/textures/wonder-day-among-us-21.png")); ///textures/wonder-day-among-us-21.png
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		while(!shouldClose()) {
			renderer.update();
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			update();
		}
		
		clean();
		shader.cleanup();
	}
	
	public void update() {
//		GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		GLFW.glfwSwapBuffers(window);
		GLFW.glfwPollEvents();
	}
	
	public void render() {
		
	}
	
	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}
	
	public void clean() {
		loader.cleanup();
	}
	
	public int getWidth() {
		return this.WIDTH;
	}
	
	public int getHeight() {
		return this.HEIGHT;
	}
}
