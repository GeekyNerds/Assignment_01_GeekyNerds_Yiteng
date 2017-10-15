package HangmanTest;


import java.util.ArrayList;
import java.util.Scanner;


public class Project1 {
	
	public static void main(String[] args) {
		Hangman hangMan = new Hangman();
		hangMan.playGame();
	}
	
}

class Hangman {
	final String[] words = {"my", "first", "hang", "man", "game"};
	ArrayList<String> chosedWord = chooseWord();
	ArrayList<String> chosedWordCopy = new ArrayList<String>(chosedWord);
	
	ArrayList<String> correctList = new ArrayList<String>();
	ArrayList<String> wrongList = new ArrayList<String>();
	
	ArrayList<String> chooseWord() {
		// use ArrayList to accept each character of randomly chosed word for further comparing with the input guess letter
		ArrayList<String> word = new ArrayList<String>();
		
		int i = (int)(Math.random()*words.length);
		String randomWord = words[i];
		for(int n=0; n<randomWord.length(); n++) {
			word.add(String.valueOf(randomWord.charAt(n)));
		}
		
		return word;	
	}
	
	
	
	void handleGuess(String guessLetter) {
		
		for(int x=0,compareTimes=0; x<chosedWordCopy.size(); x++) {
		
			if (guessLetter.equals(chosedWordCopy.get(x))) {
				correctList.add(guessLetter);
				chosedWordCopy.remove(x);
				break;
			}
			else {
				compareTimes++;
				if(compareTimes==chosedWordCopy.size()) {
					wrongList.add(guessLetter);
				}
				
			}
				
		}
		
	}
	
	boolean gameWon() {

		if(correctList.size()==chosedWord.size()) {
			System.out.println("Great, you won :) You guessed the word and saved the man!!!");
			return true;
		}
		else {
			return false;
		}
	}
	
	boolean gameLose() {
		if(wrongList.size()==8) {
			System.out.println("Ooops, you lose :(  You have attempted eight incorrect letters...");	
			return true;
		}
		else {
			return false;
		}
	}
	
	void gameOver() {	
		System.out.println("Thanks for your playing! Game has been exited, bye-bye!");
	}
	
	void printHangman() {
		int pillarScaffold = 6;
		System.out.println(" -----------");
		System.out.println("|         |");
		// put each part of the man into an ArrayList: index number in accordance with wrongList size
		ArrayList<String> man = new ArrayList<String>();
		man.add("|         O");  //0
		man.add("|         |");  //1
		man.add("|      ---");   //2
		man.add(" ---");         //3
		man.add("|        /");   //4
		man.add(" \\");          //5
		man.add("|     --");     //6 
		man.add("     --");      //7
		man.add("|       /");     //8 left leg lower part
		man.add("|       /   \\");//9 left & right leg lower part
        
		// print each part of the man from ArrayList: index number i is in accordance with wrongList size
		for(int i=0;i<wrongList.size();i++) {
	
			if(i==2) {
				if(wrongList.size()==3)
					System.out.println(man.get(i));
				else {
					System.out.print(man.get(i));
				}
			}
			else if(i==4) {
				if(wrongList.size()==5) { 
					System.out.println(man.get(i));
					System.out.println(man.get(i+4));//8 left leg lower part	
				}
				else {
					System.out.print(man.get(i));
				}
			}
			else if(i==5) {
				System.out.println(man.get(i));
				System.out.println(man.get(i+4)); //9 left & right leg lower part
			}
			
			else if(i==6) {
				if(wrongList.size()==7) {
					System.out.println(man.get(i));
				}
				else {
					System.out.print(man.get(i));	
				}
			}
			
			else {
				System.out.println(man.get(i));
				
			}
			
		}
		
		for(int i=0;i<pillarScaffold-wrongList.size();i++) {
			System.out.println("|");
		}
		
		System.out.println("|______________");
		
	}
 
	void playGame() {
		System.out.println("Welcome to the Hangman Game!");
		printHangman();
		System.out.print("Word to be guessed: ");
		displayWord();
		
		while(wrongList.size()<=8) {
			if(gameWon()) {
				gameOver();
				break;
			}
			else if(gameLose()) {
				gameOver();
				break;
			}
			else {
				
				System.out.print("Please enter a letter to guess: ");
				Scanner scanner = new Scanner(System.in);
				String guessLetter = scanner.nextLine();
				handleGuess(guessLetter);
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				printHangman();
				System.out.println("Previous letters you have guessed: "+"1. correctly guessed letters: "+correctList+"  2. wrongly guessed letters: "+wrongList);
				System.out.print("Your current word guessed status: ");
				displayWord();
				
			}
		}	
	}
	
	void displayWord() {
		char[] dashWord = new char[chosedWord.size()]; 
		for(int i=0; i<chosedWord.size(); i++) {
			dashWord[i]='-';	
		}
		
		for(int j=0;j<correctList.size();j++) {
			dashWord[chosedWord.indexOf(correctList.get(j))]=correctList.get(j).charAt(0);	
		}
		
		System.out.println(dashWord);
		
	}
		
}
