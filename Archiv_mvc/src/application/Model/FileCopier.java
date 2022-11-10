package application.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FileCopier {
	Model model;
	public FileCopier(Model model) {
		this.model = model;
		copyToArchive();
	}
	
public void copyToArchive() {
		
		File selectedFile2 = new File(this.model.archivPfadString + "\\" + String.valueOf(this.model.projectNumber)+ "\\\\" 
		+ ExcelWriter.foundProjNameString + "\\\\" + String.valueOf(this.model.date));
		System.out.println("\nArchivordner wurde erfolgreich erstellt: " + selectedFile2.mkdirs() + "\n"); 
		
		if (this.model.gotValues == false) {
			
			 Alert fnfAlert = new Alert(AlertType.WARNING);
				fnfAlert.setTitle("Werte Ermitteln");
				fnfAlert.setContentText("Erst Werte ermitteln und kontrollieren");
				fnfAlert.show();
		} else {
			 try {
			String oldFilePath = this.model.getZipDirName();
			String newFilePath = this.model.archivPfadString + "\\" + String.valueOf(this.model.projectNumber) ;
			
		    System.out.println(newFilePath);
		    System.out.println(newFilePath + "\\" + this.model.getDate() 
	               		+ ExcelWriter.foundProjNameString 
	            		+ this.model.archivPfadString +  ".zip");
		          		           
		   FileInputStream in = new FileInputStream(oldFilePath + ".zip");
		   FileOutputStream out = null;
		    do{
		    out = new FileOutputStream(newFilePath + "\\\\" + this.model.getProjectNumber() 
		               		+ ExcelWriter.foundProjNameString + this.model.getDate() +  ".zip");
		    }while(out==null);
		            long dateiLaenge = (new File(oldFilePath+ ".zip")).length();
		            
		            byte[] b = new byte[(int) dateiLaenge];
		            int len;
		            
		            while ((len = in.read(b)) > 0) {
		                out.write(b, 0, len);
		            }
		            out.close();
		            in.close();
		            ExcelWriter.archivPfadString = newFilePath;
		            System.out.println("Datei '" + oldFilePath + ".zip' wurde in die Datei '"
		                    + newFilePath + ".zip" + "' kopiert.");
		        }catch (Exception e) {
					e.printStackTrace();
				}
		    	
		    }
		}
		
	
	
}