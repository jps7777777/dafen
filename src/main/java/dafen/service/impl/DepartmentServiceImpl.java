package dafen.service.impl;

import dafen.bean.DepartmentDO;
import dafen.dao.DepartmentDOMapper;
import dafen.exception.EnumException;
import dafen.exception.FinallyException;
import dafen.service.DepartmentService;
import dafen.service.model.DepartmentModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDOMapper departmentDOMapper;

    @Override
    public void addDepartment(DepartmentModel departmentModel) throws FinallyException {
        if (departmentModel == null) {
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }
        DepartmentDO departmentDO = convertByModel(departmentModel);
        departmentDOMapper.insertSelective(departmentDO);
        if (departmentDO.getId() < 0) {
            throw new FinallyException(EnumException.DATA_INSERT_ERROR);
        }
    }

    @Override
    public DepartmentModel getDepartmentById(int id) throws FinallyException {
        if (id < 1) {
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }

        DepartmentDO departmentDO = departmentDOMapper.selectByPrimaryKey(id);
        if (departmentDO.getId() < 0) {
            throw new FinallyException(EnumException.DATA_INSERT_ERROR);
        }
        return convertByDO(departmentDO);
    }

    @Override
    public List<DepartmentModel> getDepartments() throws FinallyException {
        List<DepartmentDO> listDO = departmentDOMapper.selectAll();
        if (listDO == null) {
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }
        List<DepartmentModel> listModel = new ArrayList<>();
        for (DepartmentDO d : listDO) {
            listModel.add(convertByDO(d));
        }
        return listModel;
    }

    @Override
    public void delDepartment(List<Integer> ids) throws FinallyException {
        if (ids.size() < 1) {
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }
        Date date = new Date();
        int timestamp = (int) (date.getTime() / 1000);
        for (int d : ids) {
            DepartmentDO departmentDO = new DepartmentDO();
            departmentDO.setId(d);
            departmentDO.setDeleteTime(timestamp);
            departmentDOMapper.updateByPrimaryKeySelective(departmentDO);
        }
    }

    private DepartmentDO convertByModel(DepartmentModel departmentModel) throws FinallyException {
        if (departmentModel == null) {
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }
        DepartmentDO departmentDO = new DepartmentDO();
        BeanUtils.copyProperties(departmentModel, departmentDO);
        return departmentDO;
    }

    private DepartmentModel convertByDO(DepartmentDO departmentDO) throws FinallyException {
        if (departmentDO == null) {
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }
        DepartmentModel departmentModel = new DepartmentModel();
        BeanUtils.copyProperties(departmentDO, departmentModel);
        return departmentModel;
    }
}
