package readfile.case1;

import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import readFile.ExcelUtil;

/*
@author YANPIER
 */
public class Case1 {

    public static void main(String[] args) throws IOException {

        /*
         * Definy the file path
         * 
         * It is assumed that the file is in the project
         * 
         * If the file is on the classpath, use "/" first followed the file path.
         * Otherwise, use only the file path
         */
        String filePath = "/readFile/case1/Excel.xlsx";

        // Get excel file of designed route
        Workbook workbook = ExcelUtil.getWorkbook(filePath);

        Sheet sheat = workbook.createSheet("haber");

        Row row = sheat.getRow(0);

        Cell cell = row.getCell(0);

        System.out.println("Nombre de la hoja: " + sheat);

    }

}
