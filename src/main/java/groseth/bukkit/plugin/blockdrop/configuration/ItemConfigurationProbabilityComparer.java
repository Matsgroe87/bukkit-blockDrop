package groseth.bukkit.plugin.blockdrop.configuration;

import java.util.Comparator;

public class ItemConfigurationProbabilityComparer implements Comparator<ItemConfiguration> {

	public int compare(ItemConfiguration arg0, ItemConfiguration arg1) {
		if(arg0.getProbabilityValue()> arg1.getProbabilityValue()) return 1;
		else return 0;
	}

}
