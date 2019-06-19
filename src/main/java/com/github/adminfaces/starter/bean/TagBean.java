package com.github.adminfaces.starter.bean;

import static com.github.adminfaces.template.util.Assert.has;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;

import com.github.adminfaces.starter.model.Car;
import com.github.adminfaces.starter.model.Tag;

@Named
@ViewScoped
public class TagBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1673275062956668457L;

	private Tag tag;

	public void init() {

		if (Faces.isAjaxRequest()) {
			return;
		}

		if (tag != null && has(tag.getId())) {
			tag = getFacade().findTagById(tag.getId());
		} else {
			tag = new Tag();
		}

	}

	public void save() {
		getFacade().saveTag(this.tag);
	}

	public List<Tag> list() {
		return getFacade().findAllTag();
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

}
