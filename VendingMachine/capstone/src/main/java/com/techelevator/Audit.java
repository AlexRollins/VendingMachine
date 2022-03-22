package com.techelevator;

import javax.imageio.IIOException;
import java.io.*;

public class Audit {
    private String Entry;
    private File outPutFile;

    // Constructor
    public Audit() {
        outPutFile = new File("C:\\Users\\Student\\workspace\\module-1-capstone-team-0\\capstone\\AuditFile.Txt");
        try {
            outPutFile.createNewFile();
        } catch (IOException e) {
        }
    }

    //Methods
    public void UpdateAuditFile(String Entry) {
        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(outPutFile, true))) {
        dataOutput.println(Entry);
        } catch(FileNotFoundException ex) {
        }
    }

}
