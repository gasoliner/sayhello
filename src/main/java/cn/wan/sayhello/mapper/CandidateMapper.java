package cn.wan.sayhello.mapper;

import cn.wan.sayhello.po.Candidate;

public interface CandidateMapper {

    /**
      * @description: 可选择性的增加
      * @param candidate
      * @return:
      * @author: wanhongji
      * @date: 2018/7/31
      */
    int insert(Candidate candidate);

    /**
      * @description: 可选择性的更新，根据主键
      * @param candidate
      * @return:
      * @author: wanhongji
      * @date: 2018/7/31
      */
    int update(Candidate candidate);
}
