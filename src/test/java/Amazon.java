import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



public class Amazon {

 private static final String FILE_NAME = "C:\\Users\\Balaji\\IdeaProjects\\Automation\\Data\\DataSheet.xls";


    public static HashMap<String,String> getList() {
   // public static void main(String args[]) {
        HashMap<String,String> values = new HashMap<String, String>();

        values.put("name","");
        values.put("email","");
        values.put("password","");
        values.put("password_check","");
        values.put("ap_email","");
        values.put("ap_password","");
        values.put("Product","");


        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new HSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentRow.getRowNum() == 0) {
                        continue; //just skip the rows if row number is 0 or 1
                    } else {

                        if(datatypeSheet.getRow(0).getCell(currentCell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("name"))
                        {
                            values.put("name",currentCell.getStringCellValue());
                        }
                        if(datatypeSheet.getRow(0).getCell(currentCell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("email"))
                        {
                            values.put("email",currentCell.getStringCellValue());
                        }
                        if(datatypeSheet.getRow(0).getCell(currentCell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("password"))
                        {
                            values.put("password",currentCell.getStringCellValue());
                        }
                        if(datatypeSheet.getRow(0).getCell(currentCell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("password_check"))
                        {
                            values.put("password_check",currentCell.getStringCellValue());
                        }
                        if(datatypeSheet.getRow(0).getCell(currentCell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("ap_email"))
                        {
                            values.put("ap_email",currentCell.getStringCellValue());
                        }
                        if(datatypeSheet.getRow(0).getCell(currentCell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("ap_password"))
                        {
                            values.put("ap_password",currentCell.getStringCellValue());
                        }
                        if(datatypeSheet.getRow(0).getCell(currentCell.getColumnIndex()).getStringCellValue().equalsIgnoreCase("Product"))
                        {
                            values.put("Product",currentCell.getStringCellValue());
                        }

                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }
}


