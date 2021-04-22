package groseth.bukkit.plugin.blockdrop.configuration;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

public class PlainFileDataStorage implements IDataStorageHandler {
	private YamlConfiguration dataConfig;
private static final String fileName  = "data.yml";
private static final String item_configuration_path  = "ItemConfigurations";
	
	public PlainFileDataStorage() throws IOException {
		init();
	}

	public List<ItemConfiguration> getItemConfigurations() {
		List<String> configurations = dataConfig.getStringList(item_configuration_path);

		if (configurations == null || configurations.size() == 0) {
			// create default configurations
		}

		List<ItemConfiguration> itemConfigurations = new ArrayList<ItemConfiguration>();

		for (String line : configurations) {
			ItemConfiguration itemConfig = MapStringToItemConfiguration(line);
			itemConfigurations.add(itemConfig);
		}

		return itemConfigurations;
	}

	public void saveConfigurations(List<ItemConfiguration> configurations) {
		List<String> configurationLines = new ArrayList<String>();
		
		for (ItemConfiguration itemConfiguration : configurations) {
			String line= MapItemConfigToString(itemConfiguration);
			configurationLines.add(line);
		}
		
		dataConfig.set(item_configuration_path, configurationLines);
	}
	
	private String MapItemConfigToString(ItemConfiguration itemConfig) {
		return String.format("%s;%s;%x;f,", itemConfig.getId(), itemConfig.getMaterialName(), itemConfig.getAmount(),
				itemConfig.getProbabilityValue());
	}

	private ItemConfiguration MapStringToItemConfiguration(String configurationString) {
		String[] configurationParts = configurationString.split(";");

		return ItemConfiguration.LoadExisting(UUID.fromString(configurationParts[0]), configurationParts[1],
				Integer.parseInt(configurationParts[2]), Double.parseDouble(configurationParts[3]));
	}

	private void init() throws IOException {
		File configFile = new File(fileName);

		if (!configFile.exists()) {
			configFile.createNewFile();
		}

		dataConfig = YamlConfiguration.loadConfiguration(configFile);
	}
}