package ${package.formPackage};

import javax.validation.Valid;

import ${package.voPackage}.${name}Vo;

public class ${bussinessName}Form extends BaseEditForm {

public final static String BUSSINESS_NAME = "${bussinessName}";

    @Valid
    public ${name}Vo ${name?uncap_first}Vo = new ${name}Vo();

    public String editFlag;

    public ${name}Vo get${name}Vo() {
        return ${name?uncap_first}Vo;
    }

    public void set${name}Vo(${name}Vo ${name?uncap_first}Vo) {
        this.${name?uncap_first}Vo = ${name?uncap_first}Vo;
    }
}