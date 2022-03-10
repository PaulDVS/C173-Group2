package com.xyz.entity;

import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Basket {
	@Getter
	@Setter
	private String customerEmail;

    @Getter
	@Setter
	private List<BasketItem> basketItems;
}
