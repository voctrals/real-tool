package cn.tradewin.reach.tool.model;

public class ConstraintModel {
    
    private String className;
    
    private String packageName;
    
    private String constraint;

    public ConstraintModel(String className, String packageName) {
        this(className, packageName, "");
    }
    
    public ConstraintModel(String className, String packageName, String constraint) {
        this.className = className;
        this.packageName = packageName;
        this.constraint = constraint;
    }
    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }
    
}
