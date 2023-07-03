package co.vinod.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.vinod.entity.Shipper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ShipperDaoImpl implements ShipperDao {

	@Autowired
	private EntityManager em;
	
	public ShipperDaoImpl() {
		log.trace("ShipperDaoImpl constructor called");
	}

	@Override
	public Shipper findById(Integer shipperId) {
		return em.find(Shipper.class, shipperId);
	}

	@Override
	public List<Shipper> findAll() {
		return em.createQuery("from Shipper", Shipper.class).getResultList();
	}

}
