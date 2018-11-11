import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

public class WarCardGameNotReady extends VectorQueue<String>{

	public static void main(String[] args) {
			
		      String PlayerName;
		      char Suit;
		      int CardValue;
		      String[] deck = new String[52];
		      int a = 25; //number of cards in player's deck
		      int b = 25; //number of cards in computer's deck
		      VectorQueue playerHand = new VectorQueue<String>(52);
		      VectorQueue computerHand = new VectorQueue<String>(52);
   	      PlayerName = getPlayerName();
		      deck = initializeDeck();
            ArrayList<String> deckArrayList = new ArrayList<String>();
               for(int i = 0; i < 52; i++){           
                  deckArrayList.add(deck[i]);
               }//end for loop
		      playerHand = getPlayerHand(deckArrayList);
		      computerHand = getComputerHand(deckArrayList);
		      
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
		            deck[i-2] = i + "H";
		         }//end hearts loop
		         
		         for (int j=2; j<15; j++){
		            deck[j+11] = j + "S";
		         }//end spades loop
		         
		         for (int k = 2; k < 15; k++){
		            deck[k + 24] = k + "D";
		         }//end diamonds loop
		         
		         for (int m = 2; m < 15; m++){
		            deck[m + 37] = m + "C";
		         }//end clubs loop
			         
		         /* The next lines are for testing purposes */
		         for (int x = 0; x < 52; x ++){
		           System.out.println (deck[x]);
		         }//end test for loop
		         System.out.println("---------------------------------------------------------------------------------------------");
		         System.out.println();
               //end of testing
		         return deck;
		      }//end initializeDeck method
		      
	            
            public static VectorQueue<String> getPlayerHand(ArrayList<String> deckArrayList){
               VectorQueue playerDeck = new VectorQueue<String>(52);
               Collections.shuffle(deckArrayList); 
               for(int index = 0; index < 26; index++){
                  playerDeck.enqueue(deckArrayList.get(index));
                  deckArrayList.remove(index);
               }//end for loop
               //Printout for testing purposes
		         System.out.println (playerDeck);
  	            System.out.println("---------------------------------------------------------------------------------------------");
		         System.out.println();
               //end of testing
		         return playerDeck;
            }//end getPlayerHand method
		              
            public static VectorQueue<String> getComputerHand(ArrayList<String> deckArrayList){
               VectorQueue computerDeck = new VectorQueue<String>(52);
               Collections.shuffle(deckArrayList); 
               for(int index = 25; index >= 0; index--){
                  computerDeck.enqueue(deckArrayList.get(index));
                  deckArrayList.remove(index);
               }//end for loop
                 //Printout for testing purposes
		            for(int i = 0; i < 52; i++){
                     System.out.println (computerDeck.get(i));
                  }//end for
		            System.out.println("---------------------------------------------------------------------------------------------");
		            System.out.println();
                  //end of testing
		            return computerDeck;
            }//end getComputerHand method
            
            

               
		      
		      
		      
} //end class