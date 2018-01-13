/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 图库管理Entity
 * @author 方坤镇
 * @version 2018-01-11
 */
public class Gallery extends DataEntity<Gallery> {
	
	private static final long serialVersionUID = 1L;
	private String coverGallery;		// 封面
	private String title;		// 标题
	private String sort;		// 排序
	private String galleryCategoryId;		// 分类
	private String vipStatus;		// vip
	private Integer hits;		// 浏览量
	private String likes;		// 点赞
	private String commentId;		// 评论
	private String praise;		// 赞赏
	private String status;		// 状态

	private Date beginInDate;
	private Date endInDate;

	private String galleryCategory;

	private String commentsNum; //评论总数
	private Comments comments;

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public String getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(String commentsNum) {
		this.commentsNum = commentsNum;
	}

	public String getGalleryCategory() {
		return galleryCategory;
	}

	public void setGalleryCategory(String galleryCategory) {
		this.galleryCategory = galleryCategory;
	}

	public Date getBeginInDate() {
		return beginInDate;
	}

	public void setBeginInDate(Date beginInDate) {
		this.beginInDate = beginInDate;
	}

	public Date getEndInDate() {
		return endInDate;
	}

	public void setEndInDate(Date endInDate) {
		this.endInDate = endInDate;
	}

	public Gallery() {
		super();
	}

	public Gallery(String id){
		super(id);
	}

	@Length(min=1, max=255, message="封面长度必须介于 1 和 255 之间")
	public String getCoverGallery() {
		return coverGallery;
	}

	public void setCoverGallery(String coverGallery) {
		this.coverGallery = coverGallery;
	}
	
	@Length(min=1, max=255, message="标题长度必须介于 1 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=255, message="排序长度必须介于 1 和 255 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=1, max=255, message="分类长度必须介于 1 和 255 之间")
	public String getGalleryCategoryId() {
		return galleryCategoryId;
	}

	public void setGalleryCategoryId(String galleryCategoryId) {
		this.galleryCategoryId = galleryCategoryId;
	}
	
	@Length(min=1, max=11, message="vip长度必须介于 1 和 11 之间")
	public String getVipStatus() {
		return vipStatus;
	}

	public void setVipStatus(String vipStatus) {
		this.vipStatus = vipStatus;
	}
	
	@Length(min=0, max=255, message="浏览量长度必须介于 0 和 255 之间")
	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}
	
	@Length(min=0, max=255, message="点赞长度必须介于 0 和 255 之间")
	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}
	
	@Length(min=0, max=255, message="评论长度必须介于 0 和 255 之间")
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
	@Length(min=0, max=255, message="赞赏长度必须介于 0 和 255 之间")
	public String getPraise() {
		return praise;
	}

	public void setPraise(String praise) {
		this.praise = praise;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}