package org.primejava.basic.util;


import org.junit.Test;
import org.primejava.basic.model.BackupFile;

public class TestBackup {
	
	@Test
	public void testList() {
		BackupFileUtil bfu = BackupFileUtil.getInstance("D:\\untitled");
		for(BackupFile bf:bfu.listBackups()) {
			System.out.println(bf);
		}
	}
	
	@Test
	public void testBackup() {
		BackupFileUtil.getInstance("D:\\untitled").backup("测试备份");
	}
	
	@Test
	public void testDelete() {
		BackupFileUtil.getInstance("D:\\untitled").delete("1377609125956_测试备份.tar.gz");
	}
	
	@Test
	public void testResume() {
		BackupFileUtil.getInstance("D:\\untitled").resume("1377610223607_测试备份.tar.gz");
	}
}
