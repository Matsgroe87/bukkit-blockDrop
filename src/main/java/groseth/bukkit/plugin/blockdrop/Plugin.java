package groseth.bukkit.plugin.blockdrop;

import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import Logs.BlockDropLogger;
import groseth.bukkit.plugin.blockdrop.listeners.BlockBreakListner;
import groseth.bukkit.plugin.blockdrop.listeners.BlockDropRewardSpawner;
import groseth.bukkit.plugin.blockdrop.listeners.BlockDropTriggeredListner;

public class Plugin extends JavaPlugin {

	private BlockBreakListner _blockBreakListner;
	private BlockDropHandler _blockDropHandler;
	private BlockDropTriggeredListner _blockDropTriggeredListner;
	private BlockDropRewardSpawner _blockDropRewardSpawner;

	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		saveDefaultConfig();
		try {
			registerEvents();
		} catch (Exception ex) {
			getLogger().warning(ex.toString()); 
		}
	}

	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
		// TODO clean up resources
		// unregister eventlistners
	}

	private BlockDropHandler getHandler() throws IOException {
		if (_blockDropHandler == null) {
			_blockDropHandler = new BlockDropHandler(getConfig(), getServer(), new BlockDropLogger(getLogger()));
		}

		return _blockDropHandler;
	}

	private void registerEvents() throws IOException {
		_blockBreakListner = new BlockBreakListner(getHandler());
		getServer().getPluginManager().registerEvents(_blockBreakListner, this);

		_blockDropTriggeredListner = new BlockDropTriggeredListner(getHandler());
		getServer().getPluginManager().registerEvents(_blockDropTriggeredListner, this);

		_blockDropRewardSpawner = new BlockDropRewardSpawner(getHandler());
		getServer().getPluginManager().registerEvents(_blockDropRewardSpawner, this);
	}

	private boolean hasPermission(Player player) {
		return !player.hasPermission("groseth.blockDrop") || player.isOp();
	}
}
