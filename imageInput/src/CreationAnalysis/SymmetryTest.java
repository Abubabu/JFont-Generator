package CreationAnalysis;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import LetterRecognition.Overlay3;
import getImage.getImage;

public class SymmetryTest {
	public static void main(String[] args) throws IOException
	{
		test("Alphabet/A.png");
	}

	public static void test(String filepath) throws IOException
	{
			getImage image = new getImage(filepath);
			BufferedImage YFlip = Reflection.reflectorYAxis(image);
			BufferedImage XFlip = Reflection.reflectorXAxis(image);
			
			ImageIO.write(YFlip, "bmp",new File("C:\\Users\\Salehaakter\\git\\JFont-Generator\\imageInput\\YFlip.bmp"));	
			ImageIO.write(XFlip, "bmp",new File("C:\\Users\\Salehaakter\\git\\JFont-Generator\\imageInput\\XFlip.bmp"));	
			
			System.out.println(filepath.substring(filepath.length()-5));
			
			Overlay3 symmetry = new Overlay3(image.getBW());
			System.out.print("Verticle: " + symmetry.overlay(YFlip) + "  ");
			System.out.println("Horizontal: " + symmetry.overlay(XFlip));
	}
}
