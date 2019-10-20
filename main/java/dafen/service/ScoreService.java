package dafen.service;

import dafen.exception.FinallyException;
import dafen.service.model.ScoreModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService {

    List<ScoreModel> getScoreByUserId(int userId) throws FinallyException;

    void setScored(ScoreModel scoreModel) throws FinallyException;

    void updateScored(ScoreModel scoreModel)throws FinallyException;

}
