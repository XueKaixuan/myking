package com.web.service.impl;

import com.web.entity.*;
import com.web.mapper.*;
import com.web.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private TestMapper testMapper;
    @Resource
    private TestGradeMapper testGradeMapper;
    @Resource
    private ExamMapper examMapper;
    @Resource
    private ExamOptionMapper examOptionMapper;

    @Override
    public List<TestBean> getDuliTestList(Long tid) {
        return teacherMapper.getDuliTestList(tid);
    }

    @Override
    public List<GradeBean> getGradeListByTid(Long tid) {
        return teacherMapper.getGradeListByTid(tid);
    }

    @Override
    public void saveTestMark(Long tid, List<ExamBean> list, TestBean testBean, Long[] gids) {
        /**
         * 先去把老师的名字查出来
         */
        TeacherBean teacherBean = teacherMapper.getTname(tid);

        testBean.setTestauthor(teacherBean.getTname());
        testBean.setAuthorid(teacherBean.getTid());
        /**
         * 先去保存考试信息，然后要把testid拿回来
         */
        testMapper.save(testBean);
        Long testid = testBean.getTestid();
        /**
         * 先保存试题或者先保存   考试和班级的关联关系
         */
        List<Long> ids = Arrays.asList(gids);
        for (Long gid : ids) {
            testGradeMapper.save(gid,testid);
        }
        /**
         * 先保存班级和考试信息  tb_test_grade
         * 在这里遍历或者把数组传进xml在遍历
         */

        /**
         * 保存试题
         */
        for (ExamBean exam : list) {
            exam.setTestid(testid);
            //去保存试题，然后拿回试题id  tb_exam
            examMapper.save(exam);
            List<ExamOption> options = exam.getOptions();
            //判断这个试题有没有选项，有的话，保存试题的选项
            for (ExamOption option : options) {
                option.setExamid(exam.getExamid());
                //再去保存   tb_examoption
                examOptionMapper.save(option);
            }
        }

    }
}

