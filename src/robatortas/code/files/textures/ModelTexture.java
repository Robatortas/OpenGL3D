package robatortas.code.files.textures;

public class ModelTexture {
	
	private int textureID;
	
	// Inputs the texture ID, from the Loader class in the loadTexture(path) method
	public ModelTexture(int id) {
		this.textureID = id;
	}
	
	// Gets ID
	public int getID() {
		return this.textureID;
	}
}
