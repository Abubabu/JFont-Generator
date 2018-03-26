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
	public static void main(String[] args) throws IOException
	{
		 
		getImage b = new getImage("circle.png");
		
		getImage a = new getImage("heart.png"); 
		
		getImage aConverted = new getImage(a.getBW());
		getImage bConverted = new getImage(b.getBW());
		
		RGBValue[][] apix = aConverted.getPixelsArray();
		RGBValue[][] bpix = bConverted.getPixelsArray();
		
		int overlap = 0;
		int nolap = 0;
		
		CardinalPixels alocation = new CardinalPixels(aConverted);
		CardinalPixels blocation = new CardinalPixels(bConverted);
		
		System.out.println("heartNorth: " + alocation.getNorth().getxPos() + "," + alocation.getNorth().getyPos());
		System.out.println("heartSouth: " + alocation.getSouth().getxPos() + "," + alocation.getSouth().getyPos());
		System.out.println("heartLeft: " + alocation.getLeft().getxPos() + "," + alocation.getLeft().getyPos());
		System.out.println("heartRight: " + alocation.getRight().getxPos() + "," + alocation.getRight().getyPos());
		System.out.println("circleNorth: " + blocation.getNorth().getxPos() + "," + blocation.getNorth().getyPos());
		System.out.println("circleSouth: " + blocation.getSouth().getxPos() + "," + blocation.getSouth().getyPos());
		System.out.println("circleLeft: " + blocation.getLeft().getxPos() + "," + blocation.getLeft().getyPos());
		System.out.println("circleRight: " + blocation.getRight().getxPos() + "," + blocation.getRight().getyPos());
		
		int bWidth = blocation.getRight().getxPos()-blocation.getLeft().getxPos();
		int bHeight = blocation.getSouth().getyPos()-blocation.getNorth().getyPos();
		
		int aWidth = alocation.getRight().getxPos()-alocation.getLeft().getxPos();
		int aHeight = alocation.getSouth().getyPos()-alocation.getNorth().getyPos();
		
		System.out.println(bWidth + " " + bHeight + " : " + aWidth + " " + aHeight);
		
		double aRatio = gcd(a.getHeight(),a.getWidth());
		double bratio = b.getHeight()/b.getWidth();
		int incrementfirst = asFractionnumerator(a.getHeight,a.getWidth); // first get the aspect ration, then get the factor from the orignal size to their respective
		// aspect ration, for example with an original size of 450 and the ratio 9 the factor is 50. Then multiply the respective RATIOS for each image. so in a 16:9 and 4:5 multiply 4 and 5
		// put the ration(5 or 9) over the product of the two and make it equal to the factors denomanator, the resulting numerator is the increment 
		// ROOUND DOWN BTW
		
		for(int ay = alocation.getNorth().getyPos(), by = blocation.getNorth().getyPos(); ay < alocation.getSouth().getyPos() && by < blocation.getSouth().getyPos(); ay++, by++)//y  apix[ycoord][xcoord]
		{
			for(int ax = alocation.getLeft().getxPos(), bx = blocation.getLeft().getxPos(); ax < alocation.getRight().getxPos() && bx < blocation.getRight().getxPos(); ax++, bx++)//x
			{
				//System.out.println(apix[j][i].getB() + "  " + bpix[j][i].getB());
				System.out.println("x:"+ ax + "  y:" + ay + "  " + "A:" + apix[ax][ay].getR() + ", " + bpix[bx][by].getR());
				if (isBlack(apix[ax][ay]) || isBlack(bpix[bx][by])) {
					if (isRGBEqual(apix[ax][ay], bpix[bx][by])) {
						overlap++;
					} else {
						nolap++;
					} 
				}
			}
		}
		
		System.out.println(overlap + " " + nolap);
		System.out.print(((double) overlap/ (double) (nolap+overlap)));
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

	private static long gcd(long[] input)
	{
	    long result = input[0];
	    for(int i = 1; i < input.length; i++) result = gcd(result, input[i]);
	    return result;
	}
	private static long lcm(long a, long b)
	{
	    return a * (b / gcd(a, b));
	}

	private static long lcm(long[] input)
	{
	    long result = input[0];
	    for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
	    return result;
	}
	public static int asFractionnumerator(long a, long b) {
	    long gcm = gcm(a, b);
	    return (a / gcm);
	}
	
	public static void gcd(int a, int b) {
        System.out.print("Type in two numbers and I will print outs its Greatest Common Divisor: ");
        int gcdNum1 = console.nextInt();
        int gcdNum2 = console.nextInt();
        while (gcdNum1 == 0) {
            gcdNum1 = 0;
        }
        while (gcdNum2 > gcdNum1) {
            int gcd = gcdNum1 % gcdNum2;
        }
        System.out.print(gcdNum1 + gcdNum2);
    }
}
}
