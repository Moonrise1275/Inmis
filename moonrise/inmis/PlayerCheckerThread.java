package moonrise.inmis;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	private Map<String, PlayerContainer> mapPlayer;
	final File fileInmisPlayers = new File("InmisPlayers.bin");
	
	@Override
	public void run() {
		
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
			this.mapPlayer = new HashMap<String, PlayerContainer>();
		}
		
		while (isRunning) {
			
			World world = Minecraft.getMinecraft().theWorld;
			
			long curTime = System.currentTimeMillis();
			SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss");
			String curDate = date.format(new Date(curTime));
			System.out.println("[Inmis] " + curDate);
			
			try {
				for (Object obj : world.playerEntities) {
					EntityPlayer player = (EntityPlayer)obj;
					addPlayTime(player.username, curDate);
					System.out.println("[Inmis] " + player.username);
				}
			} catch (NullPointerException e) {}
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[Inmis] PlayerCheckerThread deactivated");
		
		try {
			@SuppressWarnings("resource")
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileInmisPlayers)));
			out.writeObject(mapPlayer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		printPlayerInfo();
		
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
		filePlayerInfo.deleteOnExit();
		try {
			@SuppressWarnings("resource")
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePlayerInfo)));
			for (String name : mapPlayer.keySet()) {
				for (String str : mapPlayer.get(name).toArray()) {
					writer.println(str);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
