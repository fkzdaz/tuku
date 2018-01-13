/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.basic.entity.Services;
import com.thinkgem.jeesite.modules.basic.entity.Users;
import com.thinkgem.jeesite.modules.basic.service.ServicesService;
import com.thinkgem.jeesite.modules.basic.service.UsersService;
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
import com.thinkgem.jeesite.modules.basic.entity.Orders;
import com.thinkgem.jeesite.modules.basic.service.OrdersService;

import java.util.List;

/**
 * 订单表Controller
 * @author 方坤镇
 * @version 2018-01-10
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/orders")
public class OrdersController extends BaseController {

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private ServicesService servicesService;
	
	@ModelAttribute
	public Orders get(@RequestParam(required=false) String id) {
		Orders entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ordersService.get(id);
		}
		if (entity == null){
			entity = new Orders();
		}
		return entity;
	}
	
	@RequiresPermissions("basic:orders:view")
	@RequestMapping(value = {"list", ""})
	public String list(Orders orders, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Orders> page = ordersService.findPage(new Page<Orders>(request, response), orders);
		List<Users> usersList = usersService.findList(new Users());
		List<Services> servicesList = servicesService.findList(new Services());
		model.addAttribute("usersList",usersList);
		model.addAttribute("servicesList",servicesList);
		model.addAttribute("page", page);
		return "modules/basic/ordersList";
	}

	@RequiresPermissions("basic:orders:view")
	@RequestMapping(value = "form")
	public String form(Orders orders, Model model) {
		List<Users> usersList = usersService.findList(new Users());
		List<Services> servicesList = servicesService.findList(new Services());
		model.addAttribute("usersList",usersList);
		model.addAttribute("servicesList",servicesList);
		model.addAttribute("orders", orders);
		return "modules/basic/ordersForm";
	}

	@RequiresPermissions("basic:orders:edit")
	@RequestMapping(value = "save")
	public String save(Orders orders, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orders)){
			return form(orders, model);
		}
		ordersService.save(orders);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/basic/orders/?repage";
	}
	
	@RequiresPermissions("basic:orders:edit")
	@RequestMapping(value = "delete")
	public String delete(Orders orders, RedirectAttributes redirectAttributes) {
		ordersService.delete(orders);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/basic/orders/?repage";
	}

}