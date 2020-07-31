package com.cjj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "app_version")
public class AppVersion {
    @KeySql(useGeneratedKeys = true) //将主键的自增长照值加入属性
    @Id  //声明主键
    private Long id;

    /**
     * APP平台;0:Android;1:IOS
     */
    private Integer platform;

    @Column(name = "version_no")
    private String versionNo;

    /**
     * 强制更新,0:否;1：是；默认1
     */
    @Column(name = "force_update")
    private Integer forceUpdate;

    @Column(name = "down_path")
    private String downPath;

    /**
     * 安装包大小
     */
    private Float size;

    @Column(name = "app_explain")
    private String appExplain;

    /**
     * 数据创建时间,在数据新增时设置
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 数据修改时间,在数据新增时和修改时设置
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    @Column(name = "del_flag")
    private String delFlag;

    /**
     * 数据创建用户,默认sys
     */
    @Column(name = "create_by")
    private String createBy;

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
     * 获取APP平台;0:Android;1:IOS
     *
     * @return platform - APP平台;0:Android;1:IOS
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * 设置APP平台;0:Android;1:IOS
     *
     * @param platform APP平台;0:Android;1:IOS
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * @return version_no
     */
    public String getVersionNo() {
        return versionNo;
    }

    /**
     * @param versionNo
     */
    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo == null ? null : versionNo.trim();
    }

    /**
     * 获取强制更新,0:否;1：是；默认1
     *
     * @return force_update - 强制更新,0:否;1：是；默认1
     */
    public Integer getForceUpdate() {
        return forceUpdate;
    }

    /**
     * 设置强制更新,0:否;1：是；默认1
     *
     * @param forceUpdate 强制更新,0:否;1：是；默认1
     */
    public void setForceUpdate(Integer forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    /**
     * @return down_path
     */
    public String getDownPath() {
        return downPath;
    }

    /**
     * @param downPath
     */
    public void setDownPath(String downPath) {
        this.downPath = downPath == null ? null : downPath.trim();
    }

    /**
     * 获取安装包大小
     *
     * @return size - 安装包大小
     */
    public Float getSize() {
        return size;
    }

    /**
     * 设置安装包大小
     *
     * @param size 安装包大小
     */
    public void setSize(Float size) {
        this.size = size;
    }

    /**
     * @return app_explain
     */
    public String getAppExplain() {
        return appExplain;
    }

    /**
     * @param appExplain
     */
    public void setAppExplain(String appExplain) {
        this.appExplain = appExplain == null ? null : appExplain.trim();
    }

    /**
     * 获取数据创建时间,在数据新增时设置
     *
     * @return create_date - 数据创建时间,在数据新增时设置
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置数据创建时间,在数据新增时设置
     *
     * @param createDate 数据创建时间,在数据新增时设置
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取数据修改时间,在数据新增时和修改时设置
     *
     * @return update_date - 数据修改时间,在数据新增时和修改时设置
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置数据修改时间,在数据新增时和修改时设置
     *
     * @param updateDate 数据修改时间,在数据新增时和修改时设置
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
     * 获取数据创建用户,默认sys
     *
     * @return create_by - 数据创建用户,默认sys
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置数据创建用户,默认sys
     *
     * @param createBy 数据创建用户,默认sys
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    @Override
    public String toString() {
        return "AppVersion{" +
                "id=" + id +
                ", platform=" + platform +
                ", versionNo='" + versionNo + '\'' +
                ", forceUpdate=" + forceUpdate +
                ", downPath='" + downPath + '\'' +
                ", size=" + size +
                ", appExplain='" + appExplain + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", delFlag='" + delFlag + '\'' +
                ", createBy='" + createBy + '\'' +
                '}';
    }
}