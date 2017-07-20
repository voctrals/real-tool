package cn.tradewin.reach.tool.test;

import java.io.Serializable;

public class CodeTableKey implements Serializable {
    private String signature;

    private String code;

    private String language;

    private static final long serialVersionUID = 1L;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", signature=").append(signature);
        sb.append(", code=").append(code);
        sb.append(", language=").append(language);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}