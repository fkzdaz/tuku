/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.basic.entity.Comments;
import com.thinkgem.jeesite.modules.basic.dao.CommentsDao;

/**
 * 评论表Service
 * @author 方坤镇
 * @version 2018-01-10
 */
@Service
@Transactional(readOnly = true)
public class CommentsService extends CrudService<CommentsDao, Comments> {

	public Comments get(String id) {
		return super.get(id);
	}
	
	public List<Comments> findList(Comments comments) {
		return super.findList(comments);
	}
	
	public Page<Comments> findPage(Page<Comments> page, Comments comments) {
		return super.findPage(page, comments);
	}
	
	@Transactional(readOnly = false)
	public void save(Comments comments) {
		super.save(comments);
	}
	
	@Transactional(readOnly = false)
	public void delete(Comments comments) {
		super.delete(comments);
	}
	
}