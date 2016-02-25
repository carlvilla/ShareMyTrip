package uo.sdi.persistence;

import java.util.Date;

import uo.sdi.model.Trip;
import uo.sdi.persistence.util.GenericDao;

public interface TripDao extends GenericDao<Trip, Long> {

	Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate);

}
