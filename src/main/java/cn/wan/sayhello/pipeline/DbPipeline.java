package cn.wan.sayhello.pipeline;

import cn.wan.sayhello.mapper.CandidateMapper;
import cn.wan.sayhello.po.Candidate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component("dbPipeline")
public class DbPipeline implements Pipeline {

    private static Logger logger = Logger.getLogger(DbPipeline.class);

    @Autowired
    CandidateMapper candidateMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Candidate> candidateList = resultItems.get("candidateList");
        if (CollectionUtils.isNotEmpty(candidateList)) {
            for (Candidate candidate1:
                 candidateList) {
                candidateMapper.insert(candidate1);
                logger.info("新增candidate成功：" + candidate1.toString());
            }
            resultItems.put("candidateList",null);
            return;
        }
        Candidate candidate = resultItems.get("candidate");
        if (candidate == null) {
            logger.warn("candidate == null!!! return");
            return;
        }
        try {
            candidate.setIsSayHello(0);
            candidateMapper.update(candidate);
            resultItems.put("candidate",null);
            logger.info("更新candidate成功：uid = " + candidate.getUid() + "gid = " + candidate.getGid() + "name = " + candidate.getName());
        } catch (Exception e) {
            logger.error("dbPipeline update error :" + e);
        }
        return;
    }
}
