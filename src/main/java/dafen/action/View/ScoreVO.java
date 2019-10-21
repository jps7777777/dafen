package dafen.action.View;

import java.util.Date;

public class ScoreVO {

    private Integer id;
    private Integer userId;
    private Integer departmentId;
    private String departmentName;
    private int times;
    private Float scord;
    private String reason;
    private String explainInDetail;
    private String createTime;

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
