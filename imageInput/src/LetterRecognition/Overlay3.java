package LetterRecognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import getImage.CardinalPixels;
import getImage.RGBValue;
import getImage.getImage;

public class Overlay3 {
	private final getImage testCase;
	
	public Overlay3(String filepath) throws IOException
	{
		getImage testcase = new getImage(filepath);
		this.testCase = testcase;
	}
	public double overlay(String filepath) throws IOException
	{
		 
		getImage b = getTestCase();
		
		getImage a = new getImage(filepath); 
		
		getImage aConverted = new getImage(a.getBW());
		getImage bConverted = new getImage(b.getBW());
		
		RGBValue[][] apix = aConverted.getPixelsArray();
		RGBValue[][] bpix = bConverted.getPixelsArray();
		
		int overlap = 0;
		int nolap = 0;
		
		CardinalPixels alocation = new CardinalPixels(aConverted);
		CardinalPixels blocation = new CardinalPixels(bConverted);
		
		/*System.out.println("heartNorth: " + alocation.getNorth().getxPos() + "," + alocation.getNorth().getyPos());
		System.out.println("heartSouth: " + alocation.getSouth().getxPos() + "," + alocation.getSouth().getyPos());
		System.out.println("heartLeft: " + alocation.getLeft().getxPos() + "," + alocation.getLeft().getyPos());
		System.out.println("heartRight: " + alocation.getRight().getxPos() + "," + alocation.getRight().getyPos());
		System.out.println("circleNorth: " + blocation.getNorth().getxPos() + "," + blocation.getNorth().getyPos());
		System.out.println("circleSouth: " + blocation.getSouth().getxPos() + "," + blocation.getSouth().getyPos());
		System.out.println("circleLeft: " + blocation.getLeft().getxPos() + "," + blocation.getLeft().getyPos());
		System.out.println("circleRight: " + blocation.getRight().getxPos() + "," + blocation.getRight().getyPos());  
		*/  
		int bWidth = blocation.getRight().getxPos()-blocation.getLeft().getxPos();
		int bHeight = blocation.getSouth().getyPos()-blocation.getNorth().getyPos();
		
		int aWidth = alocation.getRight().getxPos()-alocation.getLeft().getxPos();
		int aHeight = alocation.getSouth().getyPos()-alocation.getNorth().getyPos();
		
		//System.out.println(bWidth + " " + bHeight + " : " + aWidth + " " + aHeight);
		// THIS IS ALL FOR THE INCREMENT HEIGHT
		//double aRatio = gcd(a.getHeight(),a.getWidth());// these are probably useless dont mind them
		//double bratio = b.getHeight()/b.getWidth();
		int aspectHeightA = asFractionnumerator(aHeight,aWidth);
		int aspectHeightB = asFractionnumerator(bHeight,bWidth);
		int scaleHeightA = aHeight / aspectHeightA;
		int scaleHeightB = bHeight / aspectHeightB;
		int heightDenomanator =  aspectHeightA * aspectHeightB;
		int incrementHeightA = (scaleHeightA / heightDenomanator) * aspectHeightA;
		int incrementHeightB = (scaleHeightB / heightDenomanator) * aspectHeightB;
		// first get the aspect ration, then get the factor from the orignal size to their respective
		// aspect ration, for example with an original size of 450 and the ratio 9 the factor is 50. Then multiply the respective RATIOS for each image. so in a 16:9 and 4:5 multiply 4 and 5
		// put the ration(5 or 9) over the product of the two and make it equal to the factors denomanator, the resulting numerator is the increment 
		// ROOUND DOWN BTW
		
		// THIS IS ALL FOR THE INCREMENT WIDTHS
		//double aRatio = gcd(a.getHeight(),a.getWidth());
		//double bratio = b.getHeight()/b.getWidth();
	/*	int aspectWidthA = asFractiondenomanator(aHeight,aWidth);
		int aspectWidthB = asFractiondenomanator(bHeight,bWidth);
		int scaleWidthA = aWidth / aspectWidthA;
		int scaleWidthB = bWidth / aspectWidthB;
		int widthDenomanator =  aspectWidthA * aspectWidthB;
		int incrementWidthA = (scaleWidthA / widthDenomanator) * aspectWidthA;
		int incrementWidthB = (scaleWidthB / widthDenomanator) * aspectWidthB;
		
		incrementHeightA = ((incrementHeightA == 0) ? 1 : incrementHeightA);
		incrementHeightB = ((incrementHeightB == 0) ? 1 : incrementHeightB);
		incrementWidthA = ((incrementWidthA == 0) ? 1 : incrementWidthA);
		incrementWidthB = ((incrementWidthB == 0) ? 1 : incrementWidthB);*/
		
		//gcdt(aHeight,bHeight);
		//gcdt(aWidth,aWidth);
		
		int aH = getIncrementA(aHeight,bHeight);
		int aW = getIncrementA(aWidth,bWidth);
		int bH = getIncrementB(aHeight,bHeight);
		int bW = getIncrementB(aWidth,bWidth);
		for(int ay = alocation.getNorth().getyPos(), by = blocation.getNorth().getyPos(); ay < alocation.getSouth().getyPos() && by < blocation.getSouth().getyPos(); ay += aH , by += bH)//y  apix[ycoord][xcoord]
		{
			for(int ax = alocation.getLeft().getxPos(), bx = blocation.getLeft().getxPos(); ax < alocation.getRight().getxPos() && bx < blocation.getRight().getxPos(); ax += aW, bx += bW)//x
			{
				//System.out.println(apix[j][i].getB() + "  " + bpix[j][i].getB());
				//System.out.println("x:"+ ax + "  y:" + ay + "  " + "A:" + apix[ax][ay].getR() + ", " + bpix[bx][by].getR());
				if (isBlack(bpix[bx][by])) {
					if (isRGBEqual(apix[ax][ay], bpix[bx][by])) {
						overlap++;
					} else {
						nolap++;
					} 
				}
			}
		}
		
		//System.out.println(overlap + " " + nolap);
		//System.out.print(((double) overlap/ (double) (nolap+overlap)));
		return ((double) overlap/ (double) (nolap+overlap));
	}
	public static boolean isRGBEqual(RGBValue one, RGBValue two)
	{
		return (one.getR() == two.getR() && one.getG() == two.getG() && one.getB() == two.getB());
	}
	public static void scale(int bWidth, int bHeight, String filepath) throws IOException
	{
		getImage scale = null;
		CardinalPixels alocation = null;
		ImageResizer imageresizer = new ImageResizer();
		int aWidth = 0;
		int aHeight = 0;
		int xinc = 0;
		int yinc = 0;
		while(true)
		{
			scale = new getImage(filepath);
			alocation = new CardinalPixels(scale);
			aWidth = alocation.getRight().getxPos()-alocation.getLeft().getxPos();
			aHeight = alocation.getSouth().getyPos()-alocation.getNorth().getyPos();
			if(aWidth == bWidth && aHeight == bHeight )
			{
				break;
			}
			if(aWidth > bWidth)
			{
				xinc--;
			}
			if(aWidth < bWidth)
			{
				xinc++;
			}
			if(aHeight > bHeight)
			{
				yinc--;
			}
			if(aHeight < bHeight)
			{
				yinc++;
			}
			imageresizer.resize(filepath, filepath, scale.getWidth()+xinc, scale.getHeight()+yinc);
			xinc = 0;
			yinc = 0;
		}
		
	}
	public static boolean isBlack(RGBValue rgb)
	{
		return rgb.getR() == 0 && rgb.getG() == 0 && rgb.getB() == 0;
	}
	private static double gcd(double a, double b)
	{
	    while (b > 0)
	    {
	        double temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}

	/*private static long gcd(long[] input)
	{
	    long result = input[0];
	    for(int i = 1; i < input.length; i++) result = (long) gcd(result, input[i]);
	    return result;
	}*/
	private static long lcm(long a, long b)
	{
	    return (long) (a * (b / gcd(a, b)));
	}

	private static long lcm(long[] input)
	{
	    long result = input[0];
	    for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
	    return result;
	}
	public static int asFractionnumerator(long a, long b) {
	    long gcm = gcm(a, b);
	    return (int) (a / gcm);
	}
	public static int asFractiondenomanator(long a, long b) {
	    long gcm = gcm(a, b);
	    return (int) (b / gcm);
	}
	public static void gcdt(int a, int b) {
        System.out.print("Type in two numbers and I will print outs its Greatest Common Divisor: ");
        int gcdNum1 =a;
        int gcdNum2 = b;
        while (gcdNum1 == 0) {
            gcdNum1 = 0;
        }
        while (gcdNum2 > gcdNum1) {
            int gcd = gcdNum1 % gcdNum2;
        }
        System.out.print(gcdNum1 + gcdNum2);
    }
	public static long gcm(long a, long b) {
	    return b == 0 ? a : gcm(b, a % b); // Not bad for one line of code :)
	}
	public getImage getTestCase() {
		return testCase;
	}
	public static int gcd(int a, int b)
	{
	  if(a == 0 || b == 0) return a+b; // base case
	  return gcd(b,a%b);
	}
	public int  getIncrementsHeightA(int a,int b){
		if( gcd(a,b) < 1)
			return a;
		else
			return gcd(a / gcd(a,b), b / gcd(a,b));
	}
	
	public int getIncrementA(int a, int b) {
		if(a < 10 || b < 10) {
			return a;
		}
		if((a > 10 || b > 10) && gcd(a,b) == 1) {
			return 1;
		}
		 return gcd(a / gcd(a,b), b / gcd(a,b));
	}															// this might actually fix the increments  yet to be tested
	public int getIncrementB(int a, int b) {
		if(a < 10 || b < 10) {
			return b;
		}
		if((a > 10 || b > 10) && gcd(a,b) == 1) {
			return 1;
		}
		 return gcd(a / gcd(a,b), b / gcd(a,b));
	}
			
}


