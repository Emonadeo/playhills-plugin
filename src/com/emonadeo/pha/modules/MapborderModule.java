package com.emonadeo.pha.modules;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

import com.emonadeo.pha.Main;

public class MapborderModule implements Listener {
	private Main plugin;
	
	public MapborderModule(Main main) {
		this.plugin = main;
		main.getServer().getPluginManager().registerEvents(this, main);
		
		// Initialize worldborder for every world
		updateWorldBorder();
	}
	
	private void updateWorldBorder() {
		FileConfiguration cfg = plugin.getConfig();
		
		// Get radius from Config
		cfg.addDefault("Radius", 20);
		int r = cfg.getInt("Radius") * 2 + 1;
		
		// Apply worldborder to world
		for(World w : plugin.getServer().getWorlds()) {
			WorldBorder wb = w.getWorldBorder();
			wb.setCenter(w.getSpawnLocation().add(0.5, 0, 0.5));
			wb.setSize(r);
		}
	}
	
	/*
	 * Using SpawnChangeEvent wasn't possible to update
	 * the worldborder immediatly due to it not being fired
	 */
}
