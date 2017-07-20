package cn.tradewin.reach.tool.model;

public class TableInfoModel {
	
	private String tableName;
	
	private String domainObjectName;

	public TableInfoModel(String tableName, String domainObjectName) {
		this.tableName = tableName;
		this.domainObjectName = domainObjectName;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDomainObjectName() {
		return domainObjectName;
	}

	public void setDomainObjectName(String domainObjectName) {
		this.domainObjectName = domainObjectName;
	}
	
}
