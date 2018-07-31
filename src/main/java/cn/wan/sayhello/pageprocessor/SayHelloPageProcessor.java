package cn.wan.sayhello.pageprocessor;

import cn.wan.sayhello.po.Candidate;
import cn.wan.sayhello.util.Constant;
import cn.wan.sayhello.util.NiceUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.HtmlNode;

import java.util.ArrayList;
import java.util.List;

public class SayHelloPageProcessor implements PageProcessor {

    private static Logger logger = Logger.getLogger(SayHelloPageProcessor.class);

    private Site site =
            Site.me()
                    .addHeader("referer",Constant.getConfig("referer"))
                    .addHeader("Cookie", Constant.getConfig("cookies"))
                    .addHeader("token",Constant.getConfig("token"))
                    .setRetryTimes(3)
                    .setSleepTime(1000)
                    .setUserAgent(Constant.getConfig("userAgent"));

    private static String DOMAIN = "https://www\\.zhipin\\.com";
    private static String GEEK_LIST = "/boss/recommend/geeks\\.json.*";
    private static String GEEK_INFO = "/chat/geek/info.*";

    public SayHelloPageProcessor() {
    }

    @Override
    public void process(Page page) {

        if (page.getUrl().regex(GEEK_LIST).match()) {
            String results = page.getJson().jsonPath("$.htmlList").get();
            Html nodes = new Html(results);
            List<String> list = nodes.xpath("//a/outerHtml()").all();
            List<Candidate> candidateList = new ArrayList<>();
            for (String s:
                    list) {
                Candidate candidate = new Candidate();
                candidate.setGid(NiceUtil.getValueByKeyInHtml(s,"data-uid"));
                candidate.setUid(NiceUtil.geValueByKeyInUrl(
                        NiceUtil.getValueByKeyInHtml(s,"href"),
                        "uid"));
                candidate.setJid(NiceUtil.geValueByKeyInUrl(
                        NiceUtil.getValueByKeyInHtml(s,"href"),
                        "jid"));
                candidate.setLid(NiceUtil.geValueByKeyInUrl(
                        NiceUtil.getValueByKeyInHtml(s,"href"),
                        "lid"));
                candidate.setExpectId(NiceUtil.geValueByKeyInUrl(
                        NiceUtil.getValueByKeyInHtml(s,"href"),
                        "expectId"));
                candidateList.add(candidate);
            }
            page.putField("candidateList",candidateList);
            List<String> all_url = nodes.links().all();
            if (CollectionUtils.isEmpty(all_url)) {
                logger.warn("推荐牛人的简历URL为空！！！ return");
                return;
            }
            page.addTargetRequests(all_url);
            logger.info("推荐牛人的简历URL——" + all_url.toString());
        }

        if (page.getUrl().regex(GEEK_INFO).match()) {
            String uid = NiceUtil.geValueByKeyInUrl(page.getUrl().get(),"uid");
            String name = page.getHtml().xpath("//div[@class='geek-name']/tidyText()").get();
            String workingyears = page.getHtml().xpath("//span[2]/tidyText()").get();
            String degree = page.getHtml().xpath("//span[3]/tidyText()").get();
            String age = page.getHtml().xpath("//span[1]/tidyText()").get();
            String dutyTime = page.getHtml().xpath("//span[4]/tidyText()").get();
            String university = page.getHtml().xpath("//div[@class='history-list']/html()").all().get(1);
            String expertSalary = page.getHtml().xpath("//span[@class='label-text']/tidyText()").all().get(6);
            List<String> historyList = page.getHtml().xpath("//div[@class='history-list']/div/html()").all();
            Candidate candidate = new Candidate();
            candidate.setUid(uid);
            candidate.setName(name);
            candidate.setWorkingYears(Integer.parseInt(workingyears.replaceAll("年","")));
            candidate.setDegree(degree);
            candidate.setAge(age);
            candidate.setDutyTime(dutyTime);
            candidate.setUniversity(university);
            candidate.setProfession("");
            candidate.setExpectedSalary(expertSalary);
            candidate.setHistoryList(JSON.toJSONString(historyList));
            candidate.setCompanyCount(historyList.size());
            page.putField("candidate",candidate);
            logger.info("候选者信息：" + candidate.toString());
        }

    }

    @Override
    public Site getSite() {
        return site;
    }
}
