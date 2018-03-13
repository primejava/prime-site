package org.primejava.cms.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.primejava.basic.model.Pager;
import org.primejava.cms.dao.AttachmentDao;
import org.primejava.cms.model.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;
@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	private AttachmentDao attachmentDao;
	@Override
	public void add(Attachment attachment) {
		attachmentDao.add(attachment);
	}
	@Override
	public Pager<Attachment> findAttachments(Pager<Attachment> page) {
		Pager<Attachment> articles=attachmentDao.findAttachments(page);
		return articles;
	}
	@Override
	public Attachment findAttachmentById(String id) {
		Attachment attachment=attachmentDao.load(id);
		return attachment;
	}
	
	@Override
	public void addWordIndex(String temppath) throws IOException {
		//索引目录--吗的跑d盘去了
		String indexDir="/upload/index";
		//获得文档列表
		File[] files = new File(temppath).listFiles(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {
				if(name.toLowerCase().endsWith(".doc")||name.toLowerCase().endsWith(".docx")){
					return true;
				}
				return false;
			}
		});
		//庖丁分析器
  		Analyzer analyzer = new IKAnalyzer(false);
		//创建Directory
  		File ee=new File(indexDir);
  		System.out.println(ee.getAbsolutePath());
		Directory dir = FSDirectory.open(ee);
		//创建Writer
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_0, analyzer);
		IndexWriter writer = new IndexWriter(dir, iwc);
		
		//只对文件修改日期大于上次索引修改时间的进行索引
		long ldate = findLastModify();
		String strtmp = "";
		try {
			for (int i = 0; i < files.length; i++) {
				if (files[i].lastModified() > ldate) {
					strtmp = files[i].getAbsolutePath();
					Attachment attachment=findAttachmentByName(files[i].getName());
					if(attachment==null){
						continue;
					}
					attachment.setLastModify(files[i].lastModified());
					FileInputStream fis = new FileInputStream(strtmp);
					String content=null;
					if(attachment.getNewName().toLowerCase().endsWith(".doc")){
						WordExtractor word2003 = new WordExtractor(fis);
						content=word2003.getText();
					}else{
						System.out.println(attachment.getNewName());
						XWPFWordExtractor word2007=new XWPFWordExtractor(new XWPFDocument(fis));
						content=word2007.getText();
					}
					//创建文档
					Document document = new Document();
					document.add(new StringField("id", attachment.getId(), Field.Store.YES));
					document.add(new StringField("type", attachment.getType(), Field.Store.YES));
					document.add(new StringField("fileName", attachment.getName(), Field.Store.YES));
					document.add(new TextField("contents", content, Field.Store.YES));
					writer.addDocument(document);
					System.out.println("创建索引");
					attachmentDao.update(attachment);
				}
			}
			writer.close();
			dir.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public long findLastModify() {
		return attachmentDao.findLastModify();
	}
	@Override
	public Attachment findAttachmentByName(String filePath) {
		return attachmentDao.findAttachmentByName(filePath);
	}

}
