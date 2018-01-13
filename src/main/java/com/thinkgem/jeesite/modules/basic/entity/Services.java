/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 服务管理Entity
 * @author 方坤镇
 * @version 2018-01-09
 */
public class Services extends DataEntity<Services> {
	
	private static final long serialVersionUID = 1L;
	private Integer sort;		// 排序
	private String serviceContent;		// 服务内容
	private String price;		// 价格（元）
	private String serviceTime;		// 时长
	private String special;		// 特价
	private String status;		// 状态
	
	public Services() {
		super();
	}

	public Services(String id){
		super(id);
	}

	@NotNull(message="排序不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=1, max=255, message="服务内容长度必须介于 1 和 255 之间")
	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=1, max=255, message="时长长度必须介于 1 和 255 之间")
	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	@Length(min=0, max=255, message="特价长度必须介于 0 和 255 之间")
	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}