package com.xyz.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BasketItem")
@AllArgsConstructor
@NoArgsConstructor
public class BasketItem {

	@Getter
	@Setter
	@Id
	private int basketItemId;

    @Getter
	@Setter
	private int itemId;

    @Getter
	@Setter
	private int quantity;

	
}
