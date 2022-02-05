package robatortas.code.files.models;

public class Model {
	
	private int vaoID;
	private int vertexCount;
	
	public Model(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}
	
	public int getVaoID() {
		return this.vaoID;
	}
	
	public int getVertexCount() {
		return this.vertexCount;
	}
}
