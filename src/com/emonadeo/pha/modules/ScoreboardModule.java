package com.emonadeo.pha.modules;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.emonadeo.pha.Main;

public class ScoreboardModule implements Listener {
	// private Main plugin;
	private Map<UUID, Scoreboard> sbRegistry = new HashMap<UUID, Scoreboard>();
	
	public ScoreboardModule(Main main) {
		// this.plugin = main;
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		setupScoreboard(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		// Decompose Scoreboard from HashMap
		Scoreboard sb = sbRegistry.get(e.getPlayer().getUniqueId());
		Objective ob;
		
		if(sb == null)
			sb = setupScoreboard(e.getPlayer());
		
		ob = sb.getObjective(DisplaySlot.SIDEBAR);	
		Location l = e.getPlayer().getLocation();
		
		// Update Location on Scoreboard
		Score sx = ob.getScore("X");
		Score sy = ob.getScore("Y");
		Score sz = ob.getScore("Z");
		
		sx.setScore(l.getBlockX());
		sy.setScore(l.getBlockY());
		sz.setScore(l.getBlockZ());
	}
	
	private Scoreboard setupScoreboard(Player p) {
		// Prepare a Scoreboard
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
				
		Objective ob = sb.registerNewObjective("location", "dummy");
		ob.setDisplayName(ChatColor.GOLD + "Location");
		ob.setDisplaySlot(DisplaySlot.SIDEBAR);
				
		// Set the Scoreboard
		p.setScoreboard(sb);
				
		// Add Scoreboard to HashMap
		sbRegistry.put(p.getUniqueId(), sb);
		
		return sb;
	}
}
