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
		
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, 1);
		
		float[] vertices = {
			-0.5f, 0.5f, 0f,
			-0.5f, -0.5f, 0f,
			0.5f, -0.5f, 0f
		};
		
		Model model = loader.loadToVAO(vertices);
		
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
