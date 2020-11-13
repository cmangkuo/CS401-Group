import java.util.ArrayList;
public class PlayerModel {
	String name; //Name of player (player1, player2, player3, or player4)
	PlayerBoardModel playerBoard;
	boolean mainActionDone = false; // true if player has performed a main action.
	public PlayerModel(String name) {
		this.name = name;
		playerBoard = new PlayerBoardModel();
	}
	
	public PlayerBoardModel getPlayerBoard() {
		return playerBoard;
	}
	public void draw() {
		playerBoard.draw();
	}
	public void collectResources(String resource, int amount) {
		playerBoard.gainResource(resource, amount);
	}
	public void payResources(String resource, int amount) {
		playerBoard.payResource(resource, amount);
	}
	
	public void setMainActionDone(boolean mainAct) {
		mainActionDone = mainAct;
	}
	public void freeAction(CardModel card) {
			if (card instanceof FundingCardModel) {
				//This is a FundingCard so you gain one coin
				collectResources("coin", 1);
			}
			if (card instanceof ExplorationCardModel) {
				//this is an ExplorationCard, gain one compass
				collectResources("compass", 1);
			}
	}
	
	
	//MAIN ACTIONS: Dig at a site, discover a new site, overcome a guardian, buy a card, play a card, research , and pass
	
	public void dig() {
		mainActionDone = true;
	}
	public void discover() {
		mainActionDone = true;
	}
	public void overcome() {
		mainActionDone = true;
	}
	public void buyCard() {
		mainActionDone = true;
	}
	
	public void playCard(CardModel card) {
		if (card.isActionFree()) {
			freeAction(card);
		}else {
			mainActionDone = true;
			ArrayList<String>effects = card.getEffects();
			for (String effect : effects) {
				// resolve the effect.
			}
			//Put card into playarea
			playerBoard.play(card);
			
		}
	}
	public void research() {
		mainActionDone = true;
	}
	public void pass() {
		mainActionDone = true;
	}
}

