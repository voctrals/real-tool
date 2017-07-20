package cn.tradewin.reach.tool.model;

import org.apache.commons.lang3.StringUtils;

public class PackageModel {
    public String rootPath;
    public String projectRootPackage;
    public String dtoPackage;
    public String servicePackage;
    public String daoPackage;
    public String modelPackage;
    public String controllerPackage;
    public String formPackage;
    public String voPackage;
    public String validatorPackage;

    public PackageModel(String rootPath, String projectRootPackage) {
        this.rootPath = rootPath;
        this.projectRootPackage = projectRootPackage;

        if (StringUtils.isEmpty(this.servicePackage)) {
            this.servicePackage = this.projectRootPackage + ".service";
        }
        if (StringUtils.isEmpty(this.dtoPackage)) {
            this.dtoPackage = this.projectRootPackage + ".service.dto";
        }
        if (StringUtils.isEmpty(this.daoPackage)) {
            this.daoPackage = this.projectRootPackage + ".dao";
        }
        if (StringUtils.isEmpty(this.modelPackage)) {
            this.modelPackage = this.projectRootPackage + ".dao.entity";
        }
        if (StringUtils.isEmpty(this.controllerPackage)) {
            this.controllerPackage = this.projectRootPackage + ".web.controller";
        }
        if (StringUtils.isEmpty(this.formPackage)) {
            this.formPackage = this.projectRootPackage + ".web.form";
        }
        if (StringUtils.isEmpty(this.validatorPackage)) {
            this.validatorPackage = this.projectRootPackage + ".web.form.validator";
        }
        if (StringUtils.isEmpty(this.voPackage)) {
            this.voPackage = this.projectRootPackage + ".web.form.vo";
        }
    }

    public String getRootPath() {
        return rootPath;
    }

    public String getProjectRootPackage() {
        return projectRootPackage;
    }

    public String getDtoPackage() {
        return dtoPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public String getFormPackage() {
        return formPackage;
    }

    public String getVoPackage() {
        return voPackage;
    }

    public String getValidatorPackage() {
        return validatorPackage;
    }

}
