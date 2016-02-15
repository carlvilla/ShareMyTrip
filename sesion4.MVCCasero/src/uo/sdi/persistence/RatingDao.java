package uo.sdi.persistence;

import uo.sdi.model.Rating;
import uo.sdi.persistence.util.GenericDao;

public interface RatingDao extends GenericDao<Rating, Long> {

	Rating findByAboutFrom(
			Long aboutUserId, 
			Long aboutTripId, 
			Long fromUserId, 
			Long fromTripId
		); 
	
}
