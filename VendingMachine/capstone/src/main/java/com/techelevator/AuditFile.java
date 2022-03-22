package com.techelevator;
import java.time.*;

import javax.imageio.IIOException;
import java.io.*;

public class AuditFile {
    private String Entry;
    private File outPutFile;

    // Constructor
    public AuditFile() {
        outPutFile = new File("C:\\Users\\Student\\workspace\\module-1-capstone-team-0\\capstone\\Log.txt");
        try {
            outPutFile.delete();
            outPutFile.createNewFile();
        } catch (IOException e) {

        }
    }

    //Methods
    public void UpdateAuditFile(String Entry) {
        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(outPutFile, true))) {
        dataOutput.println(LocalDate.now() + " " +
                LocalTime.now() + " " + Entry);
        } catch(FileNotFoundException ex) {
        }
    }

}
