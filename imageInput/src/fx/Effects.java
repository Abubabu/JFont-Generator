package fx;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import CreationAnalysis.LineDirections;
import CreationAnalysis.SymmetryTest;
import LetterRecognition.OverlayTest3;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;

import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class Effects extends Application {
	String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	int count = 0;
	int timeLeft = 0;
	long secondcounter = System.nanoTime();
	String path = new File(".").getAbsolutePath();
	String relativeFilePath = path.substring(0, path.length()-2)+ "\\";
	ArrayList<Double> data = OverlayTest3.getPercentages(relativeFilePath+"Random\\MyA.PNG"); // user image filepath goes here
	double[] data1 = SymmetryTest.test(relativeFilePath + "Random\\MyA.PNG");  // user image filepath goes here
	double data2[] = LineDirections.compareAllLetters("random/myA.png");
	double[] masterData = whatIsIt.compile(data,data2,data1);
	
	
	public void start(Stage primaryStage) throws Exception {
		findTop5(masterData);
		primaryStage.setWidth(1500);
		primaryStage.setHeight(800);
		BorderPane border = new BorderPane();
		ImageView userLetter = new ImageView();
		Image userImage = new Image( new File(relativeFilePath + "Random\\MyA.PNG").toURI().toURL().toExternalForm());  // user image filepath goes here
		userLetter.setFitHeight(500);
		userLetter.setFitWidth(700);
		userLetter.setImage(userImage);
		border.setLeft(userLetter);
		count = 0;
		if(timeLeft == 0) {
			timeLeft = 54;
			secondcounter = System.nanoTime() + 2000000000L;
			new AnimationTimer() {	
				@Override
				public void handle(long now) {
					// TODO Auto-generated method stub
					if(timeLeft == 0) {
						Label finalScores = new Label("");
						String firstPlace = "1st " + alphabet[top5[0]]  + ": " + masterData[top5[0]] + "\n";
						String secondPlace = "2nd " + alphabet[top5[1]]  + ": " + masterData[top5[1]] + "\n";
						String thirdPlace = "3rd " + alphabet[top5[2]]  + ": " + masterData[top5[2]] + "\n";
						String fourthPlace = "4th " + alphabet[top5[3]]  + ": " + masterData[top5[3]] + "\n";
						String fifthPlace = "5th " + alphabet[top5[4]]  + ": " + masterData[top5[4]] + "\n";
						finalScores.setText(firstPlace + secondPlace + thirdPlace + fourthPlace + fifthPlace);
						finalScores.setTranslateX(450);
						finalScores.setFont(new Font("Arial", 22));
						border.setBottom(finalScores);
						stop();
					}
				   if(now > secondcounter) {
					  if(timeLeft <= 52) {
						  count++;
						  if(timeLeft == 52) {
							  count = 0;
						  }
					   ImageView staticLetter = new ImageView();
						Image staticImage = null;
						try {
							staticImage = new Image(new File(relativeFilePath + "alphabet\\" + alphabet[count] + ".png").toURI().toURL().toExternalForm());
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						staticLetter.setImage(staticImage);
						staticLetter.setFitHeight(500);
						staticLetter.setFitWidth(700);
						border.setRight(staticLetter);
						DecimalFormat numberFormat = new DecimalFormat("#.00###");
						String overlayResult = (numberFormat.format(data.get(count)) + " %"); // this is where the % goes
					//	String symmetryResult = ("Verticle Sym % : " + numberFormat.format(data1[0]) + " %" + " Horizontal Sym % : " + numberFormat.format(data1[1]) + "%"); // this is where the % goes
						String directionResult = (data2[count] * 100) + "%";
						Label result = new Label("");
						result.setText("Overlay Result" +overlayResult + "\n" + "Line Direction Result " + directionResult);
						result.setMaxHeight(300);
						result.setMinHeight(300);
						result.setMinWidth(700);
						result.setMaxWidth(700);
						result.setTextAlignment(TextAlignment.CENTER);
						result.setFont(new Font("Arial", 22));
						result.setTranslateX(450);
						border.setBottom(result);
					//	border.setBottom(symmetryResult);
					  }
						timeLeft = timeLeft - 2;
						secondcounter += 2000000000L;
						
					}
				}

				
			}.start();
		}
    ;
    
		
		Scene scene = new Scene(border);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	int[] top5 = new int[5];
	public static void main(String[] args) throws IOException {
		Application.launch(args);
	}
	
	public void findTop5(double[] masterData) {
		int count = 0;
			for(int c = 0; c < 24; c++) {
				if(count >= 5)
					return;
				int largest = c;
				for(int d = 0; d < 25; d++) {
					boolean notExist = true;
					if(c != d) {
					if(masterData[largest] < masterData[d] ) {
						for(int i = 0; i < count; i++) {
							if(top5[i] == d) {
								notExist = false;
							}
						}
						if(notExist)
							largest = d;
					}
					}
				}
				if(count >= 5)
					return;
				top5[count] = largest;
				count++;
			}
		}
	
}
