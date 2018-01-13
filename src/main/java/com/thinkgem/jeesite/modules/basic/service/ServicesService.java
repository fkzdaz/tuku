/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.basic.entity.Services;
import com.thinkgem.jeesite.modules.basic.dao.ServicesDao;

/**
 * 服务管理Service
 * @author 方坤镇
 * @version 2018-01-09
 */
@Service
@Transactional(readOnly = true)
public class ServicesService extends CrudService<ServicesDao, Services> {

	public Services get(String id) {
		return super.get(id);
	}
	
	public List<Services> findList(Services services) {
		return super.findList(services);
	}
	
	public Page<Services> findPage(Page<Services> page, Services services) {
		return super.findPage(page, services);
	}
	
	@Transactional(readOnly = false)
	public void save(Services services) {
		super.save(services);
	}
	
	@Transactional(readOnly = false)
	public void delete(Services services) {
		super.delete(services);
	}
	
}