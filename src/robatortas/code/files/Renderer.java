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
	
	// Prepares OpenGL to render game
	public void update() {
		GL11.glClearColor(0.5f, 0.5f, 1, 1);
		GL11.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
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
}
