package CreationAnalysis;

import java.io.IOException;

import getImage.CardinalPixels;
import getImage.RGBValue;
import getImage.getImage;

public class Scanner {
	
	public static void scanner(String filepath) throws IOException
	{
		getImage image = new getImage(filepath); 	
		getImage imageConverted = new getImage(image.getBW());	
		RGBValue[][] imagePix = imageConverted.getPixelsArray();		
		CardinalPixels imagelocation = new CardinalPixels(imageConverted);
		
	}
}
