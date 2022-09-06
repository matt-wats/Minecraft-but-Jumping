package io.github.amutau.JumpPlug;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import net.md_5.bungee.api.ChatColor;

public final class JumpPlug extends JavaPlugin {
	
	int boost = -1;
	boolean going = false;
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("jumpStart")) {
			going = true;
			joomp();
			jumper();
		} else if(cmd.getName().equalsIgnoreCase("jumpToggle")) {
			going = !going;
			if(sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(ChatColor.RED + "The jumping is now set to: " + going);
			}
		}
		
		return true;
	}
	
	public boolean joomp() {
		
		BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @SuppressWarnings("unchecked")
			public void run() {
            	
            	if(boost < 90 && going) {
	                boost += 1;
        			for(Player p: (Collection<Player>) Bukkit.getOnlinePlayers()) {
        				int str = boost + 1;
        				p.sendMessage(ChatColor.GREEN + "The Jump Boost is: " + str);
        			}
            	}
        		
            }
        }, 0L, 2*(20*60));
	    
        return true;
	}
	
	public boolean jumper() {
		
		if(going) {
		
			BukkitScheduler scheduler = getServer().getScheduler();
	        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
	            public void run() {
            		@SuppressWarnings("unchecked")
            		Collection<Player> onPlayers = (Collection<Player>) Bukkit.getOnlinePlayers();
            		
            		for(Player p: onPlayers) {
            			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60*20, boost));
            		}
            	
        		
	            }
	        }, 0L, 20);
        
		}
        return true;
	}
	
	
	
	
	

}
