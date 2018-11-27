import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

public class WarCardGame4 extends VectorQueue<String>{

   public static void main(String[] args) throws InterruptedException {
   		
      String PlayerName;
      char Suit;
      int CardValue;
      String[] deck = new String[52];
      int a = 25; //number of cards in player's deck
      int b = 25; //number of cards in computer's deck
      VectorQueue playerHand = new VectorQueue(26);
      VectorQueue computerHand= new VectorQueue(26);
      PlayerName = getPlayerName();
      deck = initializeDeck();
      ArrayList<String> deckArrayList = new ArrayList<String>();
      for(int i = 0; i < 52; i++){           
         deckArrayList.add(deck[i]);
      }//end for loop
      playerHand = getPlayerHand(deckArrayList);
      computerHand = getComputerHand(deckArrayList);
      gameplay(playerHand, computerHand);      
   }//end main    
		      

   public static String getPlayerName(){
      Scanner keyboard = new Scanner(System.in);
      System.out.println ("Please enter your name.");
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
   public static void gameplay(VectorQueue<String> playerHand, VectorQueue<String> computerHand) throws InterruptedException{
      String[] warOne = new String[6];
      String[] warTwo = new String[6];
      String[] warThree = new String[6];
      String[] warFour = new String[6];
      String[] warFive = new String[6];
      String[][] warCollection = new String[5][6];
      Scanner keyboard = new Scanner(System.in);
      int row = 0;
      int column = 0;

      while(!playerHand.isEmpty() && !computerHand.isEmpty()){   
         //System.out.println("Press Enter to flip a card.");
         //String junk = keyboard.nextLine();
    	  String playerCard = (String) playerHand.dequeue();
          String playerSubstring = playerCard.substring(1);
          int playerCardValue = Integer.parseInt(playerSubstring);
          System.out.println("You have played the " + getValue(playerCard, playerCardValue));
          String computerCard = (String) computerHand.dequeue();
          String computerSubstring = computerCard.substring(1);
          int computerCardValue = Integer.parseInt(computerSubstring);
          System.out.println("The computer played the " + getValue(computerCard, computerCardValue));
          if(playerCardValue > computerCardValue){
             playerHand.enqueue(playerCard);
             playerHand.enqueue(computerCard);
             System.out.println("You win this round!");
             System.out.println();
             Thread.sleep(1500);
          }//end if
          else if(playerCardValue < computerCardValue){
             computerHand.enqueue(playerCard);
             computerHand.enqueue(computerCard);
             System.out.println("The computer wins this round.");
             System.out.println();
             Thread.sleep(1500);
          }//end else if
          
         else{
        	System.out.println(); 
            System.out.println("Go to WAR!!!");
            for(int i = 0; i < 3; i++){
               warCollection[row][column] = playerHand.dequeue();
               column++;
            }//end for
            for(int j = 0; j < 3; j++){
               warCollection[row][column] = computerHand.dequeue();               
               if(column == 5){
                  row++;
                  column = 0;
               }//end if
               else
                  column++;
               // TODO: make a normal gameplay method
            }//end for
            playerCard = (String) playerHand.dequeue();
            playerSubstring = playerCard.substring(1);
            playerCardValue = Integer.parseInt(playerSubstring);
            System.out.println("You have played the " + playerCard);
            computerCard = (String) computerHand.dequeue();
            computerSubstring = computerCard.substring(1);
            computerCardValue = Integer.parseInt(computerSubstring);
            System.out.println("The computer played the " + computerCard);
            if(playerCardValue > computerCardValue){
               playerHand.enqueue(playerCard);
               playerHand.enqueue(computerCard);
               System.out.println("You win the war!");
               System.out.println();
            }//end if
            else if(playerCardValue < computerCardValue){
               computerHand.enqueue(playerCard);
               computerHand.enqueue(computerCard);
               System.out.println("The computer wins the war.");
               System.out.println();
            }//end else if
         }//end else
      }//end while   
   
   }//end gameplay method
      
      
      
      
      
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
