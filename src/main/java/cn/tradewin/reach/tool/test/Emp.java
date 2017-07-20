package cn.tradewin.reach.tool.test;

import java.io.Serializable;
import java.util.Date;

public class Emp implements Serializable {
    private String empId;

    private String empName;

    private Date joinDate;

    private String sysCreateUserid;

    private String sysCreateIp;

    private String sysCreateDate;

    private String sysUpdateUserid;

    private String sysUpdateIp;

    private String sysUpdateDate;

    private static final long serialVersionUID = 1L;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
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
        sb.append(", empId=").append(empId);
        sb.append(", empName=").append(empName);
        sb.append(", joinDate=").append(joinDate);
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