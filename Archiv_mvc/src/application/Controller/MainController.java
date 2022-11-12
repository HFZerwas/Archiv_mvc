package application.Controller;

import java.io.IOException;
import application.Model.*;
import application.View.MainView;
import javafx.scene.control.*;


public class MainController {

	private application.Model.Model ourModel;
	private application.View.MainView ourView;	
	private application.Model.ExcelWriter ourEcExcelWriter;	
	public MainController () throws IOException {
		this.ourView = new MainView(this);
		this.ourModel = new Model(this);
		this.ourEcExcelWriter = new ExcelWriter(this, ourModel);
	}
	
	
	public ListView<String> getErkDokuTF() {
		return ourView.getErkDokuTF();
	}

	public void setErkDokuTF(ListView<String> erkDokuTF) {
		ourView.setErkDokuTF(erkDokuTF);
	}

	public  ListView<String> gettFList() {
		return ourView.gettFList();
	}

	public void settFList(ListView<String> tFList) {
		ourView.settFList(tFList);
	}

	public Label getProjectNumberLabel() {
		return ourView.getProjectNumberLabel();
	}

	public void setProjectNumberLabel(Label projectNumberLabel) {
		ourView.setProjectNumberLabel(projectNumberLabel);
	}

	public Label getProjectDaTeLabel() {
		return ourView. getProjectDaTeLabel();
	}

	public void setProjectDaTeLabel(Label projectDaTeLabel) {
		ourView.setProjectDaTeLabel(projectDaTeLabel);
	}

	public Label getProjectHashLabel() {
		return ourView.getProjectHashLabel();
	}

	public void setProjectHashLabel(Label projectHashLabel) {
		ourView.setProjectHashLabel(projectHashLabel);
	}

	public TextArea getFoundDocumentsTextArea() {
		return ourView.getFoundDocumentsTextArea();
	}

	public void setFoundDocumentsTextArea(TextArea foundDocumentsTextArea) {
		ourView.setFoundDocumentsTextArea(foundDocumentsTextArea);
	}

	public TextArea getDocIdtextArea() {
		return ourView.getDocIdtextArea();
	}

	public void setDocIdtextArea(TextArea docIdtextArea) {
		ourView.setDocIdtextArea(docIdtextArea);
	}

	public TextArea getSelectFileTextarea() {
		return ourView.getSelectFileTextarea();
	}

	public void setSelectFileTextarea( String selectFileTextarea) {
		ourView.setSelectFileTextarea(selectFileTextarea);
	}

	
	public ListView<String> getSelectArchivListView(){
		return ourView.getSelectArchivListView();
	}

	
	
	public void getValues() {
		ourModel.getValues();
		ourModel.getDateWithDots(); 
	}

	public String getZipDirName() {
		return ourModel.getZipDirName();
		
	}
	
	public boolean getSafeToSaveStatus() {
		return ourModel.isSafetosave();
	}

	public void copyToArchive() {
		System.out.println("Maincontroller copyToArchive");
		new FileCopier (this.ourModel);
		
	}
	
	public void resetAll() {
		ourModel.resetAll();
	}


	public void setCheckedDikumentString(String text) {
		ExcelWriter.setCheckedDikumentString(text);		
	}


	public void setdocIDString(String text) {
		ExcelWriter.setDocIDString(text);
		
	}
	public void setSafeToSave(boolean isSafe) {
		ourModel.setSafetosave(isSafe);
	}
	
	public void writeExcel() throws IOException {
		ourEcExcelWriter.excelWriter();
	}


	public void findMissingDates() {
		ourEcExcelWriter.findMissingDates();
		
	}


	public void configloader() throws IOException {
		ourEcExcelWriter.configLoader();
		
	}


	public void selectDirectory(int i) {
		ourModel.selectDirectory(i);
	}
	
	}
	
	
	
	
	
	
	
	

