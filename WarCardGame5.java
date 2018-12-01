import java.util.Scanner;
import java.util.Random;
import java.util.Collection;
import java.util.ArrayList;
import java.util.*;

public class WarCardGame5 extends VectorQueue<String>{
	public static String PlayerName;
	public static String ComputerName;
	public static void main(String[] args) throws InterruptedException {


		char Suit;
		int CardValue;
		String[] deck = new String[52];
		int a = 25; //number of cards in player's deck
		int b = 25; //number of cards in computer's deck
		VectorQueue playerHand = new VectorQueue(26);
		VectorQueue computerHand= new VectorQueue(26);
		String[][] warCollection = new String[5][8];
		int warCount = 0;
		PlayerName = getPlayerName();
		ComputerName = getComputerName();
		deck = initializeDeck();
		ArrayList<String> deckArrayList = new ArrayList<String>();
		for(int i = 0; i < 52; i++){           
			deckArrayList.add(deck[i]);
		}//end for loop
		playerHand = getPlayerHand(deckArrayList);
		computerHand = getComputerHand(deckArrayList);
		gameplay(playerHand, computerHand, warCollection, warCount);      
	}//end main    


	public static String getPlayerName(){
		Scanner keyboard = new Scanner(System.in);
		System.out.println ("Please enter player one's name.");
		String name = keyboard.nextLine();
		return name;
	}//end getPlayerName method

	public static String getComputerName(){
		Scanner keyboard = new Scanner(System.in);
		System.out.println ("Please enter player two's name.");
		String name = keyboard.nextLine();
		return name;
	}//end getPlayerName method
	
	public static String[] initializeDeck(){  //method to initialize the deck
		String[] deck = new String[52];
		for (int i=2; i<15; i++){
			deck[i-2] = "H" + i;
		}//end hearts loop

		for (int j=2; j<15; j++){
			deck[j+11] = "S" + j;
		}//end spades loop

		for (int k = 2; k < 15; k++){
			deck[k + 24] = "D" + k;
		}//end diamonds loop

		for (int m = 2; m < 15; m++){
			deck[m + 37] = "C" + m;
		}//end clubs loop
		return deck;
	}//end initializeDeck method


	public static VectorQueue getPlayerHand(ArrayList<String> deckArrayList){
		String[] playerDeck = new String[26];
		Collections.shuffle(deckArrayList); 
		for(int index = 0; index < 26; index++){
			playerDeck[index] = deckArrayList.get(index);
			deckArrayList.remove(index);
		}//end for loop
		VectorQueue playerDeckVQ = new VectorQueue(52);
		for(int i = 0; i < playerDeck.length; i++){
			playerDeckVQ.enqueue(playerDeck[i]);
		}//end for
		return playerDeckVQ;
	}//end getPlayerHand method

	public static VectorQueue getComputerHand(ArrayList<String> deckArrayList){
		String[] computerDeck = new String[26];
		Collections.shuffle(deckArrayList); 
		for(int index = 25; index >= 0; index--){
			computerDeck[index] = deckArrayList.get(index);
			deckArrayList.remove(index);
		}//end for loop
		VectorQueue computerDeckVQ = new VectorQueue(52);
		for(int i = 0; i < computerDeck.length; i++){
			computerDeckVQ.enqueue(computerDeck[i]);
		}//end for
		return computerDeckVQ;
	}//end getComputerHand method

	////////////////////////////PLAYING PORTION//////////////////////////////////////////////////////
	public static void gameplay(VectorQueue<String> playerHand, VectorQueue<String> computerHand, String[][] warCollection, int warCount) throws InterruptedException{

		//Scanner keyboard = new Scanner(System.in);



		//checks that there are cards in both people's hands 
		while((playerHand.size() != 0) && (computerHand.size() != 0)){   
			//System.out.println("Press Enter to flip a card.");
			//String junk = keyboard.nextLine();

			// sets a card to player card. currently displayed as LetterNumber (Example: D9)
			String playerCard = (String) playerHand.dequeue(); 

			// Card number alone
			String playerSubstring = playerCard.substring(1);


			int playerCardValue = Integer.parseInt(playerSubstring);
			System.out.println(PlayerName + " played the " + getValue(playerCard, playerCardValue));
			String computerCard = (String) computerHand.dequeue();
			String computerSubstring = computerCard.substring(1);
			int computerCardValue = Integer.parseInt(computerSubstring);
			System.out.println(ComputerName + " played the " + getValue(computerCard, computerCardValue));

			if(playerCardValue > computerCardValue){
				playerHand.enqueue(playerCard);
				playerHand.enqueue(computerCard);

				System.out.println(PlayerName + " wins this round!");
				System.out.println();
				//Thread.sleep(500);
			}//end if
			else if(playerCardValue < computerCardValue){
				computerHand.enqueue(playerCard);
				computerHand.enqueue(computerCard);
				System.out.println(ComputerName + " wins this round.");
				System.out.println();
				//Thread.sleep(500);
			}//end else if

			else{
				goToWar(playerHand, computerHand, playerCard, computerCard, warCollection, warCount);
				if((playerHand.size() == 0) || (computerHand.size() == 0)){
					return;
				}//end if
			}//end else
			System.out.println("Player hand: " + playerHand.size());
			System.out.println("Computer hand: " + computerHand.size());
			System.out.println(playerHand.size() + computerHand.size());
		}//end while  
		if(playerHand.size() == 0){
			gameWinning(ComputerName);
			return;
		}//end if
		else{
			gameWinning(PlayerName);
			return;
		}//end else
	}//end gameplay method

	//*******************************************WAR************************************************************************

	public static void goToWar(VectorQueue<String> playerHand, VectorQueue<String> computerHand, String playerCard, String computerCard, String warCollection[][], int warCount){









		//Variable Declarations---------------------------------------------------------------------

		int column = 0; //
		String playerSubstring; // value of player card as string
		int playerCardValue; // value of player card as int
		String computerSubstring; // value of comp card as string
		int computerCardValue; // value of comp card as int
		//String tempPlayerCard = playerCard; //holding place for war card
		//String tempComputerCard = computerCard; // holding place for war card

		System.out.println(); 
		System.out.println("Go to WAR!!!----------------------------------------------------");

		//End variable declarations-----------------------------------------------------------------


		if(warCollection[warCount][column] != null)//if there is already been a war there will be values stored in war collection
		{
			warCount++;
		}


		//Adding cards to war collection (if no cards calls winner)--------------------------------------------
	
		//Add player card to warCollection
		warCollection[warCount][0] = playerCard;
		
		//add comp card to warCollection
		warCollection[warCount][1] = computerCard;
		
		//Dequeues the face-down cards for the war
	
		
		

		//Setting player's down cards to first 3 spots in war collection array
		for(int i = 2; i < 5; i++){

			//makes sure that the user has cards to play
			if(playerHand.size() != 0){

				//puts each card in place 
				warCollection[warCount][i] = playerHand.dequeue();
			}//end if

			//if user doesn't have enough cards to play then call method that says comp won
			else{
				gameWinning(WarCardGame5.ComputerName);
				return;
			}//end else
		}//end for


		// setting comp's cards to next 3 spots in war collection array
		for(int j = 5; j < 8; j++){

			// makes sure comp has cards to play
			if(computerHand.size() != 0){

				// dequeues cards one at a time from comp hand and places in spot in war Collection 
				warCollection[warCount][j] = computerHand.dequeue();
			}//end if


			// if comp doesn't have enough cards calls method to say player won
			else{
				gameWinning(WarCardGame5.PlayerName);
				return;
			}//end else
		}//end for
		//end else


		//End of putting down cards in war collection------------------------------------------------










		//----------------who won the war----------------------------------------------------------

		// so long as each player has cards
		if((playerHand.size() != 0) && (computerHand.size() != 0)){


			//Dequeues the face-up war cards

			//new player flip card to see who won war suit and number
			playerCard = (String) playerHand.dequeue();

			// player flip card only number
			playerSubstring = playerCard.substring(1);

			//convert substring to int 
			playerCardValue = Integer.parseInt(playerSubstring);


			System.out.println(PlayerName + " played the " + getValue(playerCard, playerCardValue));

			//new comp flip card to see who won war (suit & num)
			computerCard = (String) computerHand.dequeue();

			// flip card. only num as string
			computerSubstring = computerCard.substring(1);

			// string to int
			computerCardValue = Integer.parseInt(computerSubstring);
			System.out.println(ComputerName + " played the " + getValue(computerCard, computerCardValue));

			/*
			System.out.println(" Players card is a " +playerCardValue);
			System.out.println(computerCardValue);
			*/
			//Compare the face-up war cards and enqueue won cards

			// player wins
			if(playerCardValue > computerCardValue){

				//player new card
				playerHand.enqueue(playerCard);
				//comp new card
				playerHand.enqueue(computerCard);
				
				System.out.println(PlayerName + ", you win the war!");
				System.out.println();

				// add facedown cards
				for(int i = 0;i < warCount + 1;i++){
					for(int j = 0;j < 8;j++){
						playerHand.enqueue(warCollection[i][j]);
						warCollection[i][j] = null;
					}//end inner for
				}//end outer for
			}//end if

			// computer wins
			else if(playerCardValue < computerCardValue){
				// new player card
				computerHand.enqueue(playerCard);
				// new comp card
				computerHand.enqueue(computerCard);
				
				System.out.println(ComputerName + " wins the war.");
				System.out.println();

				// face down cards
				for(int i = 0;i < warCount + 1;i++){
					for(int j = 0;j < 8;j++){
						computerHand.enqueue(warCollection[i][j]);
						warCollection[i][j] = null;
					}//end inner for
				}//end outer for
			}//end else if

			//----Add winner of war------------------------------------------------------------------------------



			//To handle multiple wars--------------------------------------------------------------------
			else{
			//In case of multiple wars
			

				goToWar(playerHand, computerHand, playerCard, computerCard, warCollection, warCount);           
			}//end else
		}//end if
		else if(playerHand.size() == 0){
			gameWinning(WarCardGame5.ComputerName);
			return;
		}//end else if
		else{
			gameWinning(WarCardGame5.PlayerName);
			return;
		}//end else
	}//end goToWar method 

	
	
	
	
	
	
	public static void gameWinning(String name){
		System.out.println(name + " won the game!");
	}//end gameWinning method


	public static String getValue(String cardName, int cardValue){
		String cardTitle;
		switch(cardName.charAt(0)){
		case 'H':
			cardTitle = "Hearts";
			break;
		case 'D':
			cardTitle = "Diamonds";
			break;
		case 'C':
			cardTitle = "Clubs";
			break;
		default:
			cardTitle = "Spades";
			break;
		}//end switch
		switch(cardValue){
		case 2:
			cardTitle = cardValue + " of " + cardTitle;
			break;
		case 3:
			cardTitle = cardValue + " of " + cardTitle;
			break;
		case 4:
			cardTitle = cardValue + " of " + cardTitle;
			break;
		case 5:
			cardTitle = cardValue + " of " + cardTitle;
			break;
		case 6:
			cardTitle = cardValue + " of " + cardTitle;
			break;
		case 7:
			cardTitle = cardValue + " of " + cardTitle;
			break;
		case 8:
			cardTitle = cardValue + " of " + cardTitle;
			break;
		case 9:
			cardTitle = cardValue + " of " + cardTitle;
			break;
		case 10:
			cardTitle = cardValue + " of " + cardTitle;
			break;
		case 11:
			cardTitle = "Jack of " + cardTitle;
			break;
		case 12:
			cardTitle = "Queen of " + cardTitle;
			break;
		case 13:
			cardTitle = "King of " + cardTitle;
			break;
		default:
			cardTitle = "Ace of " + cardTitle;
		}//end switch
		return cardTitle;
	}//end getValue method 

} //end class

