package application.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import application.Controller.MainController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExcelWriter {
	
	static String hashValueString = "";
	String archivedateString; 
	static String foundProjNameString = "";
	static String archivPfadString = "";
	static String aktuelleProjektnamenListeString = "";
	static String archivlisteString; //Liste in die die Daten übernommen werden (Archivliste.xlsx)
	String archiveYearString; 
	private static String inspBerString = "";
	private static String unterauftragsnehmerString = ""; 
	private static String auslandString = "";
	 
	
	
	private static String checkedDikumentString = "";
	private static String docIDString = "";
	
	MainController controller;
	Model model;
	
	public ExcelWriter(MainController controller, Model model) throws IOException {
		this.controller = controller;
		this.model = model;
		}
	
	public static String getCheckedDikumentString() {
		return checkedDikumentString;
	}

	public static void setCheckedDikumentString(String checkedDikumentString) {
		ExcelWriter.checkedDikumentString = checkedDikumentString;
	}

	
	
	public static String getDocIDString() {
		return docIDString;
	}

	public static void setDocIDString(String docIDString) {
		ExcelWriter.docIDString = docIDString;
	}

	
	
	public void excelWriter() throws IOException {
		
		
		FileInputStream fis = new FileInputStream(new File(archivlisteString));
		XSSFWorkbook book = new XSSFWorkbook(fis);
		XSSFSheet sheet = book.getSheetAt(0); 
		 
		 CellStyle cs = book.createCellStyle();
		 cs.setWrapText(true);
		 cs.setAlignment(HorizontalAlignment.LEFT);
		 cs.setVerticalAlignment(VerticalAlignment.TOP);
		 
		 Row row = sheet.createRow(sheet.getLastRowNum()+1);
		 Cell projnCell = row.createCell(0);
		 projnCell.setCellStyle(cs);
		 projnCell.setCellValue(foundProjNameString);
		 
		
		
		 Cell auftragsnrCell = row.createCell(1);
		 auftragsnrCell.setCellStyle(cs); 
		 auftragsnrCell.setCellValue(this.model.projectNumber);  
		 
		
		 
		 Cell abgelDoku = row.createCell(2);
		 abgelDoku.setCellStyle(cs);
		 abgelDoku.setCellValue(checkedDikumentString);  
		 
		 Cell abgelam = row.createCell(3);
		 abgelam.setCellStyle(cs); 
		 abgelam.setCellValue(archivedateString );  
		 
		 Cell saveTill = row.createCell(4);
		 saveTill.setCellStyle(cs);  
		 saveTill.setCellValue("31.12." + archiveYearString);
		 
		 Cell docId = row.createCell(5);
		 docId.setCellStyle(cs); 
		 docId.setCellValue(docIDString);  
		 
		 Cell shaHashCell = row.createCell(6);
		 shaHashCell.setCellStyle(cs);  
		 shaHashCell.setCellValue(hashValueString);  
		 
		 Cell Speicherort = row.createCell(7);
		 Speicherort.setCellStyle(cs);  
		 Speicherort.setCellValue(this.model.archivPfadString + "\\" + String.valueOf(this.model.projectNumber) + "\\\\" + String.valueOf(this.model.date));  
		 
		 Cell inspBCell = row.createCell(8);
		 inspBCell.setCellStyle(cs);
		 inspBCell.setCellValue(inspBerString);
		 
		 Cell unterauftrCell = row.createCell(9);
		 unterauftrCell.setCellStyle(cs);
		 unterauftrCell.setCellValue(unterauftragsnehmerString);
		 
		 Cell auslCell = row.createCell(10);
		 auslCell.setCellStyle(cs);
		 auslCell.setCellValue(auslandString);
		 	 
		 fis.close();
		 System.out.println(" ");
		book.write(new FileOutputStream(archivlisteString));
		 System.out.println("Write is successfull");
		 System.out.println(archivlisteString);
		 Alert fnfAlert = new Alert(AlertType.CONFIRMATION);
		 fnfAlert.setTitle("schreiben erfolgreich");
		 fnfAlert.setContentText("Archivierung erfolgreich.");
		 fnfAlert.setHeaderText("whoop whoop");
		 fnfAlert.show();
		book.close();
		controller.resetAll();
	}
	
	
	public void findMissingDates()  {
		
		try (FileInputStream fis  = new FileInputStream(new File(aktuelleProjektnamenListeString));
			XSSFWorkbook wbHssfWorkbook = new XSSFWorkbook(fis);
			){
			XSSFSheet sheet = wbHssfWorkbook.getSheetAt(2);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (cell.getCellType() == CellType.NUMERIC) {
				if (cell.getNumericCellValue() == this.model.projectNumber) {
					foundProjNameString = cellIterator.next().toString();
					cellIterator.next();
					cellIterator.next();
					cellIterator.next();
					inspBerString = cellIterator.next().toString();
					unterauftragsnehmerString = cellIterator.next().toString();
					auslandString = cellIterator.next().toString();
					System.out.println("Projektname: " + foundProjNameString);
					System.out.println("inspBerString: " + inspBerString);
					System.out.println("Unterauftragsnehmer: " + unterauftragsnehmerString);
					System.out.println("Ausland: " + auslandString);
					}
				}
			}
		}
			wbHssfWorkbook.close();
		} catch (IOException e) {
			Alert fnfAlert = new Alert(AlertType.WARNING);
			fnfAlert.setTitle("File not Found");
			fnfAlert.setContentText("Aktuelle Projektnamenliste konnte nicht gefunden werden. "
					+ "Angaben über Projektname, Inspektionsbereich, Unterauftragnehmer und Ausland müssen per Hand eingetragen werden. ");
			fnfAlert.show();
			e.printStackTrace();
		}
	}
	
	
	/** 
	 * this method gets the path of ProjektnameList and the  Archivepath from a configFile
	 */
	public void configLoader() throws IOException {
		File dateiFile = new File("ArchivConfig.txt");
		try (Scanner archivScanner = new Scanner(dateiFile);){
		while(archivScanner.hasNextLine()) {
		String object = archivScanner.nextLine();
		if (object.charAt(0)=='2') {
			controller.getSelectArchivListView().getItems().add(object.substring(3));
			System.out.println("selectArchivListView = " + object.substring(3));
			}
		else if (object.charAt(0)=='1') {
			aktuelleProjektnamenListeString = object.substring(3);
			System.out.println("aktuelleProjektnamenListeString = " + object.substring(3));
			System.out.println(aktuelleProjektnamenListeString);
					}
		else if (object.charAt(0)=='3') {
			 archivlisteString = object.substring(3);
			}
		}
		archivScanner.close();
		} catch (NumberFormatException e) {
		e.printStackTrace();
		} 
		catch (FileNotFoundException e) {
			Alert fnfAlert = new Alert(AlertType.WARNING);
			fnfAlert.setTitle("File not Found");
			fnfAlert.setContentText("Kein File zu laden gefunden...");
			fnfAlert.show();
			e.printStackTrace();			
		}
	}
}
