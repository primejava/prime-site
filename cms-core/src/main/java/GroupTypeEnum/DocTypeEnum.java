package GroupTypeEnum;

import java.util.ArrayList;
import java.util.List;

public enum DocTypeEnum {
	TXT(".txt"),
	DOC(".doc"),
	DOCX(".docx"),
	XLS(".xls"),
	XLSX(".xlsx"),
	PDF(".pdf"),
	SWF(".swf"),
	PPT(".ppt"),
	PPTX(".pptx");

	String name;

	private DocTypeEnum(String name) {
		this.name = name;

	}

	public String getName() {
		return this.name;
	}


	public static List<DocTypeEnum> getAllEnums() {
		List<DocTypeEnum> list = new ArrayList<DocTypeEnum>();
		for (DocTypeEnum enumEntity : values()) {
			list.add(enumEntity);
		}
		return list;
	}
	
	
//	public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {  
//	    return (T)Enum.valueOf(enumType, name);  
//	}  
}
