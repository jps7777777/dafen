package dafen.service.impl;

import dafen.bean.ScoreDO;
import dafen.dao.ScoreDOMapper;
import dafen.exception.EnumException;
import dafen.exception.FinallyException;
import dafen.service.ScoreService;
import dafen.service.model.ScoreModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDOMapper scoreDOMapper;

    @Override
    public List<ScoreModel> getScoreByUserId(int userId) throws FinallyException {
        if(userId < 1){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        List<ScoreDO> listDO = scoreDOMapper.selectByUserId(userId);
        if(listDO == null){
            throw new FinallyException(EnumException.DATA_EMPTY);
        }
        List<ScoreModel> models = new ArrayList<>();
        for (ScoreDO s:listDO
             ) {
            models.add(convertByDO(s));
        }
        return models;
    }

    @Override
    public void setScored(ScoreModel scoreModel) throws FinallyException {
        if(scoreModel == null){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        ScoreDO scoreDO = convertByModel(scoreModel);
        scoreDOMapper.insertSelective(scoreDO);
        if(scoreDO.getId() < 1){
            throw new FinallyException(EnumException.DATA_INSERT_ERROR);
        }
    }

    @Override
    public void updateScored(ScoreModel scoreModel) throws FinallyException {
        if(scoreModel == null){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        ScoreDO scoreDO = convertByModel(scoreModel);
        scoreDOMapper.updateByPrimaryKeySelective(scoreDO);
        if(scoreDO.getId() < 1){
            throw new FinallyException(EnumException.DATA_INSERT_ERROR);
        }
    }

    private ScoreDO convertByModel(ScoreModel scoreModel) throws FinallyException {
        if(scoreModel == null){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        ScoreDO scoreDO = new ScoreDO();
        BeanUtils.copyProperties(scoreModel,scoreDO);
        return scoreDO;
    }


    private ScoreModel convertByDO(ScoreDO scoreDO) throws FinallyException {
        if(scoreDO == null){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        ScoreModel scoreModel = new ScoreModel();
        BeanUtils.copyProperties(scoreDO,scoreModel);
        return scoreModel;
    }
}
