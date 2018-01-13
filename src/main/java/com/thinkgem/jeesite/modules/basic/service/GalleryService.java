/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.basic.entity.Gallery;
import com.thinkgem.jeesite.modules.basic.dao.GalleryDao;

/**
 * 图库管理Service
 * @author 方坤镇
 * @version 2018-01-11
 */
@Service
@Transactional(readOnly = true)
public class GalleryService extends CrudService<GalleryDao, Gallery> {

	public Gallery get(String id) {
		return super.get(id);
	}
	
	public List<Gallery> findList(Gallery gallery) {
		return super.findList(gallery);
	}
	
	public Page<Gallery> findPage(Page<Gallery> page, Gallery gallery) {
		return super.findPage(page, gallery);
	}
	
	@Transactional(readOnly = false)
	public void save(Gallery gallery) {
		super.save(gallery);
	}
	
	@Transactional(readOnly = false)
	public void delete(Gallery gallery) {
		super.delete(gallery);
	}

	/**
	 * 获取评论数
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer getCommentsNum(String id){
		return dao.getCommentsNum(id);
	}

	/**
	 * 点击数加一
	 */
	@Transactional(readOnly = false)
	public void updateHitsAddOne(String id) {
		dao.updateHitsAddOne(id);
	}
	
}