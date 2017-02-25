/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;

/**
 *
 * @author Zikri
 */
public class ApplicationFile {

    private int fileId;
    //private int applicationNo;
    private FileInputStream file;
    private String fileName;

    public ApplicationFile() {
    }

    public ApplicationFile( FileInputStream file, String fileName) {
      
        this.file = file;
        this.fileName = fileName;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public FileInputStream getFile() {
        return file;
    }

    public void setFile(FileInputStream file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
