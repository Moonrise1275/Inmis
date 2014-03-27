package moonrise.inmis;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class PlayerCheckerThread extends Thread {
	
	private boolean isRunning = true;
	public Map<String, PlayerContainer> mapPlayer;
	final File fileInmisPlayers = new File("InmisPlayers.bin");
	
	public PlayerCheckerThread() {
		this.mapPlayer = new TreeMap<String, PlayerContainer>();
	}
	
	@Override
	public void run() {
		/*
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(new File("InmisPlayerInfo.txt")));
		while (true) {
			String message = br.readLine();
			if (message == null)
				break;
			System.out.println(message);
		};
		} catch (IOException e) {
				e.printStackTrace();
		}
		*/
		System.out.println("[Inmis] PlayerCheckerThread activated");
		if (fileInmisPlayers.exists()) {
			try {
				@SuppressWarnings("resource")
				ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileInmisPlayers)));
				this.mapPlayer = (Map<String, PlayerContainer>) in.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			this.mapPlayer = new TreeMap<String, PlayerContainer>();
		}
		
		while (isRunning) {
			
			World world = Minecraft.getMinecraft().theWorld;
			
			long curTime = System.currentTimeMillis();
			SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss");
			String curDate = date.format(new Date(curTime));
			//System.out.println("[Inmis] " + curDate);
			
			try {
				for (Object obj : world.playerEntities) {
					EntityPlayer player = (EntityPlayer)obj;
					addPlayTime(player.username, curDate);
					//System.out.println("[Inmis] " + player.username);
				}
			} catch (NullPointerException e) { 
				e.printStackTrace(); 
			}
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//System.out.println(mapPlayer);
		}
		System.out.println("[Inmis] PlayerCheckerThread deactivated");
		
		printPlayerInfo();

		try {
			@SuppressWarnings("resource")
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileInmisPlayers));
			out.writeObject(mapPlayer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("[Inmis] PlayerCheckerThread closed");
	}
	
	private void addPlayTime(String player, String time) {
		
		if (mapPlayer.containsKey(player)) {
			mapPlayer.get(player).addTime();
		}
		else {
		PlayerContainer container = new PlayerContainer(player);
		mapPlayer.put(player, container);
		}
		mapPlayer.get(player).setLastPlay(time);
		/*
		String[] strarr = mapPlayer.get(player).toArray();
		
		System.out.println(strarr[0]);
		System.out.println(strarr[1]);
		System.out.println(strarr[2]);
		*/
	}
	
	public void setOff() {
		this.isRunning = false;
	}
	
	//public Collection<PlayerContainer> getList() {
	//	return mapPlayer.values();
	//}
	
	public void printPlayerInfo() {
		System.out.println("[Inmis] Saving to InmisPlayerInfo.txt");
		
		File filePlayerInfo = new File("InmisPlayerInfo.txt");
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(filePlayerInfo));
			for (PlayerContainer player : this.mapPlayer.values()) {
				writer.println(player);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
