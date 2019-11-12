package com.cg.pizzaordering.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.pizzaordering.dto.Store;
import com.cg.pizzaordering.dto.Customer;
import com.cg.pizzaordering.dto.Pizza;

import com.cg.pizzaordering.exception.PizzaException;
//import com.cg.pizzaordering.service.ExcelGenerator;
import com.cg.pizzaordering.service.IAdminService;
import com.cg.pizzaordering.service.ICustomerService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	IAdminService adminService;
	@Autowired
	ICustomerService customerService;

	Long storeID = null;
	Long pizzaID = null;

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	// ----------------------Store--------------------------

	/**
	 * Show all 
	 * 
	 * @return
	 * @throws PizzaException
	 */
	@GetMapping("/stores")
	public ResponseEntity<List<Store>> getCities() {
		logger.info("getStores in Controller");
		List<Store> storeList = adminService.showStore();
		if (storeList.size() == 0) {
			logger.info("No cities present");
			return new ResponseEntity<List<Store>>(HttpStatus.NO_CONTENT);
		} else {
			logger.info("Returning store list");
			return new ResponseEntity<List<Store>>(storeList, HttpStatus.OK);
		}
	}

	/**
	 * Find store by storeId
	 * 
	 * @param storeId
	 * @return
	 */
	@GetMapping("/stores/{storeId}")
	public Store getStore(@PathVariable long storeId) {
		logger.trace("getStore in Controller");
		return adminService.findStoreById(storeId);
	}

	/**
	 * Add store
	 * 
	 * @param store
	 * @return
	 */
	@PostMapping("/stores")
	public ResponseEntity<?> addStore(@RequestBody Store store) {
		logger.info("Store added in Controller");
		return new ResponseEntity<Store>(adminService.addStore(store), HttpStatus.OK);
	}
	
	
	@PutMapping("/stores")
	public ResponseEntity<Store> updateStore(@RequestParam("storeId") long storeId, @RequestBody Store store) {
		adminService.updateStore(storeId, store);
		logger.trace("updatePizza in Controller");
		return new ResponseEntity<Store>(store, HttpStatus.OK);
	}

	/**
	 * Delete store by storeId
	 */
	@DeleteMapping("/stores/{storeId}")
	public ResponseEntity<?> deleteStore(@PathVariable long storeId) {

		adminService.removeStore(storeId);
		logger.trace("deleteStore in Controller");
		return new ResponseEntity<String>("Store with Id: " + storeId + " deleted", HttpStatus.OK);
	}

	/**
	 * Download excel
	 * 
	 * @return File
	 * @throws IOException
	 */
//	@GetMapping("/cities/download/cities.xlsx")
//	public ResponseEntity<InputStreamResource> downloadStore() throws IOException {
//		List<Store> storeList = customerService.getStoreList();
//
//		ByteArrayInputStream in = ExcelGenerator.customersToExcel(storeList);
//		// return IOUtils.toByteArray(in);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
//		logger.trace("Download");
//		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
//	}

	/**
	 * Upload file
	 * 
	 * @param reapExcelDataFile
	 * @throws IOException
	 */
	@PostMapping("/upload/store")
	public void uploadStore(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			Store tempStore = new Store();

			XSSFRow row = worksheet.getRow(i);

			tempStore.setStoreName(row.getCell(0).getStringCellValue());
			adminService.addStore(tempStore);
			workbook.close();

		}
	}

	// ----------------------Pizza--------------------------

	/**
	 * Get all pizzas in a store
	 * 
	 * @param storeId
	 * @return
	 */
	@GetMapping("/pizzas/{storeId}")
	public ResponseEntity<List<Pizza>> getPizzas(@PathVariable long storeId) {
		logger.info("getPizzas in Controller");
		List<Pizza> pizzaList = adminService.showPizzas(storeId);
		if (pizzaList.size() == 0) {
			logger.info("No pizzas present in store with Id: " + storeId);
			return new ResponseEntity<List<Pizza>>(HttpStatus.NO_CONTENT);
		} else {
			logger.info("Returning pizza list");
			return new ResponseEntity<List<Pizza>>(pizzaList, HttpStatus.OK);
		}
	}

	/**
	 * Find a pizza using pizzaId
	 * 
	 * @param pizzaId
	 * @return
	 */
	@GetMapping("/pizzas")
	public Pizza getPizza(@RequestParam long pizzaId) {
		logger.trace("getPizza in Controller");
		return adminService.viewSinglePizza(pizzaId);
	}

	/**
	 * Find pizza by Id
	 * 
	 * @param storeId
	 * @param pizza
	 * @return
	 * @throws PizzaException
	 */
	@PostMapping("/pizzas")
	public ResponseEntity<Pizza> addPizza(@RequestParam("storeId") long storeId, @RequestBody Pizza pizza) {
		adminService.addPizza(storeId, pizza);
		logger.trace("addPizza in Controller");
		return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
	}

	/**
	 * Add pizza
	 * 
	 * @param pizzaId
	 * @param pizza
	 * @return
	 */
	@PutMapping("/pizzas")
	public ResponseEntity<Pizza> updatePizza(@RequestParam("pizzaId") long pizzaId, @RequestBody Pizza pizza) {
		adminService.updatePizza(pizzaId, pizza);
		logger.trace("updatePizza in Controller");
		return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
	}

	/**
	 * Delete pizza
	 * 
	 * @param pizzaId
	 * @return
	 */
	@DeleteMapping("/pizzas/{pizzaId}")
	public ResponseEntity<?> deletePizza(@PathVariable long pizzaId) {
		logger.info("deletePizza in Controller");
		adminService.removePizza(pizzaId);
		return new ResponseEntity<String>("Pizza deleted with Id: " + pizzaId, HttpStatus.OK);
	}


	@GetMapping("/finduser")
	public ResponseEntity<?> findUser(@RequestParam("userEmail") String userEmail) {
		try {
			logger.info("Fetching user object linked with user Email..");
			Customer customer = adminService.findUser(userEmail);
//			System.out.println(customer.toString());
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (PizzaException exception) {
			logger.info("PizzaException caught in find user controller..");
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
