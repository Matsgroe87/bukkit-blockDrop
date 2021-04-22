package groseth.bukkit.plugin.blockdrop.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BlockDropTriggeredEvent extends Event implements Cancellable, IBlockDropEvent {
	public BlockDropTriggeredEvent(Player player, Block brokenBlock) {
		_player = player;
		_brokenBlock = brokenBlock;
 
	}

	private boolean _cancelled;
	private static final HandlerList _handlers = new HandlerList();
	private Block _brokenBlock;
	private Player _player;

	public Player getPlayer() {
		return _player;
	}

	public Block getBrokenBlock() {
		return _brokenBlock;
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
