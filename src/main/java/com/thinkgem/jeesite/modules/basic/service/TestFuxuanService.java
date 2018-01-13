/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.basic.entity.TestFuxuan;
import com.thinkgem.jeesite.modules.basic.dao.TestFuxuanDao;

/**
 * 复选框Service
 * @author 方坤镇
 * @version 2018-01-10
 */
@Service
@Transactional(readOnly = true)
public class TestFuxuanService extends CrudService<TestFuxuanDao, TestFuxuan> {

	public TestFuxuan get(String id) {
		return super.get(id);
	}
	
	public List<TestFuxuan> findList(TestFuxuan testFuxuan) {
		return super.findList(testFuxuan);
	}
	
	public Page<TestFuxuan> findPage(Page<TestFuxuan> page, TestFuxuan testFuxuan) {
		return super.findPage(page, testFuxuan);
	}
	
	@Transactional(readOnly = false)
	public void save(TestFuxuan testFuxuan) {
		super.save(testFuxuan);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestFuxuan testFuxuan) {
		super.delete(testFuxuan);
	}
	
}