package groseth.bukkit.plugin.blockdrop.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import groseth.bukkit.plugin.blockdrop.BlockDropHandler;
import groseth.bukkit.plugin.blockdrop.events.BlockDropRewardTriggeredEvent;
import groseth.bukkit.plugin.blockdrop.events.BlockDropTriggeredEvent;

public class BlockDropTriggeredListner implements Listener {
	private BlockDropHandler _handler;

	public BlockDropTriggeredListner(BlockDropHandler handler) {
		_handler = handler;
	}

	@EventHandler
	public void onDropBlockTriggered(BlockDropTriggeredEvent e) {

		_handler.getLogger().tryLog("onDropBlockTriggered, calculating reward");

		Block brokenBlock = e.getBrokenBlock();
		// Calculate reward
		ItemStack reward = _handler.CalculateReward(e.getPlayer(), brokenBlock);
	 
		// trigger
		BlockDropRewardTriggeredEvent triggerEvent = new BlockDropRewardTriggeredEvent(e.getPlayer(),
				brokenBlock.getLocation(), reward);
		
		_handler.callEvent(triggerEvent);
	}
}
