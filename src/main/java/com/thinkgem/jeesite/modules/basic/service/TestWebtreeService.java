/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.basic.entity.TestWebtree;
import com.thinkgem.jeesite.modules.basic.dao.TestWebtreeDao;

/**
 * 测试批量删除Service
 * @author 方坤镇
 * @version 2018-01-10
 */
@Service
@Transactional(readOnly = true)
public class TestWebtreeService extends CrudService<TestWebtreeDao, TestWebtree> {

	@Autowired
	private TestWebtreeDao testWebtreeDao;

	public TestWebtree get(String id) {
		return super.get(id);
	}
	
	public List<TestWebtree> findList(TestWebtree testWebtree) {
		return super.findList(testWebtree);
	}
	
	public Page<TestWebtree> findPage(Page<TestWebtree> page, TestWebtree testWebtree) {
		return super.findPage(page, testWebtree);
	}
	
	@Transactional(readOnly = false)
	public void save(TestWebtree testWebtree) {
		super.save(testWebtree);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestWebtree testWebtree) {
		super.delete(testWebtree);
	}

	@Transactional(readOnly = false)
	public void deleteUser(Integer chk_value) {
		testWebtreeDao.deleteUser(chk_value);
	}



	
}