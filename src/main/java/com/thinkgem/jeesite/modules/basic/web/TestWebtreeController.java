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
import com.thinkgem.jeesite.modules.basic.entity.TestWebtree;
import com.thinkgem.jeesite.modules.basic.service.TestWebtreeService;

/**
 * 测试批量删除Controller
 * @author 方坤镇
 * @version 2018-01-10
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/testWebtree")
public class TestWebtreeController extends BaseController {

	@Autowired
	private TestWebtreeService testWebtreeService;
	
	@ModelAttribute
	public TestWebtree get(@RequestParam(required=false) String id) {
		TestWebtree entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = testWebtreeService.get(id);
		}
		if (entity == null){
			entity = new TestWebtree();
		}
		return entity;
	}
	
	@RequiresPermissions("basic:testWebtree:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestWebtree testWebtree, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestWebtree> page = testWebtreeService.findPage(new Page<TestWebtree>(request, response), testWebtree); 
		model.addAttribute("page", page);
		return "modules/basic/testWebtreeList";
	}

	@RequiresPermissions("basic:testWebtree:view")
	@RequestMapping(value = "form")
	public String form(TestWebtree testWebtree, Model model) {
		model.addAttribute("testWebtree", testWebtree);
		return "modules/basic/testWebtreeForm";
	}

	@RequiresPermissions("basic:testWebtree:edit")
	@RequestMapping(value = "save")
	public String save(TestWebtree testWebtree, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testWebtree)){
			return form(testWebtree, model);
		}
		testWebtreeService.save(testWebtree);
		addMessage(redirectAttributes, "保存测试批量删除成功");
		return "redirect:"+Global.getAdminPath()+"/basic/testWebtree/?repage";
	}
	
	@RequiresPermissions("basic:testWebtree:edit")
	@RequestMapping(value = "delete")
	public String delete(TestWebtree testWebtree, RedirectAttributes redirectAttributes) {
		testWebtreeService.delete(testWebtree);
		addMessage(redirectAttributes, "删除测试批量删除成功");
		return "redirect:"+Global.getAdminPath()+"/basic/testWebtree/?repage";
	}


	/**
	 * 批量删除
	 * @param id
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("basic:testWebtree:edit")
	@RequestMapping(value = "deleteSelect")
	public String deleteSelect(String id, Model model, RedirectAttributes redirectAttributes){
		String [] poidArray= id.split(",");
		for (int i = 0;i<poidArray.length;i++){
			TestWebtree testWebtree = testWebtreeService.get(poidArray[i]);
			testWebtreeService.delete(testWebtree);
		}
		addMessage(redirectAttributes,"批量删除成功");
		return "redirect:"+Global.getAdminPath()+"/basic/testWebtree/?repage";
	}

}