package stock.service;

import stock.entity.ItemType;
import stock.entity.ItemTypes;

public interface ItemTypeService {
	public ItemTypes getAllTypes();
	public ItemType addItemType(ItemType itemType);
	public ItemType setTaxtRate(String itemTypeId, float taxRate);
	public float getTaxtRate(String itemTypeId);
}
