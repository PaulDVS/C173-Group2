package com.xyz.stock.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ItemTypes {
	@Getter
	@Setter
	private List<ItemType> itemTypes;

}
