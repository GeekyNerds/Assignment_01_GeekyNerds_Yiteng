import java.util.ArrayList;
import java.util.Scanner;

public class Card {
	
	public static void main(String[] args) {
		Game game = new Game(5);
		game.playAGame();
		
	}
	
	
	private int num;
	private int suit;
	public Card(int num, int suit) {
		this.num = num;
		this.suit = suit;
	}
	
	int getNum() {
		return this.num;
	}
	int getSuit() {
		return this.suit;
	}
	
	void display() {
		String cardNum="";
		String cardSuit="";
		
		switch(this.suit) {
		case 0: cardSuit = "Clubs";break;
		case 1: cardSuit = "Diamonds";break;
		case 2: cardSuit = "Hearts";break;
		case 3: cardSuit = "Spades";break;
		}
		
		switch(this.num) {
		case 11: cardNum = "Jack";break;
		case 12: cardNum = "Queen";break;
		case 13: cardNum = "King";break;
		case 14: cardNum = "Ace";break;	
		default: cardNum = String.valueOf(this.num);
		}
		
		System.out.println(cardNum+" of "+cardSuit);
	}	
	
}

class GroupOfCards {
	
	protected ArrayList<Card> cards =new ArrayList<Card>();
	private int currentSize;
	
	public GroupOfCards(int number) {
		this.currentSize = number;
	}

	int getCurrentSize() {
		return this.cards.size();
	}
	
	Card getCard(int i) {
		return this.cards.get(i);
	}
	
	void addCard(Card card) {
		cards.add(card);
		this.currentSize++;
		
	}
	
	Card removeCard(int index){
		Card cardToRemove = this.cards.get(index);
		this.cards.remove(index);
		this.currentSize--;
		return cardToRemove;

	}
	
	void display() {
		for(int i=0;i<this.cards.size();i++) {
			this.cards.get(i).display();
		}
		
	}
	
	
}


class Deck extends GroupOfCards{
	public static final int TOTAL_CARDS = 52;
	
	public Deck() {
		super(TOTAL_CARDS);
		for(int i=1;i<=TOTAL_CARDS/4;i++) {
			this.cards.add(new Card(i, 0));
			this.cards.add(new Card(i, 1));
			this.cards.add(new Card(i, 2));
			this.cards.add(new Card(i, 3));
		}
	}
	
	void shuffle() {
		for(int unshuffled=this.getCurrentSize(); unshuffled>0; unshuffled--) {
			int index = (int) (Math.random()*this.getCurrentSize());
			Card cardShuffle = this.cards.get(index);
			this.cards.remove(index);
			this.cards.add(cardShuffle);
		}
		
	}
	
	Card dealCard() {
		Card cardToDeal = this.cards.get(0);
		this.cards.remove(0);
		return cardToDeal;
	}
	
}


class Trick extends GroupOfCards{
	private int winner;
	private Card winningCard = new Card(0, 0);
	private boolean hearts = false;
	private boolean queen = false;
	
	public Trick(int players) {
		super(2*players-1);
	}
	
	int getWinner() {
		return this.winner;
	}
	
	Card getWinningCard(){
		return this.winningCard;
	}
	
	boolean getHearts() {
		return this.hearts;
	}
	
	boolean getQueen() {
		return this.queen;
	}
	
	
	void update(int playerNum, Card card){
		if(isWinner(card)) {
			this.winningCard=card;
			this.winner =playerNum;
		}
		if(card.getSuit()==2) {
			this.hearts = true;
		}
		if(card.getSuit()==3 && card.getNum()==12) {
			this.queen = true;
		}
	}
	
	
	// isWinner() should return true unless the previous winning card is not null 
	// and the current card is not in the suit being played or its number is 
	// less than the winning card’s number.
	private boolean isWinner(Card card) {
		
		if((this.winningCard!=null && card.getSuit()!=this.getCard(0).getSuit()) || card.getNum()< this.winningCard.getNum()) {
			return false;
		}
		 else {
			return true;
		}
	}
	
	
}


class Hand extends GroupOfCards{
	final int NUM;
	private int shortest;
	public Hand(int playerNum, int numberOfCards) {
		super(numberOfCards);
		this.NUM = playerNum;
	
	}
	
	void sort() {
		for(int unsorted=this.cards.size()-1; unsorted>0; unsorted--) {
			for(int j=unsorted-1; j>=0;j--) {
				if((13*this.cards.get(j).getSuit()+this.cards.get(j).getNum())<(13*this.cards.get(unsorted).getSuit()+this.cards.get(unsorted).getNum())){
					Card temp = this.cards.get(j);
					this.cards.set(j, this.cards.get(unsorted));
					this.cards.set(unsorted,temp);
				}
			}
		}
		
//		for(int i=0;i<this.cards.size()-1;i++) {
//			for(int j=i+1;j<this.cards.size();j++) {
//				if((13*this.cards.get(j).getSuit()+this.cards.get(j).getNum())>(13*this.cards.get(i).getSuit()+this.cards.get(i).getNum())){
//					Card temp = this.cards.get(j);
//					this.cards.set(j, this.cards.get(i));
//					this.cards.set(i,temp);
//				
//				}	
//			}
//		}
		
	}
	
	void setShortest() {
		this.shortest = 0;
		int numberOfClubs = findCount(0);
		int numberOfDiamonds = findCount(1);
		int numberOfHearts = findCount(2);
		int numberOfSpades = findCount(3);

		if(numberOfDiamonds<=numberOfClubs) {
			this.shortest = 1;	
		}
		
		if(numberOfSpades<=Math.min(numberOfDiamonds,numberOfClubs) && find(12,3)==-1 && find(13,3)==-1 && find(14,3)==-1) {
			this.shortest = 3;
		}
		
		
	}
	

	
	int getShortest() {
		return this.shortest;
	}
	
	Card playACard(Game game, Trick trick) {
		int index =-1;
		
		if(trick.cards.size()==0) {
			if(findCount(getShortest())==0) {
				index=findLowestOfAll();
			}
			else {
				index = findHighest(getShortest());
			}	
		}
		
		
		else if ((trick.cards.size() == game.PLAYERS - 1)
				&& !trick.getHearts() && !trick.getQueen() 
				&& (index = findLastHigh(trick.getCard(0).getSuit())) >= 0);
		
		else if ((index = findHighestBelow(trick.getWinningCard())) >= 0);
		else if ((index = findMiddleHigh(game, trick.getCard(0).getSuit())) >= 0);
		else if ((index = find(12, 3)) >= 0); // queen of Spades
		else if ((index = find(14, 3)) >= 0); // Ace of Spades
		else if ((index = find(13, 3)) >= 0); // King of Spades
		else if ((index = findHighest(2)) >= 0); // heart
		
		
		else {
			index = findHighest();
		}
			
		trick.addCard(this.cards.get(index));
		trick.update(this.NUM, this.cards.get(index));
		game.updateHeartsAndQueen(this.cards.get(index));
		return removeCard(index);
		//game.computePoints(this.NUM);
		
	}
	
	
	
	int findLowest(int suit) {
		//int lowest = -1;
		int index = -1;
		int low = 15;
		for(int i=0;i<this.cards.size();i++) {
			if(this.cards.get(i).getSuit()==suit) {
				if(this.cards.get(i).getNum()<low) {
					low=this.cards.get(i).getNum();
					index=i;
				}	
			}
		}
		return index;
	}
	
	int findLowestOfAll() {
		int low = 15;
		int index = -1;
		for(int i=0; i<this.cards.size();i++) {
			if(this.cards.get(i).getNum()<low) {
				low=this.cards.get(i).getNum();
				index=i;
			}
		}
		return index;
	}
	
	int findCount(int suit) {
		int count = 0;
		for(int i=0;i<this.cards.size();i++) {
			if(this.cards.get(i).getSuit()==suit) {
				count++;
			}
		}
		return count;
			
	}
	
	int findHighest(int suit) {
		int index = -1;
		int high = 0;
		for(int i=0;i<this.cards.size();i++) {
			if(this.cards.get(i).getSuit()==suit) {
				if(this.cards.get(i).getNum()>high) {
					high=this.cards.get(i).getNum();
					index = i;
				}	
			}
		}
		return index;
	}
	
	
	
	int findHighestBelow(Card winningCard) {
		//findHighest(suit);
		int index = -1;
		if(findCount(winningCard.getSuit())>0) {
			for(int i=0;i<this.cards.size();i++) {
				if(this.cards.get(i).getSuit()==winningCard.getSuit() && this.cards.get(i).getNum()<winningCard.getNum()) {
					index = i;
				}
			}
		}

		return index;
	}
	
	int findMiddleHigh(Game game, int suit) {
		int index = -1;
		if(suit==3 && game.getQueenOfSpades()==false && findCount(3)>0) {
			int highh = 0;
			for(int i=0;i<this.cards.size();i++) {
				if(this.cards.get(i).getSuit()==3) {
					if(this.cards.get(i).getNum()>highh && this.cards.get(i).getNum()<=11) {
						highh=this.cards.get(i).getNum();
						index = i;
					}	
				}
			}
			
		}
		else {
			index = findHighest(suit);
		}
		return index;
		
	}
	
	
	int findLowest(Game game) {
		
		
		int index =-1;
		int low = 15;
		if(game.getHearts()==false) {
			if(findCount(0)==0 && findCount(1)==0 && findCount(3)==0) {
				index = -1;
			}
			else {
				for(int i=0;i<this.cards.size(); i++) {
					if(this.cards.get(i).getSuit()!=2 && this.cards.get(i).getNum()<low) {
						low = this.cards.get(i).getNum();
						index = i;
					}
				}
			}
		}
		else {
			index = findLowestOfAll();
		}
		
		return index;
	}
	
	int findLastHigh(int suit) {
		int index= findHighest(suit);
		if(index >=0 && this.cards.get(index).getSuit()==3 && this.cards.get(index).getNum()==12 && findCount(3)>1) {
			int highh = 0;
			for(int i=0;i<this.cards.size();i++) {
				if(this.cards.get(i).getSuit()==suit) {
					if(this.cards.get(i).getNum()>highh && this.cards.get(i).getNum()<12 ) {
						highh=this.cards.get(i).getNum();
						index = i;
					}	
				}
			}
		}
		
		return index;
	}
	
	
	
	
	
	
	
	//For the find method, return the index of the card having suit and number equal to the parameter values. If you can’t find that card, return -1.
	int find(int num, int suit) {
		int index = -1;
		for(int i=0; i<this.cards.size(); i++) {
			if(this.cards.get(i).getNum()==num && this.cards.get(i).getSuit()==suit) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	
	int findHighest() {
		int index = -1;
		int high = 0;
		for(int i=0;i<this.cards.size();i++) {
			if(this.cards.get(i).getNum()>high) {
				high=this.cards.get(i).getNum();
				index = i;
			}	
		}
		
		return index;
	}
	
	
	
	
	
}


class Game {
	public final int PLAYERS;
	private Deck deck = new Deck();
	private ArrayList<Hand> players = new ArrayList<Hand>();
	private ArrayList<Trick> tricks = new ArrayList<Trick>();
	private int numberOfTricks =0;
	private boolean hearts = false;
	private boolean queenOfSpades = false;
	
	
	//This constructor should instantiate a Hand array with constructor parameter 
	//equal to the number of players. It should instantiate individual Hand objects 
	//for each player, with player identification number and maximum number of cards 
	//in a player’s hand as constructor arguments
	//It should also instantiate a Trick array with total number of tricks 
	//as the constructor argument, but it should not populate this array 
	//with any individual tricks.
	

	
	public Game(int numberOfPlayers) {
		this.PLAYERS = numberOfPlayers;
		for(int playerNum = 0; playerNum < this.PLAYERS;playerNum++) {
			players.add(new Hand( playerNum, Deck.TOTAL_CARDS / this.PLAYERS));	
		}
		
	}
	
	
	int getNumberOfTricks() {
		this.numberOfTricks = this.tricks.size();
		return this.numberOfTricks;
	}
	
	boolean getHearts() {
		return this.hearts;
	}
	
	boolean getQueenOfSpades() {
		return this.queenOfSpades;
	}
	
	void playAGame() {
		boolean isPlay = true;
		
		while(isPlay) {
	
		this.deck.shuffle();
		int cardsLeft = Deck.TOTAL_CARDS % this.PLAYERS;
		int playerNum = 0 ;
		int lowestClubNum = 15;
		
		for(int i = 0; i < Deck.TOTAL_CARDS / this.PLAYERS; i++) {
			for(int playerNo = 0; playerNo < this.PLAYERS; playerNo++) {
				this.players.get(playerNo).addCard(this.deck.dealCard());
			}
		}
		
		// Loop through all players and for each player call sort the setShortest to sort the hand and find that player’s best void opportunity. 
		// For that player, print the value of shortest and display that player’s hand 
		for(int i=0;i<this.players.size();i++) {
			
			
			this.players.get(i).sort();
			this.players.get(i).setShortest();
			System.out.printf("\nPlayer %d: shortest = %d\n",i,this.players.get(i).getShortest());
			this.players.get(i).display();
			
			
			if(this.players.get(i).findCount(0)!=0) {
				int lowestClubOfThisPlayer = this.players.get(i).getCard(this.players.get(i).findLowest(0)).getNum();
				if(lowestClubOfThisPlayer< lowestClubNum) {
					lowestClubNum = lowestClubOfThisPlayer;
					playerNum=i;
				}
			}
			
		}
	System.out.println("\nLowest Club Owner PlayerNumber: "+playerNum+"\n");

		
		
		//Then loop through the total number of tricks. In each iteration, instantiate a new Trick, 
		//add it to the tricks array, and increment numberOfTricks. 
		for(int i=0; i < Deck.TOTAL_CARDS / this.PLAYERS; i++) {
			this.tricks.add(new Trick(this.PLAYERS));
			this.numberOfTricks++;
			int index;
			Card card;
			
			if(i==0) {
				index = this.players.get(playerNum).findLowest(0);
				card = this.players.get(playerNum).getCard(index);
				this.tricks.get(i).addCard(card);
				this.tricks.get(i).update(playerNum, card);
				this.updateHeartsAndQueen(card);
				this.players.get(playerNum).removeCard(index);
			//System.out.println(this.tricks.get(i).getWinner());
				System.out.printf("player %d        ",playerNum);
				card.display();
				
				
			}
			else {
			    playerNum =this.tricks.get(i-1).getWinner();
			//System.out.println(this.tricks.get(i-1).getWinner());
				card = this.players.get(playerNum).playACard(this, this.tricks.get(i));
				System.out.printf("player %d        ",playerNum);
				card.display();
				}
					
			//int n = (playerNum+1) % this.PLAYERS;
			for(int x=(playerNum+1) % this.PLAYERS; x<(playerNum+1) % this.PLAYERS+4; x++) {
				card = this.players.get(x % this.PLAYERS).playACard(this, this.tricks.get(i));
				System.out.printf("player %d        ", x % this.PLAYERS);
				card.display();		
			}
			
			if(i==0) {
				for(int j=0;j<cardsLeft; j++) {
					card = this.deck.dealCard();
					this.tricks.get(i).addCard(card);
				this.tricks.get(i).update(this.tricks.get(i).getWinner(), card);
					this.updateHeartsAndQueen(card);
					System.out.print("Undelt Card     ");
					card.display();
				}
			}
			System.out.println();
					

		}
	
//System.out.println("left deck cards001: "+this.deck.cards.size());
		System.out.println("Final Score: ");
		for(int i=0; i<this.PLAYERS;i++) {
			System.out.printf("player %d score = %d\n",i,computePoints(i));

		}
		
		// put all the 52 cards back to the Deck to get ready for another game
		for(int i=0; i<this.tricks.size();i++) {
			for(int j=0; j<this.tricks.get(i).cards.size();j++) {
				this.deck.addCard(this.tricks.get(i).getCard(j));
			}
		}
		
		
//System.out.println(this.tricks.size());
//System.out.println(this.players.size());
//System.out.println("left deck cards 002: "+this.deck.cards.size());
		
		
		System.out.print("\nPlay another game (y/n)?: ");

		Scanner scanner = new Scanner(System.in);
		String yesOrNo = scanner.nextLine();
		if(yesOrNo.equalsIgnoreCase("y")) {
			this.tricks.clear();
			isPlay = true;
		
		}
		else if(yesOrNo.equalsIgnoreCase("n")) {
			isPlay = false; //break;
			
		}
		else {
			System.out.println("Please input y or n to continue or exit the game!");
		}
		
		}
		
		
	}
	
	void updateHeartsAndQueen(Card card) {
		if(card.getSuit()==2 && this.hearts==false) {
			System.out.println("Hearts is now broken");
			this.hearts=true;
		}
		if(card.getSuit()==3 && card.getNum()==12) {
			this.queenOfSpades = true;
		}
		
	}
	
	private int computePoints(int playerNum) {
		//return -1;
		int points = 0;
		for(int i=0; i<this.tricks.size();i++) {
			if(this.tricks.get(i).getWinner()==playerNum) {
				if(this.tricks.get(i).getHearts()==true || this.tricks.get(i).getQueen()==true) {
					for(int j =0;j<this.tricks.get(i).cards.size();j++) {
						if(this.tricks.get(i).cards.get(j).getSuit()==2) {
							points++;
						}
						if(this.tricks.get(i).cards.get(j).getSuit()==3 && this.tricks.get(i).cards.get(j).getNum()==12) {
							points+=13;
						}
					}	
				}
			
			}
		}
		return points;
	}
	
	
	
}






