package com.rsa.bingo.domain.models;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

public final class Writer {

    private Writer() { }

    public static byte[] write(Card card, Colors colors) throws IOException {
        try (var workbook = new HSSFWorkbook()) {
            var sheet = getSheet(workbook);
            var primaryCellStyle = getPrimaryCellStyle(workbook);
            var secondaryCellStyle = getSecondaryCellStyle(workbook);
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

    private static Font getFont(Workbook workbook) {
        var font = workbook.createFont();
        font.setColor(IndexedColors.GREEN.index);
        font.setFontHeightInPoints((short) 28);
        return font;
    }

    private static CellStyle getPrimaryCellStyle(Workbook workbook) {
        var font = getFont(workbook);
        var cellStyle = workbook.createCellStyle();
        setDefaultStyle(cellStyle);
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    private static CellStyle getSecondaryCellStyle(Workbook workbook) {
        var cellStyle = workbook.createCellStyle();
        setDefaultStyle(cellStyle);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
        return cellStyle;
    }

    private static void setDefaultStyle(CellStyle cellStyle) {
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.GREEN.index);
        cellStyle.setTopBorderColor(IndexedColors.GREEN.index);
        cellStyle.setRightBorderColor(IndexedColors.GREEN.index);
        cellStyle.setBottomBorderColor(IndexedColors.GREEN.index);
    }

    private static void fillSheet(Sheet sheet, Card card, CellStyle primaryCellStyle, CellStyle secondaryCellStyle) {
        for (var i = 0; i < Card.ROWS; i++) {
            var row = sheet.createRow(i +1);
            for (var j = 0; j < Card.COLUMNS; j++) {
                var column = row.createCell(j + 1);
                card.get(i, j).ifPresentOrElse(value -> {
                    column.setCellValue(value);
                    column.setCellStyle(primaryCellStyle);
                }, () -> {
                    column.setCellStyle(secondaryCellStyle);
                });
            }
        }
    }
}
