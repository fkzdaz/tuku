/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.basic.entity.TestFuxuan;
import com.thinkgem.jeesite.modules.basic.service.TestFuxuanService;

/**
 * 复选框Controller
 * @author 方坤镇
 * @version 2018-01-10
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/testFuxuan")
public class TestFuxuanController extends BaseController {

	@Autowired
	private TestFuxuanService testFuxuanService;
	
	@ModelAttribute
	public TestFuxuan get(@RequestParam(required=false) String id) {
		TestFuxuan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testFuxuanService.get(id);
		}
		if (entity == null){
			entity = new TestFuxuan();
		}
		return entity;
	}
	
	@RequiresPermissions("basic:testFuxuan:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestFuxuan testFuxuan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestFuxuan> page = testFuxuanService.findPage(new Page<TestFuxuan>(request, response), testFuxuan); 
		model.addAttribute("page", page);
		return "modules/basic/testFuxuanList";
	}

	@RequiresPermissions("basic:testFuxuan:view")
	@RequestMapping(value = "form")
	public String form(TestFuxuan testFuxuan, Model model) {
		model.addAttribute("testFuxuan", testFuxuan);
		return "modules/basic/testFuxuanForm";
	}

	@RequiresPermissions("basic:testFuxuan:edit")
	@RequestMapping(value = "save")
	public String save(TestFuxuan testFuxuan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testFuxuan)){
			return form(testFuxuan, model);
		}
		testFuxuanService.save(testFuxuan);
		addMessage(redirectAttributes, "保存复选成功");
		return "redirect:"+Global.getAdminPath()+"/basic/testFuxuan/?repage";
	}
	
	@RequiresPermissions("basic:testFuxuan:edit")
	@RequestMapping(value = "delete")
	public String delete(TestFuxuan testFuxuan, RedirectAttributes redirectAttributes) {
		testFuxuanService.delete(testFuxuan);
		addMessage(redirectAttributes, "删除复选成功");
		return "redirect:"+Global.getAdminPath()+"/basic/testFuxuan/?repage";
	}

}