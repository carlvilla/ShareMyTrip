package uo.sdi.tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;

public class FechaYhoraTag extends TagSupport{
	
	private static final long serialVersionUID = 1L;
	
	private Long idViaje;
	
	private String date;


	public int doEndTag(){
		TripDao dao = PersistenceFactory.newTripDao();
		Trip trip = dao.findById(idViaje);
		
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat(
				"dd/MM/yyyy-HH:mm:ss");
		Date fecha;
		if(date.compareTo("departureDate")==0){
			fecha= trip.getDepartureDate();
		}
		else if(date.compareTo("arrivalDate")==0){
			fecha = trip.getArrivalDate();
		}else
			fecha = trip.getClosingDate();
		
		
		JspWriter out = pageContext.getOut();
		
		try {
			out.println(formatoDeFecha.format(fecha));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}


	public Long getIdViaje() {
		return idViaje;
	}


	public void setIdViaje(Long idViaje) {
		this.idViaje = idViaje;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	
}
