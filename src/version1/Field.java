package version1;


public class Field {
	int position;
	int seeds;

	public Field(int position, int seeds) {
		this.position = position;
		this.seeds = seeds;
	}

	public int getPosition() {
		return this.position;
	}

	public int getSeeds() {
		return this.seeds;
	}

	public void increaseSeeds() {
		this.seeds++;
	}

	public void removeSeeds(int seed) {
		this.seeds -= seed;
	}
	
	
	
}
