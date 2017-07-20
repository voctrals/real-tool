package ${package.modelPackage};

import java.io.Serializable;
<#list importList as str>
import ${str};
</#list>

public class ${name} extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    <#list allColumnList as field>
        <#if !field['sysFieldFlag'] >
    /** ${field['columnComment']} */
    private ${field['javaType']} ${field['propertyName']};
        </#if>
    </#list>
    
	<#list allColumnList as field>
        <#if !field['sysFieldFlag'] >
    public ${field['javaType']} get${field['propertyName']?cap_first}() {
        return ${field['propertyName']};
    }
    
    public void set${field['propertyName']?cap_first}(${field['javaType']} ${field['propertyName']}) {
        this.${field['propertyName']} = ${field['propertyName']};
    }
        </#if>
	</#list>
}