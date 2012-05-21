package HSSF;

import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;

public class Write {
	
	private HSSFWorkbook wb;
	private HSSFSheet s = null;
	private HSSFRow row = null;
	private HSSFRow headerrow = null;
	private HSSFCell headercell = null;
	private HSSFCell cell = null;
	private FileOutputStream out;
	HSSFCellStyle style;
	
	public Write (String dataname){
		try{
		out = new FileOutputStream(dataname);
		wb = new HSSFWorkbook();
		s = wb.createSheet();
		style = wb.createCellStyle().setFont(wb.createFont());
		headerrow = s.createRow(0);
		headercell = headerrow.createCell(0);
		headercell.setCellValue("Spiel");
		headerrow.setRowStyle(style);
	}catch(IOException e){
		System.out.println("Datei existiert bereits");
	}
	}
	
	public void createSheet(String sheetname){
		s = wb.createSheet(sheetname);
	}
	
	public void createnewRow(int number){
		row = s.createRow(number);
	}
	
	public void createnewCell(int number,String value){
		cell = row.createCell(number); 	
		cell.setCellValue(value);
	}
	
	public void cellStyle(int number){
		
	}
	
	public void writeout(){
		try {
			wb.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
