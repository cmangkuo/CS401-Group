//import java.util.ArrayList;
public class ExplorationCardModel extends CardModel{
	public ExplorationCardModel(String travelValue, int travelPoints) {
		this.travelValue = travelValue;
		this.travelPoints = travelPoints;
		this.effects.add("add 1 compass");
		this.isActionFree = true;
		this.points = 0;
	}
}
