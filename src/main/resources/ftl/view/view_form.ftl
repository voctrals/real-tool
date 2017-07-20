package ${package.formPackage};

<#list allColumnList as field>
	<#if field['javaPackageName'] != 'java.lang'>
import ${field['javaPackageName']}.${field['javaType']};
	</#if>
</#list>

import java.util.List;
import ${package.voPackage}.${name}Vo;
import ${package.dtoPackage}.${name}Dto;

public class ${bussinessName}Form extends BaseForm {

	public final static String BUSSINESS_NAME = "${bussinessName}";

    private ${name}Vo condition;
    
    private List<${name}Dto> ${name?uncap_first}List;

	public ${name}Vo getCondition() {
		return condition;
	}

	public void setCondition(${name}Vo condition) {
		this.condition = condition;
	}

	public List<${name}Dto> get${name}List() {
		return ${name?uncap_first}List;
	}

	public void set${name}List(List<${name}Dto> ${name?uncap_first}List) {
		this.${name?uncap_first}List = ${name?uncap_first}List;
	}

    public String getBussinessName() {
        return BUSSINESS_NAME;
    }
}