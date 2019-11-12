//package com.cg.pizzaordering.service;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.web.servlet.view.document.AbstractXlsView;
//
//import com.cg.pizzaordering.dto.Store;
//
//public class ExcelReportView extends AbstractXlsView {
//
//	@Override
//	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//
//		response.setHeader("Content-Disposition", "attachment;filename=\"userData.xls\"");
//		@SuppressWarnings("unchecked")
//		List<Store> cityList = (List<Store>) model.get("cityList");
//		Sheet sheet = workbook.createSheet("Store Data");
//		Row header = sheet.createRow(0);
//		header.createCell(0).setCellValue("Store ID");
//		header.createCell(1).setCellValue("Store Name");
//
//		int rowNum = 1;
//		for (Store city:cityList) {
//			Row row = sheet.createRow(rowNum++);
//			row.createCell(0).setCellValue(city.getCityId());
//			row.createCell(1).setCellValue(city.getCityName());
//		}
//	}
//}
//
