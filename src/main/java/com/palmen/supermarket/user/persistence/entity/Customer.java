package com.palmen.supermarket.user.persistence.entity;

import java.util.List;
import java.util.Set;
import com.palmen.supermarket.cart.persistence.entity.Cart;
import com.palmen.supermarket.order.persistence.entity.Purchase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "customers")
public class Customer extends User {

	@Column(nullable = false)
	@Builder.Default
	private Boolean ticketEnabled = false;

	@Column(nullable = false)
	@Builder.Default
	private Boolean clubCardEnabled = false;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Wishlist wishlist;

	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Cart cart;

	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private ClubCard clubCard;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PaymentMethod> paymentMethods;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Purchase> purchase;
	
	private String telephoneNumber;
	
	private String dni;
	
	private String address;
	
}
