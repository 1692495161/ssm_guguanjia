package com.cjj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "examine")
public class Examine {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    @Column(name = "examine_user_id")
    private Long examineUserId;

    private Integer score;

    /**
     * 每一道题的分别得分详情            {'1':2;'2':1}
     */
    @Column(name = "score_info")
    private String scoreInfo;

    /**
     * 1、我是产废方            2、我是处置方
     */
    private Integer type;

    /**
     * 数据创建时间,在数据新增时设置
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 数据修改时间,在数据新增时和修改时设置
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    @Column(name = "del_flag")
    private String delFlag;

    @Column(name = "create_by")
    private String createBy;

    @Transient
    private String officeName;
    @Transient
    private String userName;

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return examine_user_id
     */
    public Long getExamineUserId() {
        return examineUserId;
    }

    /**
     * @param examineUserId
     */
    public void setExamineUserId(Long examineUserId) {
        this.examineUserId = examineUserId;
    }

    /**
     * @return score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取每一道题的分别得分详情            {'1':2;'2':1}
     *
     * @return score_info - 每一道题的分别得分详情            {'1':2;'2':1}
     */
    public String getScoreInfo() {
        return scoreInfo;
    }

    /**
     * 设置每一道题的分别得分详情            {'1':2;'2':1}
     *
     * @param scoreInfo 每一道题的分别得分详情            {'1':2;'2':1}
     */
    public void setScoreInfo(String scoreInfo) {
        this.scoreInfo = scoreInfo == null ? null : scoreInfo.trim();
    }

    /**
     * 获取1、我是产废方            2、我是处置方
     *
     * @return type - 1、我是产废方            2、我是处置方
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1、我是产废方            2、我是处置方
     *
     * @param type 1、我是产废方            2、我是处置方
     */
    public void setType(Integer type) {
        this.type = type;
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
        return "Examine{" +
                "id=" + id +
                ", examineUserId=" + examineUserId +
                ", score=" + score +
                ", scoreInfo='" + scoreInfo + '\'' +
                ", type=" + type +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", delFlag='" + delFlag + '\'' +
                ", createBy='" + createBy + '\'' +
                ", officeName='" + officeName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}