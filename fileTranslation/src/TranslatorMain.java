import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import service.FileReaderService;
import service.TranslationWord;

public class TranslatorMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long lStartTime = new Date().getTime(); // start time
		FileReaderService fileservice = new FileReaderService();
		ArrayList<String> findWordsList = fileservice.getFindWordsList();
		Map<String,String> dictionaryMap=fileservice.getDictionaryList();
		Map<String,String> findWordsDictionary=dictionaryMap.entrySet().stream()
				.filter(map->findWordsList.contains(map.getKey()))
		.collect(Collectors.toMap(m->m.getKey(),m->m.getValue()));
		
		//dictionaryMap.keySet();
		
		Set<String> findWordsnotinDictionary=(HashSet<String>) findWordsList.stream()
				.filter(word->!dictionaryMap.keySet()
						.contains(word)).collect(Collectors.toSet());
		System.out.println("**********dictionaryMap*********"+dictionaryMap);
		System.out.println("**********findWordsDictionary*********"+findWordsDictionary);
		System.out.println("********findWordsnotinDictionary*********"+findWordsnotinDictionary);
		
		Set<TranslationWord> translatedWords=fileservice.translateFile(findWordsDictionary);
		
		fileservice.getReport(translatedWords);
		
		long lEndTime = new Date().getTime(); // end time

	    long difference = lEndTime - lStartTime; // check different

	    System.out.println("Elapsed milliseconds: " + difference);
	    System.out.println("Elapsed seconds: " + difference/1000F);
	    Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory +" Megabytes: "+memory/(1024L*1024L)+" MB");
        /* MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
       long memoryUsage= heapMemoryUsage.getUsed();
        System.out.println("Used memory is bytes: " + memoryUsage +" Megabytes: "+ memoryUsage/(1024L*1024L)+" MB");
*/	}

}
