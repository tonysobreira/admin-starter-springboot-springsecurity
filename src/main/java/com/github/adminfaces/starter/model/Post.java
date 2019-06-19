package com.github.adminfaces.starter.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name = "Post")
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PostTag> tags = new ArrayList<>();

	public Post() {
	}

	public Post(String title) {
		this.title = title;
	}

	public void addTag(Tag tag) {
		PostTag postTag = new PostTag(this, tag);
		tags.add(postTag);
		tag.getPosts().add(postTag);
	}

	public void removeTag(Tag tag) {
		for (Iterator<PostTag> iterator = tags.iterator(); iterator.hasNext();) {
			PostTag postTag = iterator.next();

			if (postTag.getPost().equals(this) && postTag.getTag().equals(tag)) {
				iterator.remove();
				postTag.getTag().getPosts().remove(postTag);
				postTag.setPost(null);
				postTag.setTag(null);
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Post post = (Post) o;
		return Objects.equals(title, post.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<PostTag> getTags() {
		return tags;
	}

	public void setTags(List<PostTag> tags) {
		this.tags = tags;
	}

}