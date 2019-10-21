package dafen.service;

import dafen.bean.ScoreDO;
import dafen.exception.FinallyException;
import dafen.service.model.ScoreModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService {

    /**
     * 查询数据
     * @param userId
     * @param times
     * @param type
     * @return
     * @throws FinallyException
     */
    List<ScoreModel> getScoreByCondition(int userId,int times,int depId,int type,int page) throws FinallyException;

    void setScored(ScoreModel scoreModel) throws FinallyException;

    void updateScored(ScoreModel scoreModel)throws FinallyException;

    List<ScoreDO> getScoreTimes(int userId, int depId, int type, int page)throws FinallyException;
}
