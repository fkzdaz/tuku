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
import com.thinkgem.jeesite.modules.basic.entity.Services;
import com.thinkgem.jeesite.modules.basic.service.ServicesService;

/**
 * 服务管理Controller
 * @author 方坤镇
 * @version 2018-01-09
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/services")
public class ServicesController extends BaseController {

	@Autowired
	private ServicesService servicesService;
	
	@ModelAttribute
	public Services get(@RequestParam(required=false) String id) {
		Services entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = servicesService.get(id);
		}
		if (entity == null){
			entity = new Services();
		}
		return entity;
	}
	
	@RequiresPermissions("basic:services:view")
	@RequestMapping(value = {"list", ""})
	public String list(Services services, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Services> page = servicesService.findPage(new Page<Services>(request, response), services); 
		model.addAttribute("page", page);
		return "modules/basic/servicesList";
	}

	@RequiresPermissions("basic:services:view")
	@RequestMapping(value = "form")
	public String form(Services services, Model model) {
		model.addAttribute("services", services);
		return "modules/basic/servicesForm";
	}

	@RequiresPermissions("basic:services:edit")
	@RequestMapping(value = "save")
	public String save(Services services, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, services)){
			return form(services, model);
		}
		servicesService.save(services);
		addMessage(redirectAttributes, "保存服务成功");
		return "redirect:"+Global.getAdminPath()+"/basic/services/?repage";
	}
	
	@RequiresPermissions("basic:services:edit")
	@RequestMapping(value = "delete")
	public String delete(Services services, RedirectAttributes redirectAttributes) {
		servicesService.delete(services);
		addMessage(redirectAttributes, "删除服务成功");
		return "redirect:"+Global.getAdminPath()+"/basic/services/?repage";
	}

}