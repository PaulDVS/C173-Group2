package stock.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ItemTypes {
	@Getter
	@Setter
	private List<ItemType> itemTypes;

}
