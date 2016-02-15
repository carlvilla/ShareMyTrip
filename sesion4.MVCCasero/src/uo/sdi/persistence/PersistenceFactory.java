package uo.sdi.persistence;

import uo.sdi.persistence.impl.ApplicationDaoJdbcImpl;
import uo.sdi.persistence.impl.RatingDaoJdbcImpl;
import uo.sdi.persistence.impl.SeatDaoJdbcImpl;
import uo.sdi.persistence.impl.TransactionJdbcImpl;
import uo.sdi.persistence.impl.TripDaoJdbcImpl;
import uo.sdi.persistence.impl.UserDaoJdbcImpl;

public class PersistenceFactory {
	
	public static Transaction newTransaction() {
		return new TransactionJdbcImpl();
	}
	
	public static RatingDao newRatingDao() {
		return new RatingDaoJdbcImpl();
	}

	public static UserDao newUserDao() {
		return new UserDaoJdbcImpl();
	}

	public static TripDao newTripDao() {
		return new TripDaoJdbcImpl();
	}

	public static SeatDao newSeatDao() {
		return new SeatDaoJdbcImpl();
	}

	public static ApplicationDao newApplicationDao() {
		return new ApplicationDaoJdbcImpl();
	}
}
