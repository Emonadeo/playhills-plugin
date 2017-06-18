package com.emonadeo.pha;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.emonadeo.pha.modules.MapborderModule;
import com.emonadeo.pha.modules.ScoreboardModule;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Main extends JavaPlugin
{
	public MongoClient mongodb;
	public MongoDatabase db;
	
	@Override
	public void onEnable()
	{
		// MongoDB
		// mongodb = new MongoClient("localhost");
		// db = mongodb.getDatabase("playhills");
		
		// Register all Modules
		new ScoreboardModule(this);
		new MapborderModule(this);
		
		// Config
		FileConfiguration cfg = this.getConfig();
		cfg.options().copyDefaults(true);
		this.saveConfig();
	}
	
	@Override
	public void onDisable()
	{
		//mongodb.close();
	}
}
