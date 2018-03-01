package getImage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class getImage
{
	private static Map<pixelPosition, RGBValue> pixels = new HashMap<pixelPosition, RGBValue>();
	private static int width;
	private static int height;
	  public static void main(String args[]) throws IOException{
	  File file= new File("your_file.jpg");
	  BufferedImage image = ImageIO.read(file);
	 
	  width = image.getWidth();
	  height = image.getHeight();
	  
	  for(int i = 0; i < height; i++) {
		for(int j = 0; j < width; j++) {
		  int pixel = image.getRGB(j, i);
		  int alpha = (pixel >> 24) & 0xff;
		  int red = (pixel >> 16) & 0xff;
		  int green = (pixel >> 8) & 0xff;
		  int blue = (pixel) & 0xff;
		  pixels.put(new pixelPosition(j,i), new RGBValue(alpha,red,green,blue));
		 }
      }
			
	  }
}