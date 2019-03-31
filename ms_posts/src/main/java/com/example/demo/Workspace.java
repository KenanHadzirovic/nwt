package com.example.demo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Workspace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", nullable = false, length = 20)
	private String title;

	@Column(name = "description", nullable = false, length = 20)
	private String description;
	
	@Column(name = "isDeleted", nullable = false, length = 20)
	private boolean isDeleted;

	@Column(name = "ownerid", nullable = true, length = 20)
	private String ownerid;

	@Column(name = "createdDate", nullable = false)
	private Date createdDate;

	@Column(name = "modifiedDate", nullable = true)
	private Date modifiedDate;

	  @OneToMany(
	            mappedBy = "workspaceid"
	    )
	  
//    private List<Post> posts = new ArrayList<>();

	/*public void addPost(Post post) {
		posts.add(post);
	}

	public void removePost(Post post) {
		posts.remove(post);
	}
*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}
	
	
	
	 public String getOwner() {
	        return ownerid;
	    }

	    public void setOwner(String ownerid) {
	        this.ownerid = ownerid;
	    }

}
