package org.primejava.cms.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.Pager;
import org.primejava.basic.util.TxtReadWriteUtil;
import org.primejava.cms.dao.ArticleDao;
import org.primejava.cms.dao.ContentsDao;
import org.primejava.cms.dao.UserDao;
import org.primejava.cms.model.Article;
import org.primejava.cms.model.Contents;
import org.primejava.cms.model.MessageSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
    @Autowired
    private ContentsDao   contentDao;
    @Autowired
    private UserDao   userDao;
	@Autowired
	private BaseDao commonDao;
	@Autowired
	private MessageService messageService;
	@Override
	public Pager<Article> findArticles(Pager<Article> page, String id) {
		Pager<Article> articles=articleDao.findArticles(page,id);
		return articles;
	}
	
	
	@Override
	public Pager<Article> findArticleByKey(Pager<Article> page, String key) {
		Pager<Article> articles=articleDao.findArticleByKey(page,key);
		return articles;
	}
	
	@Override
	public Article findArticleById(String id) {
		Article article=articleDao.load(id);
       Contents contents = (Contents) commonDao.findById(id, Contents.class);
		article.setContents(contents);
		return article;
	}

	@Override
	public void saveOrUpdate(Article article) {
	    if (StringUtils.isBlank(article.getId())) {
	    	articleDao.add(article);
            contentDao.saveContents(article.getId(), article.getContents());
           String[] tags=article.getKeyword().split(",");
           List<String> tagLists=Arrays.asList(tags);
           writeTags(tagLists);
           sendMsgToUser(article, tagLists);
            return;
        }
        Article targetNews = articleDao.load(article.getId());
        if (null == targetNews) {
            return;
        }
        targetNews.setTitle(article.getTitle());
        if(!targetNews.getKeyword().equals(article.getKeyword())){
        	 targetNews.setKeyword(article.getKeyword());
    	    String[] tags=article.getKeyword().split(",");
    	    
            writeTags(Arrays.asList(tags));
        }
        articleDao.update(targetNews);
        contentDao.saveContents(article.getId(), article.getContents());
    
	}


	private void sendMsgToUser(Article article, List<String> tagLists) {
		List<String> users=findUsersByTag(tagLists);
           String receiverIds=StringUtils.join(users, ",");
           MessageSend messageSend=new MessageSend();
   		messageSend.setTitle("文章推送:"+article.getTitle());
   		Contents contents=new Contents();
   		contents.setContent(article.getContents().getContent());
   		messageSend.setContent(contents);
   		messageSend.setReceiver(receiverIds);
   		try {
   			messageService.saveOrUpdateMessageSend(MessageSend.SYSTEM, messageSend);
   		} catch (IllegalAccessException | InvocationTargetException e) {
   			e.printStackTrace();
   		}
	}

	@Override
	public List<String> findUsersByTag(List<String> tagLists) {
		Set<String> result=new HashSet<String>();
		for(String tag:tagLists){
			List<String> users=userDao.findUsersByTag(tag);
			result.addAll(users);
		}
		return  new ArrayList(result);
	}


	@Override
	public List<String> readAllTag() {
		File cfgFile;
		try {
			cfgFile = ResourceUtils.getFile("classpath:tags.txt");
			List<String> exists=TxtReadWriteUtil.readTxt(cfgFile.getPath());
			return exists;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void writeTags(List<String> tags) {
		try {
			File cfgFile = ResourceUtils.getFile("classpath:tags.txt");
			List<String> exists=TxtReadWriteUtil.readTxt(cfgFile.getPath());
			List<String> add=new ArrayList<String>();
			for(String tag:tags){
				if(!exists.contains(tag)){
					add.add(tag);
				}
			}
			TxtReadWriteUtil.writerListToTXT(add, cfgFile.getPath(), true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}
}
