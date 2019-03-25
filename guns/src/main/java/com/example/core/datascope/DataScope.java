package com.example.core.datascope;

import java.util.List;

/**
 * 数据范围
 * @author DINGJUN
 * @date 2019.03.13
 */
public class DataScope {

    /**
     * 限制范围的字段名称
     */
    private String scopeName = "deptid";

    /**
     * 具体的数据范围
     */
    private List<Long> deptIds;

    public DataScope() {
    }

    public DataScope(List<Long> deptIds) {
        this.deptIds = deptIds;
    }

    public DataScope(String scopeName, List<Long> deptIds) {
        this.scopeName = scopeName;
        this.deptIds = deptIds;
    }

    public List<Long> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Long> deptIds) {
        this.deptIds = deptIds;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

}