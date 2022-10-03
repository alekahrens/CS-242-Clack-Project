package main.data;

/* Class for file data to be transferred between client and server. */
public class FileClackData extends ClackData {
    private String fileName;
    private String fileContents;

    /*
        Constructor with all arguments: username, filename, and type.
     */
    public FileClackData(String userName, String fileName, int type) {
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = null;
    }
    /*
        Constructor with no arguments provided. Defaults to "Anon" username, type 0, "N/A" for filename, and null filecontents."
     */
    public FileClackData() {
        super("Anon", 0);
        this.fileName = "N/A";
        this.fileContents = null;
    }
    /*
        Setter for fileName.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /*
        Getter for fileName.
     */
    public String getFileName() {
        return fileName;
    }
    /*
        Overridden getData() to return the fileContents.
     */
    @Override
    public String getData() {
        return fileContents;
    }
    /*
        Currently returns nothing.
     */
    public void readFileContents() {

    }
    /*
        Currently returns nothing.
     */
    public void writeFileContents() {
    
    }
    /*
        Overridden hashCode() method.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result+getType();
        result = 31*result+(getUserName()!= null ? getUserName().hashCode():0);
        return result;
    }
    /*
        Overridden equals() method.
     */
    @Override
    public boolean equals(Object other) {
        FileClackData otherFileClackData = (FileClackData)other;
        return this.fileName == otherFileClackData.fileName;
    }
    /*
        Overridden toString() method.
     */
    @Override
    public String toString() {
        return "The username is: " + this.getUserName() + ".\n The type is: " + this.getType() + ".\n The filename is: " + this.fileName + ".";
    } 
}
