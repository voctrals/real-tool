package cn.tradewin.reach.tool.model;

public class TemplateModel implements Cloneable {

    private int templateType;
    private String templateName;
    private String outputPath;
    private String suffix;
    private String extension;
    private String bussinessName;
    private String patternName;
    private boolean commonTemplateFlag = false;

    public TemplateModel() {

    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getBussinessName() {
        return bussinessName;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }

    public int getTemplateType() {
        return templateType;
    }

    public void setTemplateType(int templateType) {
        this.templateType = templateType;
    }

    public boolean isCommonTemplateFlag() {
        return commonTemplateFlag;
    }

    public void setCommonTemplateFlag(boolean commonTemplateFlag) {
        this.commonTemplateFlag = commonTemplateFlag;
    }

    @Override
    public TemplateModel clone() {
        try {
            return (TemplateModel)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
