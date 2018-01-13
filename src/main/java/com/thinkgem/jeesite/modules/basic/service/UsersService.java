/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.basic.entity.Users;
import com.thinkgem.jeesite.modules.basic.dao.UsersDao;

/**
 * 用户管理Service
 * @author 方坤镇
 * @version 2018-01-09
 */
@Service
@Transactional(readOnly = true)
public class UsersService extends CrudService<UsersDao, Users> {

	public Users get(String id) {
		return super.get(id);
	}
	
	public List<Users> findList(Users users) {
		return super.findList(users);
	}
	
	public Page<Users> findPage(Page<Users> page, Users users) {
		return super.findPage(page, users);
	}
	
	@Transactional(readOnly = false)
	public void save(Users users) {
		super.save(users);
	}
	
	@Transactional(readOnly = false)
	public void delete(Users users) {
		super.delete(users);
	}

}