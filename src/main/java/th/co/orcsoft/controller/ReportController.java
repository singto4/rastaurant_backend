package th.co.orcsoft.controller;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import th.co.orcsoft.dto.BillReport;
import th.co.orcsoft.dto.HeaderDto;
import th.co.orcsoft.dto.MenuReport;
import th.co.orcsoft.dto.Model;
import th.co.orcsoft.service.impl.JwtService;
import th.co.orcsoft.service.impl.ReportServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/resteurant/report")
public class ReportController {
	
	@Autowired
	private ReportServiceImpl reportservice;
	
	@Autowired
	private JwtService jwtservice;

	@RequestMapping(method= {RequestMethod.POST}, path="/menureport")
	public Model menuReport(@RequestBody Date date, @RequestHeader String Authorization) {
		
		Model model = new Model();
		
		try {
			
			jwtservice.check_token(Authorization);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String time = dateFormat.format(date).toString();
			ArrayList<MenuReport> menureport = reportservice.menuReport(time);
			model.setBody(menureport);
			
		}catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			
			HeaderDto header = new HeaderDto();
			header.setStatus("Unsuccess");
			header.setErrorcode("401");
			header.setErrormessage(ex.getMessage());
			
			model.setHeader(header);
		}
		
		return model;	
	}
	
	@RequestMapping(method= {RequestMethod.POST}, path="/billreport")
	public Model billReport(@RequestBody Date date, @RequestHeader String Authorization) {
		
		Model model = new Model();
		
		try {
			
			jwtservice.check_token(Authorization);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String time = dateFormat.format(date).toString();
			HashMap<String, BillReport> map = reportservice.billReport(time);
			model.setBody(map);
			
		}catch (Exception ex) {
			
			System.out.println(ex.getMessage());
			
			HeaderDto header = new HeaderDto();
			header.setStatus("Unsuccess");
			header.setErrorcode("401");
			header.setErrormessage(ex.getMessage());
			
			model.setHeader(header);
		}
		
		return model;	
	}
	
	@RequestMapping(method= {RequestMethod.POST}, path="/genexcel")
	public ResponseEntity<InputStreamResource> genExcelFile(@RequestBody Date date, @RequestHeader String Authorization) {

		try {
			
			jwtservice.check_token(Authorization);
			ByteArrayInputStream byte_arr = reportservice.genExcelFile(date);
			
			return ResponseEntity
					.ok()
					.header("Content-Disposition", "attachement; filename=report.xlsx")
					.body(new InputStreamResource(byte_arr));
			
		} catch (Exception ex) {
			
			ex.getStackTrace();
			
			return null;
		} 
	}
	
	@RequestMapping(method= {RequestMethod.POST}, path="/genpdf")
	public ResponseEntity<InputStreamResource> genPdfFile(@RequestBody Date date, @RequestHeader String Authorization) {

		ByteArrayInputStream byte_arr = null;
		
		try {
			
			jwtservice.check_token(Authorization);
			byte_arr = reportservice.getPdfFile(date);
			
			return ResponseEntity
					.ok()
					.header("Content-Disposition", "inline; filename=report.pdf")
					.contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(byte_arr));
				
		}catch (Exception ex) {
			
			ex.getStackTrace();

			return null;
		}
	}
}
