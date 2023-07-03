package co.vinod.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.vinod.dao.ShipperDao;
import co.vinod.entity.Shipper;
import co.vinod.model.CustomResponse;
import co.vinod.model.ShipperList;

@RestController
@RequestMapping("/api/shippers")
public class ShipperController {

	@Autowired
	private ShipperDao dao;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> handleGetAll() {
		return ResponseEntity.ok(new ShipperList(dao.findAll()));
	}

	@GetMapping(path = "/{shipperId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> handleGetOne(@PathVariable Integer shipperId) {
		Shipper shipper = dao.findById(shipperId);

		CustomResponse err = new CustomResponse("No data found for id " + shipperId);
		if (shipper == null) {
			return ResponseEntity.status(404).body(err);
		}

		return ResponseEntity.ok(shipper);
	}

}
