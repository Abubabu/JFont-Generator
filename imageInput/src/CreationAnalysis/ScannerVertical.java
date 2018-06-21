package CreationAnalysis;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import getImage.CardinalPixels;
import getImage.RGBValue;
import getImage.getImage;
import getImage.pixelPosition;

public class ScannerVertical {
	private String newFile;
	static String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	public ScannerVertical() throws IOException {
		this.newFile = new File("usersScannedImage").getAbsolutePath();
	}

	public static void main(String[] args) throws IOException
	{
		//System.out.println(new File("usersScannedImage.bmp").getAbsolutePath());
	}
	
	public String getNewFile() {
		return newFile + ".bmp";
	}
	public void scanImage(String filepath) throws IOException {
		getImage image = new getImage(filepath);
		getImage imageConverted = new getImage(image.getBW());
		pixelPosition[][] imagePix = imageConverted.getArrayPixels();
		RGBValue[][] imageColors = imageConverted.getPixelsArray();
		CardinalPixels cardinalColors = new CardinalPixels(imageConverted);
		pixelPosition[][] topBotLeft = new pixelPosition[imagePix.length][imagePix[1].length];
		pixelPosition[][] botTopLeft = new pixelPosition[imagePix.length][imagePix[1].length];
		pixelPosition[][] topBotRight = new pixelPosition[imagePix.length][imagePix[1].length];
		pixelPosition[][] botTopRight = new pixelPosition[imagePix.length][imagePix[1].length];
		int midway = cardinalColors.getLeft().getxPos()
				+ ((cardinalColors.getRight().getxPos() - cardinalColors.getLeft().getxPos()) / 2);
		// System.out.println(midway);
		System.out.println(imagePix.length);
		for (int x = 0; x < imagePix.length; x++) {
			boolean foundtopBotLeft = false;
			boolean foundbotTopLeft = false;
			boolean foundtopBotRight = false;
			boolean foundbotTopRight = false;
			for (int y = 0; y < imagePix[x].length; y++) {
				// seperates the left hand array code and right hand for efficiency
				if (!foundtopBotLeft && isBlack(imageColors[x][y])) { // checks for a found in order to keep the loop
																		// going to find both pixels in one loop without
																		// accidentally replacing one
					topBotLeft[x][y] = imagePix[x][y];
					foundtopBotLeft = true;
				}
				if (!foundbotTopLeft && isBlack(imageColors[x][imageColors[x].length - 1 - y])) {
					botTopLeft[x][imageColors[x].length - 1 - y] = imagePix[x][imageColors[x].length - 1 - y];
					foundbotTopLeft = true;
				}
				if (foundbotTopLeft && foundtopBotLeft) {// if both are found move on
					break;
				}
				/*
				 * else { if(!foundtopBotRight&& isBlack(imageColors[x][y])) { // checks for a
				 * found in order to keep the loop going to find both pixels in one loop without
				 * accidentally replacing one topBotRight[x][y] = imagePix[x][y];
				 * foundtopBotRight = true; } if(!foundbotTopRight &&
				 * isBlack(imageColors[x][imageColors[x].length - 1 - y])) {
				 * botTopRight[x][imageColors[x].length - 1 - y] =
				 * imagePix[x][imageColors[x].length - 1 - y]; foundbotTopRight = true; }
				 * if(foundbotTopRight && foundtopBotRight) {// if both are found move on break;
				 * }
				 * 
				 * }
				 */

			}
			BufferedImage VerTop = new BufferedImage(topBotLeft.length, topBotLeft[0].length,
					BufferedImage.TYPE_INT_RGB);
			// System.out.println("first");
			for (int v = 0; v < VerTop.getWidth(); v++) {
				// System.out.println("second");
				for (int w = 0; w < VerTop.getHeight(); w++) {
					if (topBotLeft[v][w] != null) {
						// System.out.println("third"); 
						Color staticColor = new Color(0, 0, 0); 
						VerTop.setRGB(v, w, staticColor.getRGB());
						// System.out.println("fourth");
					} else {

						Color staticColor = new Color(255, 255, 255);
						VerTop.setRGB(v, w, staticColor.getRGB());
					}
				}
			}
			ImageIO.write(VerTop, "bmp", new File("usersScannedImage.bmp").getAbsoluteFile());
			// System.out.println("fifth");
		}
	}

	public static boolean isBlack(RGBValue rgb) {
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}

}
