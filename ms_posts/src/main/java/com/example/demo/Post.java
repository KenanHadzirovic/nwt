package com.example.demo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.springframework.data.annotation.Id;
import javax.persistence.Id;

@Entity
public class Post {

        @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "title", nullable = false, length = 20)
	    private String title;
	    
	    @Column(name = "content", nullable = false, length = 20)
	    private String content;
	    	    
	    @Column(name = "createdDate", nullable = false)
	    private Date createdDate;

	    @Column(name = "modifiedDate", nullable = true)
	    private Date modifiedDate;
	    
	 //   private List<PostType> posttypelist = new ArrayList<>();
	 //   private List<Workspace> workspacelist = new ArrayList<>();

 
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }
	    
	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }
	   	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "workspaceid")
	    private Workspace workspace;
	    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "posttypeId")
	    private PostType posttype;
	    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

	    public Date getCreatedDate() {
	        return createdDate;
	    }
	    
	    public void setCreatedDate(Date createdDate) {
	        this.createdDate = createdDate;
	    }

	    public Date getModifiedDate() {
	        return modifiedDate;
	    }
	    
	    public void setModifiedDate(Date modifiedDate) {
	        this.modifiedDate = modifiedDate;
	    }

	    public void setWorkspace(Long workspaceId) {
	        this.workspace.setId(workspaceId);
	    }
	    
	    public void setPostType(Long posttypeId) {
	        this.posttype.setId(posttypeId);
	    }
	    
	/*    public void addWorkspace(Workspace workspace) {
	        workspacelist.add(workspace);
	        workspace.setOwner("2");
	    }

	    public void removeWorkspace(Workspace workspace) {
	        workspacelist.remove(workspace);
	        workspace.setOwner(null);
	    }
	    
	    public void addPostType(PostType posttype) {
	        posttypelist.add(posttype);
	    }

	    public void removePostType(PostType posttype) {
	    	posttypelist.remove(posttype);
	    }*/
}
