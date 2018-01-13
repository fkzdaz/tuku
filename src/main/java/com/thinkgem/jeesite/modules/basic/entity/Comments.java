/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 评论表Entity
 * @author 方坤镇
 * @version 2018-01-10
 */
public class Comments extends DataEntity<Comments> {
	
	private static final long serialVersionUID = 1L;
	private Integer userId;		// 用户ID
	private String galleryId;		// 图库ID
	private String commentsContent;		// 评论内容
	private String status;		// 状态
	private Date beginInDate; //开始时间
	private Date endInDate;  //结束时间
	private String userName; //用户名称

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Comments() {
		super();
	}

	public Comments(String id){
		super(id);
	}

	@NotNull(message="用户ID不能为空")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Length(min=1, max=255, message="图库ID长度必须介于 1 和 255 之间")
	@NotNull(message = "图库ID不能为空")
	public String getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(String galleryId) {
		this.galleryId = galleryId;
	}
	
	@Length(min=1, max=255, message="评论内容长度必须介于 1 和 255 之间")
	public String getCommentsContent() {
		return commentsContent;
	}

	public void setCommentsContent(String commentsContent) {
		this.commentsContent = commentsContent;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}