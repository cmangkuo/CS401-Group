public class CardView {
    public void printCardDetails(CardModel model){
		System.out.println("Card: ");
        System.out.println("Travel Value: " + model.travelValue);
        System.out.println("Travel points: " + model.travelPoints);
        System.out.println("Points: " + model.points);
        System.out.println("IsActionFree: " + model.isActionFree);

    }
}
