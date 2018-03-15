package getImage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import java.awt.Color;
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
		//System.out.print(image.getType());
		 
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
	public static RGBValue getRGB(int xcoord, int ycoord)
	{
		for (pixelPosition key : pixels.keySet()) {
			if(key.getxPos() == xcoord && key.getyPos() == ycoord)
			{
				return pixels.get(key);
			}
		}
		return null;
	}
	public static void main(String args[]) throws IOException{
		
		getImage test = new getImage("colors.png");	
		Map<pixelPosition, RGBValue> pixel = test.getPixels();
	//	System.out.println("hashmap: RGB " + pixel.get(new pixelPosition(500,500)).getR() + " " +  pixel.get(new pixelPosition(500,500)).getG() + " " + pixel.get(new pixelPosition(500,500)).getB());
		
		RGBValue[][] potato = test.getArrayRGB();
		System.out.print(potato[0][0].getR() + " , " + potato[0][0].getG() + " , " + potato[0][0].getB() );
	//	System.out.println(test.getRGB(0,0).getR());
		
		
		/*RGBValue[][] testvalues = test.getArrayRGB();
		System.out.println("ArrayValues: RGB" + testvalues[0][0].getR() + " " + testvalues[0][0].getG() + " " + testvalues[0][0].getB());*/
	}
	/*public static void main(String args[]) throws IOException{
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
	
	for(pixelPosition pixel : test.getPixels().keySet()) {  // the long list of colors is required. for each color we must get the orignal pixel using the pixellocation which is by gettingpixelsthenget class
		Color color = new Color(test.getPixels().get(pixel).getR(),test.getPixels().get(pixel).getG(),test.getPixels().get(pixel).getB());
		newimg.setRGB(pixel.getxPos(), pixel.getyPos(), color.getRGB());
	}
	System.out.println("hashmap: RGB " + pixels.get(new pixelPosition(0,0)).getR() + " " +  pixels.get(new pixelPosition(0,0)).getG() + " " + pixels.get(new pixelPosition(0,0)).getB());
	RGBValue[][] testvalues = test.getArrayRGB();
	System.out.println("ArrayValues: RGB" + testvalues[0][0].getR() + " " + testvalues[0][0].getG() + " " + testvalues[0][0].getB());

	}*/
	
	public pixelPosition[][] getArrayPixels() {
		 pixelPosition[][] positions = new pixelPosition[width][height];
		 int arrayHeight = width - 1;
		 int arrayWidth = height - 1;
		 for(pixelPosition pos : this.getPixels().keySet()) {
			 positions[arrayWidth][arrayHeight] = pos;
			 arrayHeight++;
			 if(arrayWidth >= width) {
				 arrayWidth = 0;
				 arrayHeight++;
			 }
		 }
		 return positions;
	}
	
	public RGBValue[][] getArrayRGB() {
		 RGBValue[][] positions = new RGBValue[width][height];
		 int arrayHeight = 0;
		 int arrayWidth = width - 1;
		 for(RGBValue pos : this.getPixels().values()) {  //	THE HASH MAP IS JUMPING EVERYWHERE FOR THE COLORS
			 positions[arrayWidth][arrayHeight] = pos;
			 arrayHeight++;
			 if(arrayHeight == 500) {
				 arrayHeight = 0;
				 arrayWidth--;
			 }
		 }
		 return positions;
	}
	public BufferedImage getBW() throws IOException {
		int sum = 0;
		 BufferedImage orignal = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB); 
		 
		     
		 
		getImage test = new getImage("Panda.png");	
		
		for(pixelPosition pixel : test.getPixels().keySet()) {  // the long list of colors is required. for each color we must get the orignal pixel using the pixellocation which is by gettingpixelsthenget class
			Color color = new Color(test.getPixels().get(pixel).getR(),test.getPixels().get(pixel).getG(),test.getPixels().get(pixel).getB());
			orignal.setRGB(pixel.getxPos(), pixel.getyPos(), color.getRGB());
		}
		
			for(RGBValue pos : test.getPixels().values()) {
				/*int avgtotalrgb = pos.getR() + pos.getB() + pos.getG();
				sum = avgtotalrgb + sum;*/
			
			}
			//sum = sum / (test.getHeight() * test.getWidth());

	
		for(RGBValue pos2 : test.getPixels().values()) {
			if((pos2.getR() + pos2.getG() + pos2.getB()) > 382) {
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
		BufferedImage newimg = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB); //copy paste this line outside and
	
		for(pixelPosition pixel : test.getPixels().keySet()) {  // the long list of colors is required. for each color we must get the orignal pixel using the pixellocation which is by gettingpixelsthenget class
			Color color = new Color(test.getPixels().get(pixel).getR(),test.getPixels().get(pixel).getG(),test.getPixels().get(pixel).getB());
			newimg.setRGB(pixel.getxPos(), pixel.getyPos(), color.getRGB());
		}
		return newimg;
		
	}
		 
}
