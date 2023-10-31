package com.rajivcoomar.ivrtest.readthemanual.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.rajivcoomar.ivrtest.readthemanual.model.TestCaseRow;


@Service
public class ExcelService {

    public  List<TestCaseRow> readExcelData() {
        List<TestCaseRow> listTestCase = new ArrayList<TestCaseRow>();

        try {
            ClassPathResource resource = new ClassPathResource("static/DB.xlsx");
            InputStream inputStream = resource.getInputStream();

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            boolean firstRow = true;
            for (Row row : sheet) {
            	if (firstRow) {
                    firstRow = false;
                    continue; // Skip the header row
                }

            	TestCaseRow data = new TestCaseRow();
                for (Cell cell : row) {
                	   if(cell.getColumnIndex() == 0)
                       {
                		   Double newData =  cell.getNumericCellValue();
                       	data.setIndex(newData.intValue());
                       	
                       }
                       else if(cell.getColumnIndex() == 1)
                       {
                       	data.setBot(cell.getStringCellValue());
                       }
                       else if(cell.getColumnIndex() == 2)
                       {
                       	data.setUserInput(cell.getStringCellValue());
                       }
                       else if(cell.getColumnIndex() == 3)
                       {
                       	data.setType(cell.getStringCellValue());
                       }
                }
                listTestCase.add(data);
            }

            inputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listTestCase;
    }
}
