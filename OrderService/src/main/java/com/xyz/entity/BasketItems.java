package com.xyz.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class BasketItems {
    @Getter
	@Setter
	private List<BasketItem> listItems;
}
