import java.util.ArrayList;

public class MainBoardModel {
	private LinkedList<ArtifactCardModel> artifacts;
	private LinkedList<FearCardModel> fears;
	private LinkedList<ItemCardModel> items;
	private LinkedList<CardModel> exilePile;
	private int moonStaff;
	private ArrayList<CardModel> cardRow;
	private ArrayList<SiteModel> sites;
	private ResearchTrackModel researchTrack;
	private SupplyBoardModel supplyboard;
	private ArrayList<PlayerModel> players;
	
	public MainBoardModel() {
		PlayerModel p1 = new PlayerModel("one");	
		PlayerModel p2 = new PlayerModel("two");	
		PlayerModel p3 = new PlayerModel("three");	
		PlayerModel p4 = new PlayerModel("four");	
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		Collections.shuffle(players); //randomize turn order
		moonstaff = 1;
		
		//there's 19 fear cards in the game
		for (int i = 0; i < 20; i++) {
			FearCardModel fear = new FearCardModel();
			fears.add(fear);
		}
		
		String travelValue;
		String effect;
		boolean free;
		//there's 40 item cards in the game; ItemCardModel(String travelValue, int travelPoints, ArrayList<String> effects, int points, boolean isActionFree, int costToBuy)
		for (int i = 0; i < 41; i++) {
			//to randomize travel value
			if(i%4 == 0)
				travelValue = "Walk";
			if(i%4 == 1)
				travelValue = "Car";
			if(i%4 == 2)
				travelValue = "Boat";
			if(i%4 == 3)
				travelValue = "Plane";
			
			//to randomize freeactions
			if(i%4 == 0)
				free = true;
			else
				free = false;
			
			//to randomize item effect, making these up
			if (i%10==0)
				effect = "Gain 1 extra funding coin";
			if (i%10==1)
				effect = "Gain 2 extra funding coins";
			if (i%10==2)
				effect = "Gain 3 extra funding coins";
			if (i%10==3)
				effect = "Gain 1 extra exploration coin";
			if (i%10==4)
				effect = "Gain 2 extra exploration coins";
			if (i%10==5)
				effect = "Gain 3 extra exploration coins";
			if (i%10==6)
				effect = "Draw an artifact card";
			if (i%10==7)
				effect = "Draw an item card";
			if (i%10==8)
				effect = "Make someone draw a fear card";
			if (i%10==9)
				effect = "Pick a card on top of exile pile";
			ItemCardModel item = new ItemCardModel(travelValue, (i%3 + 1), effect, (i%4+1), free, (i%3 + 1));
			items.add(item);
		}
		
		//there's 35 artifact cards public ArtifactCardModel (String travelValue, String effect, int points, int costToBuy, int costToPlay, int travelPoints)
		for (int i = 0; i < 36; i++) {
			//to randomize travel value
			if(i%4 == 0)
				travelValue = "Walk";
			if(i%4 == 1)
				travelValue = "Car";
			if(i%4 == 2)
				travelValue = "Boat";
			if(i%4 == 3)
				travelValue = "Plane";
			
			//to randomize item effect; making these up.
			if (i%7==0)
				effect = "Keep one player from using a MAIN action for one turn";
			if (i%7==1)
				effect = "Buy an item at 0 cost";
			if (i%7==2)
				effect = "Use two main actions";
			if (i%7==3)
				effect = "Gain 5 funding coins";
			if (i%7==4)
				effect = "Gain 5 exploration coins";
			if (i%7==5)
				effect = "Draw 3 item cards, choose 1 and put the other 2 back";
			if (i%7==6)
				effect = "Draw 3 artifact cards, choose 1 and put the other 2 back";
			ArtifactCardModel item = new ArtifactCardModel(travelValue, effect, (i%4+1), (i%3 + 1), (i%3 + 1), (i%3 + 1));
			artifacts.add(item);
		}
		Collections.shuffle(items);
		Collections.shuffle(artifacts);
		
		//for initializing all the sites, there are 5 free, 10 level 1, 6 level 2.
		String resource;
		//public SiteModel(String resource, int resAmt, int t)
		for (int i = 0; i < 22; i++) {
			if(i % 5 = 0) {
				resource = "arrowheads";
			}
			if(i % 5 = 1) {
				resource = "tablets";
			}
			if(i % 5 = 2) {
				resource = "funding coins";
			}
			if(i % 5 = 3) {
				resource = "exploration coins";
			}
			if(i % 5 = 4) {
				resource = "jewels";
			}
			if(i < 6) {
				SiteModel site = new SiteModel(resource, (i%2+1), 0);
				sites.add(site);
			}
			else if (i > 6 && i < 16) {
				SiteModel site = new SiteModel(resource, (i%2+1), 1);
				sites.add(site);
			}
			else {
				SiteModel site = new SiteModel(resource, (i%2+1), 2);
				sites.add(site);
			}
		}
		Collections.shuffle(site);
	}
	
	public void roundEnd() {
		moonstaff++;
		//add the end of the round, we need to fill the cardrow back up if any cards were bought, anything less than the moonstaff is an artifact card, else it's an item card.
		for (int i = 0; i < 7; i++) {
			if(cardRow.get(i) == null) {
				if (i < moonstaff) {
					//if artifacts deck is empty, we fill it up with item cards and vise versa.
					if (artifacts.size() > 0) {
						cardRow.add(i, artifacts.poll());
					}
					else {
						cardRow.add(i, items.poll());
					}
				}
				else {
					if (items.size() > 0) {
						cardRow.add(i, items.poll());
					}
					else {
						cardRow.add(i, artifacts.poll());
					}
				}
			}
		}
	}
}
