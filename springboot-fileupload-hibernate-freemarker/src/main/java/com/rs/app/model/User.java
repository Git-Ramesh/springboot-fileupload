package com.rs.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "HB_USER")
@DynamicUpdate
@DynamicInsert
public class User implements Serializable {
	private static final long serialVersionUID = -1275384391417903111L;
	@Id
	@SequenceGenerator(name = "USER_ID_SEQ_GEN", initialValue = 1, allocationSize = 1, sequenceName = "USER_ID_SEQ")
	@GeneratedValue(generator = "USER_ID_SEQ_GEN", strategy = GenerationType.AUTO)
	private int id;
	private String username;
	@JsonIgnore
	private String password;
	@Column(unique = true)
	private String email;
	private int age;
	private String address;
}
