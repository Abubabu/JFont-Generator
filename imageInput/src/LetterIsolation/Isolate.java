package LetterIsolation;

import java.util.Map;

import getImage.RGBValue;
import getImage.pixelPosition;

public class Isolate {
	
	public static void main(String[] args)
	{
	
	}
	
	public void HorizontalLoop(int buffer,int imageHeight, int imageWidth, Map<pixelPosition, RGBValue> pixels)
	{
		for(int i = 0; i < imageHeight; i = i + buffer)
		{
			for(int j = 0; j < imageWidth; j++)
			{
				RGBValue color = pixels.get(new pixelPosition(j,i));
				System.out.println("R: "+ color.getR() + " G: " + color.getG() +" B: " + color.getB());
			}
		}
	}
	
	public void VerticalLoop(int buffer,int imageHeight, int imageWidth, Map<pixelPosition, RGBValue> pixels)
	{
		for(int i = 0; i < imageWidth; i = i + buffer)
		{
			for(int j = 0; j < imageHeight; j++)
			{
				RGBValue color = pixels.get(new pixelPosition(i,j));
				System.out.println("R: "+ color.getR() + " G: " + color.getG() +" B: " + color.getB());
			}
		}
	}
}
