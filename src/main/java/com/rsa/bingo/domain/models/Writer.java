package com.rsa.bingo.domain.models;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;

public final class Writer {

    private Writer() { }

    public static byte[] write(Card card, Colors colors) throws IOException {
        try (var workbook = new HSSFWorkbook()) {
            var sheet = getSheet(workbook);
            var primaryColor = getColor(colors.getPrimaryColor());
            var secondaryColor = getColor(colors.getSecondaryColor());
            var primaryCellStyle = getPrimaryCellStyle(workbook, primaryColor);
            var secondaryCellStyle = getSecondaryCellStyle(workbook, primaryColor, secondaryColor);
            fillSheet(sheet, card, primaryCellStyle, secondaryCellStyle);
            return workbook.getBytes();
        }
    }

    private static Sheet getSheet(Workbook workbook) {
        var sheet = workbook.createSheet("Bingo");
        sheet.setDefaultColumnWidth(8);
        sheet.setDefaultRowHeightInPoints(50);
        return sheet;
    }

    private static short getColor(String color) {
        return IndexedColors.fromInt(Integer.parseInt(color)).index;
    }

    private static CellStyle getPrimaryCellStyle(Workbook workbook, short primaryColor) {
        var font = getFont(workbook, primaryColor);
        var cellStyle = workbook.createCellStyle();
        setDefaultStyle(cellStyle, primaryColor);
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    private static Font getFont(Workbook workbook, short color) {
        var font = workbook.createFont();
        font.setColor(color);
        font.setFontHeightInPoints((short) 28);
        return font;
    }

    private static CellStyle getSecondaryCellStyle(Workbook workbook, short primaryColor, short secondaryColor) {
        var cellStyle = workbook.createCellStyle();
        setDefaultStyle(cellStyle, primaryColor);
        cellStyle.setFillForegroundColor(secondaryColor);
        return cellStyle;
    }

    private static void setDefaultStyle(CellStyle cellStyle, short color) {
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

    private static void fillSheet(Sheet sheet, Card card, CellStyle primaryCellStyle, CellStyle secondaryCellStyle) {
        for (var i = 0; i < Card.ROWS; i++) {
            var row = sheet.createRow(i +1);
            for (var j = 0; j < Card.COLUMNS; j++) {
                var column = row.createCell(j + 1);
                card.get(i, j).ifPresentOrElse(value -> {
                    column.setCellValue(value);
                    column.setCellStyle(primaryCellStyle);
                }, () -> column.setCellStyle(secondaryCellStyle));
            }
        }
    }
}
