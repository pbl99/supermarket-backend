package com.palmen.supermarket.shared.entity;

import java.time.LocalDateTime;
import com.palmen.supermarket.store.persistence.entity.Store;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "incidents")
public class Incident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime createdDate;
	
	private String description;
	
	private Boolean isWebIncident;
	
	private Boolean fixed;
	
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;
}
