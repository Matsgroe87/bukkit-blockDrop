package groseth.bukkit.plugin.blockdrop.configuration;

import java.util.UUID;

public class ItemConfiguration {

	private UUID _id;
	private String _materialName;
	private int _amount;
	private double _probabilityValue;
	private boolean _isNew;

	public ItemConfiguration(String materialName) {
		this(materialName, 1);
	}

	public ItemConfiguration(String materialName, double probabilityValue) {
		_id = UUID.randomUUID();
		_materialName = materialName;
		_amount = 1;
		_probabilityValue = probabilityValue;
		_isNew = true;
	}

	public UUID getId() {
		return _id;
	}

	public int getAmount() {
		return _amount;
	}

	public String getMaterialName() {
		return _materialName;
	}

	public double getProbabilityValue() {
		return _probabilityValue;
	}

	public void setProbabilityValue(double value) {
		_probabilityValue = value;
	}

	public boolean getIsNew() {
		return _isNew;
	}

	public static ItemConfiguration LoadExisting(UUID id, String materialName, int amount, double probabilityValue) {
		return new ItemConfiguration(id, materialName, amount, probabilityValue);
	}

	private ItemConfiguration(UUID id, String materialName, int amount, double probabilityValue) {
		_id = id;
		_materialName = materialName;
		_amount = amount;
		_probabilityValue = probabilityValue;
	}
}
