package stock.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ItemType {
	
	@Getter
	@Setter
	@Id
	private String type;
	@Getter
	@Setter
	private float taxRate;
	
}
