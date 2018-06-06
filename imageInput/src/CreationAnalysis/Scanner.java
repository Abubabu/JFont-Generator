package CreationAnalysis;

import java.io.IOException;

import getImage.CardinalPixels;
import getImage.RGBValue;
import getImage.getImage;

public class Scanner {
	
	public static void scanner(String filepath) throws IOException
	{
		getImage image = new getImage(filepath); 	
		getImage imageConverted = new getImage(image.getBW());	
		RGBValue[][] imagePix = imageConverted.getPixelsArray();		
		CardinalPixels imagelocation = new CardinalPixels(imageConverted);
		
		for(int ay = imagelocation.getNorth().getyPos(), by = blocation.getNorth().getyPos(); ay < alocation.getSouth().getyPos() && by < blocation.getSouth().getyPos(); ay += aH , by += bH)//y  apix[ycoord][xcoord]
		{
			for(int ax = alocation.getLeft().getxPos(), bx = blocation.getLeft().getxPos(); ax < alocation.getRight().getxPos() && bx < blocation.getRight().getxPos(); ax += aW, bx += bW)//x
			{
				//System.out.println(apix[j][i].getB() + "  " + bpix[j][i].getB());
				//System.out.println("x:"+ ax + "  y:" + ay + "  " + "A:" + apix[ax][ay].getR() + ", " + bpix[bx][by].getR());
				if (isBlack(bpix[bx][by]) || isBlack(apix[ax][ay])) {
					if (isRGBEqual(apix[ax][ay], bpix[bx][by])) {
						overlap++;
					} else {
						nolap++;
					} 
				}
			}
		}
		
	}
}
