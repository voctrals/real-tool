package ${package.controllerPackage};

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ${package.servicePackage}.${bussinessName}Service;
import ${package.dtoPackage}.${name}Dto;
import ${package.formPackage}.${bussinessName}Form;
import ${package.voPackage}.${name}Vo;

<#if hasPagination>
import cn.tradewin.reach.web.pagination.Pager;
</#if>
import cn.tradewin.reach.web.util.BeanMapper;

@Controller
@RequestMapping("/" + ${bussinessName}Form.BUSSINESS_NAME)
public class ${bussinessName}Controller extends BaseController {

    @Autowired
    private ${bussinessName}Service ${bussinessName?uncap_first}Service;
    
    @RequestMapping("")
    public ModelAndView init() {
    	${bussinessName}Form form = new ${bussinessName}Form();
        return searchData(form);
    }
    
    @RequestMapping(value = "/search")
    public ModelAndView search(@ModelAttribute("form") @Valid ${bussinessName}Form form, HttpServletRequest request) {
    	ModelAndView mav = searchData(form);
    	if (!form.get${name}List().isEmpty()) {
    		request.getSession().setAttribute(getPageName() + "_condition", form.getCondition());
    	}
    	return mav;
    }

    <#if hasPagination>
    @RequestMapping("/page/{page}")
    public ModelAndView turnPage(@PathVariable int page, HttpServletRequest request) {
        ${bussinessName}Form form = new ${bussinessName}Form();
    	
    	${name}Vo condition = (${name}Vo)request.getSession().getAttribute(getPageName() + "_condition");
    	if (condition != null) {
    		form.setCondition(condition);
    	}
        form.getPager().page = page;
        return searchData(form);
    }
    </#if>

    @RequestMapping("/delete/<#list primaryKeyColumnWithSysFieldList as field>${"{"}${field['propertyName']}${"}"}<#if field_has_next>/</#if></#list>")
    public ModelAndView delete(<#list primaryKeyColumnWithSysFieldList as field>@PathVariable String ${field['propertyName']}<#if field_has_next>, </#if></#list>) {
        ${name}Dto condition = new ${name}Dto();
        
        <#list primaryKeyColumnWithSysFieldList as field>
            <#if field['javaType'] == 'Integer'>
        condition.set${field['propertyName']?cap_first}(Integer.parseInt(${field['propertyName']}));
            <#elseif field['javaType'] == 'BigDecimal'>
        condition.set${field['propertyName']?cap_first}(new java.math.BigDecimal(${field['propertyName']}));
            <#elseif field['javaType'] == 'Long'>
        condition.set${field['propertyName']?cap_first}(Long.parseLong(${field['propertyName']}));
            <#else>
        condition.set${field['propertyName']?cap_first}(${field['propertyName']});
            </#if>
        </#list>

        ${bussinessName?uncap_first}Service.delete(condition);

        return new ModelAndView("redirect:/" + ${bussinessName}Form.BUSSINESS_NAME);
    }

    private ModelAndView searchData(${bussinessName}Form form) {
    	ModelAndView mav = new ModelAndView();
        mav.setViewName(getPageName());

        ${name}Dto condition = BeanMapper.map(form.getCondition(), ${name}Dto.class);

        <#if hasPagination>
        List<${name}Dto> list = ${bussinessName?uncap_first}Service.selectAllList(condition, form.getPager());
        <#else>
            List<${name}Dto> list = ${bussinessName?uncap_first}Service.selectAllList(condition, null);
        </#if>

    form.set${name}List(list);
        
        mav.addObject("form", form);
        
    	return mav;
    }

	@Override
	public String getPageName() {
		return "${htmlName}";
	}
	
}
