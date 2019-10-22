package dafen.service;

import dafen.bean.ScoreDO;
import dafen.exception.FinallyException;
import dafen.service.model.ScoreModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据次数导出数据
     * @param times
     * @return
     * @throws FinallyException
     */
    List<Map<String,String>> exportScoreByTimes(int times) throws FinallyException;

    void setScored(ScoreModel scoreModel) throws FinallyException;

    void updateScored(ScoreModel scoreModel)throws FinallyException;

    List<ScoreDO> getScoreTimes(int userId, int depId, int type, int page)throws FinallyException;
}
