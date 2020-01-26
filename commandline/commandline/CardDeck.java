
package commandline;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* This class will shuffle the card deck. This class will also check for a draw by comparing the 5 cards attributes, and will further place the
 *  cards that were satisfying the draw condition within the common pile. 
 */
public class CardDeck {

	// deck size is always 40
	private int DECK_SIZE = 40;
	// initial deck, empty
	private ArrayList<Cards> deck;

	public CardDeck(ArrayList<Cards> deck) {
		this.deck = deck;
	}

	public void initializeDeck() {
		File file = new File("resources/StarCitizenDeck.txt");

		BufferedReader bufferedReader = null;

		try {
			java.io.FileReader fileReader = new java.io.FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

			String line;
			// this prints out the value of the cards
			bufferedReader.readLine();
			// THING IS PRINTED OUT
			while ((line = bufferedReader.readLine()) != null) {
				String[] splited = line.split(" ");
				deck.add(new Cards(splited[0], Integer.parseInt(splited[1]), Integer.parseInt(splited[2]), Integer.parseInt(splited[3]),  Integer.parseInt(splited[4]), Integer.parseInt(splited[5])));
				
				for(Cards card : deck) System.out.println(card.toString());
				
//				System.out.println(arr[0].getClass().getName());
//				System.out.println(deck.get(0).toString());
			}

		} catch (FileNotFoundException exception) {
			System.out.println("File not found: " + file.toString());
		} catch (IOException exception) {
			System.out.println("Unable to read file: " + file.toString());
		} finally {

			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.out.println("Unable to close file: " + file.toString());
			} catch (NullPointerException exception) {
			}
		}
	}
// 				File text = new File("StarCitizenDeck.txt");
// 				//Scanner t = new Scanner(text);
// 				Scanner a = new Scanner(text);
// 				System.out.println(a.nextLine());
// 				a.close();
//				try {
//					Scanner t = new Scanner(text);
//					while(t.hasNextLine()) {
//	 					deck.add(new Cards(t.next(),t.nextInt(),t.nextInt(),t.nextInt(),t.nextInt(),t.nextInt()));
//	 					System.out.println("het");
//	 					t.nextLine(); //?
//	 					//i++;
//	 				}
//					t.close();
//					
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				}
// 				
// 				//int i = 0
// 					
// 				}

//	       shuffles the card deck, reference: https://stackoverflow.com/questions/39557701/shuffle-a-deck-of-cards-in-java

	// method to check for draw
//			public static boolean checkForDraw(Cards[] card) {
//				
//				
//			}
//	
	public void dealCards(int cardsPerPlayer, Player player) {
		for (int i = 0; i < cardsPerPlayer; i++) {
			player.addCard(deck.get(i));
		}
		for (int i = 0; i < cardsPerPlayer; i++) {
			deck.remove(i);
		}
	}

	// the cards that were checked in the previous method are now placed in the
	// common pile
	public void addCommonPile() {

	}

	public ArrayList<Cards> getDeck() {
		return deck;
	}

}
