import java.util.Scanner;
import java.util.Random;
import java.util.Collection;
import java.util.ArrayList;
import java.util.*;

public class WarCardGame6 extends VectorQueue<String>{
    public static String PlayerName;
    public static String ComputerName = "Cameron";
    public static String[] tempPlayerCard = new String[5];
    public static String[] tempComputerCard = new String[5];
   public static void main(String[] args) throws InterruptedException {
   		

      char Suit;
      int CardValue;
      int warCount = 0;
      String[] deck = new String[52];
      int a = 25; //number of cards in player's deck
      int b = 25; //number of cards in computer's deck
      VectorQueue playerHand = new VectorQueue(26);
      VectorQueue computerHand= new VectorQueue(26);
      String[][] warCollection = new String[5][6];
      PlayerName = getPlayerName();
      deck = initializeDeck();
      ArrayList<String> deckArrayList = new ArrayList<String>();
      for(int i = 0; i < 52; i++){           
         deckArrayList.add(deck[i]);
      }//end for loop
      playerHand = getPlayerHand(deckArrayList);
      computerHand = getComputerHand(deckArrayList);
      gameplay(playerHand, computerHand, warCollection, warCount, tempPlayerCard, tempComputerCard);      
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
   public static void gameplay(VectorQueue<String> playerHand, VectorQueue<String> computerHand, String[][] warCollection, int warCount, String[] tempPlayerCard, String[] tempComputerCard) throws InterruptedException{

      //Scanner keyboard = new Scanner(System.in);

      while((playerHand.size() != 0) && (computerHand.size() != 0)){   
         //System.out.println("Press Enter to flip a card.");
         //String junk = keyboard.nextLine();

    	  String playerCard = (String) playerHand.dequeue();
          String playerSubstring = playerCard.substring(1);
          int playerCardValue = Integer.parseInt(playerSubstring);
          System.out.println(PlayerName + ", you have played the " + getValue(playerCard, playerCardValue));
          String computerCard = (String) computerHand.dequeue();
          String computerSubstring = computerCard.substring(1);
          int computerCardValue = Integer.parseInt(computerSubstring);
          System.out.println(ComputerName + " played the " + getValue(computerCard, computerCardValue));

          if(playerCardValue > computerCardValue){
             playerHand.enqueue(playerCard);
             playerHand.enqueue(computerCard);
             
             System.out.println(PlayerName + ", you win this round!");
             System.out.println();
             Thread.sleep(1500);
          }//end if
          else if(playerCardValue < computerCardValue){
             computerHand.enqueue(playerCard);
             computerHand.enqueue(computerCard);
             System.out.println(ComputerName + " wins this round.");
             System.out.println();
             Thread.sleep(1500);
          }//end else if
          
         else{
        	 warCount = 0;
        	 goToWar(playerHand, computerHand, playerCard, computerCard, warCollection, warCount, tempPlayerCard, tempComputerCard);
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
      
   public static void goToWar(VectorQueue<String> playerHand, VectorQueue<String> computerHand, String playerCard, String computerCard, String warCollection[][], int warCount, String[] tempPlayerCard, String[] tempComputerCard){
       int column = 0;
       String playerSubstring;
       int playerCardValue;
       String computerSubstring;
       int computerCardValue;
       tempPlayerCard[warCount] = playerCard;
       tempComputerCard[warCount] = computerCard;
       System.out.println(); 
       System.out.println("Go to WAR!!!");
           //In case of multiple wars
           if(warCollection[warCount][column] != null){
              warCount++;
           }//end if
           else{
              //Dequeues the face-down cards for the war
              for(int i = 0; i < 3; i++){
           	    if(playerHand.size() != 0){
           		   warCollection[warCount][i] = playerHand.dequeue();
           	    }//end if
           	    else{
           	    	gameWinning(WarCardGame6.ComputerName);
           	    	return;
           	    }//end else
              }//end for
              for(int j = 3; j < 6; j++){
           	   if(computerHand.size() != 0){
           		   warCollection[warCount][j] = computerHand.dequeue();
           	   }//end if
           	   else{
           		   gameWinning(WarCardGame6.PlayerName);
           		   return;
           	   }//end else
              }//end for
           }//end else
           if((playerHand.size() != 0) && (computerHand.size() != 0)){
           	//Dequeues the face-up war cards
           	playerCard = (String) playerHand.dequeue();
           	playerSubstring = playerCard.substring(1);
           	playerCardValue = Integer.parseInt(playerSubstring);
           	System.out.println(PlayerName + ", you have played the " + getValue(playerCard, playerCardValue));
           	computerCard = (String) computerHand.dequeue();
           	computerSubstring = computerCard.substring(1);
           	computerCardValue = Integer.parseInt(computerSubstring);
           	System.out.println(ComputerName + " played the " + getValue(computerCard, computerCardValue));
           	//Compare the face-up war cards and enqueue won cards
           	if(playerCardValue > computerCardValue){
           		
           		playerHand.enqueue(playerCard);
           		playerHand.enqueue(computerCard);
           		for(int k = 0; k <= warCount;k++){
           			playerHand.enqueue(tempPlayerCard[k]);
           			playerHand.enqueue(tempComputerCard[k]);
           		}//end for
           		System.out.println(PlayerName + ", you win the war!");
           		System.out.println();
           		for(int i = 0;i <= warCount;i++){
           			for(int j = 0;j < 6;j++){
           				playerHand.enqueue(warCollection[i][j]);
           				warCollection[i][j] = null;
           			}//end inner for
           		}//end outer for
           	}//end if
           	else if(playerCardValue < computerCardValue){
           		computerHand.enqueue(playerCard);
           		computerHand.enqueue(computerCard);
           		for(int k = 0; k <= warCount;k++){
           			playerHand.enqueue(tempPlayerCard[k]);
           			playerHand.enqueue(tempComputerCard[k]);
           		}//end for
           		System.out.println(ComputerName + " wins the war.");
           		System.out.println();
           		for(int i = 0;i <= warCount;i++){
           			for(int j = 0;j < 6;j++){
           				playerHand.enqueue(warCollection[i][j]);
           				warCollection[i][j] = null;
           			}//end inner for
           		}//end outer for
           	}//end else if
           	//To handle multiple wars
           	else{
           		goToWar(playerHand, computerHand, playerCard, computerCard, warCollection, warCount, tempPlayerCard, tempComputerCard);           
           	}//end else
           }//end if
           else if(playerHand.isEmpty()){
           	gameWinning(WarCardGame6.ComputerName);
           	return;
           }//end else if
           else{
           	gameWinning(WarCardGame6.PlayerName);
           	return;
           }//end else
       }//end goToWar method 
   
//**************************************END OF WAR****************************************************************

       public static void gameWinning(String name){
       	System.out.println(name + " you have won the game!");
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