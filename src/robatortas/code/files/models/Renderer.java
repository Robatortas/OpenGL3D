package robatortas.code.files.models;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class Renderer {
	
	// NOTE: If you want to change a VAO or VBO, you NEED TO BIND IT!
	
	// Prepares OpenGL to render game
	public void update() {
		GL11.glClearColor(1, 0, 0, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	public void render(Model model) {
		GL30.glBindVertexArray(model.getVaoID());
		// Activates attribList where data is stored
		GL30.glEnableVertexAttribArray(0);
		// Draws arrays
		GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, model.getVertexCount());
		// Disables attrib list once we're done on attribList 0
		GL30.glDisableVertexAttribArray(0);
		// Deselects the array
		GL30.glBindVertexArray(0);
	}
}
