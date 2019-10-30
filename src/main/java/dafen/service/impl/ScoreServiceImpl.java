package dafen.service.impl;

import dafen.bean.DepartmentDO;
import dafen.bean.ScoreDO;
import dafen.bean.SelfDO;
import dafen.bean.UserDO;
import dafen.dao.DepartmentDOMapper;
import dafen.dao.ScoreDOMapper;
import dafen.dao.UserDOMapper;
import dafen.exception.EnumException;
import dafen.exception.FinallyException;
import dafen.service.ScoreService;
import dafen.service.model.ScoreModel;
import dafen.utils.FuncUntils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDOMapper scoreDOMapper;
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private DepartmentDOMapper departmentDOMapper;

    @Override
    public List<ScoreModel> getScoreByCondition(int userId, int times, int depId, int type) throws FinallyException {
        // 根据用户编号导出数据，用户可用
        if (type == 0 && userId < 1) {
            throw new FinallyException(EnumException.PARAMS_ERROR, "用户编号不能为空");
        }
        // 部门获取最新一周的数据，部门管理员可用
        if (type == 1 && depId < 1) {
            throw new FinallyException(EnumException.PARAMS_ERROR, "部门编号不能为空");
        }
        // 根据times导出数据，超级管理员可用
        if (times < 1) {
            throw new FinallyException(EnumException.PARAMS_ERROR, "查询次数不能为空");
        }

        List<ScoreDO> listDO;
        // 查询次数下的打分信息
        if (type == 0) {// 用户的某次打分结果
            listDO = scoreDOMapper.selectByTimesAndUserId(userId,times);
        } else if (type == 1) {// 部门的某次打分列表
            listDO = scoreDOMapper.selectByTimesAndDepId(depId,times);
        } else {// 某次打分
            listDO = scoreDOMapper.selectByTimes(times);
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

    /**
     * 超级管理员根据次数导出数据
     * 做连表查询
     *
     * @param times
     * @return
     * @throws FinallyException
     */
    @Override
    public List<Map<String,String>> exportScoreByTimes(int times) throws FinallyException {
        if (times < 1) {
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        List<ScoreDO> list = scoreDOMapper.exportScoreByTimes(times);
        if (list.size() < 1) {
            throw new FinallyException(EnumException.DATA_EMPTY, "没有该期的导出数据");
        }
        // 配置用户及部门信息
        List<UserDO> userDOList = userDOMapper.selectAll();
        List<DepartmentDO> departmentDOList = departmentDOMapper.selectAll();
        Map<Integer, String> userMap = new HashMap<>();
        Map<Integer, String> depMap = new HashMap<>();
        for (UserDO u : userDOList) {
            userMap.put(u.getId(), u.getUserName());
        }
        for (DepartmentDO d : departmentDOList) {
            depMap.put(d.getId(), d.getdName());
        }

        // 组合数据
        List<Map<String,String>> res_list = new ArrayList<>();
        for (ScoreDO s : list
        ) {
            Map<String, String> map = new HashMap<>();
            map.put("id",s.getId()+"");
            map.put("user_id",s.getUserId()+"");
            map.put("userName", userMap.get(s.getUserId()));
            map.put("dep_id",s.getDepartmentId()+"");
            map.put("depName", depMap.get(s.getDepartmentId()));
            map.put("score", s.getScord() + "");
            res_list.add(map);
        }
        return res_list;
    }

    /**
     *
     * @param scoreModel
     * @throws FinallyException
     */
    @Override
    public void setScored(ScoreModel scoreModel) throws FinallyException {
        if (scoreModel == null || scoreModel.getScord() < 0) {
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        if(scoreModel.getTimes() < 0){
            throw new FinallyException(EnumException.PARAMS_ERROR,"没有时间参数。");
        }
        ScoreDO scoreDO = convertByModel(scoreModel);
        // 验证数据是否存在
        ScoreDO scoreDO_old = scoreDOMapper.selectByDepUserIdTimes(
                scoreModel.getUserId(),scoreModel.getDepartmentId(),scoreModel.getTimes());
        if(scoreDO_old != null){
            scoreDO.setId(scoreDO_old.getId());
            scoreDOMapper.updateByPrimaryKeySelective(scoreDO);
        }else{
            scoreDOMapper.insertSelective(scoreDO);
        }
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
    public List<Map<String,Object>> getScoreTimes(int userId, int depId, int type, int page) throws FinallyException {
        if ((type == 0 && userId < 1) || (type == 1 && depId < 1)) {
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        // 分页显示
        if (page < 1) {
            page = 1;
        }
        List<SelfDO> listDO;
        int start = (page - 1) * 20;
        int end = page * 20;
        //
        if (type == 0) {// 用户的次数列表
            listDO = scoreDOMapper.selectByUserId(userId, start, end);
        } else if (type == 1) {// 部门的次数列表
            listDO = scoreDOMapper.selectByDepartmentId(depId, start, end);
        } else {// 次数列表
            listDO = scoreDOMapper.getTimes(start, end);
        }
        if(listDO.size() == 0){
            throw new FinallyException(EnumException.DATA_EMPTY);
        }
        List<Map<String,Object>> list = new ArrayList<>();
        for(SelfDO s:listDO){
            Map<String,Object> map = new HashMap<>();
            map.put("times",s.getTimes());
            list.add(map);
        }
        return list;
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
