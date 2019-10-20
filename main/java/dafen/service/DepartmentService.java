package dafen.service;

import dafen.exception.FinallyException;
import dafen.service.model.DepartmentModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    void addDepartment(DepartmentModel departmentModel) throws FinallyException;

    DepartmentModel getDepartmentById(int id) throws FinallyException;

    List<DepartmentModel> getDepartments() throws FinallyException;

}
