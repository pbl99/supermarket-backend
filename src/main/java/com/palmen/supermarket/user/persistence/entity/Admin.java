package com.palmen.supermarket.user.persistence.entity;

import java.util.List;
import java.util.Set;
import jakarta.persistence.Entity;
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
@Table(name = "admins")
public class Admin extends User{

	private String prueba;
}
