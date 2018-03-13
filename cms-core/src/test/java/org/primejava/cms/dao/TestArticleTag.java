package org.primejava.cms.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primejava.basic.test.util.AbstractDbUnitTestCase;
import org.primejava.basic.util.TxtReadWriteUtil;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestArticleTag extends AbstractDbUnitTestCase{
	//http://blog.csdn.net/yangkai_hudong/article/details/28910059?utm_source=tuicool&utm_medium=referral
	@Test
	public void testfindArticleTag() {
		
		try {
			File cfgFile = ResourceUtils.getFile("classpath:tags.txt");
			List<String> ss=TxtReadWriteUtil.readTxt(cfgFile.getPath());
			for(String s:ss){
				System.out.println(s);
			}
		} catch (Exception e) {

		}  finally{
			
		}
	}
	
	@Test
	public void testWriteArticleTag() {
		
		try {
			File cfgFile = ResourceUtils.getFile("classpath:tags.txt");
			TxtReadWriteUtil.writerTXT("你好", cfgFile.getPath(), true);
		} catch (Exception e) {

		}  finally{
			
		}
	}
	
}
