package getImage;

import java.io.IOException;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class testImage extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
    	
    	getImage getimage = new getImage("Twitch.png");
        primaryStage.setTitle("Title");
        Group root = new Group();
        Scene scene = new Scene(root, 150, 150, Color.WHITE);
        
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        
        final ImageView imv = new ImageView();
        final Image image2 = SwingFXUtils.toFXImage(getimage.createImg(), null);
        imv.setImage(image2);

        final HBox pictureRegion = new HBox();
        
        pictureRegion.getChildren().add(imv);
        gridpane.add(pictureRegion, 1, 1);
        
        
        root.getChildren().add(gridpane);        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}