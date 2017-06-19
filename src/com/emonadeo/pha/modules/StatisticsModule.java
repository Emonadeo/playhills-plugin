package com.emonadeo.pha.modules;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.emonadeo.pha.Main;

public class StatisticsModule implements Listener {
	 
	Main plugin;
	
	public StatisticsModule(Main main) {
		this.plugin = main;
		init();
	}
	
	private void init() {
		// Update Location every 3 Seconds
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					Location l = p.getLocation();
					// Record ...
				}
			}
			
		}, 0, 60L); // 60 Ticks = 3 Seconds
	}
	
	// Record Stats
	@EventHandler
	public void onKill(EntityDamageByEntityEvent e) {
		// Check if damaged Entity is Living
		if(e.getEntity() instanceof LivingEntity) {
			// Cast Entity to LivingEntity
			LivingEntity entity = (LivingEntity) e.getEntity();
			// Check if Entity died
			if(e.getDamage() >= entity.getHealth()) {
				// Record ...
			}
		}
	}
	
	@EventHandler
	public void onDie(PlayerDeathEvent e) {
		// Record ...
	}
}
