package Utils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;
import java.util.ArrayList;

/**
 * Class used for writing and reading binary files and writing a text file (to export the XML file format)
 * @author Adrian Militaru, Adrian Pompierescu, Gabriel Moutinho Tristan, Freja Hansen
 * @version 1.0
 */
public class MyFileHandler
{
  /**
   * A method that will write an object to a binary file
   * @param fileName the binary file name
   * @param obj Object that will be inserted into the binary file
   */
  public void writeObjectToFile(String fileName, Object obj)
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

  /**
   * A method that will read an object from a binary file
   * @param fileName the binary file name
   */
  public Object readObjectFromFile(String fileName)
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
    catch(FileNotFoundException e)
    {
      System.out.println("File not found");
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

  /**
   * A method that will write an array list of string on text file in xml format
   * @param arrayList the list of strings
   * @param fileName the text file name
   */
  public void writeToXmlFile(ArrayList<?> arrayList, String fileName)
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
