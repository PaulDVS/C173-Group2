package stock.service;

import stock.entity.Item;
import stock.entity.ItemType;
import stock.entity.Items;

public interface ItemService {
	public Item addItem(Item item);
	public Items getAllItems();
	public Items getItemsByType(ItemType itemType);
	public Item getItemById(int itemId);
	public int getStockQuantityById(int itemId);
	public float getPriceById(int itemId);
}
