package fx;

import java.io.File;
import java.util.ArrayList;

import CreationAnalysis.LineDirections;
import CreationAnalysis.SymmetryTest;
import LetterRecognition.OverlayTest3;

public class whatIsIt {
	String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	String[] horiz = {"A","H","I","M","O","T","U","V","W","X","Y"};
	String[] vert = {"B","C","D","E","H","I","K","O","X"};
	String[] bothS = {"H","I","O","X"};
	public static double[] compile(ArrayList<Double> overlay , double[] lineRec, double[] symmetry) //verticle [0] horiz[1]
	{
		double[] result = new double[26];
		boolean verticle = false, horizontal = false, both = false;
		if(symmetry[0] > .55)
		{
			verticle = true;
		}
		if(symmetry[1] > .55)
		{
			horizontal = true;
		}
		if(horizontal && verticle)
		{
			both = true;
		}
		for(int i = 0; i < 26; i++)
		{
			double value = 0;
			char let = (char) ((char)i+65);
			String letter = String.valueOf(let);
			value = overlay.get(i) + lineRec[i]/2;
			result[i] = value;
		}
		return result;
	}
	public static void main(String[] args)
	{
		String path = new File(".").getAbsolutePath();
		String relativeFilePath = path.substring(0, path.length()-2)+ "\\";
		ArrayList<Double> data = OverlayTest3.getPercentages(relativeFilePath+"Random\\MyA.PNG"); // user image filepath goes here
		double[] data1 = SymmetryTest.test(relativeFilePath + "Random\\MyA.PNG");  // user image filepath goes here
		double data2[] = LineDirections.compareAllLetters("random/myA.png");
		double[] masterData = whatIsIt.compile(data,data2,data1);
	}
}
