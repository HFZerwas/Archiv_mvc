package application;

import application.Controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	
	
	public static AnchorPane root;
	public static AnchorPane root2;
	Scene scene;
	Scene secondScene;
	public static Stage SecondaryStage;
	
	public static void toggleSecondStage() {
		
		if(SecondaryStage.isShowing()) {
			SecondaryStage.hide();
		}else{
			SecondaryStage.show();
		}
	}
    
    
    @Override
	public void start(Stage primaryStage) throws Exception {
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
		scene = new Scene(root, 800,400 );
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("ESE Archiv_v_1.0");
		primaryStage.setScene(scene);
		primaryStage.show();
		SecondaryStage = new Stage();
		root2 = new AnchorPane();
		SecondaryStage.initOwner(primaryStage);
		secondScene = new Scene(root2, 500 ,400 );
		SecondaryStage.setScene(secondScene);
		SecondaryStage.setTitle("Kontrolle der Dokumente");
		SecondaryStage.initModality(Modality.WINDOW_MODAL);
		SecondaryStage.hide();
		MainController ourController = new MainController();

		
	}

	public static void main(String[] args) throws Exception {
		
		launch(args);
		
	
	}

	

	

}
