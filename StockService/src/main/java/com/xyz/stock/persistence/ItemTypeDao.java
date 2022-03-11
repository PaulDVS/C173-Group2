package stock.persistence;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.jpa.repository.JpaRepository;

import stock.entity.ItemType;

@Persistent
public interface ItemTypeDao extends JpaRepository<ItemType, String>{
	


}
