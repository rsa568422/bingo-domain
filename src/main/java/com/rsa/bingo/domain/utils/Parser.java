package com.rsa.bingo.domain.utils;

import com.rsa.bingo.domain.models.Card;
import com.rsa.bingo.domain.models.Colors;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public final class Parser {

    private Parser() { }

    public static byte[] getBytes(Card card, Colors colors) {
        var workbook = XlsUtils.getWorkbook();
        var sheet = XlsUtils.getSheet(workbook, "Bingo", 8, 50);
        var primaryCellStyle = XlsUtils.getPrimaryCellStyle(workbook, colors, 28);
        var secondaryCellStyle = XlsUtils.getSecondaryCellStyle(workbook, colors);
        fillSheet(card, sheet, primaryCellStyle, secondaryCellStyle);
        return workbook.getBytes();
    }

    private static void fillSheet(Card card, HSSFSheet sheet,
                                  HSSFCellStyle primaryCellStyle,
                                  HSSFCellStyle secondaryCellStyle) {
        for (var i = 0; i < Card.ROWS; i++) {
            var row = sheet.createRow(i + 1);
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
