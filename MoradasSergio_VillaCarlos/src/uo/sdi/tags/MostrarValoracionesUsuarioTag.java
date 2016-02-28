package uo.sdi.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import uo.sdi.model.Rating;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.RatingDao;

public class MostrarValoracionesUsuarioTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private Long idUsuario;

	public int doEndTag() {

		RatingDao ratingDao = PersistenceFactory.newRatingDao();

		List<Rating> comentarios = ratingDao.findByAbout(idUsuario);

		JspWriter out = pageContext.getOut();
		
		try {
		
		if(comentarios.size()>0){
		
		out.println("<div id='comentarios'>");
			
		out.println("<div id='comentario'>");
			out.println("Estos son los comentarios que te han hecho tus "
					+ "compañeros de viaje. Con ellos podrás saber que tienes "
					+ "(o no) que mejorar");
		out.println("</div>");
			
		out.println("<table class='table'>");
		
		out.println("<tr>");

		out.println("<th> Puntuación</th>");
		out.println("<th> Comentario</th>");

		out.println("</tr>");


			for (Rating comentario : comentarios) {

				
				out.println("<tr>");

				out.println("<td>" + comentario.getValue() + "</td>");
				out.println("<td><i>'" + comentario.getComment() + "'</i></td>");

				out.println("</tr>");

			}

		out.println("</table>");

		out.println("</div>");
		
		}else{
			
			out.println("<div id='info'>En esta página se mostrarán notificaciones e "
					+ "información importante para ti, como los comentarios "
					+ "que hagan sobre ti. Cuanto mejor sean tus comentarios "
					+ "y tu puntuación, en más viajes "
					+ "serás aceptado asi que... ¡Portate bien! </div>");
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return EVAL_PAGE;
	}



	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsario) {
		this.idUsuario = idUsario;
	}


}
