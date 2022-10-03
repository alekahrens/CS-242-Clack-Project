package main.data;

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
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void getData() {
   
    }
    
    public void readFileContents() {
    
    }
    
    public void writeFileContents() {
    
    }
    
    public int hashCode() {
        int result = 17;
        result = 31*result+getType();
        result = 31*result+(getUserName()!= null ? getUserName().hashCode():0);
        return result;
    }
    
    public boolean equals(Object other) {
        FileClackData otherFileClackData = (FileClackData)other;
        return this.fileName == otherFileClackData.fileName;
    }
    
    public String toString() {
        return "The username is: " + this.getUserName() + ".\n The type is: " + this.getType() + ".\n The filename is: " + this.fileName + ".";
    } 
}
