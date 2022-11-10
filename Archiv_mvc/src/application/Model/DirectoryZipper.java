package application.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

//import application.WriteInExcel;
import application.Controller.MainController;

public class DirectoryZipper {

	MainController controller;
    List<String> filesListInDir = new ArrayList<String>();
	
	public DirectoryZipper(MainController controller) {
	this.controller = controller;	
	}
	
    /**
     * This method zips the directory
     * @param dir
     * @param zipDirName
     */
    
	
    public void zipDirectory(File dir, String zipDirName) {
        try (FileOutputStream fos = new FileOutputStream(zipDirName+".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
        		){
            populateFilesList(dir);
            //now zip files one by one
            //create ZipOutputStream to write to the zip file
            
            for(String filePath : filesListInDir){ 
                System.out.println("Zipping "+filePath);
                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1, filePath.length()));
                zos.putNextEntry(ze);
                //read the file and write to ZipOutputStream
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method zips the directory
     * @param dir
     * @param zipDirName
     * @param newPathString
     */
//    public  void zipDirectory(File dir, String zipDirName, String newPathString) {
//        try {
//        	
//        	
//    populateFilesList(dir);
//	//now zip files one by one
//	//create ZipOutputStream to write to the zip file
//	//File dir1 = new File(zipDirName);
//	// File zipFile = new File(zipDirName + "\\\\" + prjDate + ".zip");
//    FileOutputStream fos = new FileOutputStream(dir + ".zip");
//    ZipOutputStream zos = new ZipOutputStream(fos);
//            
//    for(String newPath2 : filesListInDir){
//    System.out.println("Zipping " + newPath2);
//    //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
//    ZipEntry ze = new ZipEntry(newPath2.substring(dir.getAbsolutePath().length()+1, newPath2.length()));
//    zos.putNextEntry(ze);
//              
//     System.out.println("test1: " + newPath2);
//     //read the file and write to ZipOutputStream
//     FileInputStream fis = new FileInputStream(newPath2);
//     byte[] buffer = new byte[1024];
//     int len;
//     while ((len = fis.read(buffer)) > 0) {
//           zos.write(buffer, 0, len);
//           }
//           zos.closeEntry();
//           fis.close();
//            }
//             
//      zos.close();
//      fos.close();
//      } catch (IOException e) {
//            e.printStackTrace();
//      }
//    }
    
    /**
     * This method populates all the files in a directory to a List
     * @param dir
     * @throws IOException
     */
    private void populateFilesList(File dir) throws IOException {
        
    	File[] files = dir.listFiles();
        for(File file : files){
            if
            (file.isFile()) this.filesListInDir.add(file.getAbsolutePath());
                        else populateFilesList(file);
        }
    }

   
    
    static void copyToArchive(String oldFilePath, String newFilePath,  String projectNrString) throws IOException { {
    	
    	    System.out.println(newFilePath);
            FileInputStream in = new FileInputStream(oldFilePath + ".zip");
            FileOutputStream out = new FileOutputStream(newFilePath + "\\\\" + projectNrString + ".zip");
            long dateiLaenge = (new File(oldFilePath+ ".zip")).length();
            byte[] b = new byte[(int) dateiLaenge];
            int len;
            while ((len = in.read(b)) > 0) {
                out.write(b, 0, len);
            }
            out.close();
            in.close();
            System.out.println("Datei '" + oldFilePath + ".zip' wurde in die Datei '"
                    + newFilePath + ".zip" + "' kopiert.");
        }
    	
    }
	
}
