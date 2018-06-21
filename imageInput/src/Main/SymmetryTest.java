package Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SymmetryTest {
	public static void main(String[] args)
	{
		test("MYA.png");
	}
	public static double[] test(String filename)
	{
		double[] data = new double[2];
			
		File x = new File(filename);
		getImage image = null;
		try {
			image = new getImage((new getImage(x.getAbsolutePath())).getBW());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage YFlip = Reflection.reflectorYAxis(image);
		BufferedImage XFlip = Reflection.reflectorXAxis(image);
		
		try {
			ImageIO.write(YFlip, "bmp",new File(x.getAbsolutePath().substring(0, x.getAbsolutePath().length()-filename.length()) + "YFlip.bmp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
			ImageIO.write(XFlip, "bmp",new File(x.getAbsolutePath().substring(0, x.getAbsolutePath().length()-filename.length()) + "XFlip.bmp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		System.out.println(x.getName());
		
		Overlay3 symmetry = null;
		try {
			symmetry = new Overlay3(x.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			data[0] = symmetry.overlay("YFlip.bmp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			data[1] = symmetry.overlay("XFlip.bmp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
}
