package robatortas.code.files.textures;

public class ModelTexture {
	
	private int textureID;
	
	private float reflecDamper = 0;
	private float reflectivity = 0;

	// Inputs the texture ID, from the Loader class in the loadTexture(path) method
	public ModelTexture(int id) {
		this.textureID = id;
	}
	
	// Gets ID
	public int getID() {
		return this.textureID;
	}
	
	// Getters and setters for reflectivity and damper
	public float getReflecDamper() {
		return reflecDamper;
	}

	public void setReflecDamper(float reflecDamper) {
		this.reflecDamper = reflecDamper;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}
}
