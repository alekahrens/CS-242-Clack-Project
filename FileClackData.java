package data;

public class FileClackData extends ClackData {
    private String fileName;
    private String fileContents;
    
    public FileClackData(String userName, String fileName, int type) {
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = null;
    }

    public FileClackData() {
        super("Anon", 0);
        this.fileName = "N/A";
        this.fileContents = null;
    }
    
    public setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public getFileName() {
        return fileName;
    }
    
    public getData() {
   
    }
    
    public readFileContents() {
    
    }
    
    public writeFileContents() {
    
    }
    
    public int hashCode() {
    
    }
    
    public boolean equals(FileClackData otherFileClackData) {
    
    }
    
    public String toString() {
    
    } 
}
