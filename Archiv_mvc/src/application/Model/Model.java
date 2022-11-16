package application.Model;

import java.io.File;
import java.io.IOException;
import application.Controller.MainController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;

public class Model {
	File dir;
	String zipDirName;
	String archivPfadString;
	String projectNumberString;
	int projectNumber;
	int date;
	boolean gotValues;
	boolean safetosave;
	boolean isANumber = false;
	MainController controller;
	DirectoryZipper zippedFile;
	ExcelWriter excelWriter;
	
public Model(MainController controller) throws IOException {
	this.controller = controller;
	this.zippedFile = new DirectoryZipper();
	this.excelWriter = new ExcelWriter(this.controller, this);
	}
	
	
 
	
public void selectDirectory(int choosenButton) {
	DirectoryChooser dChooser = new DirectoryChooser();
	File directoryFile = dChooser.showDialog(null);
	this.controller.getSelectArchivListView().getItems().clear();
	if (directoryFile != null && choosenButton == 2) {
		this.controller.getSelectArchivListView().getItems().add(directoryFile.getAbsolutePath());
		archivPfadString = directoryFile.getAbsolutePath();
		System.out.println("Archivpfad: " + archivPfadString);
	} else if(directoryFile != null && choosenButton == 1) {
		this.dir = directoryFile;
		this.controller.getSelectFileTextarea().clear();
		this.controller.setSelectFileTextarea(directoryFile.getAbsolutePath());
		this.zipDirName = directoryFile.getAbsolutePath();
		System.out.println(this.zipDirName);}
	else {
		
		System.out.println("\n select Archiv hat nicht geklappt. \n");
	}
}
	
public void getValues() {
	
if (this.gotValues == false) {
			
	if (this.archivPfadString == null) { 
		this.archivPfadString = this.controller.getSelectArchivListView().getItems().get(0).replace("\\", "\\\\");
		}
			
	this.gotValues = true;
	this.dir = new File(this.controller.getSelectFileTextarea().getText().replace("\\", "\\\\"));
	System.out.println("Main.dir= " + this.dir.getAbsolutePath());
			
	numberSearch(this.zipDirName);
	
	this.controller.getProjectNumberLabel().setText(String.valueOf(this.projectNumber));
	this.controller.getProjectDaTeLabel().setText(String.valueOf(this.date));
	DirectoryZipper zippedFile = new DirectoryZipper();
	zippedFile.zipDirectory(this.dir,  this.zipDirName);
			
	hashMeIfYouCan();
			
	/** puts Values in sceneTwo TextArea and checks if as much "checklist" as "Gutachten" */
				 
	 int checklistCounter = 0;
	 int gutachtenCounter = 0;
	 boolean moreDokuments = false;
	 for(String filePath : zippedFile.filesListInDir) {
		 
		if(filePath.contains("GUT")) {
		if (gutachtenCounter==0) {
			this.controller.getFoundDocumentsTextArea().appendText("Gutachten\n");
			}
		if((filePath.contains("heckliste") == false)) {
			this.controller.getDocIdtextArea().appendText(filePath.substring(this.zipDirName.length()+1) + "\n");
			}
		gutachtenCounter++;
		}
							
		if (filePath.contains("heckliste") && filePath.contains("GUT")) {
		if (checklistCounter == 0) {
			this.controller.getFoundDocumentsTextArea().appendText("Checklisten\n");
			}								
			this.controller.getDocIdtextArea().appendText(filePath.substring(this.zipDirName.length()+1) + "\n");
			checklistCounter++;}
		
		if(filePath.substring(this.zipDirName.length()+1).contains("\\")) {
			if (!moreDokuments) {
			moreDokuments=true;
			this.controller.getFoundDocumentsTextArea().appendText("\nWeitere Dokumentation zu Gutachten\n");
			}
		}
	 }  
			if (gutachtenCounter != checklistCounter && gutachtenCounter != (2*checklistCounter)) { 
			this.controller.getFoundDocumentsTextArea().appendText("\nAnzahl der Checklisten entspricht nicht der Anzahl der Gutachten");
				}
			System.out.println("Gutachtencounter= " +gutachtenCounter);
			System.out.println("Checklistencounter= " +checklistCounter);
		} else {
		Alert fnfAlert = new Alert(AlertType.WARNING);
		fnfAlert.setTitle("File not Found");
		fnfAlert.setContentText("Ziel-Ordner muss angegeben werden oder Werte bereits ermittelt.");
		fnfAlert.show();
			
		}
			
	}

	private void hashMeIfYouCan() {

		new FileHasher(this); 
	}

/** 
		 * this method finds numbers in a String and puts them in an Array (String)
		 * it also searches for the projectnumber and date
		 * @param zipDirName2
		 */
	
private void numberSearch(String zipDirName2) {
	
		this.isANumber = false;
		boolean isANumber = false;
		byte stringcount=0;
		String [] foundNumbersArray = new String[15];
		zipDirName2 = zipDirName2.replaceAll("[^0-9]", "-");
			
		for (int i = 0; i < zipDirName2.length(); i++) {
			if (Character.isDigit(zipDirName2.charAt(i))) {
				isANumber = true;
				if(foundNumbersArray[stringcount]==null) {
				foundNumbersArray[stringcount] = Character.toString(zipDirName2.charAt(i));
				}else{
					foundNumbersArray[stringcount] += zipDirName2.charAt(i);
					}
				}else if (isANumber == true) {
				stringcount++;	
				isANumber=false;
				} 
			}
		for(String string : foundNumbersArray) {
			if (string!=null) {
			if (string.length() == 6) {
					this.projectNumber = Integer.valueOf(string);
				}else if (string.length() == 8) {
					this.date = Integer.valueOf(string);
					}
				}			
			}
			System.out.println("Erkanntes Datum: " + this.date);
			System.out.println("Erkannte Projektnummer: " + this.projectNumber);
		}
		
public void getDateWithDots() {
		
		if (date != 0) {
			
			String datefirsthalfString 	 = String.valueOf(date).substring(0,4);
			String datesecondthalfString = String.valueOf(date).substring(4,8);
						
			if (Integer.valueOf(datesecondthalfString)>2012 && (Integer.valueOf(datefirsthalfString)<2013 
					|| Integer.valueOf(datefirsthalfString)>2100))
			{
				ExcelWriter.archiveYearString = String.valueOf((Integer.valueOf(datesecondthalfString) + 10));
				ExcelWriter.archivedateString = datefirsthalfString.substring(0,2) + "." + datefirsthalfString.substring(2) 
				+ "." + datesecondthalfString;
				System.out.println("archivedateString =" + ExcelWriter.archivedateString);
				
			}else {
				ExcelWriter.archiveYearString = String.valueOf((Integer.valueOf(datefirsthalfString) + 10));
				ExcelWriter.archivedateString =  datesecondthalfString.substring(2)  + "." 
				+ datesecondthalfString.substring(0, 2) + "." + datefirsthalfString;
				System.out.println("archivedateString =" + ExcelWriter.archivedateString);
				this.controller.getProjectDaTeLabel().setText(ExcelWriter.archivedateString);
				}
			}
	}

public void resetAll() {
	
	dir = new File("");
	zipDirName = "";
	projectNumber = 0;
	date = 0;
	gotValues = false;
	
	this.controller.getErkDokuTF().getItems().clear();
	this.controller.gettFList().getItems().clear();
	this.controller.getProjectNumberLabel().setText("");
	this.controller.getProjectDaTeLabel().setText("");
	this.controller.getProjectHashLabel().setText("");
	this.controller.getFoundDocumentsTextArea().clear();
	this.controller.getDocIdtextArea().clear();
	this.controller.getSelectFileTextarea().clear();
	this.safetosave = false;
	this.zippedFile.filesListInDir.clear();
	this.controller.getSelectArchivListView().getItems().clear();
	
}

/* getter and setter */

public int getProjectNumber() {
	return this.projectNumber;
}


public void setProjectNumber(int projectNumber) {
	this.projectNumber = projectNumber;
}


public int getDate() {
	return this.date;
}


public void setDate(int date) {
	this.date = date;
}


public boolean getGotValues() {
	return this.gotValues;
}


public void setGotValues(boolean gotValues) {
	this.gotValues = gotValues;
}


public boolean isSafetosave() {
	return this.safetosave;
}


public void setSafetosave(boolean safetosave) {
	this.safetosave = safetosave;
}


public void setDir(File dir) {
	this.dir = dir;
}


public File getDir() {
return this.dir;	
}

public void setDir(String dirPath) {
			
}

public String getZipDirName() {
	return this.zipDirName;
}
public void setZipDirName(String dirName) {
	this.zipDirName = dirName;
}

}
		
	

