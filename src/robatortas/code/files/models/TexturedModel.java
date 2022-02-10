package robatortas.code.files.models;

import robatortas.code.files.textures.ModelTexture;

public class TexturedModel {
	
	private Model model;
	private ModelTexture texture;
	
	// The model with the texture printed in it
	public TexturedModel(Model model, ModelTexture texture) {
		this.model = model;
		this.texture = texture;
	}
	
	public Model getModel() {
		return this.model;
	}
	
	public ModelTexture getTexture() {
		return this.texture;
	}
}
