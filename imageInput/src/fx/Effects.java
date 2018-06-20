package fx;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
public class Effects extends Application {
	String[] alphabet = {"A","B","C","D","E","F","G","H","I","G","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	int count = 0;
	int timeLeft = 0;
	long secondcounter = System.nanoTime();
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setWidth(1500);
		primaryStage.setHeight(800);
		BorderPane border = new BorderPane();
		ImageView userLetter = new ImageView();
		Image userImage = new Image( new File("C:\\Users\\Administrator\\git\\JFont-Generator\\imageInput\\Random\\MyA.PNG").toURI().toURL().toExternalForm());
		userLetter.setFitHeight(500);
		userLetter.setFitWidth(700);
		userLetter.setImage(userImage);
		border.setLeft(userLetter);
		
		if(timeLeft == 0) {
			timeLeft = 25;
			secondcounter = System.nanoTime() + 1000000000L;
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
							staticImage = new Image(new File("C:\\Users\\Administrator\\git\\JFont-Generator\\imageInput\\alphabet\\" + alphabet[count] + ".png").toURI().toURL().toExternalForm());
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						staticLetter.setImage(staticImage);
						staticLetter.setFitHeight(500);
						staticLetter.setFitWidth(700);
						border.setRight(staticLetter);
						Label finalResult = new Label("reeeee"); // this is where the % goes
						finalResult.setMaxHeight(300);
						finalResult.setMinHeight(300);
						finalResult.setMaxWidth(300);
						finalResult.setMaxWidth(300);
						border.setBottom(finalResult);
						finalResult.setTextAlignment(TextAlignment.CENTER);
						finalResult.setFont(new Font("Arial", 30));
						finalResult.setTranslateX(700);

						timeLeft = timeLeft - 1;
						secondcounter += 1000000000L;
						
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
