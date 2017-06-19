package com.emonadeo.pha.modules;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.emonadeo.pha.Main;
import com.emonadeo.pha.lib.MongoHelper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class StatisticsModule implements Listener {
	 
	Main plugin;
	MongoDatabase db;
	MongoCollection<Document> collection;
	
	public StatisticsModule(Main main) {
		this.plugin = main;
		this.db = main.db;
		this.collection = db.getCollection("Stats");
		
		main.getServer().getPluginManager().registerEvents(this, main);
		
		init();
	}
	
	private void init() {
		// Update Location every 3 Seconds
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					Location l = p.getLocation();
					// Record on MongoDB
					MongoHelper.createStat(collection, p.getUniqueId(), "Location", new Document("X", l.getBlockX())
							.append("Y", l.getBlockY())
							.append("Z", l.getBlockZ()));
				}
			}
			
		}, 0, 60L); // 60 Ticks = 3 Seconds
	}
	
	// Record Stats
	@EventHandler
	public void onKill(EntityDamageByEntityEvent e) {
		// Check if damaged Damager is Player
		if(e.getDamager() instanceof Player) {
			// Cast Damager to Player
			Player p = (Player) e.getDamager();
			// Check if Entity is living
			if(e.getEntity() instanceof LivingEntity) {
				// Cast Entity to LivingEntity
				LivingEntity en = (LivingEntity) e.getEntity();
				// Check if Entity died
				if(e.getDamage() >= en.getHealth()) {
					// Record on MongoDB
					MongoHelper.incrementStat(collection, p.getUniqueId(), "Kills");
				}
			}
		}
	}
	
	@EventHandler
	public void onDie(PlayerDeathEvent e) {
		// Record on MongoDB
		MongoHelper.incrementStat(collection, e.getEntity().getUniqueId(), "Deaths");
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		// Record on MongoDB
		MongoHelper.createStat(collection, e.getPlayer().getUniqueId(), "Name", e.getPlayer().getName());
	}
}
