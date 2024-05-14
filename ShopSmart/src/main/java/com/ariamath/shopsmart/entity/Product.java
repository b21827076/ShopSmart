package com.ariamath.shopsmart.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product",schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private String description;
	
	private String img_url;
	
	private Date modifiedDate;
	
	private String name;
	
	private Long price;

	private Long stock;

	@OneToOne()
	@JoinColumn(name = "subcategory_id", nullable=false)
	private SubCategory subcategory;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable=false)
	private User User;

	private Double rate;

}