package main.data;

/** Class for file data to be transferred between client and server. */
public class FileClackData extends ClackData {
    private String fileName;
    private String fileContents;

    /**
     *  Constructor with all arguments.
     *  @param userName     the username.
     *  @param fileName     the filename.
     *  @param type         the type.
     */
    public FileClackData(String userName, String fileName, int type) {
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = null;
    }
    /**
     *  Constructor with no arguments provided. Defaults to "Anon" username, type 0, "N/A" for filename, and null filecontents."
     */
    public FileClackData() {
        super("Anon", 0);
        this.fileName = "N/A";
        this.fileContents = null;
    }
    /**
     *  Setter for fileName.
     *  @param fileName     the file name.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     *  Getter for fileName.
     *  @return fileName    returns the file name.
     */
    public String getFileName() {
        return fileName;
    }
    /**
     *  Overridden getData() to return the fileContents.
     *  @return fileContents    returns the file contents.
     */
    @Override
    public String getData() {
        return fileContents;
    }
    
    @Override
    public String getData(String key) {
        String decryptedContents = super(decrypt(this.fileContents, key));
        return decryptedContents;
    }
    /**
     *  Currently returns nothing.
     */
    public void readFileContents() {

    }
    /**
     *  Currently returns nothing.
     */
    public void writeFileContents() {
    
    }
    /**
     *  Overridden hashCode() method.
     *  @return result      returns the hashcode.
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result+this.getType();
        result = 31*result+(this.getUserName()!= null ? this.getUserName().hashCode():0);
        return result;
    }
    /**
     *  Overridden equals() method.
     *  @param other    object to compare to.
     *  @return     returns the boolean value for the comparison.
     */
    @Override
    public boolean equals(Object other) {
        FileClackData otherFileClackData = (FileClackData)other;
        return this.fileName == otherFileClackData.fileName;
    }
    /**
     *  Overridden toString() method.
     *  @return     returns the object as a string.
     */
    @Override
    public String toString() {
        return "This instance of FileClackData has the following properties:\n"
                + "Username: " + this.userName + "\n"
                + "Type: " + this.type + "\n"
                + "Date: " + this.date.toString() + "\n"
                + "File Name: " + this.fileName + "\n"
                + "File Contents: " + this.fileContents + "\n";
    } 
}
