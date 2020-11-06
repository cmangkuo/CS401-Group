import java.util.ArrayList;

/*Each player has a player board, player number, play area and research tokens.
there are more things inside each player board
each player start with 4 basic cards which is different from item/fear/artifact
each player also get 2 fear cards*/
public class PlayerModel {
  private int id; //player number
	private ArrayList<CardModel> playArea; //like a graveyard for used cards and is sent to bottom of deck after round ends
	private PlayerBoardModel playerBoard; //player board
	private int researchTokens; //number of research tokens

	//methods:
	//every player draws at the beginning of their turn to have 5 cards always
	public void Draw(){}

	/*every player can do 1 main action and as many free action
	main actions are: dig at a site, discover a new site, overcome a guardian,
	buy a card, play a card, research, or pass.
	any card with a lightning bolt is a free action */
	public void Dig(){}
	public void Discover(){}
	public void OvercomeGuardian(){}
	public void Buy(){}
	public void Play(){}
	public void Research(){}
	
	//indicate that turn is over.
	public void Pass(){}

	//all cards played are shuffled and returned to the bottom of your own deck
	public void SetUpNextRound(){}
}
