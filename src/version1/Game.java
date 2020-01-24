package version1;

public class Game {

	private Field[] pits;
	private Player currentPlayer;
	private Player player1;
	private Player player2;
	public gameStage currentGameStage;
	
	// Game Status
	public enum gameStage {
		OPEN, GAMEOVER
	}

	// Konstruktur für ein neues Game
	public Game(Player player1, Player player2) {
		
		// create 12 Fields, filled with 4 seeds
		this.pits = new Field[12];
		for (int i = 0; i < this.pits.length; i++) {
			Field pit = new Field(i, 4);
			this.pits[i] = pit;
		}
		this.currentPlayer = player1;
		this.player1 = player1;
		this.player2 = player2;
		this.currentGameStage = gameStage.OPEN;
	}
	// Get Methode
	public Field getPits(int pit) {
		return this.pits[pit];
	}
	public Field[] getAllPits() {
		return this.pits;
	}
	public void setPits(Field[] pits) {
		this.pits = pits;
	}
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	//Feld-Position für das Spiel ist immmer
	//Player1:	5 	4 	3	2	1	0
	//Player2:	6 	7	8	9	10	11
	public int getFieldPosition(int field) {
		return this.pits[field].getPosition();
	}

	public int getSeeds(int field) { //get amount of seeds
		return this.pits[field].getSeeds();
	}

	public Field getField(int field) { //return selected field, this method is not used yet
		return this.pits[field];
	}
	
	
	
	//Jede Aktion auf dem Spielfeld landet zuerst hier
	public void tapField(int tapped) { 
		int seedsToRemove = this.pits[tapped].getSeeds();
		
		// go to next field and increase or eat their seeds
		this.goNext(tapped); 
		// remove seeds of the field you have tapped
		this.pits[tapped].removeSeeds(seedsToRemove); 
		// switch to next Player
		this.switchPlayer();
		// identify of Human-Player or Computer-Player
		if (this.currentPlayer.getName() == "Computer") {
			int n = this.robotPlay();
			tapField(n);
			System.out.println("Tipp == "+n);
		}
	}
	
	//Prüfen: das nächste Feld
	public void goNext(int tappedField) { 
		int nextField = tappedField + 1;
		this.currentPlayer.decreaseTotalSeed(this.pits[tappedField].getSeeds());
		for (int i = 0; i < this.pits[tappedField].getSeeds(); i++, nextField++) {
			nextField %= this.pits.length;
			this.pits[nextField].increaseSeeds();
			
			if (this.currentPlayer == this.player1 && this.pits[nextField].getPosition() >= 6) {
				if (this.pits[nextField].getSeeds() == 2) {
					this.takePoint(nextField);
				}
			}		
			if (this.currentPlayer == this.player2 && this.pits[nextField].getPosition() <= 5) {	
				if (this.pits[nextField].getSeeds() == 2) {
					this.takePoint(nextField);
				}
			}
			
			if (this.pits[nextField].getPosition() <= 5) {
				this.player1.addTotalSeed(1);
			} else {
				this.player2.addTotalSeed(1);
			}

		}

	}
	
	//Methode: Fall der Spieler das Spielfeld von Gegner 2 Kugeln auffüllen kann
	//So kann er die 2 Kugeln vom Spielfeld wegnehmen
	private void takePoint(int tappedField) {
		this.pits[tappedField].removeSeeds(2);
		
		if (this.currentPlayer == this.player1) {
			this.player1.addPoint(2);
			this.player2.decreaseTotalSeed(2);
		} else {
			this.player2.addPoint(2);
			this.player1.decreaseTotalSeed(2);
		}		
	}

	//Die Methode prüft, wer der nächste Spieler ist
	public void switchPlayer() {//switch player if legal move is possible for the next player
		this.currentPlayer = (this.currentPlayer == this.player1)? this.player2: this.player1;
		if (this.currentPlayer.getTotalSeed() == 0) { //switch player if player1 has at least 1 seed
			this.currentPlayer = (this.currentPlayer == this.player1)? this.player2: this.player1;
		}
		System.out.println("Player1 hat "+this.player1.getTotalSeed() + " Player2 hat "+ this.player2.getTotalSeed());
	}

	//Die Methode prüft, ob das Game zu Ende ist.
	//Exit Strategie ist, wenn 4 Kugeln auf dem Spielfeld sind. Das Game wird beendet. 
	//Jeder darf das Kugel von Spielfeldern des Gegners wegnehmen
	// aber es muss ein Kugel in Spielfeldern des Gegners übrig bleiben.
	public void checkGameStage() { 
		int totalSeeds = this.player1.getTotalSeed()+this.player2.getTotalSeed();
		if (totalSeeds <= 4) {
			this.currentGameStage = gameStage.GAMEOVER;
			int seedsInPit1 = (this.player1.getTotalSeed() >1)? this.player1.getTotalSeed()-1: 0;
			int seedsInPit2 = (this.player2.getTotalSeed() >1)? this.player2.getTotalSeed()-1: 0;
			this.player2.addPoint(seedsInPit1);
			this.player1.addPoint(seedsInPit2);

		}
	}


	//robot is player2, pit 6 to 11
	//robot with playSafe logic, protect a pit with value 1 as first
	//else take a pit from the right to play
	private int robotPlay() {
		int nextTapp = 0;
		
		/// search pit == 1 from left, pit to play is the left
		for (int i = 6; i <= 11; i++) {
			if (this.pits[i].getSeeds() == 1) {
				nextTapp = i-1;
				break;
			}
		}	
		
		while (nextTapp > 5) {
			if (this.pits[nextTapp].getSeeds() != 0) break;
			nextTapp--;
		}
		
				
		// if the next < 6 jump to test from 11
		if(nextTapp < 6 && nextTapp != 0) {
			for (int i = 11 ,j =1 ; i >= 6; i--, j++) {
				if (this.pits[i].getSeeds() - 6 >= j) {
					nextTapp = i;
					break;
				}
			}
			nextTapp = (nextTapp < 6)? 0: nextTapp;
		}
		
		//// else
		if (nextTapp == 0) {
			for (int i = 11; i >= 6; i--) {
				if (this.pits[i].getSeeds() != 0) {
					nextTapp = i;
					break;
				}
			}
		}
		
		return nextTapp;

	}

}