/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.basic.entity.TestWebtree;
import com.thinkgem.jeesite.modules.basic.entity.Users;
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
import com.thinkgem.jeesite.modules.basic.entity.Comments;
import com.thinkgem.jeesite.modules.basic.service.CommentsService;

import java.util.List;

/**
 * 评论表Controller
 * @author 方坤镇
 * @version 2018-01-10
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/comments")
public class CommentsController extends BaseController {

	@Autowired
	private CommentsService commentsService;

	@Autowired
	private UsersService usersService;
	
	@ModelAttribute
	public Comments get(@RequestParam(required=false) String id) {
		Comments entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = commentsService.get(id);
		}
		if (entity == null){
			entity = new Comments();
		}
		return entity;
	}
	
	@RequiresPermissions("basic:comments:view")
	@RequestMapping(value = {"list", ""})
	public String list(Comments comments, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Comments> page = commentsService.findPage(new Page<Comments>(request, response), comments);
		List<Users> usersList = usersService.findList(new Users());
		model.addAttribute("usersList",usersList);
		model.addAttribute("page", page);
		return "modules/basic/commentsList";
	}

	@RequiresPermissions("basic:comments:view")
	@RequestMapping(value = "form")
	public String form(Comments comments, Model model) {
		List<Users> usersList = usersService.findList(new Users());
		model.addAttribute("usersList",usersList);
		model.addAttribute("comments", comments);
		return "modules/basic/commentsForm";
	}

	@RequiresPermissions("basic:comments:edit")
	@RequestMapping(value = "save")
	public String save(Comments comments, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comments)){
			return form(comments, model);
		}
		commentsService.save(comments);
		addMessage(redirectAttributes, "保存评论成功");
		return "redirect:"+Global.getAdminPath()+"/basic/comments/?repage";
	}
	
	@RequiresPermissions("basic:comments:edit")
	@RequestMapping(value = "delete")
	public String delete(Comments comments, RedirectAttributes redirectAttributes) {
		commentsService.delete(comments);
		addMessage(redirectAttributes, "删除评论成功");
		return "redirect:"+Global.getAdminPath()+"/basic/comments/?repage";
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
			Comments comments = commentsService.get(poidArray[i]);
			commentsService.delete(comments);
		}
		addMessage(redirectAttributes,"批量删除成功");
		return "redirect:"+Global.getAdminPath()+"/basic/comments/?repage";
	}

}