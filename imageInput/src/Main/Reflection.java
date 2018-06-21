package Main;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Reflection {

	
	public static BufferedImage reflectorYAxis(getImage img) {
		BufferedImage ReflectedImg = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
		RGBValue[][] colors = img.getPixelsArray();
		for(int x = 0; x < img.getWidth(); x++) {
			for(int y = 0; y < img.getHeight();y++) {
				if(isBlack(colors[x][y])) {
					ReflectedImg.setRGB(img.getWidth()-x-1, y, new Color(0,0,0).getRGB());
				}
				else
				{
					
					ReflectedImg.setRGB(img.getWidth()-x-1,y, new Color(255,255,255).getRGB());
				}
			}
		}
		return ReflectedImg;
	}
	public static BufferedImage reflectorXAxis(getImage img) {
		BufferedImage ReflectedImg = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
		RGBValue[][] colors = img.getPixelsArray();
		for(int x = 0; x < img.getWidth(); x++) {
			for(int y = 0; y < img.getHeight();y++) {
				if(isBlack(colors[x][y])) {
					ReflectedImg.setRGB(x, img.getHeight()-y-1, new Color(0,0,0).getRGB());
				}
				else
				{
					
					ReflectedImg.setRGB(x,img.getHeight()-y-1, new Color(255,255,255).getRGB());
				}
			}
		}
		return ReflectedImg;
	}
	
	public static boolean isBlack(RGBValue rgb){
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}

}
