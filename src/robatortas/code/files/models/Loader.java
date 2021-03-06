package robatortas.code.files.models;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;

import robatortas.code.files.utils.ImageUtils;
import robatortas.code.files.utils.MyBufferUtils;

public class Loader {
	
	public MyBufferUtils bufferUtils = new MyBufferUtils();
	
	public ImageUtils imageUtils = new ImageUtils();
	
	// Lists to keep track of VAOs and VBOs so we can delete them once the program closes
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	
	// Takes in positions of the model's vertices and returns info as a Model Object.
	public Model loadToVAO(float[] positions, float[] textureCoords, float[] normals, int[] indices) {
		int vaoID = createVAO();
		// Data is in attribList 0!
		// AttribLists are all the attributes that a vertex contains, like the position or the color of the vertex
		// So with this function we are inputting basic configurations for the attributes, like the size of the vertices(x,y,z)
		storeDataInAttribList(0, 3, positions);
		// attrib 1 = texture VAO
		// 2 because it's only a 2 float vertex (x, y)
		storeDataInAttribList(1, 2, textureCoords);
		storeDataInAttribList(2, 3, normals);
		bindIndicesBuffer(indices);
		unbindVAO();
		// 3 because each vertex has 3 floats (AKA: positions) which are: X,Y,Z
		return new Model(vaoID, indices.length);
	}
	
	public int width, height;
	public int texture;
	
	// Loads texture
	public int loadTexture(String path) {
		int[] pixels = null;
		// Typical BufferedImage code
		try {
			BufferedImage image = ImageIO.read(Loader.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width*height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Convert image data to usable OpenGL data
		int[] data = new int[width*height];
		for(int i = 0; i < width*height; i++) {
			// each hex number = 4 bits
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a<<24|b<<16|g<<8|r; // a<<24|b<<16|g<<8|r
		}
		
		// Generate texture
		texture = GL30.glGenTextures();
		// Bind texture
		GL30.glBindTexture(GL30.GL_TEXTURE_2D, texture);
		// Texture parameters
		GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_WRAP_S, GL30.GL_REPEAT);
		GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_WRAP_T, GL30.GL_REPEAT);
		GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MIN_FILTER, GL30.GL_NEAREST);
		GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MAG_FILTER, GL30.GL_NEAREST);
		
		// Tells OpenGL how it's going to receive image data
		GL30.glTexImage2D(GL30.GL_TEXTURE_2D, 0, GL30.GL_RGBA, width, height, 0, GL30.GL_RGBA, GL30.GL_UNSIGNED_BYTE, bufferUtils.createIntBufferFromArray(data));
		// Unbind texture
		GL30.glBindTexture(GL30.GL_TEXTURE_2D, 0);
		
		// Adds the texture to the textures list
		textures.add(texture);
				
		return texture;
	}
	
	public int getTextureID() {
		return texture;
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
	// Basically, converts an array to a VBO
	private void storeDataInAttribList(int attribNumber, int coordinateSize, float[] data) {
		int vboID = GL30.glGenBuffers();
		vbos.add(vboID);
		// Selects it basically
		// GL30.GL_ARRAY_BUFFER specifies what kind of VBO it's going to be
		GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = bufferUtils.createFloatBuffer(data);
		// Lets OpenGL know that we will never change data from this buffer, to just draw it on screen basically.
		GL30.glBufferData(GL30.GL_ARRAY_BUFFER, buffer, GL30.GL_STATIC_DRAW);
		// Specifies the properties of the attribList
		// The number of the attribute, length of each vertex (x,y,z), type of data, normalized, any other data between vertices?(like positions, and colors and stuff), start at where in data?
		GL30.glVertexAttribPointer(attribNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
		GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO() {
		// 0 = unbind
		GL30.glBindVertexArray(0);
	}
	
	// Does the same as the attribList function, but for Buffer Indices
	public void bindIndicesBuffer(int[] indices) {
		// Creates Buffer
		int vboID = GL30.glGenBuffers();
		// Adds it to the vbo management list
		vbos.add(vboID);
		// Binds the buffer (Makes it usable/active)
		GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, vboID);
		// Creates a new BufferInt with the storeDataInIntBuffer function, inputing the indices
		IntBuffer buffer = bufferUtils.createIntBufferFromArray(indices);
		// Creates Buffer Data
		GL30.glBufferData(GL30.GL_ELEMENT_ARRAY_BUFFER, buffer, GL30.GL_STATIC_DRAW);
	}
	
	// Cleans VAOs and VBOs
	public void cleanup() {
		for(int vao:vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo:vbos) {
			GL30.glDeleteBuffers(vbo);
		}
		for(int tex:textures) {
			GL30.glDeleteTextures(tex);
		}
	}
}