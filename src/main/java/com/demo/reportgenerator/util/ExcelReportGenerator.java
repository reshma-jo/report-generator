package com.demo.reportgenerator.util;

import com.demo.reportgenerator.model.ToDoData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelReportGenerator {
    public void generateToDoExcelReport(List<ToDoData> todoList, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Todo List");

        // Header row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"ID", "Title", "Due Date", "Completed"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Fill in data
        int rowNum = 1;
        for (ToDoData toDoData : todoList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(toDoData.getId());
            row.createCell(1).setCellValue(toDoData.getTitle());
            row.createCell(2).setCellValue(toDoData.getDueDate());
            row.createCell(3).setCellValue(toDoData.isCompleted());
        }

        // Resize columns to fit content
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }
}
