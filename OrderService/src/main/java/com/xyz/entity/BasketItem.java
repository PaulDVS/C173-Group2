package com.xyz.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BasketItem {
    @Getter
	@Setter
	private int itemId;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "OrderId")
	private int OrderId;

    @Getter
	@Setter
	private int quantity;
}
