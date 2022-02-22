package robatortas.code.files.utils;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;


public class MyBufferUtils {
	
	public IntBuffer createIntBufferFromArray(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public ByteBuffer createByteBufferFromArray(byte[] data) {
		ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public FloatBuffer createFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		// Puts data in buffer
		buffer.put(data);
		// We finished writing to it and prepares to be read from
		buffer.flip();
		return buffer;
	}

	public IntBuffer createIntBuffer(int data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public FloatBuffer createFloatBuffer(float data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer((int) data);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	public ByteBuffer createByteBuffer(byte data) {
		ByteBuffer buffer = BufferUtils.createByteBuffer(data);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	public static DoubleBuffer createDoubleBuffer(double data) {
		DoubleBuffer buffer = BufferUtils.createDoubleBuffer((int) data);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
