package th.co.orcsoft.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.hql.internal.ast.tree.IsNullLogicOperatorNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import th.co.orcsoft.dao.IOrderDao;
import th.co.orcsoft.dto.BillReport;
import th.co.orcsoft.dto.MenuReport;


@Service
public class ReportServiceImpl {

	@Autowired
	private IOrderDao orderdao;

	public ArrayList<MenuReport> menuReport(String date) {

		ArrayList<MenuReport> report = new ArrayList<MenuReport>();

		try {

			List<Object[]> list = orderdao.findAllMenuReport(date);

			for (Object[] obj : list) {

				MenuReport menureport = new MenuReport();
				menureport.setMenu(obj[0].toString());
				menureport.setQuantity((long) obj[1]);

				report.add(menureport);
			}

		} catch (Exception ex) {

			ex.getStackTrace();
			throw ex;
		}

		return report;
	}

	public HashMap<String, BillReport> billReport(String date) {

		HashMap<String, BillReport> map = new HashMap<String, BillReport>();

		try {

			List<Object[]> list = orderdao.findAllBillReport(date);

			for (Object[] obj : list) {

				if (!map.containsKey(obj[1].toString())) {

					BillReport billreport = new BillReport();
					billreport.setBill(obj[0].toString());
					billreport.setTotal(Integer.parseInt(obj[2].toString().substring(1)));

					map.put(obj[1].toString(), billreport);

				} else {

					int total = map.get(obj[1].toString()).getTotal();
					int price = Integer.parseInt(obj[2].toString().substring(1));
					total = total + price;

					map.get(obj[1].toString()).setTotal(total);

				}
			}

		} catch (Exception ex) {

			ex.getStackTrace();
			throw ex;
		}

		return map;
	}
	
	private Row row;
	private int rowNum = 1;
	private int total_sum = 0;

	public ByteArrayInputStream genExcelFile(Date date) throws IOException {
			
		Row headerRow;
		DateFormat dateFormat;
		
		//dateFormat = new SimpleDateFormat("ddMMyyyy_hhmmss");
		//String datetime = dateFormat.format(new Date()).toString();
		
		//String filename = "report_"+datetime+".xlsx";
		
		String[] bill_columns = {"Bill", "Price"};
		String[] menu_columns = {"Menu", "Quantity"};

		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Reporting");
		
		headerRow = sheet.createRow(0);
		for (int i = 0; i < bill_columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(bill_columns[i]);
		}
		
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String _date = dateFormat.format(date).toString();
		
		//Get data bill report
		HashMap<String, BillReport> bill_report = billReport(_date);
		
		bill_report.forEach((key, value) -> {
			
			row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(Integer.parseInt(bill_report.get(key).getBill()));
			row.createCell(1).setCellValue(bill_report.get(key).getTotal());
			
			total_sum = total_sum + bill_report.get(key).getTotal();
		});
		
		row = sheet.createRow(rowNum++);
		row.createCell(0).setCellValue("Total");
		row.createCell(1).setCellValue(total_sum);
			
		row = sheet.createRow(rowNum++);
		row.createCell(0).setCellValue("");
		row.createCell(1).setCellValue("");
		
		headerRow = sheet.createRow(rowNum++);
		for (int i = 0; i < menu_columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(menu_columns[i]);
		}
		
		//Get data menu report
		ArrayList<MenuReport> menu_report = menuReport(_date);
		
		for(MenuReport report : menu_report) {
			row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(report.getMenu());
			row.createCell(1).setCellValue(report.getQuantity());
		}
		
	    for(int i = 0; i < bill_columns.length; i++) {
	    	sheet.autoSizeColumn(i);
	    }
		
		this.rowNum = 1;
		this.total_sum = 0;

		try {
			
			//FileOutputStream fileOut = new FileOutputStream("D:\\Orcsoft\\training_restaurant_project\\temp\\"+filename);
			ByteArrayOutputStream byte_arr = new ByteArrayOutputStream();
			workbook.write(byte_arr);
			workbook.close();
			
			return new ByteArrayInputStream(byte_arr.toByteArray());
			 
		} catch (FileNotFoundException ex) {
			
			workbook.close();
			ex.printStackTrace();
			throw ex;
		
		}
	}
	
	public ByteArrayInputStream getPdfFile(Date date) throws Exception {
		
		DateFormat dateFormat;
		
		// dateFormat = new SimpleDateFormat("ddMMyyyy_hhmmss");
		// String datetime = dateFormat.format(new Date()).toString();
		
		Document document = new Document();
		
		// String filename = "reporting_" + datetime + ".pdf";
		
		PdfWriter writer = null;
		
		ByteArrayOutputStream byte_arr = new ByteArrayOutputStream();
		
		// Model model = new Model();
		
		try {
			
			dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String _date = dateFormat.format(date).toString();
			
			writer = PdfWriter.getInstance(document, byte_arr);
			
			//writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Orcsoft\\training_restaurant_project\\temp\\" + filename));
			document.open();
			
			String[] bill_columns = {"Bill", "Price"};
			String[] menu_columns = {"Menu", "Quantity"};
			
			//Write bill table report
			PdfPTable bill_table = new PdfPTable(2);
			bill_table.setWidthPercentage(100);
			bill_table.setSpacingBefore(11f);
			bill_table.setSpacingAfter(11f);
			
			float[] colWidth = {2f, 2f};
			bill_table.setWidths(colWidth);

			for(String str_bill : bill_columns) {
				
				PdfPCell column = new PdfPCell(new Paragraph(str_bill));
				column.setHorizontalAlignment(Element.ALIGN_CENTER); //Set text center
				//column.setBackgroundColor(new BaseColor(199, 0, 57)); //Set backgroundcolor
				bill_table.addCell(column);
			}
			
			HashMap<String, BillReport> bill_report = billReport(_date);
			
			bill_report.forEach((key, value) -> {
				
				PdfPCell cell_bill = new PdfPCell(new Paragraph(bill_report.get(key).getBill()));
				cell_bill.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				String total = String.valueOf(bill_report.get(key).getTotal());
				PdfPCell cell_total = new PdfPCell(new Paragraph(total));
				cell_total.setHorizontalAlignment(Element.ALIGN_CENTER);
			
				bill_table.addCell(cell_bill);
				bill_table.addCell(cell_total);
				
				this.total_sum = this.total_sum + bill_report.get(key).getTotal();
				
			});
			
			//Write price total
			PdfPCell total_text = new PdfPCell(new Paragraph("Total"));
			total_text.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			String value = String.valueOf(this.total_sum);
			PdfPCell total_value = new PdfPCell(new Paragraph(value));
			total_value.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			bill_table.addCell(total_text);
			bill_table.addCell(total_value);
			
			//Write menu table report
			PdfPTable menu_table = new PdfPTable(2);
			menu_table.setWidthPercentage(100);
			menu_table.setSpacingBefore(11f);
			menu_table.setSpacingAfter(11f);
			
			menu_table.setWidths(colWidth);
			
			for(String str_menu : menu_columns) {
				PdfPCell column = new PdfPCell(new Paragraph(str_menu));
				column.setHorizontalAlignment(Element.ALIGN_CENTER); //Set text center
				menu_table.addCell(column);
			}
			
			ArrayList<MenuReport> menu_report = menuReport(_date);
			
			for(MenuReport report : menu_report) {
				PdfPCell cell_menu = new PdfPCell(new Paragraph(report.getMenu()));
				cell_menu.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				String quantity = String.valueOf(report.getQuantity());
				PdfPCell cell_quantity = new PdfPCell(new Paragraph(quantity));
				cell_quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
				
				menu_table.addCell(cell_menu);
				menu_table.addCell(cell_quantity);
			}
			
			dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
			String _datetime = dateFormat.format(date).toString();
			
			Paragraph datetime_text = new Paragraph();
			datetime_text.setAlignment(Element.ALIGN_RIGHT);
			datetime_text.add("Date : " + _datetime);
			document.add(datetime_text);
			
			Paragraph title = new Paragraph();
			title.setAlignment(Element.ALIGN_CENTER);
			title.add("Reporting");
			document.add(title);
			
			document.add(new Paragraph("Bill reporting"));
			document.add(bill_table);
			document.add(new Paragraph("Menu reporting"));
			document.add(menu_table);
			document.close();
			writer.close();
			
			return new ByteArrayInputStream(byte_arr.toByteArray());
			
		}catch (Exception ex) {
			
			ex.getStackTrace();
			document.close();
			writer.close();
			
			throw ex;
			
		}
	}
}
