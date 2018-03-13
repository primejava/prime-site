/**
 * Project Name:com.wisdombud.education.public
 * File Name:DuplicateStudentBean.java
 * Package Name:com.wisdombud.education.support
 * Date:2014年9月25日 下午3:37:22
 * Copyright (c) 2014, www.wisdombud.com All Rights Reserved.
 */

package org.primejava.cms.imp.pojo;

import java.util.List;

/**
 * ClassName: ImportRecordBean. <br/>
 * Function: TODO <br/>
 * date: 2014年9月25日 下午3:37:22 <br/>
 * @author mmzhao
 * @version
 * @since JDK 1.6
 */
public class ImportRecordBean {
    private int          sum;
    private int          saveOrUpdateCount;
    private int          errorCount;
    private List<String> errorKeys;

    /**
     * saveOrUpdateCount.
     * @return the saveOrUpdateCount
     * @since JDK 1.6
     */
    public int getSaveOrUpdateCount() {
        return saveOrUpdateCount;
    }

    /**
     * saveOrUpdateCount.
     * @param saveOrUpdateCount the saveOrUpdateCount to set
     * @since JDK 1.6
     */
    public void setSaveOrUpdateCount(int saveOrUpdateCount) {
        this.saveOrUpdateCount = saveOrUpdateCount;
    }

    /**
     * errorCount.
     * @return the errorCount
     * @since JDK 1.6
     */
    public int getErrorCount() {
        return errorCount;
    }

    /**
     * errorCount.
     * @param errorCount the errorCount to set
     * @since JDK 1.6
     */
    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    /**
     * errorKeys.
     * @return the errorKeys
     * @since JDK 1.6
     */
    public List<String> getErrorKeys() {
        return errorKeys;
    }

    /**
     * errorKeys.
     * @param errorKeys the errorKeys to set
     * @since JDK 1.6
     */
    public void setErrorKeys(List<String> errorKeys) {
        this.errorKeys = errorKeys;
    }

    /**
     * sum.
     * @return the sum
     * @since JDK 1.6
     */
    public int getSum() {
        return sum;
    }

    /**
     * sum.
     * @param sum the sum to set
     * @since JDK 1.6
     */
    public void setSum(int sum) {
        this.sum = sum;
    }

}
