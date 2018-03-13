package org.primejava.basic.util;

import java.io.File;

import org.junit.Test;

public class TestImage {

	@Test
	public void testCompress() {
		ImageUtil iu = ImageUtil.getInstance();
		iu.compressImg(new File("D:/01_thumb.png"), new File("D:/捕获.PNG"),
				200, 0, true);
		iu.cropImg("D:/捕获.PNG", 20, 40, 150, 100);
	}
}
