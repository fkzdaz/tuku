/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.basic.entity.GalleryCategory;
import com.thinkgem.jeesite.modules.basic.dao.GalleryCategoryDao;

/**
 * 图库分类管理Service
 * @author 方坤镇
 * @version 2018-01-11
 */
@Service
@Transactional(readOnly = true)
public class GalleryCategoryService extends CrudService<GalleryCategoryDao, GalleryCategory> {

	public GalleryCategory get(String id) {
		return super.get(id);
	}
	
	public List<GalleryCategory> findList(GalleryCategory galleryCategory) {
		return super.findList(galleryCategory);
	}
	
	public Page<GalleryCategory> findPage(Page<GalleryCategory> page, GalleryCategory galleryCategory) {
		return super.findPage(page, galleryCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(GalleryCategory galleryCategory) {
		super.save(galleryCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(GalleryCategory galleryCategory) {
		super.delete(galleryCategory);
	}
	
}