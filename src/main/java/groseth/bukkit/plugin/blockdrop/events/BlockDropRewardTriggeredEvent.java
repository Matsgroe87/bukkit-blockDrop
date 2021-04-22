package groseth.bukkit.plugin.blockdrop.events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class BlockDropRewardTriggeredEvent extends Event implements Cancellable, IBlockDropEvent {
	public BlockDropRewardTriggeredEvent(Player player, Location rewardSpawnLocation, ItemStack reward) {
		_player = player;
		_rewardSpawnLocation = rewardSpawnLocation;
		_reward = reward; 
	} 
	
	private static final HandlerList _handlers = new HandlerList();
	private boolean _cancelled; 
	private	ItemStack _reward;
	private Location _rewardSpawnLocation;
	private Player _player;

	public Player getPlayer() {
		return _player;
	}

	public ItemStack getReward() {
		return _reward;
	}

	public Location getRewardSpawnLocation() {
		return _rewardSpawnLocation;
	}

	public boolean isCancelled() {
		return _cancelled;
	}

	public void setCancelled(boolean cancel) {
		_cancelled = !_cancelled || cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return _handlers;
	}

	public static HandlerList getHandlerList() {
		return _handlers;
	}
}
