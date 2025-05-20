package readFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import readfile.case1.Case1;




/*
@author YANPIER
 */
public class ExcelUtil {
    
    /**
     * Identifies the extension type of an Excel file
     * 
     * @param filename  File name with its extension
     * @return The extension ("xlsx" or "xls") if the file is an Excel file,
     *         otherwise, returns {@code null}.
     */
    public static String identifyWorkbookType(String filename){
        
        if (filename == null) {
            return null;
        }
        
        // Split the file name by "." to gets its parts.
        String[] fileNames = filename.split("\\.");
        int amount = fileNames.length;
        
        // Gets the extension the last element in the "fileName" array,
        String extension = fileNames[amount-1];
        
        // If the file has no extension or ends with a dot, it's consider invalid
        if (amount < 2 || extension.trim().isEmpty()) {
            return null;
        } 
        
        // Check if the extension matches know Excel formats.
        else if (extension.equalsIgnoreCase("xlsx") || extension.equalsIgnoreCase("xls")) {
            return extension;
        }
        
        return null;
    }
    
    /**
     * Generates a workbook instance depending of the Excel file extension.
     * 
     * @param urlFile file path where file is located.
     * @return A Workbook instance representing the Excel file. 
     * @throws IllegalArgumentException if the file extension is not supported
     * @throws IOException if the file not found or cannot be opened
     */
    public static Workbook getWorkbook(String urlFile) throws IOException {
        
        //Generate a file reference
        File excelFile = new File(urlFile);
        
        try (InputStream input = ExcelUtil.class.getResourceAsStream(urlFile)) {
            
            if (input == null) {
                throw new IOException("File not found or cannot be opened: "+urlFile);
            }
            
            //Get file extension name
            String fileExtension = ExcelUtil.identifyWorkbookType(excelFile.getName());
            
            // Validate that the extension is supported
            if (fileExtension == null) {
                throw new IllegalArgumentException("The file does not have a supported extension (.xls or .xlsx): " + excelFile.getAbsolutePath());
            }
            
            // Create the corresponding workbook
            if (fileExtension.equals("xlsx")) {
                return new XSSFWorkbook(input);
            } else {
                return new HSSFWorkbook(input);
            } 
        }
    }
    
    
    
}
