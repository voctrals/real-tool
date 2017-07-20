package ${package.controllerPackage};

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ${package.servicePackage}.${bussinessName}Service;
import ${package.dtoPackage}.${name}Dto;
import ${package.formPackage}.${bussinessName}Form;
import ${package.validatorPackage}.${bussinessName}Validator;
import ${package.voPackage}.${name}Vo;

import cn.tradewin.reach.web.util.BeanMapper;

@Controller
@RequestMapping("/" + ${bussinessName}Form.BUSSINESS_NAME)
public class ${bussinessName}Controller extends BaseController {

    @Autowired
    private ${bussinessName}Service ${bussinessName?uncap_first}Service;

    @InitBinder
    public void initBinder(DataBinder binder) {
        binder.setValidator(new ${bussinessName}Validator(messageSource));
    }

    @RequestMapping()
    public ModelAndView initAdd() {
        ModelAndView mav = new ModelAndView();

        ${bussinessName}Form form = new ${bussinessName}Form();
        mav.addObject("form", form);
        mav.setViewName(getPageName());

        return mav;
    }

    @RequestMapping("/<#list primaryKeyColumnWithSysFieldList as field>${"{"}${field['propertyName']}${"}"}<#if field_has_next>/</#if></#list>")
    public ModelAndView initUpdate(<#list primaryKeyColumnWithSysFieldList as field>@PathVariable String ${field['propertyName']}<#if field_has_next>, </#if></#list>) {
        ModelAndView mav = new ModelAndView();

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
        ${name}Dto dto = ${bussinessName?uncap_first}Service.selectByPrimaryKey(condition);

        ${bussinessName}Form form = new ${bussinessName}Form();
        form.${name?uncap_first}Vo = BeanMapper.map(dto, ${name}Vo.class);
        form.updatePageFlag = true;

        mav.addObject("form", form);
        mav.setViewName(getPageName());

        return mav;
    }

    @RequestMapping(value = "/save")
    public ModelAndView save(@ModelAttribute("form") @Valid ${bussinessName}Form form, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.setViewName(getPageName());
        } else {
            ${name}Dto dto = BeanMapper.map(form.${name?uncap_first}Vo, ${name}Dto.class);
            setSysFieldInfo(dto, request);
            if (form.isUpdatePageFlag()) {
                ${bussinessName?uncap_first}Service.update(dto, bindingResult);
            } else {
                ${bussinessName?uncap_first}Service.insert(dto, bindingResult);
            }

            mav.setViewName("redirect:/${name}View");
        }
        return mav;
    }

    @Override
    public String getPageName() {
        return "${htmlName}";
    }
}
