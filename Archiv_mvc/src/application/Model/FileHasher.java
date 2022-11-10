package application.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHasher {

	Model model;
	String hashValue2;
	
	public FileHasher(Model model) {
		this.model = model;
		hashFile();
	}
	
	/** hash-funktion */
	private void hashFile() {
	
	try {
		byte[] mdSHA512  = MessageDigest.getInstance("SHA-512").digest(Files.readAllBytes(Paths.get(this.model.getZipDirName() + ".zip")));
		StringBuilder s = new StringBuilder();
		for(byte b : mdSHA512) {
		s.append(String.format("%02x",b));
		}
		hashValue2 = s.toString();
		this.model.controller.getProjectHashLabel().setText(hashValue2);
		ExcelWriter.hashValueString = hashValue2;
	} catch (NoSuchAlgorithmException e11) {
		e11.printStackTrace();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	}
	
}
