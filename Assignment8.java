/*---------------------------------Q1: File Analyzer (FileAnalyzer.java)----------------------------------------------------------------*/

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileAnalyzer {
   public static void main(String[] args) throws IOException {
      System.out.println("Filename: ");
      Scanner in = new Scanner(System.in);
      String name = in.nextLine();
      FileCounter counter = new FileCounter();
      FileReader reader = new FileReader(name);
      Scanner fileIn = new Scanner(reader);
      counter.read(fileIn);
      fileIn.close();
      System.out.println("Characters: " + counter.getCharacterCount());
      System.out.println("Words: " + counter.getWordCount());
      System.out.println("Lines : " + counter.getLineCount());
   }
}

class FileCounter{

	   private int characterCount, wordCount, lineCount;
	   
	   public FileCounter() {
	      
	   }

	   /**
	      Processes an input source and adds its character, word, and line
	      counts to the respective variables.
	      @param in the scanner to process
	   */
	   
	   public void read(Scanner in) throws IOException {
	    // your code
		   
		   while(in.hasNextLine()) {
			   
			   // count line
			   this.lineCount++;
			   String line = in.nextLine();
			   
			   // count character and word
			   String[] strArray = line.split(" ");
			   for(String str : strArray) {
				   // character++
				   this.characterCount+=str.length();
				   
				   // word++, only if this particular String contains A-Z or a-z or 0-9
				   for(int i=0; i<str.length();i++) {
					   char c = str.charAt(i);
					   if( (c>=65 && c<=90) || (c>=97 && c<=122) || (c>=48 && c<=57) ) {
						   this.wordCount++;
						   System.out.println(str);
						   break;
					   }
				   }
			   }			   
		   } 

	   }
	   
	   public int getCharacterCount() {
		   return this.characterCount;   
	   }
	   
	   public int getWordCount() {
		   return this.wordCount;   
	   }
	   
	   public int getLineCount() {
		   return this.lineCount;   
	   }
	      
}




/*---------------------------------Q2: Lyric Analyzer (LyricAnalyzer.java)----------------------------------------------------------------*/

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class LyricAnalyzer {
	
	private HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
	
	public static void main(String[] args) {
		File file = new File("src/Question2_test1.txt");
		LyricAnalyzer la =new LyricAnalyzer();
		try {
			la.read(file);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		la.displayWords();
		System.out.println(la.mostFrequentWord());
		File f = new File("src/writeLyrics.txt");
		try {
			la.writeLyrics(f);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public void read(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line =null;
		int count =0;
		while((line=br.readLine())!=null) {
			String[] lyricWordArray = line.split(" ");
			for(int i=0;i<lyricWordArray.length;i++) {
				count++;
				if(i==lyricWordArray.length-1) {
					add(lyricWordArray[i],-count);
				}
				else {
					add(lyricWordArray[i],count);
				}
			}	
		}
		br.close();
		
	}
	
	
	private void add(String lyricWord, int wordPosition) {
		if(this.map.containsKey(lyricWord)==false) {
			ArrayList<Integer> positionList = new ArrayList<Integer>();
			positionList.add(wordPosition);
			this.map.put(lyricWord, positionList);
		}
		else {
			this.map.get(lyricWord).add(wordPosition);
		}
		
		
	}
	
	
	public void displayWords() {
		String[] words = new String[this.map.size()];
		int index=0;
		for(String lyricWord: this.map.keySet()) {
			words[index]=lyricWord;
			index++;
		}
		Arrays.sort(words);
		for(String word:words) {
			System.out.println(word+"    "+this.map.get(word));
		}
		
	}
	
	public void writeLyrics(File file) throws IOException {
		
		FileWriter fr = new FileWriter(file);
		String[] wordsAll = new String[count()+1];
		Arrays.fill(wordsAll, "");
		
		for(Entry<String, ArrayList<Integer>> entry : this.map.entrySet()) {
			for(int i=0; i<entry.getValue().size();i++) {
				if(entry.getValue().get(i)<0) {
					wordsAll[-entry.getValue().get(i)] = entry.getKey()+"\n";
				}
				else {
					wordsAll[entry.getValue().get(i)] = entry.getKey();
				}
			}
		}
		
		for(String word : wordsAll) {
			
			if(word.equals("")) {
				continue;
			}
			else if(word.contains("\n")) {
				fr.write(word);
			}
			else {
				fr.write(word+" ");
			}
		}
		
		fr.close();
		
	}
	
	public int count() {
		//return this.map.size();
		int size = 0;
		for(ArrayList<Integer> arrayList : this.map.values()) {
			size+=arrayList.size();
		}
		return size;
	}
	
	
	public String mostFrequentWord() {
		
		String[] words = new String[this.map.size()];
		int index = 0;
		for(String word : this.map.keySet()) {
			words[index] = word;
			index++;
		}
		Arrays.sort(words);
		for(int i=0; i<words.length-1;i++) {
			for(int j=i+1; j<words.length;j++) {
				if(this.map.get(words[j]).size()>this.map.get(words[i]).size()) {
					String temp = words[i];
					words[i] = words[j];
					words[j] = temp;
				}
			}		
		}
		return words[0];		
	}

}





/*---------------------------------Q3: My Json (MyJson.java)----------------------------------------------------------------*/


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;
import java.util.ArrayList;



public class MyJson {
	
	public static void main(String[] args) throws IOException{
	    File file = new File("src/Question3_camino.txt");
	    ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
	    String s = getJsonString(vehicles);
	    writeToFile(s, file.getParent());
	    
	}
	
	private static ArrayList<Vehicle> readAndGetVehicles(File file) throws IOException {
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		BufferedReader br =new BufferedReader(new FileReader(file));
		String line=null;
		int count=0;
		while((line=br.readLine())!=null) {
			count++;
			if(count==1) {continue;}
			String[] carDetailArray = line.split("~");
			Vehicle vehicle =new Vehicle(carDetailArray);
			vehicles.add(vehicle);
		}
		br.close();
		return vehicles;
		
		
	}
	
	
	public static String getJsonString(ArrayList<Vehicle> vehicles) {
		String jsonString = new String();
		int count = 0;
		jsonString += "{\n" + "\""+vehicles.get(0).webId+"\" : [\n";
		for(Vehicle vehicle : vehicles) {
			count++;
			if(count == vehicles.size()) {
				jsonString += vehicle+"\n";
			}
			else {
				jsonString += vehicle+",\n";	
			}
		}
		jsonString += "]\n" + "}";
		return jsonString;
		
	}

	
	public static void writeToFile(String inputToWrite, String filePath) {
		try {
			FileWriter fr = new FileWriter(filePath+"/writeToJsonFile.txt");
			fr.write(inputToWrite);
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	
}




class Vehicle{

    String id;
    String webId;
    Category category;
    Year year;
    String make;
    String model;
    String trim;
    String type;
    double price;
    URL photo;

    Vehicle(String[] arr){
        this.id = arr[0];
        this.webId = arr[1];
        this.category = Category.getCategory(arr[2].toLowerCase());
        this.year = Year.parse(arr[3]);
        this.make = arr[4];
        this.model = arr[5];
        this.trim = arr[6];
        this.type = arr[7];
        this.price = Double.parseDouble(arr[8]);
        try {
            this.photo = new URL(arr[9]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
    		String str = "{\n" + 
    				"\"id\" : \""+this.id+"\",\n" + 
    				"\"category\" : \""+this.category+"\",\n" + 
    				"\"year\" : \""+this.year+"\",\n" + 
    				"\"make\" : \""+this.make+"\",\n" + 
    				"\"model\" : \""+this.model+"\",\n" + 
    				"\"trim\" : \""+this.trim+"\",\n" + 
    				"\"type\" : \""+this.type+"\",\n" + 
    				"\"price\" : "+this.price+",\n" + 
    				"\"photo\" : \""+this.photo+"\"\n" + 
    				"}";
    		
    		return str;
    }

}

enum Category{
    NEW , USED, CERTIFIED;

    public static Category getCategory(String cat){
       switch (cat){
           case "used": return USED;
           case "new": return NEW;
           case "certified": return CERTIFIED;
           default: throw new IllegalArgumentException();
       }
    }

    @Override
    public String toString() {
        switch (this){
            case NEW: return "NEW";
            case USED: return "USED";
            case CERTIFIED: return "CERTIFIED";
            default: throw new IllegalArgumentException();
        }
    }
}


