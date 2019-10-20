package dafen.action;

import com.alibaba.druid.util.StringUtils;
import dafen.exception.EnumException;
import dafen.exception.FinallyException;
import dafen.response.CommonResponse;
import dafen.service.ScoreService;
import dafen.service.model.ScoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller("score")
@RequestMapping("/s")
public class ScoreAction extends BaseAction {

    @Autowired
    private ScoreService scoreService;


    @RequestMapping("/add")
    @ResponseBody
    public CommonResponse setScore(@RequestParam(name = "user_id")int user_id,
                                   @RequestParam(name = "dep_id")int dep_id,
                                   @RequestParam(name = "score")float score,
                                   @RequestParam(name = "reason")String reason) throws FinallyException {
        if(StringUtils.isEmpty(reason) || user_id<1 || dep_id < 1 || score < 1){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        ScoreModel scoreModel = new ScoreModel();
        scoreModel.setDepartmentId(dep_id);
        scoreModel.setUserId(user_id);
        scoreModel.setScord(score);
        scoreModel.setReason(reason);
        scoreService.setScored(scoreModel);
        return CommonResponse.create("打分成功");
    }

    @RequestMapping("/getList")
    @ResponseBody
    public CommonResponse getListByUserId(@RequestParam(name = "user_id")int user_id) throws FinallyException {
        if(user_id < 1){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        List<ScoreModel> listModel = scoreService.getScoreByUserId(user_id);
        return CommonResponse.create(listModel);
    }

    @RequestMapping("/explain")
    @ResponseBody
    public CommonResponse setExplain(@RequestParam(name = "id")int id,
                                     @RequestParam(name = "reason")String reason) throws FinallyException {
        if(id < 1 || StringUtils.isEmpty(reason)){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        ScoreModel scoreModel = new ScoreModel();
        scoreModel.setId(id);
        scoreModel.setReason(reason);
        scoreService.updateScored(scoreModel);
        return CommonResponse.create("打分成功");
    }

    @RequestMapping("/export")
    @ResponseBody
    public CommonResponse exportExplain(@RequestParam(name = "user_id")int id) throws FinallyException {
        if(id < 1){
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        List<ScoreModel> listModel = scoreService.getScoreByUserId(id);
        // TODO 导出数据为excel
        return CommonResponse.create("打分成功");
    }

}
