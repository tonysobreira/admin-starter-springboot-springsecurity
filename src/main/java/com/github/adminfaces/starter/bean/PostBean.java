package com.github.adminfaces.starter.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.security.core.GrantedAuthority;

import com.github.adminfaces.starter.model.Post;
import com.github.adminfaces.starter.model.PostTag;
import com.github.adminfaces.starter.model.Tag;
import com.github.adminfaces.starter.model.User;

@Named
@ViewScoped
public class PostBean extends BaseBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1254944928833121419L;
	
	private Post post;
	private List<Tag> tagList;
	private List<Tag> selectedTags;
	
	private String title;
	
	@PostConstruct
	public void init() {
		post = new Post();
	}
	
	public void save() {
		
		this.post.setTitle(this.title);
		
		for (Tag tag : selectedTags) {
			this.post.addTag(tag);
		}
		
		getFacade().savePost(this.post);
	}
	
	public List<Post> list() {
		return getFacade().findAllPost();
	}
	
	public List<String> getPostTags(Post post) {
		List<String> tagsString = new ArrayList<String>();
		
		for (PostTag postTag : post.getTags()) {
			Tag tag = getFacade().findTagById(postTag.getTag().getId());
			tagsString.add(tag.getName());
		}
		
		return tagsString;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Tag> getTagList() {
		
		if (tagList == null) {
			return getFacade().findAllTag();
		}
		
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

	public List<Tag> getSelectedTags() {
		return selectedTags;
	}

	public void setSelectedTags(List<Tag> selectedTags) {
		this.selectedTags = selectedTags;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
