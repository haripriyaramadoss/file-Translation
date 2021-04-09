import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


import java.io.*;

public class FindWordList{

    public static void main(String args[]) throws Exception {

      FileInputStream fstream1 = new FileInputStream("C:\\Users\\harip\\find_words.txt");
      FileInputStream fstream2 = new FileInputStream("C:\\Users\\harip\\t8.shakespeare.txt");

      DataInputStream in1= new DataInputStream(fstream1);
      DataInputStream in2= new DataInputStream(fstream2);

      BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
      BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));

      String strLine1, strLine2;


      while((strLine1 = br1.readLine()) != null && (strLine2 = br2.readLine()) != null){
          if(!strLine1.equals(strLine2)){
              System.out.println(strLine1);

          }
      }
    }
}