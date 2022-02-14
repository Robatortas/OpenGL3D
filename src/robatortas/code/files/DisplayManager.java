package robatortas.code.files;

import java.util.Random;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.util.vector.Vector3f;

import robatortas.code.files.entities.Entity;
import robatortas.code.files.models.Loader;
import robatortas.code.files.models.Model;
import robatortas.code.files.models.TexturedModel;
import robatortas.code.files.shaders.java.StaticShader;
import robatortas.code.files.textures.ModelTexture;

public class DisplayManager {
	
	public long window;
	
	private int WIDTH = 1024;
	private int HEIGHT = 720;
	
	public Loader loader = new Loader();
	public Renderer renderer = new Renderer();
	
	public Random random = new Random();
	
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
		
		vSync(1);
		
		/* INDICES
		 * 
		 * (1 connects with 3**)
		 * 
		 * 
		 * 		0	______   3
		 * 		|	|    /|	 |
		 * 	    |   |   / |	 |
		 * 	    |   |  /  |	 |
		 *	    V   | /   |	 V
		 *	    1   |/____|  2
		 *		------------>
		 */
		
		// Vertices
		float[] vertices = {
//			// 0
//			-0.5f, 0.5f, 0,
//			// 1
//			-0.5f, -0.5f, 0,
//			// 3
//			0.5f, -0.5f, 0,
//			// 1 && 2 (connects 1 with 2)
//			0.5f, 0.5f, 0
				
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
				
				-0.5f,-0.5f,1,
				-0.5f,-0.5f,0,
				0.5f,-0.5f,0,
				0.5f,-0.5f,1
		};
		
		// Marks where each vertex is (in a weird way to bind them :D)
		int[] indices = {
//				0, 1, 3,
//				3, 1, 2
				
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
		
		float[] uvMapping = {
//				0, 0, // V0
//				0, 1, // V1
//				1, 1, // V2
//				1, 0 // V3
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		GL30.glActiveTexture(GL30.GL_TEXTURE1);
		GL30.glEnable(GL30.GL_BLEND);
		GL30.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		
		GL30.glEnable(GL30.GL_TEXTURE_2D);
		
		StaticShader shader = new StaticShader();
		
		Model model = loader.loadToVAO(vertices, uvMapping, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("/textures/face.png")); ///textures/wonder-day-among-us-21.png
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		Entity entity = new Entity(texturedModel, new Vector3f(0,0,0),0,0,0,1);
		Entity entity2 = new Entity(texturedModel, new Vector3f(0,0,0),0,0,0,1);
		
		while(!shouldClose()) {
			entity.move(0, 0, 0);
			entity.rotate(0.3f, 0, 0.3f);
			entity2.rotate(0.7f, 0, 0.7f);
			renderer.update();
			shader.start();
			renderer.render(entity, shader);
//			renderer.render(entity2, shader);
			shader.stop();
			update();
		}
		
		destroyWindow();
		
		clean();
		shader.cleanup();
	}
	
	public void update() {
//		GL30.glClear(GL30.GL_COLOR_BUFFER_BIT);
		GL30.glEnable(GL30.GL_DEPTH_TEST);
		GLFW.glfwSwapBuffers(window);
		GLFW.glfwPollEvents();
	}
	
	public void render() {
		
	}
	
	public void vSync(int interval) {
		GLFW.glfwSwapInterval(interval);
	}
	
	public void destroyWindow() {
		GL.destroy();
		// Destroys window and it's components
		GLFW.glfwDestroyWindow(window);
		// Terminates GLFW library
		GLFW.glfwTerminate();
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
