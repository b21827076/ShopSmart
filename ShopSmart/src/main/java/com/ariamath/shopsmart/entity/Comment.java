package com.ariamath.shopsmart.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "comment",schema = "public")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "product_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Product product;
	
	@ManyToOne()
	@JoinColumn(name = "user_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	@Lob
    @Column(columnDefinition="text")
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	private Date modifiedDate;
	
}
