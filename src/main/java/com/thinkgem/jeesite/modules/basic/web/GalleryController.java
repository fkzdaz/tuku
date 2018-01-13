/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.basic.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.basic.entity.Comments;
import com.thinkgem.jeesite.modules.basic.entity.GalleryCategory;
import com.thinkgem.jeesite.modules.basic.service.CommentsService;
import com.thinkgem.jeesite.modules.basic.service.GalleryCategoryService;
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
import com.thinkgem.jeesite.modules.basic.entity.Gallery;
import com.thinkgem.jeesite.modules.basic.service.GalleryService;

import java.util.List;

/**
 * 图库管理Controller
 * @author 方坤镇
 * @version 2018-01-11
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/gallery")
public class GalleryController extends BaseController {

	@Autowired
	private GalleryService galleryService;

	@Autowired
	private GalleryCategoryService galleryCategoryService;

	@Autowired
	private CommentsService commentsService;
	
	@ModelAttribute
	public Gallery get(@RequestParam(required=false) String id) {
		Gallery entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = galleryService.get(id);
		}
		if (entity == null){
			entity = new Gallery();
		}
		return entity;
	}
	
	@RequiresPermissions("basic:gallery:view")
	@RequestMapping(value = {"list", ""})
	public String list(Gallery gallery, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Gallery> page = galleryService.findPage(new Page<Gallery>(request, response), gallery);
		List<GalleryCategory> galleryCategoryList = galleryCategoryService.findList(new GalleryCategory());
		List<Gallery> gList= page.getList();

		for(int i = 0;i<gList.size();i++){

			String id = gList.get(i).getId();
			Integer commentsNum =  galleryService.getCommentsNum(id);
			String commentsNumString = Integer.toString(commentsNum);
			gList.get(i).setCommentId(commentsNumString);
		}
		page.setList(gList);
		model.addAttribute("galleryCategoryList",galleryCategoryList);
		model.addAttribute("page", page);
		return "modules/basic/galleryList";
	}

	@RequiresPermissions("basic:gallery:view")
	@RequestMapping(value = "form")
	public String form(Gallery gallery, Model model) {
		List<GalleryCategory> galleryCategoryList = galleryCategoryService.findList(new GalleryCategory());
		model.addAttribute("galleryCategoryList",galleryCategoryList);
		model.addAttribute("gallery", gallery);
		return "modules/basic/galleryForm";
	}

	@RequiresPermissions("basic:gallery:edit")
	@RequestMapping(value = "save")
	public String save(Gallery gallery, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gallery)){
			return form(gallery, model);
		}
		galleryService.save(gallery);
		addMessage(redirectAttributes, "保存图库成功");
		return "redirect:"+Global.getAdminPath()+"/basic/gallery/?repage";
	}
	
	@RequiresPermissions("basic:gallery:edit")
	@RequestMapping(value = "delete")
	public String delete(Gallery gallery, RedirectAttributes redirectAttributes) {
		galleryService.delete(gallery);
		addMessage(redirectAttributes, "删除图库成功");
		return "redirect:"+Global.getAdminPath()+"/basic/gallery/?repage";
	}

	/**
	 * 批量删除
	 * @param id
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("basic:gallery:edit")
	@RequestMapping(value = "deleteSelect")
	public String deleteSelect(String id, Model model, RedirectAttributes redirectAttributes){
		String [] poidArray= id.split(",");
		for (int i = 0;i<poidArray.length;i++){
			Gallery gallery = galleryService.get(poidArray[i]);
			galleryService.delete(gallery);
		}
		addMessage(redirectAttributes,"批量删除成功");
		return "redirect:"+Global.getAdminPath()+"/basic/gallery/?repage";
	}


//	/**
//	 * 获取评论的个数
//	 * @return
//	 */
//	@RequiresPermissions("basic:gallery:view")
//	@RequestMapping(value = "getCommentsNum")
//	public String getCommentsNum(){
//		Gallery gallery = new Gallery();
//		String commentsNum =  galleryService.getCommentsNum();
//		gallery.setCommentId(commentsNum);
//	}

}