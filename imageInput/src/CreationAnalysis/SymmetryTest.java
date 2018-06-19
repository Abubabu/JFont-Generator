package CreationAnalysis;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import LetterRecognition.Overlay3;
import LetterRecognition.OverlayTest;
import getImage.getImage;

public class SymmetryTest {
	public static void main(String[] args) throws IOException
	{
		test();
	}

	public static void test() throws IOException
	{
		OverlayTest test = new OverlayTest();
		File[] library = test.listOfImages("Alphabet");
		
		
			getImage image = new getImage("Random/MYA.png");
			BufferedImage YFlip = Reflection.reflectorYAxis(image);
			BufferedImage XFlip = Reflection.reflectorXAxis(image);
			
			ImageIO.write(YFlip, "bmp",new File("C:\\Users\\Salehaakter\\git\\JFont-Generator\\imageInput\\YFlip.bmp"));	
			ImageIO.write(XFlip, "bmp",new File("C:\\Users\\Salehaakter\\git\\JFont-Generator\\imageInput\\XFlip.bmp"));	
			
		//	System.out.println(library[0].getName().substring(library[0].getName().length()-5));
			
			/*Overlay3 symmetry = new Overlay3(x.getAbsolutePath());
			System.out.print("Verticle: " + symmetry.overlay(YFlip) + "  ");
			System.out.println("Horizontal: " + symmetry.overlay(XFlip));*/
		
	}
}
