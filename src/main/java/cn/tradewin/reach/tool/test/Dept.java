package cn.tradewin.reach.tool.test;

import java.io.Serializable;
import java.util.Date;

public class Dept implements Serializable {
    private String deptId;

    private String deptName;

    private Date createDate;

    private String sysCreateUserid;

    private String sysCreateIp;

    private String sysCreateDate;

    private String sysUpdateUserid;

    private String sysUpdateIp;

    private String sysUpdateDate;

    private static final long serialVersionUID = 1L;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSysCreateUserid() {
        return sysCreateUserid;
    }

    public void setSysCreateUserid(String sysCreateUserid) {
        this.sysCreateUserid = sysCreateUserid;
    }

    public String getSysCreateIp() {
        return sysCreateIp;
    }

    public void setSysCreateIp(String sysCreateIp) {
        this.sysCreateIp = sysCreateIp;
    }

    public String getSysCreateDate() {
        return sysCreateDate;
    }

    public void setSysCreateDate(String sysCreateDate) {
        this.sysCreateDate = sysCreateDate;
    }

    public String getSysUpdateUserid() {
        return sysUpdateUserid;
    }

    public void setSysUpdateUserid(String sysUpdateUserid) {
        this.sysUpdateUserid = sysUpdateUserid;
    }

    public String getSysUpdateIp() {
        return sysUpdateIp;
    }

    public void setSysUpdateIp(String sysUpdateIp) {
        this.sysUpdateIp = sysUpdateIp;
    }

    public String getSysUpdateDate() {
        return sysUpdateDate;
    }

    public void setSysUpdateDate(String sysUpdateDate) {
        this.sysUpdateDate = sysUpdateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deptId=").append(deptId);
        sb.append(", deptName=").append(deptName);
        sb.append(", createDate=").append(createDate);
        sb.append(", sysCreateUserid=").append(sysCreateUserid);
        sb.append(", sysCreateIp=").append(sysCreateIp);
        sb.append(", sysCreateDate=").append(sysCreateDate);
        sb.append(", sysUpdateUserid=").append(sysUpdateUserid);
        sb.append(", sysUpdateIp=").append(sysUpdateIp);
        sb.append(", sysUpdateDate=").append(sysUpdateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}