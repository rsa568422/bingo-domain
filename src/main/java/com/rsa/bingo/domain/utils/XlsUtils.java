package com.rsa.bingo.domain.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

final class XlsUtils {

    private XlsUtils() {}

    static HSSFWorkbook getWorkbook() {
        return new HSSFWorkbook();
    }

    static HSSFSheet getSheet(HSSFWorkbook workbook, String name, int width, int height) {
        var sheet = workbook.createSheet(name);
        sheet.setDefaultColumnWidth(width);
        sheet.setDefaultRowHeightInPoints(height);
        return sheet;
    }

    static HSSFCellStyle getPrimaryCellStyle(HSSFWorkbook workbook, String primaryColor, int height) {
        var font = getFont(workbook, primaryColor, height);
        var cellStyle = workbook.createCellStyle();
        setDefaultStyle(cellStyle, primaryColor);
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    static HSSFCellStyle getSecondaryCellStyle(HSSFWorkbook workbook, String primaryColor, String secondaryColor) {
        var cellStyle = workbook.createCellStyle();
        setDefaultStyle(cellStyle, primaryColor);
        cellStyle.setFillForegroundColor(IndexedColors.valueOf(secondaryColor).index);
        return cellStyle;
    }

    private static HSSFFont getFont(HSSFWorkbook workbook, String color, int height) {
        var font = workbook.createFont();
        font.setColor(IndexedColors.valueOf(color).index);
        font.setFontHeightInPoints((short) height);
        return font;
    }

    private static void setDefaultStyle(HSSFCellStyle cellStyle, String color) {
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setLeftBorderColor(IndexedColors.valueOf(color).index);
        cellStyle.setTopBorderColor(IndexedColors.valueOf(color).index);
        cellStyle.setRightBorderColor(IndexedColors.valueOf(color).index);
        cellStyle.setBottomBorderColor(IndexedColors.valueOf(color).index);
    }
}
