public class ItemCardView {
  public void printItemCardStats(ItemCardModel model) {
    System.out.println("Travel value: " + model.travelValue+
                       "\nTravel points: " + model.travelPoints+
                       "\nEffects: " + model.effects+
                       "\nFree Action: " + model.isActionFree+
                       "\nPoints: " + model.points+
                       "\nCost to Buy: " + model.costToBuy");
  }
}
