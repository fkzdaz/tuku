/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.basic.entity.TestWebtree;

/**
 * 测试批量删除DAO接口
 * @author 方坤镇
 * @version 2018-01-10
 */
@MyBatisDao
public interface TestWebtreeDao extends CrudDao<TestWebtree> {

    void deleteUser(Integer chk_value);
	
}