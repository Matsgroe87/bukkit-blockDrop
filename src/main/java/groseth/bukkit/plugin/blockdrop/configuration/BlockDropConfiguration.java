package groseth.bukkit.plugin.blockdrop.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import Logs.BlockDropLogger;

public class BlockDropConfiguration {
	private FileConfiguration _fileConfiguration;
	private Logger _log;
	private static double defaultDropRate = 0.01;
	private List<ItemConfiguration> _itemConfigurations;
	private IDataStorageHandler _dataHandler;

	public BlockDropConfiguration(FileConfiguration fileConfiguration, BlockDropLogger log) throws IOException {
		_fileConfiguration = fileConfiguration;
		InitDataHandler();
	}

	public boolean isBlockDropEnabled() {
		return _fileConfiguration.getBoolean(ConfigurationPaths.IS_ENABLED, false);
	}

	public boolean isBlockDropEnabledInCreative() {
		return _fileConfiguration.getBoolean(ConfigurationPaths.IS_ENABLED_CREATIVE, false);
	}

	public double getDropRate() {
		double dropRate = _fileConfiguration.getDouble(ConfigurationPaths.DROP_RATE, defaultDropRate);
		if (dropRate > 1) {
			if (dropRate <= 100) {
				dropRate = dropRate / 100;
				_fileConfiguration.set(ConfigurationPaths.DROP_RATE, dropRate);
			} else {
				dropRate = defaultDropRate;
				_fileConfiguration.set(ConfigurationPaths.DROP_RATE, dropRate);
			}
		}

		return dropRate;
	} 

	public List<ItemConfiguration> getItemConfigurations(){
		return _itemConfigurations;
	}
	
	private void InitDataHandler() throws IOException {
		// TODO add support for SQL/MySQL/etc.
		_dataHandler = new PlainFileDataStorage();
		_itemConfigurations = _dataHandler.getItemConfigurations();
		
		if(_itemConfigurations == null || _itemConfigurations.size() == 0) {
			SetDefaultItemConfiguration();
		}
	}
	
	private void SetDefaultItemConfiguration() {
		_itemConfigurations = new ArrayList<ItemConfiguration>();
		_itemConfigurations.add(new ItemConfiguration(Material.DIAMOND.name(), 0.01));
		_itemConfigurations.add(new ItemConfiguration(Material.EMERALD.name(), 0.02));
		_itemConfigurations.add(new ItemConfiguration(Material.IRON_INGOT.name(), 0.1));
		
		_dataHandler.saveConfigurations(_itemConfigurations);
	}
}
