package robatortas.code.files;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import robatortas.code.files.models.Loader;
import robatortas.code.files.models.Model;
import robatortas.code.files.models.Renderer;

public class DisplayManager {
	
	public long window;
	
	private int WIDTH = 300;
	private int HEIGHT = 300;
	
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
		 * 		0	_______  3
		 * 		|   |    /|	 |
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
		
		Model model = loader.loadToVAO(vertices, indices);
		
		while(!shouldClose()) {
			renderer.update();
			renderer.render(model);
			update();
		}
		
		clean();
	}
	
	public void update() {
//		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GLFW.glfwSwapBuffers(window);
		GLFW.glfwPollEvents();
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
