/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 复选框Entity
 * @author 方坤镇
 * @version 2018-01-10
 */
public class TestFuxuan extends DataEntity<TestFuxuan> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名字
	private String age;		// 年龄
	private String status;		// 状态
	
	public TestFuxuan() {
		super();
	}

	public TestFuxuan(String id){
		super(id);
	}

	@Length(min=0, max=255, message="名字长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="年龄长度必须介于 0 和 255 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}