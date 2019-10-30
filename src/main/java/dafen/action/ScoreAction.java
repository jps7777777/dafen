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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.util.calendar.BaseCalendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Controller("score")
@RequestMapping("/s")
@Api(tags = "打分接口",description = "用户打分/申述/查看等接口")
public class ScoreAction extends BaseAction {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "部门给用户打分接口",notes = "每次提交一个用户的打分信息")
    @PostMapping("/add")
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
     * 根据条件查询分次信息列表
     * @param userId
     * @param depId
     * @param page
     * @param type
     * @return
     * @throws FinallyException
     */
    @ApiOperation(value = "查询分次列表",notes = "type为0表示查询用户的分次信息列表，用户标号不能为空；" +
            "type为1表示查询部门的分次列表，dep_id不能为空；type==2，表示管理员查询所有的分次信息")
    @PostMapping("/times")
    @ResponseBody
    public CommonResponse getScoreTimes(@RequestParam(name = "user_id") int userId,
                                       @RequestParam(name = "dep_id") int depId,
                                       @RequestParam(name = "page") int page,
                                       @RequestParam(name = "type") int type) throws FinallyException {
        if ((type == 0 && userId < 1) || (type == 1 && depId < 1)) {
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }
        List<Map<String,Object>> listModel = scoreService.getScoreTimes(userId, depId, type, page);
        return CommonResponse.create(listModel);
    }

    /**
     * @param userId
     * @param times
     * @param type
     * @return
     * @throws FinallyException
     */
    @ApiOperation(value = "根据type获取不同的打分列表",notes = "当type为0时，根据用户编号获取用户的打分列表；" +
            "当type为1时，根据dep_id获取部门的打分列表；当type为2时，获取times次的打分列表。")
    @PostMapping("/list")
    @ResponseBody
    public CommonResponse getScoreList(@RequestParam(name = "user_id") int userId,
                                       @RequestParam(name = "times") int times,
                                       @RequestParam(name = "dep_id") int depId,
                                       @RequestParam(name = "type") int type) throws FinallyException {
        if ((type == 0 && userId < 1) || (type == 1 && depId < 1) || times < 1 ) {
            throw new FinallyException(EnumException.PARAMS_IS_EMPTY);
        }
        List<ScoreModel> listModel = scoreService.getScoreByCondition(userId, times, depId, type);
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
    @ApiOperation(value = "用户对打分信息进行申述的接口")
    @PostMapping("/explain")
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
