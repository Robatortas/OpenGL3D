package robatortas.code.files;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import robatortas.code.files.models.Model;
import robatortas.code.files.models.TexturedModel;

public class Renderer {
	
	// NOTE: If you want to change a VAO or VBO, you NEED TO BIND IT!
	
	// Prepares OpenGL to render game
	public void update() {
		GL11.glClearColor(1, 0, 0, 1);
		GL11.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
	}
	
	public void render(TexturedModel texturedModel) {
		Model model = texturedModel.getModel();
		GL30.glBindVertexArray(model.getVaoID());
		// Activates attribList where data is stored
		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);
		GL30.glActiveTexture(GL30.GL_TEXTURE0);
		GL30.glBindTexture(GL30.GL_TEXTURE_2D, texturedModel.getTexture().getID());
//		GL30.glEnable(GL30.GL_TEXTURE_2D); 
		// Draws elements
		GL30.glDrawElements(GL30.GL_TRIANGLES, model.getVertexCount(), GL30.GL_UNSIGNED_INT, 0);
		// Disables attrib list once we're done on attribList 0
		GL30.glDisableVertexAttribArray(0);
		GL30.glDisableVertexAttribArray(1);
//		GL30.glDisable(GL30.GL_TEXTURE_2D);
		// Deselects the array
		GL30.glBindVertexArray(0);
	}
}
