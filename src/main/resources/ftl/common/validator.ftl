package ${package.validatorPackage};

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;

import ${package.formPackage}.${bussinessName}Form;
import ${package.voPackage}.${name}Vo;
import ${package.projectRootPackage}.web.util.MessagesUtils;

public class ${bussinessName}Validator extends BaseValidator {

    public ${bussinessName}Validator(MessageSource messageSource) {
		super(messageSource);
	}

	@Override
	public boolean supports(Class<?> clazz) {
        return ${bussinessName}Form.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
        ${bussinessName}Form form = (${bussinessName}Form) target;
	    ${name}Vo vo = form.${name?uncap_first}Vo;
        <#list allColumnList as field>
        <#if field['jdbcType'] == '12'>
		if (StringUtils.isEmpty(vo.get${field['propertyName']?cap_first}())) {
            Object[] errorArgs = new Object[]{MessagesUtils.get(messageSource, "${name?lower_case}.label.${field['propertyName']}")};
            errors.rejectValue("${name?uncap_first}Vo.${field['propertyName']}", "common.message.error.notempty", errorArgs, null);
        } else if (vo.get${field['propertyName']?cap_first}().getBytes().length > ${field['length']}) {
            Object[] errorArgs = new Object[]{MessagesUtils.get(messageSource, "${name?lower_case}.label.${field['propertyName']}"), String.valueOf(${field['length']})};
            errors.rejectValue("${name?uncap_first}Vo.${field['propertyName']}", "common.message.error.maxlength", errorArgs, null);
        }
        </#if>
        </#list>
	}

}
