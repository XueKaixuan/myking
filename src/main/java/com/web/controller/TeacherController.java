package com.web.controller;

import com.web.comm.ResultInfo;
import com.web.entity.ExamBean;
import com.web.entity.GradeBean;
import com.web.entity.TestBean;
import com.web.service.TeacherService;
import com.web.utlis.PoiInput;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 创作时间：2020/11/6 8:55
 * 作者：李增强
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;
    /**
     * 只能查看登录用户有权限的部分列表
     * 登录成功了，要把用户信息存入session中
     */
    Long tid=1L;
    @RequestMapping("/getDuliTestList")
    public String getDuliTestList(Model model){

        /**
         * SELECT DISTINCT a.* from tb_test a
         * LEFT JOIN tb_test_grade b on a.testid = b.testid
         * LEFT JOIN tb_grade c on c.gid = b.gid
         * LEFT JOIN tb_teacher_grade d On d.gid = c.gid
         * where d.tid = 2
         */
        List<TestBean> list = teacherService.getDuliTestList(tid);
        model.addAttribute("list", list);
        return "test_dl";
    }

    @RequestMapping("/toMarkTest")
    public String toMarkTest(Model model){

        /**
         * 把这个老师可以发布的班级查询出来，直接给页面
         */
        List<GradeBean> list = teacherService.getGradeListByTid(tid);
        model.addAttribute("list", list);
        return "test_mark";
    }
    @RequestMapping("/fileUpload")
    @ResponseBody
    public ResultInfo fileUpload(MultipartFile file, HttpServletRequest request){
        /**
         * 应该返回 上传成功还是失败，失败了，需要把原因返回回去，
         * 成功了，需要总分返回回去
         */
        String filename = file.getOriginalFilename();
        String[] split = filename.split("\\.");
        List<ExamBean> list;
        if (split[1].equals("xls")){
            list = PoiInput.getTestList03(file);
        }else{
            list = PoiInput.getTestList07(file);
        }

        double totalscore=0.0;
        for (ExamBean exam : list) {
            totalscore+=exam.getEfenzhi();
        }
        request.getSession().setAttribute("list", list);
        return new ResultInfo(true,""+totalscore);
    }

    /**
     *  DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
     *   LocalDateTime.parse(“后端接收时间的字符串”.replaceAll("T", " ") + ":00", df)
     */
    @RequestMapping("/saveTestMark")
    @ResponseBody
    public ResultInfo saveTestMark(TestBean testBean, Long[] gids, HttpServletRequest request){
        try {
            List<ExamBean> list = (List<ExamBean>)request.getSession().getAttribute("list");
            teacherService.saveTestMark(tid,list,testBean,gids);
            return new ResultInfo(true,"success");
        }catch (Exception e){
            return new ResultInfo(true,"添加失败");
        }
    }
}
