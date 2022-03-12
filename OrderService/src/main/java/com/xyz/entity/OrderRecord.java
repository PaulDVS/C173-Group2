package com.xyz.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orderRecord")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRecord {

	@Getter
	@Setter
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;

	@Getter
	@Setter
	private String customerEmail;

    @Getter
	@Setter
	private boolean checkedOut;

	@OneToMany(mappedBy = "orderRecord")
	@Getter
	@Setter
  	private List<BasketItem> basketItems;
}
