/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.basic.entity.Comments;
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
import com.thinkgem.jeesite.modules.basic.entity.GalleryCategory;
import com.thinkgem.jeesite.modules.basic.service.GalleryCategoryService;

/**
 * 图库分类管理Controller
 * @author 方坤镇
 * @version 2018-01-11
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/galleryCategory")
public class GalleryCategoryController extends BaseController {

	@Autowired
	private GalleryCategoryService galleryCategoryService;
	
	@ModelAttribute
	public GalleryCategory get(@RequestParam(required=false) String id) {
		GalleryCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = galleryCategoryService.get(id);
		}
		if (entity == null){
			entity = new GalleryCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("basic:galleryCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(GalleryCategory galleryCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GalleryCategory> page = galleryCategoryService.findPage(new Page<GalleryCategory>(request, response), galleryCategory); 
		model.addAttribute("page", page);
		return "modules/basic/galleryCategoryList";
	}

	@RequiresPermissions("basic:galleryCategory:view")
	@RequestMapping(value = "form")
	public String form(GalleryCategory galleryCategory, Model model) {
		model.addAttribute("galleryCategory", galleryCategory);
		return "modules/basic/galleryCategoryForm";
	}

	@RequiresPermissions("basic:galleryCategory:edit")
	@RequestMapping(value = "save")
	public String save(GalleryCategory galleryCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, galleryCategory)){
			return form(galleryCategory, model);
		}
		galleryCategoryService.save(galleryCategory);
		addMessage(redirectAttributes, "保存图库分类成功");
		return "redirect:"+Global.getAdminPath()+"/basic/galleryCategory/?repage";
	}
	
	@RequiresPermissions("basic:galleryCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(GalleryCategory galleryCategory, RedirectAttributes redirectAttributes) {
		galleryCategoryService.delete(galleryCategory);
		addMessage(redirectAttributes, "删除图库分类成功");
		return "redirect:"+Global.getAdminPath()+"/basic/galleryCategory/?repage";
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
			GalleryCategory galleryCategory = galleryCategoryService.get(poidArray[i]);
			galleryCategoryService.delete(galleryCategory);
		}
		addMessage(redirectAttributes,"批量删除成功");
		return "redirect:"+Global.getAdminPath()+"/basic/galleryCategory/?repage";
	}

}