package groseth.bukkit.plugin.blockdrop.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import groseth.bukkit.plugin.blockdrop.BlockDropHandler; 
import groseth.bukkit.plugin.blockdrop.events.BlockDropTriggeredEvent; 

public class BlockBreakListner implements Listener  { 
	private BlockDropHandler _handler; 

	public BlockBreakListner(BlockDropHandler handler) {
	  	_handler = handler;
	 }

	  
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) { 
		// Is Plugin Active
		if(!_handler.isPluginActive()) {
			_handler.getLogger().tryLog("BlockDrop is not enabled");
			return;
		}
		
		// Get player
		Player player = e.getPlayer();

		// Get broken block
		Block block = e.getBlock();
		
		// Is Argument OK
		if(!_handler.doInputTriggerReward(player, block)) {
			_handler.getLogger().tryLog("BlockDrop is not triggered");
			return;
		}
		 
		   BlockDropTriggeredEvent triggeredEvent = new BlockDropTriggeredEvent(player, block);
		   _handler.callEvent(triggeredEvent);
	}
}

