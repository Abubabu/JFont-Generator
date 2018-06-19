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
public class LineDirections {

	
	public static double[] findDifferences(getImage img) {
		boolean foundFirst = false;
		int prevX = 0;
		int prevY = 0;
		int count = 0;
		RGBValue[][] colors = img.getPixelsArray();
		double[] differences = new double[img.getWidth()];
		for(int x = 0; x < img.getWidth(); x++) {
			for(int y = 0; y < img.getHeight();y++) {
				if(isBlack(colors[x][y]) && !foundFirst) {
					prevX = x;
					prevY = y;
					foundFirst = true;
				}
				else if(isBlack(colors[x][y]) && foundFirst) {
					differences[count] = y - prevY;
					count++;
				}
			}
		}
		return differences;
	}
	
	private static String[] allDirections;
	
	public static double[][] findLines(double[] differences) {
		int lineCount= 0;
		String currentChange = "";
		for(int x = 0; x < differences.length; x++) {
			if(x != 0) {
				if(differences[x] - differences[x - 1] > 5 && !currentChange.equals("+")) {
					lineCount++;
					currentChange = "+";
				}
				else if(differences[x] - differences[x - 1] < 5 && !currentChange.equals("-")) {
					lineCount++;
					currentChange = "-";
				}
				else if(!currentChange.equals("0")) {
					lineCount++;
					currentChange = "0";
				}
			}
		}
		double[][] allLines = new double[lineCount][differences.length];
		String[] directions = new String[lineCount];
		int currentLine = 0;
		int currentRow = 0;
		String currentChange2 = "";
		for(int a = 0; a < differences.length;) {
			if(a == 0) {
				allLines[currentLine][currentRow] = differences[a];
				currentRow++;	
			}
			if(a == 1) {
				if(differences[a] - differences[a - 1] > 5 && !currentChange2.equals("+")) {
					directions[currentLine] = "+";
					currentChange2 = "+";
				}
				else if(differences[a] - differences[a - 1] < 5 && !currentChange2.equals("-")) {
					directions[currentLine] = "-";
					currentChange2 = "-";
				}
				else if(!currentChange2.equals("0")) {
					directions[currentLine] = "0";
					currentChange = "0";
				}
				allLines[currentLine][currentRow] = differences[a];
				currentRow++;
			}	
			else if(a != 0) {
				if(differences[a] - differences[a - 1] > 5 && !currentChange2.equals("+")) {
					currentLine++;
					currentRow = 0;
					currentChange2 = "+";
					directions[currentLine] = "+";
				}
				else if(differences[a] - differences[a - 1] < 5 && !currentChange2.equals("-")) {
					currentLine++;
					directions[currentLine] = "-";
					currentRow = 0;
					currentChange2 = "-";
				}
				else if(!currentChange2.equals("0")) {
					currentLine++;
					directions[currentLine] = "0";
					currentRow = 0;
					currentChange = "0";
				}
				allLines[currentLine][currentRow] = differences[a];
				currentRow++;
			}	
		}
		allDirections = directions;
		return allLines;
	}
	public static boolean isBlack(RGBValue rgb)
	{
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}
}
