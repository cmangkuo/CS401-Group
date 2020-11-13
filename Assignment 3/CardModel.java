import java.util.ArrayList;
public class CardModel {
	String travelValue;		//the type of travel: foot, plane, car, or boat
	int travelPoints;		//number of travel points on the card (# of travel points = # of travelValue icons on the card) 
	ArrayList<String> effects;
	boolean isActionFree; // true means it doesn't count as your main action, false means it does count as your main action
	int points;
	
	public CardModel() {}
	
	public String getTravelValue() { return travelValue; }
	public ArrayList<String> getEffects() { return effects; }
	public int getPoints() { return points; }
	public int getTravelPoints() { return travelPoints; }
	public boolean isActionFree() { return isActionFree; }
}
