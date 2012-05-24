package HSSF;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;


public class Write {
	
	private Workbook wb;
	private Row headerrow = null;
	private HSSFCell headercell = null;
	private FileOutputStream out;
	private String datan;
	private CellStyle style;
	private InputStream inp;
	
	
	public Write (String dataname){
		datan = dataname;
	    File file = new File(datan);
	    if(!file.exists()){
	    	//out = new FileOutputStream(datan);
			wb = new HSSFWorkbook();
			wb.createSheet("Spiel"+(wb.getNumberOfSheets()+1));
	    }
	    else{
	    	 try {
			   inp = new FileInputStream(dataname);
		
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 
	    	 try {
				wb = new HSSFWorkbook(inp);
				wb.createSheet("Spiel"+(wb.getNumberOfSheets()+1));
				wb.setActiveSheet(wb.getNumberOfSheets()-1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	public void createheader(){
		
		style = wb.createCellStyle();
		style.setFillForegroundColor(HSSFColor.YELLOW.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		org.apache.poi.ss.usermodel.Font font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		headerrow = wb.getSheet(wb.getSheetName(wb.getActiveSheetIndex())).createRow(0);
		headerrow.createCell(0);
		headerrow.getCell(0).setCellStyle(style);
		headerrow.getCell(0).setCellValue("Spielzug");
		headerrow.createCell(1);
		headerrow.getCell(1).setCellStyle(style);
		headerrow.getCell(1).setCellValue("Berechnungsdauer");
		headerrow.createCell(2);
		headerrow.getCell(2).setCellStyle(style);
		headerrow.getCell(2).setCellValue("Spieler");
			}
	
	public void createSheet(){
		wb.createSheet("Spiel"+(wb.getNumberOfSheets()+1));
	}
	
	public void createnewRow(int number,String s){
		wb.getSheetAt(wb.getActiveSheetIndex()).createRow(number);
	}
	
	public void createnewCell(int number,String value,int rowindex){
		Cell cell = wb.getSheet(wb.getSheetName(wb.getActiveSheetIndex())).getRow(rowindex).createCell(number);
		cell.setCellValue(value);
	}
	
	public Cell getCell(int cellnumber,String sheetname,int rowindex){
		return wb.getSheet(sheetname).getRow(rowindex).getCell(cellnumber);
	}
	
		
	public void writeout(){
		   try {
			out = new FileOutputStream(datan);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			wb.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
