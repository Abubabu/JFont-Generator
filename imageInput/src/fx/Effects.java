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
	String[] alphabet = {"A","B","C","D","E","F","G","H","I","G","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	int count = 0;
	int timeLeft = 0;
	long secondcounter = System.nanoTime();
	String path = new File(".").getAbsolutePath();
	String relativeFilePath = path.substring(0, path.length()-2)+ "\\";
	ArrayList<Double> data = OverlayTest3.getPercentages(relativeFilePath+"Random\\MyA.PNG"); // user image filepath goes here
	double data2[] = LineDirections.compareAllLetters("random/myA.png");
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setWidth(1500);
		primaryStage.setHeight(800);
		BorderPane border = new BorderPane();
		ImageView userLetter = new ImageView();
		Image userImage = new Image( new File(relativeFilePath + "Random\\MyA.PNG").toURI().toURL().toExternalForm());  // user image filepath goes here
		userLetter.setFitHeight(500);
		userLetter.setFitWidth(700);
		userLetter.setImage(userImage);
		border.setLeft(userLetter);
		
		if(timeLeft == 0) {
			timeLeft = 75;
			secondcounter = System.nanoTime() + 3000000000L;
			new AnimationTimer() {	
				@Override
				public void handle(long now) {
					// TODO Auto-generated method stub
					if(timeLeft == 0) {
						stop();
					}
				   if(now > secondcounter) {
					  
					   count++;
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
						double[] data1 = SymmetryTest.test("Alphabet/" + alphabet[count] + ".png");  // user image filepath goes here
						String symmetryResult = ("Verticle Sym % : " + numberFormat.format(data1[0]) + " %" + " Horizontal Sym % : " + numberFormat.format(data1[1]) + "%"); // this is where the % goes
						Label result = new Label("");
						result.setText(overlayResult + "\n" + symmetryResult);
						result.setMaxHeight(300);
						result.setMinHeight(300);
						result.setMinWidth(700);
						result.setMaxWidth(700);
						result.setTextAlignment(TextAlignment.CENTER);
						result.setFont(new Font("Arial", 22));
						result.setTranslateX(450);
						border.setBottom(result);
					//	border.setBottom(symmetryResult);

						timeLeft = timeLeft - 1;
						secondcounter += 3000000000L;
						
					}
				}

				
			}.start();
		}
    ;
    
		
		Scene scene = new Scene(border);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) throws IOException {
		Application.launch(args);
	}
}
