package ${package.voPackage};

import java.io.Serializable;
<#list importList as str>
import ${str};
</#list>

<#list importConstraintList as c>
import ${c.packageName}.${c.className};
</#list>

public class ${name}Vo implements Serializable {

    private static final long serialVersionUID = 1L;
    
	<#list allColumnList as field>
    /** ${field['columnComment']} */
    <#if field['identity'] != '1'>
        <#list field['constraintList'] as c>
    ${c.constraint}
        </#list>	    
    </#if>
	private ${field['javaType']} ${field['propertyName']};

	</#list>
	
	<#list allColumnList as field>
    public ${field['javaType']} get${field['propertyName']?cap_first}() {
        return ${field['propertyName']};
    }
    
    public void set${field['propertyName']?cap_first}(${field['javaType']} ${field['propertyName']}) {
        this.${field['propertyName']} = ${field['propertyName']};
    }
	</#list>
}