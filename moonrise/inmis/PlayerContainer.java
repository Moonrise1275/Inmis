package moonrise.inmis;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerContainer implements Serializable, Comparable<PlayerContainer> {
	String playerName;
	int playTime;
	String lastPlay;
	
	public PlayerContainer(String name) {
		this.playerName = name;
		this.playTime = 0;
		this.lastPlay = "";
	}
	
	public void addTime() {
		this.playTime++;
		//System.out.println(playTime);
	}
	
	public void setLastPlay(String str) {
		this.lastPlay = str;
		//System.out.println(lastPlay);
	}
	@Deprecated
	public String[] toArray() {
		/*
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss");
		String playTimeStrUnprocessed = format.format(new Date(((long)playTime) * 1000l));
		String date = playTimeStrUnprocessed.split("_")[0];
		System.out.println(date);
		
		int[] dateInt = new int[3];
		for (int i=0; i<dateInt.length; i++)
			dateInt[i] = new Integer(date.split(".")[i]);
		
		String playTimeStr = new StringBuilder().append(dateInt[0]-1970).append(".").append(dateInt[1]-1).append(".").append(dateInt[2]-1).append("   ")
				.append(playTimeStrUnprocessed.split("_")[1]).toString();
		*/
		
		String[] tostr = toString().split("  |  ");
		
		String[] result = {tostr[0], tostr[1], tostr[2], ""};
		return result;
	}
	
	@Override
	public String toString() {
		
		int playTimeSec = playTime%60;
		int playTimeMin = (playTime/60)%60;
		int playTimeHour = ((playTime/60)/60)%24;
		int playTimeDay = ((playTime/60)/60)/24;
		
		return playerName +"  |  "+ playTimeDay+"."+playTimeHour+":"+playTimeMin+":"+playTimeSec +"  |  "+ lastPlay;
	}

	@Override
	public int compareTo(PlayerContainer container) {
		if (this.playTime > container.playTime)
			return 1;
		else if (this.playTime == container.playTime)
			return 0;
		else return -1;
	}
	
}