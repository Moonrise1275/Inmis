package moonrise.inmis;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerContainer implements Serializable {
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
	}
	
	public void setLastPlay(String str) {
		this.lastPlay = str;
	}
	
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
		String[] result = {playerName, "" + playTime, lastPlay, ""};
		return result;
	}
	
}