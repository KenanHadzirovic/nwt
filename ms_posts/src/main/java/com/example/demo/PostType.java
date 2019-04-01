package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PostType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "posttypename", nullable = false, length = 20)
	private String posttypename;
	
	@OneToMany(
		mappedBy = "posttype_id"
	)
	  
	  
	public String getPostTypeName() {
	        return posttypename;
	  }

	public void setPostTypeName(String posttypename) {
	        this.posttypename = posttypename;
      }
	  
	public Long getId() {
			return id;
		}

	public void setId(Long id) {
			this.id = id;
		}

	public PostType()
	{
		this.setId((long)0);
		this.setPostTypeName("");
	}

	public PostType(Long postTypeId)
	{
		this.setId(postTypeId);
	}
}
