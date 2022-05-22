
package scproject2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Maega
 */

//this is an sbstract class containing the functions required to save and load objects to file
public abstract class AbstractFile {
    
    //this function loads objects from the file
    public Object Load(String fileName){
        try {
            //opens file stream and object stream
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            //reads in object
            Object obj = objectIn.readObject();
            //closes streams
            objectIn.close();
            //returns the read object
            return obj;
        } catch (Exception ex) {
            System.out.println("Warning loading was not succesful!");
            return null;
        }
    }
    
    //this function saves objects to file
    public void Save(String fileName, Object obj){
        try {
            //opens file and object stream
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            //writes the object to file
            objectOut.writeObject(obj);
            //closes streams
            objectOut.close();
            System.out.println("Save succesful");
        } catch (Exception ex) {
               System.out.println("Warning save not succesful!!");
        }
    }
    
}
