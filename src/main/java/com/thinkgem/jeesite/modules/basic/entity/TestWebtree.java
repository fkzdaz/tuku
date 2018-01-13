/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 测试批量删除Entity
 * @author 方坤镇
 * @version 2018-01-10
 */
public class TestWebtree extends DataEntity<TestWebtree> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名字
	private String num;		// 手机号码
	private String status;		// 状态
	
	public TestWebtree() {
		super();
	}

	public TestWebtree(String id){
		super(id);
	}

	@Length(min=0, max=255, message="名字长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="手机号码长度必须介于 0 和 255 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}