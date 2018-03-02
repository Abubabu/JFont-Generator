package getImage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class getImage
{
	final private static Map<pixelPosition, RGBValue> pixels = new HashMap<pixelPosition, RGBValue>();
	final private int width;
	final private int height;
	
	public Map<pixelPosition, RGBValue> getPixels() {
		return pixels;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public getImage(String filepath) throws IOException
	{
		File file= new File(filepath);
		BufferedImage image = ImageIO.read(file);
		 
		this.width = image.getWidth();
		this.height = image.getHeight();
		  
		  for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
			  int pixel = image.getRGB(j, i);
			  int alpha = (pixel >> 24) & 0xFF;
			  int red = (pixel >> 16) & 0xFF;
			  int green = (pixel >> 8) & 0xFF;
			  int blue = (pixel) & 0xFF;
			  //System.out.println(i + " , " + j + "    :   " + alpha + " , " + red + " , " + green + " , " + blue);
			  pixels.put(new pixelPosition(j,i), new RGBValue(alpha,red,green,blue));
			 }
	      }
	}
    
	public static void main(String args[]) throws IOException{
		getImage test = new getImage("Twitter.png");	
    }
}