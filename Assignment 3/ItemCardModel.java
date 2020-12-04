import java.util.ArrayList;

public class ItemCardModel extends CardModel {
	int costToBuy;
	
	public ItemCardModel(String travelValue, int travelPoints, String effects, boolean isActionFree, int costToBuy) {
		
		this.travelValue = travelValue;
		this.travelPoints = travelPoints;
		this.effects = effects;
		this.isActionFree = isActionFree;
		this.costToBuy = costToBuy;
	}
	public int getCostToBuy() { return costToBuy; }
	public void setCostToBuy(int costToBuy) { this.costToBuy = costToBuy; }
}
