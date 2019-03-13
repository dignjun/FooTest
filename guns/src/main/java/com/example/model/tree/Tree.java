package com.example.model.tree;

import java.util.List;

/**
 * 构造树节点的接口规范
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
public interface Tree {

    /**
     * 获取节点id
     */
    String getNodeId();

    /**
     * 获取节点父id
     */
    String getNodeParentId();

    /**
     * 设置children
     */
    void setChildrenNodes(List childrenNodes);
}
