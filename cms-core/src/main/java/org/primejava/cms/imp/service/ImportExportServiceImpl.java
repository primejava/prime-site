package org.primejava.cms.imp.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.primejava.basic.dao.BaseDao;
import org.primejava.basic.model.SystemContext;
import org.primejava.cms.imp.AbstractImport;
import org.primejava.cms.imp.Imports;
import org.primejava.cms.imp.dao.ImportOrExportDao;
import org.primejava.cms.imp.pojo.ExcelErrorBean;
import org.primejava.cms.imp.pojo.ImportRecord;
import org.primejava.cms.model.Attachment;
import org.primejava.cms.model.CmsException;
import org.primejava.cms.model.ImportOrExportParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: ImportServiceImpl. <br/>
 * Function: TODO <br/>
 * date: 2015年5月4日 下午4:56:47 <br/>
 * 
 * @author mmzhao
 * @version
 * @since JDK 1.6
 */
@Service("importExportService")
public class ImportExportServiceImpl extends AbstractImport implements ImportExportService {

    private static Logger     LOGGER = Logger.getLogger(ImportExportServiceImpl.class);

    @Autowired
    private BaseDao         commonDao;
    @Autowired
    private ImportOrExportDao importOrExportDao;

    @Override
    public ImportRecord importByType(Attachment attachment, String type, String category) {
        if (null == attachment) {
            try {
                throw new CmsException("上传文件为空");
            } catch (CmsException e) {
                e.printStackTrace();
            }
        }
        InputStream excelStream = null;
		try {
			excelStream = new FileInputStream(attachment.getPath());
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
        ImportRecord result = new ImportRecord();
        HSSFWorkbook workbook;
        try {
            workbook = new HSSFWorkbook(excelStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            result = (ImportRecord) importByType(type, category, result, sheet);
        } catch (OfficeXmlFileException e) {
            List<ExcelErrorBean> errorBeans = new ArrayList<ExcelErrorBean>();
            errorBeans.add(getErrorBean(0, 0, "文件格式错误，请上传97-2003版本的Excel!"));
            result.setErrorMsgs(errorBeans);
            return result;
        } catch (IOException e) {
            try {
                throw new CmsException("读取excel失败！", e);
            } catch (CmsException e1) {
                e1.printStackTrace();
            }
        } finally {
//            deleteFileAndAttachment(attachment);
        }
        return result;
    }


    private Object importByType(String type, String category, ImportRecord result, HSSFSheet sheet) {
        Imports imports = null;
        try {
            imports = ImportExportFactory.instance().buildImportMap(type, category);
        } catch (InstantiationException e) {
            logger.error(String.format("get %s type import class fiald!"), e);
        } catch (IllegalAccessException e) {
            logger.error(String.format("get %s type import class fiald!"), e);
        } catch (CmsException e) {
            logger.error(String.format("get %s type import class fiald!"), e);
        } catch (ClassNotFoundException e) {
            logger.error(String.format("get %s type import class fiald!"), e);
        }
        if (null == imports) {
            return null;
        }
        return imports.importByType(result, sheet, findImportParams(type, category));
    }

    @Override
    public List<ImportOrExportParam> findImportParams(String type, String category) {
        List<ImportOrExportParam> list = importOrExportDao.findImportParams(type, category, null);
        return list;
    }



    /**
     * 删除上传文件及附件. <br/>
     * 
     * @author mmzhao
     * @param excelUrl
     * @param attachmentId
     */
    protected void deleteFileAndAttachment(Attachment attachment) {
        String contextPath = SystemContext.getRealPath();
        String excelPath = contextPath + attachment.getUrl();
        File file = new File(excelPath);
        file.delete();
        logger.info("删除上传的文件: " + excelPath);
        Attachment oldAttachment = (Attachment) commonDao.queryObject("from Attachment where id=?",attachment.getId());
        if (attachment != null) {
            commonDao.delete(oldAttachment);
        }
    }



    @Override
    public Attachment findAttachment(String excelName) {
        Attachment attachment = (Attachment) commonDao.queryObject("from Attachment where name=?",excelName);
        if (null == attachment) {
            attachment = new Attachment();
        }
        return attachment;
    }
}
