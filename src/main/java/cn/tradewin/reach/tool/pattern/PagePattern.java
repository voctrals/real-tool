package cn.tradewin.reach.tool.pattern;

import cn.tradewin.reach.tool.model.PackageModel;
import cn.tradewin.reach.tool.model.TemplateModel;
import cn.tradewin.reach.tool.util.Constant;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class PagePattern {

    public PackageModel packageModel;

    public String bussinessName;

	public String tableName;

    public String domainObjectName;

    public String autoColumnName;

    public boolean createCommonTemplateFlag;

    public boolean writeFileFlag = false;

    public boolean outputSqlPartOnly = false;

    public IntrospectedTable table;

    public Map<String, Field> fieldMap;

    public boolean mapperUsePatternNameFlag = false;

    public String fixPath;

    public String ftlPath;

    private String projectRootPackagePath;

    private String sqlTemplatePath;

    private String daoTemplatePath;

    private String controllerTemplatePath;

    private String serviceTemplatePath;

    private String jspTemplatePath;

    private String formTemplatePath;

    private String voTemplatePath;

    private String validatorTemplatePath;

    private String dtoTemplatePath;

    private String modelTemplatePath;

    public abstract String patternName();

    public void additional(Map<String, Object> input) {

    }

    public boolean check(TemplateModel template) {
        boolean ret = true;
        if (template.isCommonTemplateFlag() && !createCommonTemplateFlag) {
            ret = false;
        }
        return ret;
    }

    public List<TemplateModel> templates() {
        List<TemplateModel> lst = new ArrayList<>();
        lst.add(createTemplateModel(Constant.TemplateType.CONTROLLER, "Controller", getControllerTemplatePath(), true, ".java", false));
        lst.add(createTemplateModel(Constant.TemplateType.FORM, "Form", getFormTemplatePath(), true, ".java", false));
        lst.add(createTemplateModel(Constant.TemplateType.JSP, "Jsp", getJspTemplatePath(), false, ".html", false));
        lst.add(createTemplateModel(Constant.TemplateType.SERVICE, "Service", getServiceTemplatePath(), true, ".java", false));
        // common template
        lst.add(createTemplateModel(Constant.TemplateType.DTO, "Dto", getDtoTemplatePath(), true, ".java", true));
        lst.add(createTemplateModel(Constant.TemplateType.VO, "Vo", getVoTemplatePath(), true, ".java", true));
        lst.add(createTemplateModel(Constant.TemplateType.DAO, "Dao", getDaoTemplatePath(), true, ".java", true));
        lst.add(createTemplateModel(Constant.TemplateType.MODEL, "Model", getModelTemplatePath(), false, ".java", true));
        lst.add(createTemplateModel(Constant.TemplateType.MAPPER, "Mapper", getSqlTemplatePath(), true, ".xml", true));
        lst.add(createTemplateModel(Constant.TemplateType.VALIDATOR, "Validator", getValidatorTemplatePath(), true, ".java", true));
        return lst;
    }

    public TemplateModel createTemplateModel(int templateType, String templateName, String outputPath, boolean useSuffix, String extension, boolean commonTemplateFlag) {
        TemplateModel template = new TemplateModel();
        template.setTemplateType(templateType);
        template.setTemplateName(templateName);
        template.setOutputPath(outputPath);
        if (useSuffix) {
            template.setSuffix(templateName);
        } else {
            template.setSuffix("");
        }
        template.setCommonTemplateFlag(commonTemplateFlag);
        template.setExtension(extension);
        return template;
    }

    public String getSqlTemplatePath() {
        if (StringUtils.isEmpty(sqlTemplatePath)) {
            sqlTemplatePath = packageModel.rootPath + "/src/main/resources/sql/";
        }
        return sqlTemplatePath;
    }

    public void setSqlTemplatePath(String sqlTemplatePath) {
        this.sqlTemplatePath = sqlTemplatePath;
    }

    public String getDaoTemplatePath() {
        if (StringUtils.isEmpty(daoTemplatePath)) {
            daoTemplatePath = packageModel.rootPath + "/src/main/java/" + getProjectRootPackagePath() + "/dao/";
        }
        return daoTemplatePath;
    }

    public void setDaoTemplatePath(String daoTemplatePath) {
        this.daoTemplatePath = daoTemplatePath;
    }

    public String getControllerTemplatePath() {
        if (StringUtils.isEmpty(controllerTemplatePath)) {
            controllerTemplatePath = packageModel.rootPath + "/src/main/java/" + getProjectRootPackagePath() + "/web/controller/";
        }
        return controllerTemplatePath;
    }

    public void setControllerTemplatePath(String controllerTemplatePath) {
        this.controllerTemplatePath = controllerTemplatePath;
    }

    public String getServiceTemplatePath() {
        if (StringUtils.isEmpty(serviceTemplatePath)) {
            serviceTemplatePath = packageModel.rootPath + "/src/main/java/" + getProjectRootPackagePath() + "/service/";
        }
        return serviceTemplatePath;
    }

    public void setServiceTemplatePath(String serviceTemplatePath) {
        this.serviceTemplatePath = serviceTemplatePath;
    }

    public String getJspTemplatePath() {
        if (StringUtils.isEmpty(jspTemplatePath)) {
            jspTemplatePath = packageModel.rootPath + "/src/main/webapp/WEB-INF/views/";
        }
        return jspTemplatePath;
    }

    public void setJspTemplatePath(String jspTemplatePath) {
        this.jspTemplatePath = jspTemplatePath;
    }

    public String getDtoTemplatePath() {
        if (StringUtils.isEmpty(dtoTemplatePath)) {
            dtoTemplatePath = packageModel.rootPath + "/src/main/java/" + getProjectRootPackagePath() + "/service/dto/";
        }
        return dtoTemplatePath;
    }

    public void setDtoTemplatePath(String dtoTemplatePath) {
        this.dtoTemplatePath = dtoTemplatePath;
    }

    public String getModelTemplatePath() {
        if (StringUtils.isEmpty(modelTemplatePath)) {
            modelTemplatePath = packageModel.rootPath + "/src/main/java/" + getProjectRootPackagePath() + "/dao/entity/";
        }
        return modelTemplatePath;
    }

    public void setModelTemplatePath(String modelTemplatePath) {
        this.modelTemplatePath = modelTemplatePath;
    }


    public String getFormTemplatePath() {
        if (StringUtils.isEmpty(formTemplatePath)) {
            formTemplatePath = packageModel.rootPath + "/src/main/java/" + getProjectRootPackagePath() + "/web/form/";
        }
        return formTemplatePath;
    }

    public String getVoTemplatePath() {
        if (StringUtils.isEmpty(voTemplatePath)) {
            voTemplatePath = packageModel.rootPath + "/src/main/java/" + getProjectRootPackagePath() + "/web/form/vo/";
        }
        return voTemplatePath;
    }

    public String getValidatorTemplatePath() {
        if (StringUtils.isEmpty(validatorTemplatePath)) {
            validatorTemplatePath = packageModel.rootPath + "/src/main/java/" + getProjectRootPackagePath() + "/web/form/validator/";
        }
        return validatorTemplatePath;
    }

    public String getProjectRootPackagePath() {
        if (StringUtils.isEmpty(projectRootPackagePath)) {
            projectRootPackagePath = StringUtils.replaceChars(packageModel.projectRootPackage, ".", "/");
        }

        return projectRootPackagePath;
    }


}
