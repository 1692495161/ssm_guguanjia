package com.cjj.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_log")
public class SysLog {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 日志类型
     */
    private String type;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 操作IP地址
     */
    @Column(name = "remote_addr")
    private String remoteAddr;

    /**
     * 用户代理
     */
    @Column(name = "user_agent")
    private String userAgent;

    /**
     * 请求URI
     */
    @Column(name = "request_uri")
    private String requestUri;

    /**
     * 操作方式
     */
    private String method;

    private String params;

    private String exception;

    private String description;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取日志类型
     *
     * @return type - 日志类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置日志类型
     *
     * @param type 日志类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取创建者
     *
     * @return create_by - 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建者
     *
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取操作IP地址
     *
     * @return remote_addr - 操作IP地址
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }

    /**
     * 设置操作IP地址
     *
     * @param remoteAddr 操作IP地址
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr == null ? null : remoteAddr.trim();
    }

    /**
     * 获取用户代理
     *
     * @return user_agent - 用户代理
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * 设置用户代理
     *
     * @param userAgent 用户代理
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * 获取请求URI
     *
     * @return request_uri - 请求URI
     */
    public String getRequestUri() {
        return requestUri;
    }

    /**
     * 设置请求URI
     *
     * @param requestUri 请求URI
     */
    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri == null ? null : requestUri.trim();
    }

    /**
     * 获取操作方式
     *
     * @return method - 操作方式
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置操作方式
     *
     * @param method 操作方式
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", remoteAddr='" + remoteAddr + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", requestUri='" + requestUri + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", exception='" + exception + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}