package com.project.dasi.admin.question.model.dao;

import com.project.dasi.admin.question.model.dto.QuestionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
    int adminquestCreate(QuestionDTO question);

    List<QuestionDTO> questionList();

    int questionCreate(QuestionDTO question);

    int questionUpdate(QuestionDTO question);

/*    QuestionDTO userquestionUpdate(int qnum);*/

    QuestionDTO userquestionUpdate(int qnumb);

    void userquestionup(QuestionDTO qdto);

    void userquestionDelete(int questionNum);
}
