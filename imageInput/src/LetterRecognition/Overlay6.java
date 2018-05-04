package LetterRecognition;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import getImage.CardinalPixels;
import getImage.RGBValue;
import getImage.getImage;

public class Overlay6 {
private final getImage testCase;
	
	public Overlay6(String filepath) throws IOException
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
		
		
		int aNativeHeight = aConverted.getHeight();
		int aNativeWidth = aConverted.getWidth();
		int bNativeHeight = bConverted.getHeight();
		int bNativeWidth = bConverted.getWidth();
		
		RGBValue[][] apix = aConverted.getPixelsArray();
		RGBValue[][] bpix = bConverted.getPixelsArray();
		
		int overlap = 0;
		int nolap = 0;
		
		CardinalPixels alocation = new CardinalPixels(aConverted);
		CardinalPixels blocation = new CardinalPixels(bConverted);
		
		//a* is test image
		//b* is user image
		
		int aH = getIncrementA(aNativeHeight,bNativeHeight);
		
		int aW = getIncrementA(aNativeWidth,bNativeWidth);
		int bH = getIncrementB(aNativeHeight,bNativeHeight) ;
		int bW = getIncrementA(aNativeWidth,bNativeWidth);;
		
		BufferedImage test = new BufferedImage(aNativeWidth / aW, aNativeHeight / aH, BufferedImage.TYPE_INT_RGB);
		BufferedImage user = new BufferedImage(bNativeWidth / bW, bNativeHeight / bH, BufferedImage.TYPE_INT_RGB);
		int TestxPos = 0;
		int TestyPos = 0;
		int UserxPos = 0;
		int UseryPos = 0;
		
		for(int ay = 0, by = 0; ay < aNativeHeight / aH;   ay += aH , by += bH)//y  apix[ycoord][xcoord]
		{
			for(int ax = 0, bx = 0; ax < aNativeWidth / aW; ax += aW, bx += bW)//x
			{
				//System.out.println(apix[j][i].getB() + "  " + bpix[j][i].getB());
				//System.out.println("x:"+ ax + "  y:" + ay + "  " + "A:" + apix[ax][ay].getR() + ", " + bpix[bx][by].getR());
					if (isRGBEqual(apix[ax][ay], bpix[bx][by])) {
						overlap++;
					} else {
						nolap++;
					} 
					
					
					int Testrgb = apix[ax][ay].getR();
					Testrgb = (Testrgb << 8) + apix[ax][ay].getG();
					Testrgb = (Testrgb << 8) + apix[ax][ay].getB();
		    	    test.setRGB(TestxPos, TestyPos, Testrgb);
		    	    if(aNativeWidth-1 == TestxPos)
		    	    {
		    	    	TestxPos = 0;
		    	    	TestyPos++;
		    	    }
		    	    else
		    	    {
		    	    	TestxPos++;
		    	    }
		    	    
		    	    int Userrgb = bpix[bx][by].getR();
		    	    Userrgb = (Userrgb << 8) + bpix[bx][by].getG();
		    	    Userrgb = (Userrgb << 8) + bpix[bx][by].getB();
		    	    user.setRGB(UserxPos, UseryPos, Userrgb);
		    	    if(bNativeWidth-1 == UserxPos)
		    	    {
		    	    	UserxPos = 0;
		    	    	UseryPos++;
		    	    }
		    	    else
		    	    {
		    	    	UserxPos++;
		    	    }
			}
		}
		ImageIO.write(test, "bmp",new File("C:\\Users\\BT_1N3_23\\git\\JFont-Generator\\imageInput\\TestCropout.bmp"));
		ImageIO.write(user, "bmp",new File("C:\\Users\\BT_1N3_23\\git\\JFont-Generator\\imageInput\\UserCropout.bmp"));
		//ImageIO.write(test, "bmp",new File("C:\\Users\\Salehaakter\\git\\JFont-Generator\\imageInput\\TestCropout.bmp"));
		//ImageIO.write(user, "bmp",new File("C:\\Users\\Salehaakter\\git\\JFont-Generator\\imageInput\\UserCropout.bmp"));
		
		//System.out.println(overlap + " " + nolap);
		//System.out.print(((double) overlap/ (double) (nolap+overlap)));
		return ((double) overlap/ (double) (nolap+overlap));
	}
	public static void main(String[] args) throws IOException
	{
		Overlay6 comparator = new Overlay6("Alphabet/Q.png");
		comparator.overlay("Alphabet/R.png");
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
	public static int makeEven(int x)
	{
		return ( (x % 2 == 0) ? x : x-1);
	}
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
		if(a < a * .01 || b < b * .01) {
			return a;
		}
		if((a > a * .01|| b > b * .01) && gcd(a,b) == 1) {
			return (int) ((int)a * .01);
		}
		 return gcd(a / gcd(a,b), b / gcd(a,b));
	}															// this might actually fix the increments  yet to be tested
	public int getIncrementB(int a, int b) {
		if(a < a * .01 || b < b * .01) {
			return b;
		}
		if((a > a * .01|| b > b * .01) && gcd(a,b) == 1) {
			return (int) ((int)b * .01);
		}
		 return gcd(a / gcd(a,b), b / gcd(a,b));
	}
			
}
