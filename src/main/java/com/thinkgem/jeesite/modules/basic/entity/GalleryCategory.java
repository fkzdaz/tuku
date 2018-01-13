/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图库分类管理Entity
 * @author 方坤镇
 * @version 2018-01-11
 */
public class GalleryCategory extends DataEntity<GalleryCategory> {
	
	private static final long serialVersionUID = 1L;
	private String coverImg;		// 封面图
	private String categoryName;		// 名称
	private String description;		// 描述
	private String sort;		// 排序
	private String status;		// 状态
	
	public GalleryCategory() {
		super();
	}

	public GalleryCategory(String id){
		super(id);
	}

	@Length(min=1, max=255, message="封面图长度必须介于 1 和 255 之间")
	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	
	@Length(min=1, max=255, message="名称长度必须介于 1 和 255 之间")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Length(min=1, max=255, message="描述长度必须介于 1 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=255, message="排序长度必须介于 0 和 255 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}