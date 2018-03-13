package GroupTypeEnum;

import java.util.ArrayList;
import java.util.List;

public enum DictionaryCommonsEnum {
	 POLITICAL("POLITICAL", "政治面貌"), EDUCATION("EDUCATION", "学历"), DEGREE("DEGREE", "学位"),

	    TRAIN("TRAIN", "培养方式"), ACADEMIC("ACADEMIC", "学制"), EMPLOY("EMPLOY", "就业类型"), CAREERS("CAREERS", "就业形式"), GRADUATE(
	            "GRADUATE", "升学形式"), ENTERPRISE("ENTERPRISE", "单位性质"), FLEXIBLE("FLEXIBLE", "就业形势"), SCALE("SCALE", "企业规模"), CHECK(
	            "CHECK", "审核状态"), FILE("FILE", "文件类型"), LOST("LOST", "遗失补办类型"), POSITION_TYPE("POSITION_TYPE", "职位类型"), EXPECT_SALARY(
	            "EXPECT_SALARY", "期望薪资"), PROFICIENCY_LEVEL("PROFICIENCY_LEVEL", "技能水平"), STUDENT_TYPE("STUDENT_TYPE",
	            "学生类型"), DISPATCH_STATUS("DISPATCH_STATUS", "派遣状态"), ECONOMY_TYPE("ECONOMY_TYPE", "经济类型"), NEWS_TYPE(
	            "NEWS_TYPE", "信息类型"), OPEN_STATUS("OPEN_STATUS", "公开状态"), TUTOR_CONTENT_TYPE("TUTOR_CONTENT_TYPE", "辅导内容"), EMPLOY_GUID_TYPE(
	            "EMPLOY_GUID_TYPE", "就业指导类型"), EMPLOY_DIFFI_TYPE("EMPLOY_DIFFI_TYPE", "就业困难类型"), LIST_TYPE("LIST_TYPE",
	            "企业名单"), JOB_FAIR_TYPE("JOB_FAIR_TYPE", "招聘会类型"), TEACHER_TYPE("TEACHER_TYPE", "老师类型"), LINK_CATEGORY(
	            "LINK_CATEGORY", "链接类别"), ENTERPRISE_LEVEL("ENTERPRISE_LEVEL", "企业评级"), FILE_TRACED("FILE_TRACED", "户档去向"), COURSE_TYPE(
	            "COURSE_TYPE", "课程类型"), UNDEREMPLOYED("UNDEREMPLOYED", "待就业类型"), BASIC_LEVEL_EMPLOYMENT_TYPE(
	            "BASIC_LEVEL_EMPLOYMENT_TYPE", "基层就业类型"), QUASI_ENTERS_A_HIGHER_SCHOOL_TYPE(
	            "QUASI_ENTERS_A_HIGHER_SCHOOL_TYPE", "拟升学类型"), LOCATION("LOCATION", "轮播图片位置"), FILE_SOLVE("FILE_SOLVE",
	            "户档去向"), OTHER_EMPLOY("OTHER_EMPLOY", "其他就业信息"), ORIENTED_OBJECT("ORIENTED_OBJECT", "面向对象"), EMPLOYMENT_PROGRESS(
	            "EMPLOYMENT_PROGRESS", "就业进展题目"), EMPLOY_MODEL("EMPLOY_MODEL ", "就业方式"), SIGN_AGREEMENT_DIRECTION(
	            "SIGN_AGREEMENT_DIRECTION", "协议方向"), GRADUATION_DIRECTION("GRADUATION_DIRECTION", "毕业去向"), NUMBERING_TYPE(
	            "NUMBERING_TYPE", "编号方式"), RESIDENCE("RESIDENCE", "户口位置"), TRANSFER_TYPE("TRANSFER_TYPE", "转出类型"), NORMAL(
	            "NORMAL", "师范生类型"), EXCELLENT("EXCELLENT", "优秀毕业生类型"), JOB_POSITION_TYPE("JOB_POSITION", "工作职位类别"), CONSULTATION_TIME(
	            "CONSULTATION_TIME", "咨询时间"), UNIT_INDUSTRY_CATEGORY("UNIT_INDUSTRY_CATEGORY", "具体行业分类"),
	    // 北邮特有
	    COUNTRY("COUNTRY", "出国就业形式"), REPORT_CARD_TYPE("REPORT_CARD_TYPE", "报到证签发类别"), RESEARCH_TYPE("RESEARCH_TYPE",
	            "调查类型"), RECRUITMENT_DISTRIBUTION("RECRUITMENT_DISTRIBUTION", "招聘发布范围"), HIRING_NUMBER("HIRING_NUMBER",
	            "招聘人数规模"), ENTERPRISE_RESUME_STATUS("ENTERPRISE_RESUME_STATUS", "企业端简历状态"), SUBJECT_NEWS_TYPE(
	            "SUBJECT_NEWS_TYPE", "专题新闻类型"), STUDEBT_RESUME_STATUS("STUDEBT_RESUME_STATUS", "学生端 简历状态"), DELIVERY_WAY(
	            "DELIVERY_WAY", "学生档案投递方式");

	    public static List<DictionaryCommonsEnum> getAllEnums() {
	        final List<DictionaryCommonsEnum> list = new ArrayList<>();
	        for (final DictionaryCommonsEnum enumEntity : DictionaryCommonsEnum.values()) {
	            list.add(enumEntity);
	        }
	        return list;
	    }

	    // 根据value获取enum对象
	    public static DictionaryCommonsEnum valueByIndex(final String code) {
	        for (final DictionaryCommonsEnum status : DictionaryCommonsEnum.values()) {
	            if (status.getCode().equals(code)) {
	                return status;
	            }
	        }
	        return null;
	    }

	    String code;

	    String name;

	    DictionaryCommonsEnum(final String code, final String name) {
	        this.code = code;
	        this.name = name;
	    }

	    public String getCode() {
	        return this.code;
	    }

	    // 获取名字
	    public String getName() {
	        return this.name;
	    }
}
