package robatortas.code.files.models;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class Loader {
	
	// Lists to keep track of VAOs and VBOs so we can delete them once the program closes
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	
	// Takes in positions of the model's vertices and returns info as a Model Object.
	public Model loadToVAO(float[] positions) {
		int vaoID = createVAO();
		// Data is in attribList 0!
		// AttribLists are all the attributes that a vertex contains, like the position or the color of the vertex
		// So with this function we are inputting basic configurations for the attributes, like the size of the vertices(x,y,z)
		storeDataInAttribList(0, positions);
		unbindVAO();
		// 3 because each vertex has 3 floats (AKA: positions) which are: X,Y,Z
		return new Model(vaoID, positions.length/3);
	}
	
	// Creates a new VAO
	private int createVAO() {
		// Creates an empty VAO and return the ID of that VAO
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		// Activates it (Binds it to a vertex array)
		// Makes the new array active, creating it if necessary
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	// attribNumber = where we want to store the float[] data
	private void storeDataInAttribList(int attribNumber, float[] data) {
		int vboID = GL30.glGenBuffers();
		vbos.add(vboID);
		// Selects it basically
		// GL30.GL_ARRAY_BUFFER specifies what kind of VBO it's going to be
		GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		// Lets OpenGL know that we will never change data from this buffer, to just draw it on screen basically.
		GL30.glBufferData(GL30.GL_ARRAY_BUFFER, buffer, GL30.GL_STATIC_DRAW);
		// Specifies the properties of the attribList
		// The number of the attribute, length of each vertex (x,y,z), type of data, normalized, any other data between vertices?(like positions, and colors and stuff), start at where in data?
		GL30.glVertexAttribPointer(attribNumber, 3, GL11.GL_FLOAT, false, 0, 0);
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO() {
		// 0 = unbind
		GL30.glBindVertexArray(0);
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		// Puts data in buffer
		buffer.put(data);
		// We finished writing to it and prepares to be read from
		buffer.flip();
		return buffer;
	}
	
	// Cleans VAOs and VBOs
	public void cleanup() {
		for(int vao:vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo:vbos) {
			GL30.glDeleteBuffers(vbo);
		}
	}
}
