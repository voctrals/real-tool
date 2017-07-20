package cn.tradewin.reach.tool.test;

import java.io.Serializable;

public class Test2 extends Test2Key implements Serializable {
    private String tname;

    private static final long serialVersionUID = 1L;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tname=").append(tname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}