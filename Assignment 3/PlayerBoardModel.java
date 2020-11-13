import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;


public class PlayerBoardModel {
	final static int MAX_IDOLS = 4;
	final static int MAX_ARCHS = 2;
	final static int MAX_HAND = 5;
	ArrayList<CardModel> deck;
	ArrayList<CardModel> hand;
	ArrayList<CardModel> playArea;
	ArrayList<AssistantModel> assistants; 
	ArrayList<IdolModel> idolSlots;
	HashMap<String, Integer> resources;
	ArchaeologistModel[] archs = new ArchaeologistModel[MAX_ARCHS];
	
	public PlayerBoardModel() {
		ExplorationCardModel explore1 = new ExplorationCardModel("car", 1);
		ExplorationCardModel explore2 = new ExplorationCardModel("boat", 1);
		FundingCardModel fund1 = new FundingCardModel("car", 1);
		FundingCardModel fund2 = new FundingCardModel("boat", 1);
		FearCardModel fear1 = new FearCardModel();
		FearCardModel fear2 = new FearCardModel();
		deck.add(explore1);
		deck.add(explore2);
		deck.add(fund1);
		deck.add(fund2);
		deck.add(fear1);
		deck.add(fear2);
		shuffleDeck();
		draw();
	}

	//returns number of cards in deck
	public int getDecKSize() {
		return deck.size();
	}
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}
	// Draw as long as you dont have the max amount in your head and as long as the deck is not empty.
	public void draw() {
		while(hand.size() < MAX_HAND && !deck.isEmpty()) {
			hand.add(deck.get(0)); //adds the top card from deck to hand
			deck.remove(0); // removes the top card from deck
		}
	}
	// Draw a specified amount of cards from your deck as long as deck is not empty
	public void draw(int i) {
		int j = 0;
		while (j < i && !deck.isEmpty()) {
			hand.add(deck.get(0));
			deck.remove(0);
			j++;
		}
	}
	
	
	// returns number of cards in hand.
	public int getHandSize() {
		return hand.size();
	}
	// returns array containing your hand
	public ArrayList<CardModel> getHand(){
		return hand;
	}
	// returns the card from your hand based on index
	public CardModel getHand(int i) {
		return hand.get(i);
	}
	
	
	// returns number of cards in playArea
	public int getPlayAreaSize() {
		return playArea.size();
	}
	// returns array containing the cards in your playarea
	public ArrayList<CardModel> getPlayArea(){
		return playArea;
	}
	// returns specific card from playArea based on index;
	public CardModel getPlayAreaCard(int i) {
		return playArea.get(i);
	}
	// Shuffles the playArea and adds it to the bottom of the deck. 
	public void shufflePlayArea() {
		Collections.shuffle(playArea);
		deck.addAll(playArea);
		playArea.clear();
	}


	// Returns how much resource you have based on type: coin, compass, tablet, arrowhead, jewel
	public int getResource(String type) {
		return resources.get(type);
	}
	// Adds specified amount to the desired resource
	public void gainResource(String type, int amount) {
		resources.put(type, resources.get(type) + amount);
	}

	
	// add IdolModel to Idolslot. Returns false if it could not be added
	public boolean setIdolSlot(IdolModel idol) {
		if (idolSlots.size() < MAX_IDOLS) {
			idolSlots.add(idol);
			return true;
		}
		return false; //max idols is reached.
	}
	// returns how many idols are in the idolSlots
	public int getIdolSlotsUsed(){
		return idolSlots.size();
	}
	
	public int getAssistantsSize() {
		return assistants.size();
	}


}




