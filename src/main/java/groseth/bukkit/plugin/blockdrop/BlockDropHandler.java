package groseth.bukkit.plugin.blockdrop;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

import Logs.BlockDropLogger;
import Logs.LogLevel;
import groseth.bukkit.plugin.blockdrop.configuration.BlockDropConfiguration;
import groseth.bukkit.plugin.blockdrop.configuration.ItemConfiguration;
import groseth.bukkit.plugin.blockdrop.configuration.ItemConfigurationProbabilityComparer;

public class BlockDropHandler {
	private static final Comparator<? super ItemConfiguration> ItemConfigurationProbabilityComparer = new ItemConfigurationProbabilityComparer();
	private PluginManager _pluginManager;
	private Server _server;
	private BlockDropLogger _log;
	private BlockDropConfiguration _blockDropConfig;
	private Random randomizer;

	public BlockDropHandler(FileConfiguration fileConfiguration, Server server, BlockDropLogger log)
			throws IOException {
		_server = server;
		_pluginManager = server.getPluginManager();
		_blockDropConfig = new BlockDropConfiguration(fileConfiguration, log);
		_log = log;
	}

	public BlockDropLogger getLogger() {
		return _log;
	}

	public boolean isPluginActive() {
		return true;
	}

	public boolean doInputTriggerReward(Player player, Block brokenBlock) {
		double randomScore = getRandomScore();
		double dropRate = _blockDropConfig.getDropRate();

		_log.tryLog(String.format("score: %s. dropRate: %s", randomScore, dropRate));
		return randomScore < dropRate;

		// Get handheldItem
		// ItemStack handStack = player.getInventory().getItemInMainHand();

		// return true;
	}

	public ItemStack CalculateReward(Player player, Block brokenBlock) {
		List<ItemConfiguration> itemconfigurations = _blockDropConfig.getItemConfigurations();
		double sumProbability = 0;
		for (ItemConfiguration itemConfig : itemconfigurations) {
			sumProbability += itemConfig.getProbabilityValue();
		}

		itemconfigurations.sort(ItemConfigurationProbabilityComparer);
		double factor = 1 / sumProbability;
		double sumCalculatedProbabilities = 0;
		double rollValue = getRandomScore();

		for (ItemConfiguration itemConfig : itemconfigurations) {
			double probability = itemConfig.getProbabilityValue() * factor;
			sumCalculatedProbabilities = sumCalculatedProbabilities + probability;
			if (rollValue < sumCalculatedProbabilities) {
				Material material = Material.getMaterial(itemConfig.getMaterialName());
				return new ItemStack(material, itemConfig.getAmount());
			}
		}

		_log.tryLog("looped throug item probabilities, didn't end up with any item", LogLevel.Error);

		return null;
	}

	public void callEvent(Event event) {
		_pluginManager.callEvent(event);
	}

	/// returns a value between 0.0 and 1.0
	private double getRandomScore() {
		if (randomizer == null)
			randomizer = new Random();

		return randomizer.nextDouble();
	}

}
