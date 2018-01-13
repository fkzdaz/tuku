/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.basic.entity.Gallery;
import com.thinkgem.jeesite.modules.cms.entity.Article;

/**
 * 图库管理DAO接口
 * @author 方坤镇
 * @version 2018-01-11
 */
@MyBatisDao
public interface GalleryDao extends CrudDao<Gallery> {

    //计算评论总数
    public Integer getCommentsNum(String id);


    //浏览量加1
    public int updateHitsAddOne(String id);
}