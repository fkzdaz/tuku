/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 用户管理Entity
 * @author 方坤镇
 * @version 2018-01-09
 */
public class Users extends DataEntity<Users> {
	
	private static final long serialVersionUID = 1L;
	private Integer userId;		// 用户ID
	private String name;		// 用户名
	private String sex;		// 性别
	private String address;		// 地址
	private String headPicture;		// 头像
	private String grade;		// 等级
	private String gossip;		// 禁言
	private String status;		// 状态
	private Date createTime;
	private Date beginInDate;
	private Date endInDate;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Users() {
		super();
	}

	public Users(String id){
		super(id);
	}

	@NotNull(message="用户ID不能为空")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Length(min=1, max=255, message="用户名长度必须介于 1 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=11, message="性别长度必须介于 1 和 11 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=255, message="地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=255, message="头像长度必须介于 0 和 255 之间")
	public String getHeadPicture() {
		return headPicture;
	}

	public void setHeadPicture(String headPicture) {
		this.headPicture = headPicture;
	}
	
	@Length(min=0, max=1, message="等级长度必须介于 0 和 1 之间")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Length(min=0, max=255, message="禁言长度必须介于 0 和 255 之间")
	public String getGossip() {
		return gossip;
	}

	public void setGossip(String gossip) {
		this.gossip = gossip;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}