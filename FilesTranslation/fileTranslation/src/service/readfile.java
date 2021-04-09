
import java.io.*;
public class readfile
{
  public static void main(String[] args) throws Exception
  {
    // pass the path to the file as a parameter
    FileReader fr =  new FileReader("C:\\Users\\harip\\find_words.txt");

  
    int i;
    while ((i=fr.read()) != -1)
      System.out.print((char) i);
FileReader fs =  new FileReader("C:\\Users\\harip\\english-french-dictionary.csv");
 int j;
 while ((j=fs.read()) != -1)
      System.out.print((char) j);  
FileReader ft =  new FileReader("C:\\Users\\harip\\t8.shakespeare.txt");
int k;
while ((k=ft.read()) != -1)
      System.out.print((char) k);
  }
}

