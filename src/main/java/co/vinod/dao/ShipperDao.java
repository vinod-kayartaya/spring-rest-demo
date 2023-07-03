package co.vinod.dao;

import java.util.List;

import co.vinod.entity.Shipper;

public interface ShipperDao {
	public Shipper findById(Integer shipperId);

	public List<Shipper> findAll();
}
