package dafen.action;

import com.alibaba.druid.util.StringUtils;
import dafen.action.View.ScoreVO;
import dafen.bean.ScoreDO;
import dafen.exception.CommonException;
import dafen.exception.EnumException;
import dafen.exception.FinallyException;
import dafen.response.CommonResponse;
import dafen.service.DepartmentService;
import dafen.service.ScoreService;
import dafen.service.model.DepartmentModel;
import dafen.service.model.ScoreModel;
import dafen.utils.FuncUntils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.util.calendar.BaseCalendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Controller("score")
@RequestMapping("/s")
public class ScoreAction extends BaseAction {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/add")
    @ResponseBody
    public CommonResponse setScore(@RequestParam(name = "user_id") int user_id,
                                   @RequestParam(name = "dep_id") int dep_id,
                                   @RequestParam(name = "score") float score,
                                   @RequestParam(name = "reason") String reason) throws FinallyException {
        if (StringUtils.isEmpty(reason) || user_id < 1 || dep_id < 1 || score < 1) {
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        ScoreModel scoreModel = new ScoreModel();
        scoreModel.setDepartmentId(dep_id);
        scoreModel.setUserId(user_id);
        scoreModel.setScord(score);
        scoreModel.setReason(reason);
        scoreModel.setTimes(FuncUntils.getTimesWithWeek());
        scoreService.setScored(scoreModel);
        return CommonResponse.create("打分成功");
    }

    /**
     * @param userId
     * @param times
     * @param type
     * @return
     * @throws FinallyException
     */
//    @RequestMapping("/list")
//    @ResponseBody
//    public CommonResponse getScoreTimes(@RequestParam(name = "user_id") int userId,
//                                       @RequestParam(name = "dep_id") int depId,
//                                       @RequestParam(name = "page") int page,
//                                       @RequestParam(name = "type") int type) throws FinallyException {
//        if ((type == 0 && userId < 1) || (type == 2 && depId < 1)) {
//            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
//        }
//        List<ScoreDO> listModel = scoreService.getScoreTimes(userId, depId, type, page);
//        List<ScoreVO> listVO = new ArrayList<>();
//        for (ScoreDO model : listModel
//        ) {
//            DepartmentModel departmentModel = departmentService.getDepartmentById(model.getDepartmentId());
//            listVO.add(convertByModel(model, departmentModel));
//        }
//        return CommonResponse.create(listVO);
//    }


    /**
     * @param userId
     * @param times
     * @param type
     * @return
     * @throws FinallyException
     */
    @RequestMapping("/list")
    @ResponseBody
    public CommonResponse getScoreList(@RequestParam(name = "user_id") int userId,
                                       @RequestParam(name = "times") int times,
                                       @RequestParam(name = "dep_id") int depId,
                                       @RequestParam(name = "page") int page,
                                       @RequestParam(name = "type") int type) throws FinallyException {
        if ((type == 0 && userId < 1) || (type == 1 && times < 1)
                || (type == 2 && depId < 1) || (type == 3 && (times < 1 || userId < 1))) {
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }
        List<ScoreModel> listModel = scoreService.getScoreByCondition(userId, times, depId, type, page);
        List<ScoreVO> listVO = new ArrayList<>();
        for (ScoreModel model : listModel
        ) {
            DepartmentModel departmentModel = departmentService.getDepartmentById(model.getDepartmentId());
            listVO.add(convertByModel(model, departmentModel));
        }
        return CommonResponse.create(listVO);
    }

    /**
     * @param id
     * @param reason
     * @return
     * @throws FinallyException
     */
    @RequestMapping("/explain")
    @ResponseBody
    public CommonResponse setExplain(@RequestParam(name = "score_id") int id,
                                     @RequestParam(name = "reason") String reason) throws FinallyException {
        if (id < 1 || StringUtils.isEmpty(reason)) {
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        ScoreModel scoreModel = new ScoreModel();
        scoreModel.setId(id);
        scoreModel.setExplainInDetail(reason);
        scoreService.updateScored(scoreModel);
        return CommonResponse.create("申述已提交");
    }

    private ScoreVO convertByModel(ScoreModel scoreModel, DepartmentModel departmentModel) throws FinallyException {
        if (scoreModel == null) {
            throw new FinallyException(EnumException.PARAMS_ERROR);
        }
        if (departmentModel == null) {
            throw new FinallyException(EnumException.PARAMS_ERROR, "部门信息不存在");
        }
        ScoreVO scoreVO = new ScoreVO();
        BeanUtils.copyProperties(scoreModel, scoreVO);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        scoreVO.setCreateTime(df.format(scoreModel.getCreateTime()));
        scoreVO.setDepartmentName(departmentModel.getdName());
        return scoreVO;
    }

}
