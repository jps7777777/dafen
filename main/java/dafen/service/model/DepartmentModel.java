package dafen.service.model;

import javax.validation.constraints.NotNull;

public class DepartmentModel {

    private Integer id;
    @NotNull
    private String dName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }
}
