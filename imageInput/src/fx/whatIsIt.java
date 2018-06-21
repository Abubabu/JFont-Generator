package fx;

import java.io.File;
import java.util.ArrayList;

import CreationAnalysis.LineDirections;
import CreationAnalysis.SymmetryTest;
import LetterRecognition.OverlayTest3;

public class whatIsIt {
	String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	static String[] vert = {"A","H","I","M","O","T","U","V","W","X","Y"};
	static String[] horiz = {"B","C","D","E","H","I","K","O","X"};
	static String[] both = {"H","I","O","X"};
	public static double[] compile(ArrayList<Double> overlay , double[] lineRec, double[] symmetry) //horiz []0 vert[1]
	{
		double[] result = new double[26];
		ArrayList<String> likely = new ArrayList<String>();
		if(symmetry[0] > .1)
		{
			for(String x : horiz)
			{
				likely.add(x);
			}
		}
		if(symmetry[1] > .1)
		{
			for(String x : vert)
			{
				likely.add(x);
			}
		}
		for(int i = 0; i < 26; i++)
		{
			double value = 0;
			double unlikely = .05;
			char let = (char) ((char)i+65);
			String letter = String.valueOf(let);
			if(!likely.contains(letter))
			{
				value-= unlikely;
			}
			value = overlay.get(i) + lineRec[i]/2;
			result[i] = value;
		}
		return result;
	}
	public static void main(String[] args)
	{
		String test = "DrawnLetters/DrawnB.JPG";//"Random\\MyA.PNG";
		String path = new File(".").getAbsolutePath();
		String relativeFilePath = path.substring(0, path.length()-2)+ "\\";
		ArrayList<Double> data = OverlayTest3.getPercentages(relativeFilePath+test); // user image filepath goes here
		double[] data1 = SymmetryTest.test(relativeFilePath + test);  // user image filepath goes here
		double data2[] = LineDirections.compareAllLetters(test);
		double[] masterData = whatIsIt.compile(data,data2,data1);
		for(double x: masterData)
		{
			System.out.println(x);
		}
	}
}
