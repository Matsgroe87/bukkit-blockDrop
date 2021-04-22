package groseth.bukkit.plugin.blockdrop.configuration;

import java.util.List;

public interface IDataStorageHandler {
	public List<ItemConfiguration> getItemConfigurations();
	public void saveConfigurations(List<ItemConfiguration> configurations);
}
