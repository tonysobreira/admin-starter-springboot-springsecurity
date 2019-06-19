package com.github.adminfaces.starter.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PostTagId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4911517491532982722L;

	@Column(name = "post_id")
	private Long postId;

	@Column(name = "tag_id")
	private Long tagId;

	private PostTagId() {
	}

	public PostTagId(Long postId, Long tagId) {
		this.postId = postId;
		this.tagId = tagId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		PostTagId that = (PostTagId) o;
		return Objects.equals(postId, that.postId) && Objects.equals(tagId, that.tagId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(postId, tagId);
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

}