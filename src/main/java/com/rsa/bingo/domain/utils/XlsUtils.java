package com.rsa.bingo.domain.utils;

import com.rsa.bingo.domain.models.Colors;
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

    static HSSFCellStyle getPrimaryCellStyle(HSSFWorkbook workbook, Colors colors, int height) {
        return getPrimaryCellStyle(workbook, getColor(workbook, colors.getPrimary()), height);
    }

    static HSSFCellStyle getSecondaryCellStyle(HSSFWorkbook workbook, Colors colors) {
        return getSecondaryCellStyle(
                workbook,
                getColor(workbook, colors.getPrimary()),
                getColor(workbook, colors.getSecondary())
        );
    }

    private static short getColor(HSSFWorkbook workbook, int[] rgb) {
        var palette = workbook.getCustomPalette();
        return palette.findSimilarColor(rgb[0], rgb[1], rgb[2]).getIndex();
    }

    private static HSSFCellStyle getPrimaryCellStyle(HSSFWorkbook workbook, short primaryColor, int height) {
        var font = getFont(workbook, primaryColor, height);
        var cellStyle = workbook.createCellStyle();
        setDefaultStyle(cellStyle, primaryColor);
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    private static HSSFCellStyle getSecondaryCellStyle(HSSFWorkbook workbook, short primaryColor, short secondaryColor) {
        var cellStyle = workbook.createCellStyle();
        setDefaultStyle(cellStyle, primaryColor);
        cellStyle.setFillForegroundColor(secondaryColor);
        return cellStyle;
    }

    private static HSSFFont getFont(HSSFWorkbook workbook, short color, int height) {
        var font = workbook.createFont();
        font.setColor(color);
        font.setFontHeightInPoints((short) height);
        return font;
    }

    private static void setDefaultStyle(HSSFCellStyle cellStyle, short color) {
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setLeftBorderColor(color);
        cellStyle.setTopBorderColor(color);
        cellStyle.setRightBorderColor(color);
        cellStyle.setBottomBorderColor(color);
    }
}
