public class ArtifactCardModel extends CardModel{
		int costToBuy;
		int costToPlay;
		boolean isBought = false;
	public ArtifactCardModel (String travelValue, String effect, int points, int costToBuy, int costToPlay, int travelPoints) {
		this.isFreeAction = false;	
		this.travelValue = travelValue;
		this.effect = effect;
		this.points = points;
		this.costToBuy = costToBuy;
		this.costToPlay = costToPlay;
		this.travelPoints = travelPoints;
	}
	
	public int getCostToPlay() { return costToPlay; } 
	public int getCostToBuy() { return costToBuy; }
	
	public void setCostToPlay(int costToPlay) { this.costToPlay = costToPlay; }
	public void setCostToBuy(int costToBuy) { this.costToBuy = costToBuy; }
	
	
	public void setIsBought(boolean isBought) {
		this.isBought = isBought; 
		// If this card was bought you can resolve its effect on the same turn and it is considered part of your main action
		if (isBought) {
			isFreeAction = true;
		}
	}
	
	
	
}
