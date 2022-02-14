package robatortas.code.files.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import robatortas.code.files.models.Loader;

public class ImageUtils {
	
	public int width, height;
	
	public int[] data, pixels;
	
	public int[] loadImage(BufferedImage image, int width, int height, String path) {
		this.width = width;
		this.height = height;
		
		// Typical BufferedImage code
		try {
			image = ImageIO.read(Loader.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width*height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Convert image data to usable OpenGL data
		data = new int[width*height];
		for(int i = 0; i < width*height; i++) {
			// each hex number = 4 bits
			int a = (pixels[i] & 0xff000000) >> 24;
			int r = (pixels[i] & 0xff0000) >> 16;
			int g = (pixels[i] & 0xff00) >> 8;
			int b = (pixels[i] & 0xff);
			
			data[i] = a<<24|b<<16|g<<8|r; // a<<24|b<<16|g<<8|r
		}
		return data;
	}
}
