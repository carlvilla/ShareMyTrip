package uo.sdi.persistence;

import java.util.List;

import uo.sdi.model.Seat;
import uo.sdi.persistence.util.GenericDao;

public interface SeatDao extends GenericDao<Seat, Long[]> {

	Seat findByUserAndTrip(Long userId, Long tripId);

	List<Seat> findByUser(Long idUsuario);

	List<Seat> findByTrip(Long idViaje);

}
