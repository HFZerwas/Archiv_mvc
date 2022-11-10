package application.View;
import java.io.IOException;

import application.Main;
import application.Controller.MainController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainView {
	
	private ListView<String> selectArchivListView = new ListView<String>();
	private ListView<String> erkDokuTF = new ListView<String>();
	private ListView<String> tFList = new ListView<String>();
	private Label projectNumberLabel = new Label();
	private Label projectDaTeLabel = new Label();
	private Label projectHashLabel = new Label();
	private Label projNrLabel = new Label();
	private Label dateLabel = new Label();
	private Label hashLabel = new Label();
	private Label afterZipLabel = new Label();
	private TextArea foundDocumentsTextArea = new TextArea();
	private TextArea docIdtextArea = new TextArea();
	private TextArea selectFileTextarea = new TextArea();
	private Text foundDocText;
	private Text otherDocText;
	private Button selectFiButton;
	private Button selectArchivButton;
	private Button getValuesButton;
	private Button zipAndHashButton; 
	private Button datasCheckedButton;
	MainController controller;
    
	public MainView(MainController controller) throws IOException {
		this.selectArchivListView = new ListView<String>();
		this.erkDokuTF = new ListView<String>();
		this.tFList = new ListView<String>();
		this.projectNumberLabel = new Label();
		this.projectDaTeLabel = new Label();
		this.projectHashLabel = new Label();
		this.projNrLabel = new Label();
		this.dateLabel = new Label();
		this.hashLabel = new Label();
		this.afterZipLabel = new Label();
		this.foundDocumentsTextArea = new TextArea();
		this.docIdtextArea = new TextArea();
		this.selectFileTextarea = new TextArea();
		this.foundDocText = new Text();
		this.otherDocText= new Text();
		this.getValuesButton = new Button();
		this.controller = controller;
		int layoutx = 20;
		int layouty = 20;
	
		/** Buttons */ 
   
    selectFiButton = new Button("Gutachten Ordner");
    selectFiButton.setLayoutX(layoutx);
    selectFiButton.setLayoutY(layouty);
    application.Main.root.getChildren().add(selectFiButton);
    		    
    selectArchivButton = new Button("Archiv Zielordner");
    selectArchivButton.setLayoutX(layoutx);
    selectArchivButton.setLayoutY(3*layouty);
    application.Main.root.getChildren().add(selectArchivButton);
    
    this.getValuesButton = new Button("Werte Ermitteln");
    getValuesButton.setLayoutX(layoutx);
    getValuesButton.setLayoutY(8*layouty);
    application.Main.root.getChildren().add(getValuesButton);
   
    zipAndHashButton = new Button("Archivierung durchführen");
    zipAndHashButton.setLayoutX(layoutx);
    zipAndHashButton.setLayoutY(350);
    application.Main.root.getChildren().add(zipAndHashButton);
    
    datasCheckedButton = new Button("Daten übernehmen");
    datasCheckedButton.setLayoutX(layoutx*18);
    datasCheckedButton.setLayoutY(layouty*18);
    application.Main.root2.getChildren().add(datasCheckedButton);
    
    
    /** Listviews */
	
	
    selectArchivListView.setLayoutX(8 * layoutx);
    selectArchivListView.setLayoutY (3 * layouty);
    selectArchivListView.setPrefHeight(30);
    selectArchivListView.setPrefWidth(500);
    selectArchivListView.autosize();
    selectArchivListView.setEditable(false);
    application.Main.root.getChildren().add(selectArchivListView);
	
	
	
	
	/** Label */
	
 projNrLabel = new Label("ermittelte Projektnummer: " );
 projNrLabel.setLayoutX(layoutx);
 projNrLabel.setLayoutY(10 * layouty);
 application.Main.root.getChildren().add(projNrLabel);
	 
 getProjectNumberLabel().setLayoutX(12 * layoutx);
 getProjectNumberLabel().setLayoutY(10 * layouty);
 application.Main.root.getChildren().add(getProjectNumberLabel());
	 
	 
 dateLabel = new Label("ermitteltes Datum: ");
 dateLabel.setLayoutX(layoutx);
 dateLabel.setLayoutY(234);
 application.Main.root.getChildren().add(dateLabel);
	 
	 
	 
 getProjectDaTeLabel().setLayoutX(234);
 getProjectDaTeLabel().setLayoutY(236);
 application.Main.root.getChildren().add(getProjectDaTeLabel());
	 
 hashLabel = new Label("ermittelter Hashwert: ");
 hashLabel.setLayoutX(layoutx);
 hashLabel.setLayoutY(260);
 application.Main.root.getChildren().add(hashLabel);
	 
	 
 getProjectHashLabel().setLayoutX(234);
 getProjectHashLabel().setLayoutY(260);
 application.Main.root.getChildren().add(getProjectHashLabel());
	
 afterZipLabel = new Label("(nach Komprimierung)");
 afterZipLabel.setLayoutX(layoutx);
 afterZipLabel.setLayoutY(280);
 application.Main.root.getChildren().add(afterZipLabel);
	
	 
	 
/** Text & TextAreas*/
	 
foundDocText = new Text("Eintragung für Reiter abgelegte Dokumente:");
foundDocText.setX(10);    
foundDocText.setY(20);
foundDocText.setFont(Font.font("Verdana", 20));
foundDocText.setUnderline(true);
application.Main.root2.getChildren().add(foundDocText);
     
getFoundDocumentsTextArea().setEditable(true);
getFoundDocumentsTextArea().setWrapText(true);
getFoundDocumentsTextArea().setCenterShape(true);
getFoundDocumentsTextArea().setLayoutX(5);
getFoundDocumentsTextArea().setLayoutY(35);
getFoundDocumentsTextArea().setMaxHeight(100);
application.Main.root2.getChildren().add(getFoundDocumentsTextArea());

otherDocText = new Text("gefundene Dokumente im Ordner - \nEintragung im Reiter DOC-ID.");
otherDocText.setX(10);    
otherDocText.setY(160);
otherDocText.setFont(Font.font("Verdana", 12));
otherDocText.setUnderline(false);
application.Main.root2.getChildren().add(otherDocText);
 
	 
getDocIdtextArea().setEditable(true);
getDocIdtextArea().setWrapText(true);
getDocIdtextArea().setCenterShape(true);
getDocIdtextArea().setLayoutX(5);
getDocIdtextArea().setLayoutY(180);
getDocIdtextArea().setMaxHeight(140);
application.Main.root2.getChildren().add(getDocIdtextArea());
	 
getSelectFileTextarea().setMaxHeight(15);
getSelectFileTextarea().setPrefWidth(500);
getSelectFileTextarea().setLayoutX(160);
getSelectFileTextarea().setLayoutY(20);
getSelectFileTextarea().setEditable(false);
application.Main.root.getChildren().add(getSelectFileTextarea());
	 
	 
	 
	 /* Eventhandling */
 selectFiButton.setOnAction(e ->{
	 controller.selectFile();
	 });
	
	 
 selectArchivButton.setOnAction(e ->{
	controller.selectArchive();
	});
	 
 getValuesButton.setOnAction(e ->{
	controller.getValues();
	Main.toggleSecondStage();
 	});
	
zipAndHashButton.setOnAction(e -> {
		
	if (controller.getSelectFileTextarea().getText().equals(controller.getZipDirName())){
		if (controller.getSafeToSaveStatus()) {
		try {
			controller.configloader();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		controller.findMissingDates();
		controller.copyToArchive();
		try {
			controller.writeExcel();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		}
	}else{
	Alert fnfAlert = new Alert(AlertType.WARNING);
	fnfAlert.setTitle("Ermittelte Werte nicht konsistent");
	fnfAlert.setContentText("Gutachtenordner und ermittelte Werte stimmen nicht überein. "
			+ "Ordner und Werte müssen neu ermittelt werden");
	fnfAlert.show();
	controller.resetAll();
	}
	});

	datasCheckedButton.setOnAction(e -> {
	 
	 controller.setCheckedDikumentString(foundDocumentsTextArea.getText());
	 controller.setdocIDString(docIdtextArea.getText());
	 System.out.println(foundDocumentsTextArea.getText());
	 System.out.println(docIdtextArea.getText());
	 Main.toggleSecondStage();
	 controller.setSafeToSave(true);
	});
		 
	}
	
	/* Constructor ending */
	
	
	/*getter and setter*/
	
	public ListView<String> getErkDokuTF() {
		return erkDokuTF;
	}

	public void setErkDokuTF(ListView<String> erkDokuTF) {
		this.erkDokuTF = erkDokuTF;
	}

	public ListView<String> gettFList() {
		return tFList;
	}

	public void settFList(ListView<String> tFList) {
		this.tFList = tFList;
	}

	public Label getProjectNumberLabel() {
		return projectNumberLabel;
	}

	public void setProjectNumberLabel(Label projectNumberLabel) {
		this.projectNumberLabel = projectNumberLabel;
	}

	public Label getProjectDaTeLabel() {
		return projectDaTeLabel;
	}

	public void setProjectDaTeLabel(Label projectDaTeLabel) {
		this.projectDaTeLabel = projectDaTeLabel;
	}

	public Label getProjectHashLabel() {
		return projectHashLabel;
	}

	public void setProjectHashLabel(Label projectHashLabel) {
		this.projectHashLabel = projectHashLabel;
	}

	public TextArea getFoundDocumentsTextArea() {
		return foundDocumentsTextArea;
	}

	public void setFoundDocumentsTextArea(TextArea foundDocumentsTextArea) {
		this.foundDocumentsTextArea = foundDocumentsTextArea;
	}

	public TextArea getDocIdtextArea() {
		return docIdtextArea;
	}

	public void setDocIdtextArea(TextArea docIdtextArea) {
		this.docIdtextArea = docIdtextArea;
	}

	public TextArea getSelectFileTextarea() {
		return selectFileTextarea;
	}

	public void setSelectFileTextarea(String selectFileTextarea) {
		this.selectFileTextarea.appendText(selectFileTextarea); 
	}



	public ListView<String> getSelectArchivListView() {
		return selectArchivListView;
	}



	public void setSelectArchivListView(ListView<String> selectArchivListView) {
		this.selectArchivListView = selectArchivListView;
	}



	public Label getProjNrLabel() {
		return projNrLabel;
	}



	public void setProjNrLabel(Label projNrLabel) {
		this.projNrLabel = projNrLabel;
	}



	public Label getDateLabel() {
		return dateLabel;
	}



	public void setDateLabel(Label dateLabel) {
		this.dateLabel = dateLabel;
	}



	public Label getHashLabel() {
		return hashLabel;
	}



	public void setHashLabel(Label hashLabel) {
		this.hashLabel = hashLabel;
	}



	public Label getAfterZipLabel() {
		return afterZipLabel;
	}



	public void setAfterZipLabel(Label afterZipLabel) {
		this.afterZipLabel = afterZipLabel;
	}



	public Text getFoundDocText() {
		return foundDocText;
	}



	public void setFoundDocText(Text foundDocText) {
		this.foundDocText = foundDocText;
	}



	public Text getOtherDocText() {
		return otherDocText;
	}



	public void setOtherDocText(Text otherDocText) {
		this.otherDocText = otherDocText;
	}



	public void setSelectFileTextarea(TextArea selectFileTextarea) {
		this.selectFileTextarea = selectFileTextarea;
	}



	
	

}
