package com.interlogica.parsecsv.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.interlogica.parsecsv.domains.CsvBean;
import com.interlogica.parsecsv.domains.Phone;
import com.interlogica.parsecsv.domains.Status;
import com.interlogica.parsecsv.repository.PhoneRepository;
import com.interlogica.parsecsv.service.ValidationService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Controller
public class PhoneController {

	private final PhoneRepository phoneRepo;
	private final ValidationService valService;

	public PhoneController(PhoneRepository phoneRepo, ValidationService valService) {
		this.phoneRepo = phoneRepo;
		this.valService = valService;
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/upload-csv")
	public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

		if (file.isEmpty()) {
			model.addAttribute("message", "Please select a CSV file to upload.");
			model.addAttribute("status", false);
		} else {

			// parse CSV file to create a list of CsvPhone objects

			try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"))) {

				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
				List<CsvBean> entries = csvReader.readAll().stream().map(r -> new CsvBean(r[0], r[1]))
						.collect(Collectors.toList());
				System.out.println("Parsed " + entries.size() + " entries");

				// save csv phone list list on model
				model.addAttribute("entries", entries);
				model.addAttribute("status", true);

				// clean-up the DB before importing
				phoneRepo.deleteAll();
				phoneRepo.flush();
				System.out.println("DB cleaned up");

				// store phones to databasa
				List<Phone> phones = entries.stream()
						.map(e -> valService.validatePhone(new Phone(e.getId(), e.getSmsPhone())))
						.collect(Collectors.toList());
				phoneRepo.saveAll(phones);

			} catch (Exception e) {
				model.addAttribute("message", "Exception while processing the CSV file: " + e.getMessage());
				model.addAttribute("status", false);
				e.printStackTrace();
			}
		}

		return "status";
	}

	@PostMapping("/validate")
	public String greetingSubmit(@RequestParam String phone, Model model) {
		System.out.println("/validate called for " + phone);
		model.addAttribute("phone", valService.validatePhone(new Phone(phone)));
		return "result";
	}

	@GetMapping(value = "/all")
	public String showAll(@RequestParam String numtype, Model model) {
		System.out.println("/all called for " + numtype);
		List<Phone> phones;
		if (numtype.equals("All")) {
			phones = phoneRepo.findAll();
		} else {
			System.out.println(Status.fromId(numtype));
			phones = phoneRepo.findByStatus(Status.fromId(numtype));
		}
		model.addAttribute("phones", phones);
		return "all";
	}

}
