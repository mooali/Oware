package version1;

public class Player {
	private String name;
	private int point;
	private int totalSeed;
	
	
	public Player(String name) {
		this.name = name;
		this.point = 0;
		this.totalSeed = 0;
	}
	public String getName() {
		return this.name;
	}
	public int getPoint() {
		return this.point;
	}
	public void addPoint(int seeds) {
		this.point += seeds;
	}
	public int getTotalSeed() {
		return this.totalSeed;
	}
	public void addTotalSeed(int seeds) {
		this.totalSeed += seeds;
	}
	public void decreaseTotalSeed(int seeds) {
		this.totalSeed -= seeds;
	}
	
}
