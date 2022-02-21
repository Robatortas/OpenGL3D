package robatortas.code.files;

import java.awt.Canvas;
import java.util.Random;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.util.vector.Vector3f;

import robatortas.code.files.Input.KeyInput;
import robatortas.code.files.entities.Camera;
import robatortas.code.files.entities.Entity;
import robatortas.code.files.entities.Light;
import robatortas.code.files.models.Loader;
import robatortas.code.files.models.Model;
import robatortas.code.files.models.ObjLoader;
import robatortas.code.files.models.TexturedModel;
import robatortas.code.files.models.shapes.Cube;
import robatortas.code.files.shaders.java.StaticShader;
import robatortas.code.files.textures.ModelTexture;

public class DisplayManager extends Canvas{
	
	private static final long serialVersionUID = 1L;

	public long window;
	
	private int WIDTH = 1024;
	private int HEIGHT = 720;
	
	public Loader loader = new Loader();
	
	public Random random = new Random();
	
	private KeyInput input;
	
	
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
		
		// Marks where each vertex is (in a weird way to bind them :D)
		
		GL30.glViewport(0, 0, WIDTH, HEIGHT);
//		GL30.glActiveTexture(GL30.GL_TEXTURE1);
//		GL30.glEnable(GL30.GL_BLEND);
//		GL30.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
//		
//		GL30.glEnable(GL30.GL_TEXTURE_2D);
		
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		Cube cube = new Cube();
		
		Model model = ObjLoader.load("res/models/pascal/pascal.obj", loader);//ObjLoader.load("res/models/myModel/myModel.obj", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("/textures/default.png")); ///textures/grass_block.png  "/models/myModel/sampleTexture.png"
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		Entity entity = new Entity(texturedModel, new Vector3f(0,-0.3f,-5f),0,0,0,0.2f);
		
		Light light = new Light(new Vector3f(5, 5, 0), new Vector3f(1,1,1));
		
		input = new KeyInput();
		addKeyListener(input);
		
		Camera camera = new Camera(input);
		
		while(!shouldClose()) {
			
			if(input.semicolon) {
				GL30.glPolygonMode(GL30.GL_FRONT_AND_BACK, GL30.GL_LINE);
			}
			
			entity.move(0, 0, -0.0000f);
			entity.rotate(0, 1, 0);
			camera.move(window);
			renderer.update();
			shader.start();
			renderer.render(entity, shader);
			shader.loadLight(light);
			shader.loadViewMatrix(camera);
			shader.stop();
			update();
		}
		
		destroyWindow();
		
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
