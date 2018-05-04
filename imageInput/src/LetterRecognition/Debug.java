package LetterRecognition;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Debug 
{	
	
    public static void main( String[] args )
    {
    	/*BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 

    	  for (int y = 0; y < height; y++) {
    	     for (int x = 0; x < width; x++) {
    	        int rgb = r[y][x];
    	        rgb = (rgb << 8) + g[y][x]; 
    	        rgb = (rgb << 8) + b[y][x];
    	        image.setRGB(x, y, rgb);
    	     }
    	  }*/

    //	  File outputFile = new File("/output.bmp");
    //	  ImageIO.write(image, "bmp", outputFile);
    }
    public static void createImg(BufferedImage image)
    {
    	
        try {       
           // ImageIO.write(image, "jpg",new File("C:\\Users\\Salehaakter\\git\\JFont-Generator\\imageInput\\out.jpg"));
           // ImageIO.write(image, "gif",new File("C:\\Users\\Salehaakter\\git\\JFont-Generator\\imageInput\\out.gif"));
            ImageIO.write(image, "bmp",new File("C:\\Users\\Salehaakter\\git\\JFont-Generator\\imageInput\\out.bmp"));
            
        } catch (IOException e) {
        	e.printStackTrace();
        }
        System.out.println("Done");
    }
}