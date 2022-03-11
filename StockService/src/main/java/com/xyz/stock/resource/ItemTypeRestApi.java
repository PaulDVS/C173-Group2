package stock.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import stock.entity.ItemType;
import stock.entity.ItemTypes;
import stock.service.ItemTypeService;

@RestController
public class ItemTypeRestApi {
	
	@Autowired
	private ItemTypeService itemTypeService;
	
	
	@PostMapping(value="ItemTypes/Create",consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ItemType addItemType (@RequestBody ItemType itemType) {
	return 	itemTypeService.addItemType(itemType);
	}
	
	@GetMapping(value="ItemTypes/All", produces = MediaType.APPLICATION_JSON_VALUE)
	public ItemTypes getAllTypes() {
		
		return itemTypeService.getAllTypes();
	}
	@GetMapping(value="ItemTypes/GetTaxRates/ItemType/{itemTypeId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public float getTaxRate(@PathVariable String itemTypeId) {
		return itemTypeService.getTaxtRate(itemTypeId);
	}
	
	@PostMapping(value="ItemTypes/SetTaxRates/ItemType/{itemTypeId}/{rate}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ItemType setTaxRate(@PathVariable String itemTypeId,@PathVariable float rate) {
		return itemTypeService.setTaxtRate(itemTypeId,rate);
	}
	
}
