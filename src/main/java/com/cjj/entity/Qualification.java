package com.cjj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "qualification")
public class Qualification {
    @Id
    private Long id;

    @Column(name = "upload_user_id")
    private Long uploadUserId;

    /**
     * 1、我是产废方            2、我是运输方            3、我是处置方
     */
    private Integer type;

    private String address;

    /**
     * 0未审核            1通过审核            2审核失败
     */
    @Column(name = "`check`")
    private Integer check;

    private String description;

    @Column(name = "check_user_id")
    private Long checkUserId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_date")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    @Column(name = "del_flag")
    private String delFlag;

    @Column(name = "create_by")
    private String createBy;

    /*根据值对象需要的字段提供封装属性*/
    @Transient   //自动生成sql语句的时候  该注解对应字段忽略生成
    private String uploadName ;
    @Transient
    private String checkName;

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return upload_user_id
     */
    public Long getUploadUserId() {
        return uploadUserId;
    }

    /**
     * @param uploadUserId
     */
    public void setUploadUserId(Long uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    /**
     * 获取1、我是产废方            2、我是运输方            3、我是处置方
     *
     * @return type - 1、我是产废方            2、我是运输方            3、我是处置方
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1、我是产废方            2、我是运输方            3、我是处置方
     *
     * @param type 1、我是产废方            2、我是运输方            3、我是处置方
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取0未审核            1通过审核            2审核失败
     *
     * @return check - 0未审核            1通过审核            2审核失败
     */
    public Integer getCheck() {
        return check;
    }

    /**
     * 设置0未审核            1通过审核            2审核失败
     *
     * @param check 0未审核            1通过审核            2审核失败
     */
    public void setCheck(Integer check) {
        this.check = check;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * @return check_user_id
     */
    public Long getCheckUserId() {
        return checkUserId;
    }

    /**
     * @param checkUserId
     */
    public void setCheckUserId(Long checkUserId) {
        this.checkUserId = checkUserId;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return update_date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取删除标记（0：正常；1：删除；2：审核；）
     *
     * @return del_flag - 删除标记（0：正常；1：删除；2：审核；）
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标记（0：正常；1：删除；2：审核；）
     *
     * @param delFlag 删除标记（0：正常；1：删除；2：审核；）
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * @return create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    @Override
    public String toString() {
        return "Qualification{" +
                "id=" + id +
                ", uploadUserId=" + uploadUserId +
                ", type=" + type +
                ", address='" + address + '\'' +
                ", check=" + check +
                ", description='" + description + '\'' +
                ", checkUserId=" + checkUserId +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", delFlag='" + delFlag + '\'' +
                ", createBy='" + createBy + '\'' +
                ", uploadName='" + uploadName + '\'' +
                ", checkName='" + checkName + '\'' +
                '}';
    }
}