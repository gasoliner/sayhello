package cn.wan.sayhello.po;

public class Candidate {

    private String uid;

    private String gid;

    private String name;

    private String lid;

    private String jid;

    private String expectId;

    private Integer workingYears;

    private String degree;

    private String age;

    private String dutyTime;

    private String university;

    private String profession;

    private String expectedSalary;

    private String historyList;

    private String remark;

    private Integer isSayHello;

    private Integer companyCount;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(Integer workingYears) {
        this.workingYears = workingYears;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDutyTime() {
        return dutyTime;
    }

    public void setDutyTime(String dutyTime) {
        this.dutyTime = dutyTime;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(String expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getHistoryList() {
        return historyList;
    }

    public void setHistoryList(String historyList) {
        this.historyList = historyList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsSayHello() {
        return isSayHello;
    }

    public void setIsSayHello(Integer isSayHello) {
        this.isSayHello = isSayHello;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getExpectId() {
        return expectId;
    }

    public void setExpectId(String expectId) {
        this.expectId = expectId;
    }

    public Integer getCompanyCount() {
        return companyCount;
    }

    public void setCompanyCount(Integer companyCount) {
        this.companyCount = companyCount;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", lid='" + lid + '\'' +
                ", jid='" + jid + '\'' +
                ", expectId='" + expectId + '\'' +
                ", workingYears=" + workingYears +
                ", degree='" + degree + '\'' +
                ", age='" + age + '\'' +
                ", dutyTime='" + dutyTime + '\'' +
                ", university='" + university + '\'' +
                ", profession='" + profession + '\'' +
                ", expectedSalary='" + expectedSalary + '\'' +
                ", historyList='" + historyList + '\'' +
                ", remark='" + remark + '\'' +
                ", isSayHello=" + isSayHello +
                ", companyCount=" + companyCount +
                '}';
    }
}
