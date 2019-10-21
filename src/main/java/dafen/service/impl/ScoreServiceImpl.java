package dafen.service.impl;

import dafen.bean.ScoreDO;
import dafen.dao.ScoreDOMapper;
import dafen.exception.EnumException;
import dafen.exception.FinallyException;
import dafen.service.ScoreService;
import dafen.service.model.ScoreModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDOMapper scoreDOMapper;

    @Override
    public List<ScoreModel> getScoreByCondition(int userId, int times, int depId, int type, int page) throws FinallyException {
        // 根据用户编号导出数据，用户可用
        if (type == 0 && userId < 1) {
            throw new FinallyException(EnumException.PARAMS_ERROR, "用户编号不能为空");
        }
        // 部门获取最新一周的数据，部门管理员可用
        if (type == 1 && depId < 1) {
            throw new FinallyException(EnumException.PARAMS_ERROR, "部门编号不能为空");
        }
        // 根据times导出数据，超级管理员可用
        if (type == 2 && times < 1) {
            throw new FinallyException(EnumException.PARAMS_ERROR, "查询次数不能为空");
        }

        // 分页显示
        if (page < 1) {
            page = 1;
        }
        int start = (page - 1) * 20;
        int end = page * 20;

        List<ScoreDO> listDO;
        if (type == 0) {// 用户的次数列表
            listDO = scoreDOMapper.selectByUserId(userId, start, end);
        } else if (type == 1) {// 部门的次数列表
            listDO = scoreDOMapper.selectByDepartmentId(depId, start, end);
        } else  {// 次数列表
            listDO = scoreDOMapper.selectByTimes(times, start, end);
        }
        if (listDO.size() == 0) {
            throw new FinallyException(EnumException.DATA_EMPTY);
        }
        List<ScoreModel> models = new ArrayList<>();
        for (ScoreDO s : listDO) {
            models.add(convertByDO(s));
        }
        return models;
    }

    @Override
    public void setScored(ScoreModel scoreModel) throws FinallyException {
        if (scoreModel == null || scoreModel.getScord() < 0) {
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        ScoreDO scoreDO = convertByModel(scoreModel);
        scoreDOMapper.insertSelective(scoreDO);
        if (scoreDO.getId() < 1) {
            throw new FinallyException(EnumException.DATA_INSERT_ERROR);
        }
    }

    @Override
    public void updateScored(ScoreModel scoreModel) throws FinallyException {
        if (scoreModel.getId() == 0) {
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        ScoreDO scoreDO = convertByModel(scoreModel);
        scoreDOMapper.updateByPrimaryKeySelective(scoreDO);
        if (scoreDO.getId() < 1) {
            throw new FinallyException(EnumException.DATA_INSERT_ERROR);
        }
    }

    @Override
    public List<ScoreDO> getScoreTimes(int userId, int depId, int type, int page) throws FinallyException {
        // 分页显示
        if (page < 1) {
            page = 1;
        }
        List<ScoreDO> listDO = scoreDOMapper.selectByCondition(userId, depId, ((page - 1) * 20), page * 20);
        return listDO;
    }

    private ScoreDO convertByModel(ScoreModel scoreModel) throws FinallyException {
        if (scoreModel == null) {
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        ScoreDO scoreDO = new ScoreDO();
        BeanUtils.copyProperties(scoreModel, scoreDO);
        return scoreDO;
    }


    private ScoreModel convertByDO(ScoreDO scoreDO) throws FinallyException {
        if (scoreDO == null) {
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        ScoreModel scoreModel = new ScoreModel();
        BeanUtils.copyProperties(scoreDO, scoreModel);
        return scoreModel;
    }
}
