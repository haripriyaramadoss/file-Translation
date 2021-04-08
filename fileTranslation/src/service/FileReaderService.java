package service;

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

public class FileReaderService {

	public ArrayList<String> getFindWordsList() {
		Scanner s = null;
		ArrayList<String> findWordslist = new ArrayList<String>();
		try {
			s = new Scanner(new File("././resources/find_words.txt"));

			while (s.hasNext()) {
				findWordslist.add(s.next());
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return findWordslist;
	}

	public Map<String, String> getDictionaryList() {
		Map<String, String> dictionaryMap = new HashMap<String, String>();

		try {
			FileInputStream fstream = new FileInputStream(new File("././resources/english-french-dictionary.txt"));
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.trim().length() > 0 && line.contains(":")) {
					String[] lineSplit = line.trim().split(":");

					if (lineSplit.length != 0 && lineSplit[0] != null) {
						dictionaryMap.put(lineSplit[0].trim(), lineSplit.length <= 1 ? "" : lineSplit[1].trim());
					}
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dictionaryMap;
	}

	public Set<TranslationWord> translateFile(Map<String, String> findWordsDictionary) {
		StringBuilder stringBuffer = new StringBuilder();
		String line = null;
		File toBeTranslateFile = new File("././resources/t8.shakespeare.txt");
		Set<TranslationWord> translatedWords = new HashSet<TranslationWord>();
		try {
			FileReader fr = new FileReader(toBeTranslateFile);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {

				if (line.equals("") || line.equals("\n")) {
					stringBuffer.append("\n");
				} else {
					String[] wordSplitArr = line.split(" ");
					for (String wordStr : wordSplitArr) {
							String targetStr=wordStr.replaceAll("[-+.^:,*]","");
							String meaningvalue=findWordsDictionary.get(targetStr.trim().toLowerCase());
							if(meaningvalue!=null && meaningvalue!="")
							{
								String[] meanings = meaningvalue.split(",");
									if (meanings.length > 0) {
										//replaced = true;
										line = line.replaceAll("(?i)" + targetStr.trim(), meanings[0]);
										TranslationWord word = new TranslationWord();
										word.setOriginalWord(targetStr.trim().toLowerCase());
										word.setReplaceWord(meanings[0]);
										word.setCount(1);
										boolean isadded = translatedWords.add(word);
										if (!isadded)// duplicate
										{
											for (Iterator<TranslationWord> it = translatedWords.iterator(); it.hasNext();) {
												TranslationWord wordit = it.next();
												if (wordit.equals(word))
													wordit.setCount(wordit.getCount() + 1);
											}
										}
									}
							}
					}

					stringBuffer.append(line).append("\n");
				}
			}
			fr.close();
			br.close();

			FileWriter fw = new FileWriter(new File("././resources/out.txt"));
			BufferedWriter out = new BufferedWriter(fw);
			out.write(stringBuffer.toString());
			out.flush();
			out.close();
			System.out.println("Successfully written to file");
			if (!Desktop.isDesktopSupported())// check if Desktop is supported
												// by Platform or not
			{
				System.out.println("not supported");
			}
			Desktop desktop = Desktop.getDesktop();
			File outPutFile = new File("././resources/out.txt");
			if (outPutFile.exists())
				desktop.open(outPutFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return translatedWords;
	}

	public void getReport(Set<TranslationWord> translatedWords) {
		try {
			FileWriter fw = new FileWriter(new File("././resources/report.txt"));
			BufferedWriter out = new BufferedWriter(fw);
			out.write("Total no. of words replaced from the find-list: "+translatedWords.size()+"\n\n");
			out.write(String.format("%20s %20s %20s %20s","SL NO", "ORIGINAL WORD","REPLACE WORD","REPLACE COUNT \r\n\n"));
			int slNo=1;
			for (Iterator<TranslationWord> it = translatedWords.iterator(); it.hasNext();) {
				TranslationWord wordit = it.next();
				//out.write(wordit.getOriginalWord() + "\t" + wordit.getReplaceWord() + "\t" +  + "\n");
				out.write(String.format("%20s %20s %20s %20s",slNo++, wordit.getOriginalWord(),wordit.getReplaceWord(),wordit.getCount()+" \r\n"));
			}
			out.flush();
			out.close();

			if (!Desktop.isDesktopSupported())// check if Desktop is supported
												// by Platform or not
			{
				System.out.println("not supported");
			}
			Desktop desktop = Desktop.getDesktop();
			File outPutFile = new File("././resources/report.txt");
			if (outPutFile.exists())
				desktop.open(outPutFile);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
