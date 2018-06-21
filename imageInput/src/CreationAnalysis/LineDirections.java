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

	/*static String[] topA = {"+","-"};			thought i would need these but i didnt
	String[] topB = {"0","-"};
	String[] TopC = {"+","0","-"};
	String[] topD = {"0","-"};
	String[] topE = {"0"};
	String[] topF = {"0"};
	String[] topG = {"+"};
	String[] topH = {"0","-","0","+"};
	String[] topI = {"0"};
	String[] topJ = {"+","0"};
	String[] topK = {"0","-","+","0"};
	String[] topL = {"0","-","0"};
	String[] topM = {"0","-","0","+","0"};
	String[] topN = {"0","-","0","+","0"};
	String[] topO = {"+","0","-"};
	String[] topP = {"0","-"};
	String[] topQ = {"+","O","-"};
	String[] TopR = {"0","-"};
	String[] topS = {"+","0","-"};
	String[] topT = {"0"};
	String[] topU = {"0","-","0","+","0"};
	static String[] topV = {"0","-","0","+","0"};
	String[] topW = {"0","-","0","+","0","-","0","+","0"};
	String[] topY = {"-","+"};
	String[] topZ = {"0"};*/
	
	
	public static void main(String[] args) throws IOException {
		/*double[] differences = findDifferences(new getImage("JFont-GeneratorVerTop.BMP"));
		String[] userLines = findLines(differences);
		double[] differences2 = findDifferences(new getImage("staticA.BMP"));
		String[] staticLines = findLines(differences2);
		System.out.println(compareStrict(staticLines, userLines));
		*/
		double percents[] = compareAllLetters("random/myA.png");
			
	}
	public static double[] findDifferences(getImage img) {
		boolean foundFirst = false;
		int prevX = 0;
		int prevY = 0;
		int count = 0;
		RGBValue[][] colors = img.getPixelsArray();
		double[] differences = new double[img.getWidth()];
		for(int x = 0; x < img.getWidth(); x+=4) {
			for(int y = 0; y < img.getHeight();y++) {
				if(isBlack(colors[x][y]) && !foundFirst) {
					prevX = x;
					prevY = y;
					foundFirst = true;
				}
				else if(isBlack(colors[x][y]) && foundFirst) {
					differences[count] = prevY - y;
					prevY = y;
					count++;
				}
			}
		}
		return differences;
	}
	
	private static String[] allDirections;
	
	public static String[] findLines(double[] differences) {
		int lineCount= 0;
		String currentChange = "";
		for(int x = 0; x < differences.length; x++) {
			if(x != 0) {
				if(differences[x] > 2 && !currentChange.equals("+")) {
					lineCount++;
					currentChange = "+";
				}
				else if(differences[x] < -2 && !(currentChange.equals("-"))) {
					lineCount++;
					currentChange = "-";
				}
				else if(!(currentChange.equals("0"))) {
					lineCount++;
					currentChange = "0";
				}
			}
		}
		double[][] allLines = new double[lineCount][differences.length];
		String[] directions = new String[differences.length];
		int currentLine = 0;
		int currentRow = 0;
		String currentChange2 = "";
		for(int a = 0; a < differences.length; a++) {
			if(a == 0) {
				allLines[currentLine][currentRow] = differences[a];
				currentRow++;	
			}
			/*if(a == 1) {
				/*if(differences[a] > 5 && !currentChange2.equals("+")) {
					directions[currentLine] = "+";
					currentChange2 = "+";
				}
				else if(differences[a] < 5 && !currentChange2.equals("-")) {
					directions[currentLine] = "-";
					currentChange2 = "-";
				}
				else if(!currentChange2.equals("0")) {
					directions[currentLine] = "0";
					currentChange = "0";
				}
				allLines[currentLine][currentRow] = differences[a];
				currentRow++;
			}	*/
			else if(a != 0) {
				if(differences[a] > 2 && !currentChange2.equals("+")) {
					currentRow = 0;
					currentChange2 = "+";
					directions[currentLine] = "+";
					currentLine++;
				}
				else if(differences[a] < -2 && !currentChange2.equals("-")) {
	
					directions[currentLine] = "-";
					currentRow = 0;
					currentChange2 = "-";
					currentLine++;
				}
				else if(!currentChange2.equals("0")) {
					directions[currentLine] = "0";
					currentRow = 0;
					currentChange2 = "0";
					currentLine++;
				}
				currentRow++;
			}	
		}
		allDirections = directions;
		return directions;
	}
	
/*	public static double compareAll(String[] staticCase, String[] user) {
		if(staticCase.length < user.length) {
			int minLength = staticCase.length;
		}
	}*/
	public static double compareStrict(String[] staticCase, String[] user) {
		int misses = 0;
		int hit = 0;
		int difference = 0;
		int minLength = 0;
		int total = 0;
		if(staticCase.length < user.length) {
			 minLength = staticCase.length;
			 difference = user.length - staticCase.length;
		}
		else if(staticCase.length > user.length) {
			 minLength = user.length;
			 difference = staticCase.length - user.length; 
		}
		else {
			  minLength = user.length;
		}
		for(int i = 1; i < minLength; i++) {
			if(staticCase[i] == null || user[i] == null)
				break;
				else if(staticCase[i].equals(user[i])){
				hit++;
				total++;
			}
			else {
				misses++;
				total++;
			}
		}
		//System.out.println("hit " + hit + " total " + (double) total);
		if(hit == 0)
			return 0;
		else
			return hit / (double) total;
	}
	public static double[] compareAllLetters(String filepath) throws IOException { // the filepath is the orignal image not the scanner cropped
		 ScannerVertical scan = new ScannerVertical();
		double[] percents = new double[26];
		scan.scanImage(filepath);
		System.out.println(scan.getNewFile());
		double[] userDifferences = findDifferences(new getImage(scan.getNewFile()));
		String[] userlines = findLines(userDifferences);
		String[] alphabet = {"A","B","C","D","E","F","G","H","I","G","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		for(int i = 0; i < 26; i++) {
			System.out.println(new File("scannedTopLetters").getAbsolutePath() + "\\"  + alphabet[i] + ".bmp");
			double[] staticDifferences = findDifferences(new getImage(new File("scannedTopLetters").getAbsolutePath() + "\\"  + alphabet[i] + ".bmp"));
			String[] staticLines = findLines(staticDifferences);
			//System.out.println(compareStrict(staticLines,userlines));
			percents[i] = compareStrict(staticLines,userlines);
		}
		return percents;
	}
	public static boolean isBlack(RGBValue rgb)
	{
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}
}
