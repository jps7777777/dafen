package dafen.service.model;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ScoreModel {

    private Integer id;

    /**
     *
     */
    @NotNull
    private Integer userId;
    /**
     *
     */
    @NotNull
    private Integer departmentId;
    /**
     *
     */
    @NotNull
    private Float scord;
    /**
     *
     */
    @NotNull
    private String reason;
    /**
     *
     */
    private String explainInDetail;

    /**
     */
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Float getScord() {
        return scord;
    }

    public void setScord(Float scord) {
        this.scord = scord;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getExplainInDetail() {
        return explainInDetail;
    }

    public void setExplainInDetail(String explainInDetail) {
        this.explainInDetail = explainInDetail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
