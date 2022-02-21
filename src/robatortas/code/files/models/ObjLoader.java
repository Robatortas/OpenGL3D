package robatortas.code.files.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class ObjLoader {
	
	public static Model load(String path, Loader loader) {
		FileReader read = null;
		
		try {
			read = new FileReader(new File(path));
		} catch (FileNotFoundException e) {
			System.err.println("Unable to load OBJ file.");
			e.printStackTrace();
		}
		
		BufferedReader bufferRead = new BufferedReader(read);
		String buffer;
		
		// Contains each piece of data individually
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		List<Vector2f> textures = new ArrayList<Vector2f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		
		// Arrays where all the data will be stored and returned as a VAO
		float[] verticesArray = null;
		float[] textureArray = null;
		float[] normalsArray = null;
		int[] indicesArray = null;
		
		try {
			while((buffer = bufferRead.readLine()) != null) {
				String[] currentLine = buffer.split(" ");
				
				if(buffer.startsWith("v ")) {
					// Applies each vertex stored on each "v" in the OBJ file in a Vector3f
					// And the same happens for all the other ones
					Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
					vertices.add(vertex);
				} else if(buffer.startsWith("vt ")) {
					Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]));
					textures.add(texture);
				} else if(buffer.startsWith("vn ")) {
					Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
					normals.add(normal);
				} else if(buffer.startsWith("f ")) {
					// Here it's just making the f contain the actual necessary things to be a FACE
					// Multiplied by two because each texture has 2 floats
					textureArray = new float[vertices.size()*2];
					normalsArray = new float[vertices.size()*3];
					break;
				}
			}
			
			// While the file hasn't ended
			while(buffer != null) {
				// f is for face btw
				if(!buffer.startsWith("f ")) {
					buffer = bufferRead.readLine();
					continue;
				}
				String[] currentLine = buffer.split(" ");
				// Splits each vertex into the indexes on current line (EXAMPLE:  v  2.5956 40.5000 1.5244)
				String[] vertex1 = currentLine[1].split("/");
				String[] vertex2 = currentLine[2].split("/");
				String[] vertex3 = currentLine[3].split("/");
				
				// processes the vertex and adds makes textures, indices and normals be in pair with the current processed vertex
				// Contains each part of the data (EXAMPLE:  v  2.5956 40.5000 1.5244)
				//												vertex1,vertex2,vertex3		variables!
				processVertex(vertex1, indices, textures, normals, textureArray, normalsArray);
				processVertex(vertex2, indices, textures, normals, textureArray, normalsArray);
				processVertex(vertex3, indices, textures, normals, textureArray, normalsArray);
				buffer = bufferRead.readLine();
			}
			bufferRead.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// Convert vertex list to a vertex array (new float array)
		verticesArray = new float[vertices.size()*3];
		// Converts indices array into an int array
		// Adds all the indices and stores them in this array
		indicesArray = new int[indices.size()];
		
		// copy across all data
		// This has all the vertices one by one
		int vertexPointer = 0;
		for(Vector3f vertex:vertices) {
			verticesArray[vertexPointer++] = vertex.x;
			verticesArray[vertexPointer++] = vertex.y;
			verticesArray[vertexPointer++] = vertex.z;
		}
		
		for(int i = 0; i < indices.size(); i++) {
			indicesArray[i] = indices.get(i);
		}
		
		// Returns a VAO with all the vertex, texture and index data
		return loader.loadToVAO(verticesArray, textureArray, normalsArray, indicesArray);
	}

	// Processes the vertex and adds makes textures, indices and normals be in pair with the current processed vertex
	private static void processVertex(String[] vertexData, List<Integer> indices, List<Vector2f> textures, List<Vector3f> normals, float[] textureArray, float[] normalsArray) {
		// - 1 because OBJ files starts at 1 and the array starts at 0
		int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
		indices.add(currentVertexPointer);
		// Gets texture that corresponds to it's vertex
		Vector2f currentTex = textures.get(Integer.parseInt(vertexData[1]) - 1);
		textureArray[currentVertexPointer*2] = currentTex.x;
		textureArray[currentVertexPointer*2+1] = 1 - currentTex.y;
		Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2]) - 1);
		
		// Keeps adding 1 because 
		normalsArray[currentVertexPointer*3] = currentNorm.x;
		normalsArray[currentVertexPointer*3+1] = currentNorm.y;
		normalsArray[currentVertexPointer*3+2] = currentNorm.z;
	}
}
