package stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stock.entity.Item;
import stock.entity.ItemType;
import stock.entity.Items;
import stock.persistence.ItemDao;

@Service
public class ItemServiceImp implements ItemService {
	
	@Autowired
	private ItemDao itemDao;
	
	private final float NO_PRICE = Float.NaN; 
	private final int NO_QUANTITY = Integer.MIN_VALUE; 
	
	public Item addItem(Item item) {
		
		return  itemDao.save(item);
		
	}
	
	public Items getAllItems() {
		List<Item> items= itemDao.findAll();
		
		return new Items(items);
		
	}
	public Items getItemsByType(ItemType itemType) {
		List<Item> items= itemDao.findItemsByItemType(itemType);
		return new Items (items);

	}
	public Item getItemById(int itemId) {
		
		return itemDao.findById(itemId).get();
	}
	
	public int getStockQuantityById(int itemId) {
		
		Item result = itemDao.findById(itemId).get();
		if (result != null) {
			return result.getQuantity();
		}
		return NO_QUANTITY;
		
	}
	public float getPriceById(int itemId) {
		
		Item result = itemDao.findById(itemId).get();
		if (result != null) {
			return result.getPrice();
		}
		
		return NO_PRICE;
	}
	
}
