package groseth.bukkit.plugin.blockdrop;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin implements Listener {

	@Override
	public void onEnable()
	{
		saveDefaultConfig(); 
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent e)
	{
    // Get player
		Player player = e.getPlayer();
	 
    // Get broken block
    Block block = e.getBlock();
    
    // Get handheldItem
    ItemStack handStack = player.getItemInHand();
    
    // Gamemode is Creative
    player.getGameMode() == GameMode.CREATIVE 
      
    // do some logic
	}

  private bool hasPermission(){
    return !player.hasPermission("groseth.blockDrop") || player.isOp();
  } 
}
