package HSSF;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class Write {
	
	private HSSFWorkbook wb;
	private HSSFSheet s = null;
	private HSSFRow Row = null;
	private HSSFCell Cell = null;
	private FileOutputStream out;
	
	public Write (String dataname){
		try{
		out = new FileOutputStream(dataname);
		wb = new HSSFWorkbook();
		s = wb.createSheet();
	}catch(IOException e){
		System.out.println("Datei existiert bereits");
	}
	}
	
	public void createSheet(String sheetname){
		s = wb.createSheet(sheetname);
	}
	
	public void createnewRow(int number){
		Row = s.createRow(number);
	}
	
	public void createnewCell(int number,String value){
		Cell = Row.createCell(number); 	
		Cell.setCellValue(value);
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
