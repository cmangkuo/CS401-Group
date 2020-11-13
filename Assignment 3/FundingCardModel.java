//import java.util.ArrayList;
public class FundingCardModel extends CardModel {
	
	public FundingCardModel(String travelValue, int travelPoints) {
		this.travelValue = travelValue;
		this.travelPoints = travelPoints;
		this.effects.add("add 1 coin");
		this.isActionFree = true;
		this.points = 0;
	}

	
}


