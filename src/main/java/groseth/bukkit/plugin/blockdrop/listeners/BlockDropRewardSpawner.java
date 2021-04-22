package groseth.bukkit.plugin.blockdrop.listeners;
 
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import groseth.bukkit.plugin.blockdrop.BlockDropHandler;
import groseth.bukkit.plugin.blockdrop.events.BlockDropRewardTriggeredEvent;

public class BlockDropRewardSpawner implements Listener  {
	private BlockDropHandler _handler;

	public BlockDropRewardSpawner(BlockDropHandler handler) {
		_handler = handler;
	}

	@EventHandler 
	public void onDropBlockRewardTriggered(BlockDropRewardTriggeredEvent e) {
		ItemStack reward = e.getReward();
		
		if(reward == null) {
			_handler.getLogger().tryLog("Reward is null");
			return;
		}
		Location spawnLocation =e.getRewardSpawnLocation();
		World world = spawnLocation.getWorld();
		
		world.dropItemNaturally(spawnLocation, reward); 
	}
}
