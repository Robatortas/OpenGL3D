package robatortas.code.files;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import robatortas.code.files.entities.Entity;
import robatortas.code.files.models.Model;
import robatortas.code.files.models.TexturedModel;
import robatortas.code.files.shaders.java.StaticShader;
import robatortas.code.files.toolbov.Maths;

public class Renderer {
	
	// NOTE: If you want to change a VAO or VBO, you NEED TO BIND IT!
	
	private Matrix4f projectionMatrix = new Matrix4f();
	
	public Renderer(StaticShader shader) {
		createProjectionMatrix();
		GL30.glEnablei(GL30.GL_PROJECTION, 1);
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	// Prepares OpenGL to render game
	public void update() {
		GL30.glEnable(GL30.GL_DEPTH_TEST);
		GL11.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.5f, 0.5f, 1, 1);
	}
	
	/* Shader so that we can upload the entities transformation, so it renders the entity model in a different position
	 * making it able to render various exact models in the same VAO
	 */
	public void render(Entity entity, StaticShader shader) {
		TexturedModel texturedModel = entity.getModel();
		Model model = texturedModel.getModel();
		GL30.glBindVertexArray(model.getVaoID());
		// Activates attribList where data is stored
		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);
		// Matrix
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		// Loads transformationMatrix to the shader
		shader.loadTransformationMatrix(transformationMatrix);
		// Loads the textures to the shader
		GL30.glActiveTexture(GL30.GL_TEXTURE0);
		GL30.glBindTexture(GL30.GL_TEXTURE_2D, texturedModel.getTexture().getID());
		// Draws elements
		GL30.glDrawElements(GL30.GL_TRIANGLES, model.getVertexCount(), GL30.GL_UNSIGNED_INT, 0);
		// Disables attrib list once we're done on attribList 0
		GL30.glDisableVertexAttribArray(0);
		GL30.glDisableVertexAttribArray(1);
		// Deselects the array
		GL30.glBindVertexArray(0);
	}
	
	private DisplayManager display = new DisplayManager();
	
	private static final float FOV = 80;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	
	private void createProjectionMatrix() {
		float aspectRatio = display.getWidth() / display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}
}
