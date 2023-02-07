package com.restfull.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "registration")
public class RegistrationEntity {

	@Id
	private String user_id;
	private String gmail;
	private String password;
	private String registered_time;
	
}
