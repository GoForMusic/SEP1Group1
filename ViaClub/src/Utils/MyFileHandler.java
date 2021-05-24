package Utils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;
import java.util.ArrayList;

//Writes the given object to a binary file with the specified file name
//If the file already exists, it will be erased and a new file with the same name will be created.
public class MyFileHandler
{
  public static void writeObjectToFile(String fileName, Object obj)
  {
    ObjectOutputStream writeToFile=null;
    try
    {
      writeToFile = new ObjectOutputStream(new FileOutputStream(fileName));
      writeToFile.writeObject(obj);
    }
    catch(IOException e)
    {
      System.out.println("IO Exception handling file: " + fileName);
    }
    finally
    {
      if(writeToFile!=null)
      {
        try
        {
          writeToFile.close();
        }
        catch(IOException e)
        {
          System.out.println("IO Exception closing file");
        }
      }
    }
  }

  //Reads the object from the file and returns it. Notice that the returned object
  //has to be casted to the desired class type.
  public static Object readObjectFromFile(String fileName)
  {
    Object obj=null;
    ObjectInputStream readFromFile=null;
    try
    {
      readFromFile = new ObjectInputStream(new FileInputStream(fileName));
      try
      {
        obj = readFromFile.readObject();
      }
      catch(EOFException e)
      {
        //Finish reading
      }
    }
    catch(IOException e)
    {
      System.out.println("IO Exception reading file: " + fileName);
    }
    catch(ClassNotFoundException e)
    {
      System.out.println("Class not found exception");
    }
    finally
    {
      if(readFromFile!=null)
      {
        try
        {
          readFromFile.close();
        }
        catch(IOException e)
        {
          System.out.println("IO Error closing the file");
        }
      }
    }

    return obj;
  }

  //converts de given array list to xml and saves it to the specified file.
  //if the file exists, it will be deleted and created a new one, as this
  //function is designed only to export data.
  public static void writeToXmlFile(ArrayList<?> arrayList, String fileName)
  {
    XStream xstream = new XStream(new DomDriver());
    String xml = xstream.toXML(arrayList);
    PrintWriter writeToFile = null;

    try
    {
      FileOutputStream fileOutStream = new FileOutputStream(fileName, false);
      writeToFile = new PrintWriter(fileOutStream);
      writeToFile.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      writeToFile.println(xml);
    }
    catch (Exception e)
    {
      System.out.println("File not found exception");
    }
    finally
    {
      if (writeToFile != null)
      {
        writeToFile.close();
      }
    }
  }
}
