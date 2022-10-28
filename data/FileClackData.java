package data;

import java.io.*;

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
    /**
     *  Overridden getData() to return decrypted fileContents
     *  @return decryptedContents   returns the decrypted file contents
     */
    @Override
    public String getData(String key) {
        String decryptedContents = decrypt(this.fileContents, key);
        return decryptedContents;
    }
    /**
     *  Reads the file given by fileName and stores the contents in fileContents
     */
    public void readFileContents() throws IOException {
        File file = new File(this.fileName);
        try {
            BufferedReader contentReader = new BufferedReader(new FileReader(file) );
            String line = contentReader.readLine();
            while (line != null){
                this.fileContents+=line;
                line = contentReader.readLine();
            }
            System.out.println(this.fileContents);
            contentReader.close();
        }
        catch (FileNotFoundException fnfe) {
            System.err.println("File does not exist.");
        }
        catch (IOException ioe) {
            System.err.println("IOException occurred.");
        }
    }
    /**
     *  Reads the file given by fileName, encrypts the contents, and stores the encrypted contents in fileContents
     */
    public void readFileContents(String key) throws IOException {
        File file = new File(this.fileName);
        try {
            BufferedReader encryptReader = new BufferedReader(new FileReader(file) );
            String line = encryptReader.readLine();
            while (line != null){
                this.fileContents+=line;
                line = encryptReader.readLine();
            }
            System.out.println(this.fileContents);
            this.fileContents = encrypt(this.fileContents, key);
            encryptReader.close();
        }
        catch (FileNotFoundException fnfe) {
            System.err.println("File does not exist.");
        }
        catch (IOException ioe) {
            System.err.println("IOException occurred.");
        }
    }
    /**
     *  Writes data stored in fileContents to the file given by fileName
     */
    public void writeFileContents() throws IOException {
        try {
            FileWriter contentWriter = new FileWriter(this.fileName);
            contentWriter.write(this.getData());
            contentWriter.close();
        }
        catch (FileNotFoundException fnfe) {
            System.err.println("File does not exist.");
        }
        catch (IOException ioe) {
            System.err.println("IOException occurred.");
        }
    }
     /**
     *  Decrypts data stored in fileContents, and writes to the file given by fileName
     */
    public void writeFileContents(String key) throws IOException {
        try {
            FileWriter contentWriter = new FileWriter(this.fileName);
            
            contentWriter.write(this.getData(key));
            contentWriter.close();
        }
        catch (FileNotFoundException fnfe) {
            System.err.println("File does not exist.");
        }
        catch (IOException ioe) {
            System.err.println("IOException occurred.");
        }
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
                + "Username: " + this.getUserName() + "\n"
                + "Type: " + this.getType() + "\n"
                + "Date: " + this.getDate().toString() + "\n"
                + "File Name: " + this.fileName + "\n"
                + "File Contents: " + this.fileContents + "\n";
    } 
}
