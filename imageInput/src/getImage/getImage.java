package getImage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
		int sum = 0;
		getImage test = new getImage("twitch.png");	
			for(RGBValue pos : test.getPixels().values()) {
				int avgtotalrgb = pos.getR() + pos.getB() + pos.getG();
				sum = avgtotalrgb + sum;
			}
			sum = sum / (test.getHeight() * test.getWidth());

	
	for(RGBValue pos2 : test.getPixels().values()) {
		if((pos2.getR() + pos2.getG() + pos2.getB()) > sum) {
			pos2.setR(255);
			pos2.setB(255);
			pos2.setG(255);
		}
		else {
			pos2.setR(0);
			pos2.setB(0);
			pos2.setG(0);
		}
	}
	BufferedImage newimg = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
}
}
