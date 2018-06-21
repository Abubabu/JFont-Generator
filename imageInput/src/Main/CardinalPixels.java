package Main;

public class CardinalPixels {
	private pixelPosition leftmost;
	private pixelPosition rightmost;
	private pixelPosition northmost;
	private pixelPosition southmost;
	private getImage img;
	
	public CardinalPixels(getImage img) {
		this.img = img;
		findPixels();
	}
	
	private void findPixels() {
		pixelPosition [][] rgbs = img.getArrayPixels();
		pixelPosition first = null;
		boolean foundFirst = false;
		pixelPosition last = null;
		pixelPosition top = null;
		boolean foundTop = false;
		boolean foundLast = false;
		pixelPosition bottom = null;
		boolean foundBottom = false;
		RGBValue[][] colors = img.getArrayRGB();
		for(int x = 0; x < rgbs.length; x++) {
			for(int y = rgbs[x].length - 1;  y >= 0; y--) {
			/*	int r = this.getPixels().get(rgbs[x][y]).getR();
				int g = this.getPixels().get(rgbs[x][y]).getG();
				int b = this.getPixels().get(rgbs[x][y]).getB();*/
				int r = colors[rgbs[x][y].getxPos()][rgbs[x][y].getyPos()].getR();
				int b = colors[rgbs[x][y].getxPos()][rgbs[x][y].getyPos()].getB();
				int g = colors[rgbs[x][y].getxPos()][rgbs[x][y].getyPos()].getG();
				if(foundLast != false) {
					if(r == 0 && g == 0 && b == 0) {
						if(last.getxPos() < rgbs[x][y].getxPos())
							last = rgbs[x][y];
						else if( last.getxPos() == rgbs[x][y].getxPos() && last.getyPos() < rgbs[x][y].getyPos())
							last = rgbs[x][y];
					}
				}
				else if(foundLast == false && r == 0 && g == 0 && b == 0) {
					last = rgbs[x][y];
					foundLast = true;
				}
				
				
				if(foundFirst != false) {
					if(r == 0 && g == 0 && b == 0) {
						if(first.getxPos() > rgbs[x][y].getxPos())
							first = rgbs[x][y];
						else if( first.getxPos() == rgbs[x][y].getxPos() && first.getyPos() < rgbs[x][y].getyPos())
							first = rgbs[x][y];
					}
				}
					else if(foundFirst == false && r == 0 && g == 0 && b == 0) {
						first = rgbs[x][y];
						foundFirst = true;
					}
				
				if(foundBottom != false) {
					if(r == 0 && g == 0 && b == 0) {
						if(bottom.getyPos() < rgbs[x][y].getyPos())
							bottom = rgbs[x][y];
						else if( bottom.getyPos() == rgbs[x][y].getyPos() && bottom.getxPos() > rgbs[x][y].getxPos())
							bottom = rgbs[x][y];
					}
				}
				else if(foundBottom == false && r == 0 && g == 0 && b == 0) {
					bottom = rgbs[x][y];
					foundBottom = true;
				}
				
				if(foundTop != false) {
					if(r == 0 && g == 0 && b == 0) {
						if(top.getyPos() > rgbs[x][y].getyPos())
							top = rgbs[x][y];
						else if( top.getyPos() == rgbs[x][y].getyPos() && top.getxPos() > rgbs[x][y].getxPos())
							top = rgbs[x][y];
					}
				}
				else if(foundTop == false && r == 0 && g == 0 && b == 0) {
					top = rgbs[x][y];
					foundTop = true;
				}
			
				
			}
				
		}
		this.leftmost = first;
		this.southmost = bottom;
		this.rightmost = last;
		this.northmost = top;
	}
	
	public pixelPosition getNorth() {
		return northmost;
	}
	
	public pixelPosition getSouth() {
		return southmost;
	}
	
	public pixelPosition getLeft() {
		return leftmost;
	}
	
	public pixelPosition getRight() {
		return rightmost;
	}
}
