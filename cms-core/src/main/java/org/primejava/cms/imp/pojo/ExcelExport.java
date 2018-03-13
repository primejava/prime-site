package org.primejava.cms.imp.pojo;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.CellView;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primejava.cms.model.ImportOrExportParam;

public class ExcelExport extends Thread {

    private static final long         serialVersionUID = 2559452968190120694L;
    private final Logger              LOGGER           = Logger.getLogger(ExcelExport.class);
    private int                       realTimeRowIndex = 0;
    private HttpServletResponse       excelResponse    = null;
    private OutputStream              out              = null;
    private final int                 count;
    private final List<Object[]>      list;
    private final Map<String, Object> strutsSession;
    private final WritableWorkbook    exportExcel;
    private WritableCellFormat        headCellFont;
    private WritableCellFormat        cellFormat;
    private WritableCellFormat        celloddFormat;
    private WritableCellFormat        celleveFormat;
    private WritableCellFormat        cellmatixFormat;
    private List<ImportOrExportParam> titles           = null;

    public ExcelExport(Map<String, Object> strutsSession, WritableWorkbook exportExcel, List<Object[]> list,
            HttpServletResponse excelResponse, OutputStream out, List<ImportOrExportParam> exportParams) {
        this.out = out;
        this.excelResponse = excelResponse;
        this.count = 0;
        this.list = list;
        this.exportExcel = exportExcel;
        this.strutsSession = strutsSession;
        this.titles = exportParams;
        try {
            WritableCellFormat baseFormat = new WritableCellFormat();
            baseFormat.setAlignment(Alignment.CENTRE);
            // baseFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

            headCellFont = new WritableCellFormat(baseFormat);
            headCellFont.setAlignment(Alignment.CENTRE);// 水平
            headCellFont.setVerticalAlignment(VerticalAlignment.CENTRE);// 纵向
            headCellFont.setFont(new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD, false,
                    jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));

            cellFormat = new WritableCellFormat(baseFormat);
            cellFormat.setAlignment(Alignment.CENTRE);// 水平
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);// 纵向
            cellFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false,
                    jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));

            celloddFormat = new WritableCellFormat(baseFormat);
            celloddFormat.setAlignment(Alignment.CENTRE);// 水平
            celloddFormat.setVerticalAlignment(VerticalAlignment.CENTRE);// 纵向
            celloddFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false,
                    jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));
            celloddFormat.setBackground(getNearestColour("#A6A659"));

            celleveFormat = new WritableCellFormat(baseFormat);
            celleveFormat.setAlignment(Alignment.CENTRE);// 水平
            celleveFormat.setVerticalAlignment(VerticalAlignment.CENTRE);// 纵向
            celleveFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false,
                    jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));
            celleveFormat.setBackground(getNearestColour("#FFB200"));

            cellmatixFormat = new WritableCellFormat(baseFormat);
            cellmatixFormat.setAlignment(Alignment.CENTRE);// 水平
            cellmatixFormat.setVerticalAlignment(VerticalAlignment.CENTRE);// 纵向
            cellmatixFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false,
                    jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));
            cellmatixFormat.setBackground(getNearestColour("#C8D6E4"));

        } catch (WriteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            if (realTimeRowIndex == 0) {
                fillData(exportExcel, list);
            } else {
                LOGGER.info("运行：" + realTimeRowIndex);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * 生成Excel文件
     * fileName 要生成的Excel文件名（可用绝对或相对路径）
     * sheetName 生成的Excel文件中的sheet名
     * dataList 要放入Excel文件的内容
     * @throws IOException
     */

    public void fillData(WritableWorkbook exportExcel, List<Object[]> list) {

        WritableSheet sheet = exportExcel.createSheet("学生就业信息", 0);
        createTitle(sheet);
        int rowIndex = 1;
        int sheetIndex = 0;
        try {
            int columnIndex = 0;
            if (rowIndex % 5000 == 0) {
                sheetIndex++;
                sheet = exportExcel.createSheet("学生就业信息" + sheetIndex, sheetIndex);
                createTitle(sheet);
                rowIndex = 1;
            }
            columnIndex = fillStudentMsg(sheet, rowIndex, list, columnIndex);
            realTimeRowIndex++;
            addRealTimeSession();
            exportExcel.write();
            exportExcel.close();
            // 这一行非常关键，否则在实际中有可能出现莫名其妙的问题！！！
            strutsSession.put("createExcelsuccess", true);
            excelResponse.flushBuffer();// 强行将响应缓存中的内容发送到目的地

        } catch (RowsExceededException e) {
            LOGGER.error("", e);
        } catch (WriteException e) {
            LOGGER.error("", e);
        } catch (InterruptedException e) {
            // ignore
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                LOGGER.error("关闭输出流异常", e);
            }
        }
    }

    private void addRealTimeSession() throws InterruptedException {
        strutsSession.put("excelrealtime", getRealTimeRowIndex());
    }

    public Colour getNearestColour(String strColor) {
        Color cl = Color.decode(strColor);
        Colour color = null;
        Colour[] colors = Colour.getAllColours();
        if ((colors != null) && (colors.length > 0)) {
            Colour crtColor = null;
            int[] rgb = null;
            int diff = 0;
            int minDiff = 999;
            for (int i = 0; i < colors.length; i++) {
                crtColor = colors[i];
                rgb = new int[3];
                rgb[0] = crtColor.getDefaultRGB().getRed();
                rgb[1] = crtColor.getDefaultRGB().getGreen();
                rgb[2] = crtColor.getDefaultRGB().getBlue();

                diff = Math.abs(rgb[0] - cl.getRed()) + Math.abs(rgb[1] - cl.getGreen())
                        + Math.abs(rgb[2] - cl.getBlue());
                if (diff < minDiff) {
                    minDiff = diff;
                    color = crtColor;
                }
            }
        }
        if (color == null)
            color = Colour.BLACK;
        return color;
    }

    private void autoColWidth(WritableSheet sheet, int... col) {
        CellView cellView = new CellView();
        cellView.setAutosize(true); // 设置自动大小
        for (int i = 0; i < col.length; i++) {
            sheet.setColumnView(col[i], cellView);// 根据内容自动设置列宽
        }
    }

    private void setColWidth(WritableSheet sheet, int width, int... col) {
        for (int i = 0; i < col.length; i++) {
            sheet.setColumnView(col[i], width);// 根据内容自动设置列宽
        }
    }

    private int fillStudentMsg(WritableSheet sheet, int rowIndex, List<Object[]> list, int columnIndex)
            throws WriteException, RowsExceededException {
        // setColWidth(sheet, 15, 0, 1, 2, 3, 5);
        // setColWidth(sheet, 24, 4);
        for (Object[] obj : list) {
            int i = 0;
            int col = 0;
            for (ImportOrExportParam title : titles) {
                if (titles.size() > 110) {
                    col = title.getColumnNumber();
                }
                try {
                    if (null != obj[i] && StringUtils.isNotBlank(obj[i].toString())) {
                        sheet.addCell(new Label(col, rowIndex, obj[i].toString(), cellFormat));
                    } else {
                        sheet.addCell(new Label(col, rowIndex, "", cellFormat));
                    }
                    setColWidth(sheet, 12, title.getColumnNumber());
                } catch (Exception e) {
                    LOGGER.info(e);
                    continue;
                }
                if (i < titles.size() - 1) {
                    i++;
                }
                col = i;
            }
            rowIndex++;
        }
        return columnIndex;
    }

    private void createTitle(WritableSheet sheet) {
        int col = 0;
        for (ImportOrExportParam title : titles) {
            if (titles.size() > 110) {
                col = title.getColumnNumber();
            }
            try {
                sheet.addCell(new Label(col, 0, title.getColumnName(), headCellFont));
            } catch (Exception e) {
                LOGGER.info(e);
            }
            if (col < titles.size() - 1) {
                col++;
            }
        }
    }

    public float getRealTimeRowIndex() {

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setMinimumFractionDigits(2);
        if (realTimeRowIndex != 0 && count != 0) {
            float result = (float) realTimeRowIndex / count;
            return (Float.parseFloat(format.format(result)) * 100);
        }
        return 0;
    }

    public int getCount() {
        return count;
    }
}