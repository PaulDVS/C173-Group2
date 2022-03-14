package com.xyz.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
public class BasketItem {

	@Getter
	@Setter

	private int basketItemId;

    @Getter
	@Setter
	private int itemId;

    @Getter
	@Setter
	private int quantity;

	// @Getter
	// @Setter
	// @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "orderId")
    // private OrderRecord orderRecord;
}
