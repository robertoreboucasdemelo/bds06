package com.devsuperior.movieflix.constants;

public class Queries {
	
	public static final String  JPQL_MOVIE = 
			"SELECT new com.devsuperior.movieflix.dto.MovieMinDTO("
		  + "obj.id, obj.title, obj.subTitle, obj.year, obj.imgUrl) "
		  + "FROM Movie obj "
		  + "WHERE (:genre IS NULL "
		  + "OR obj.genre IN :genre) "
		  + "ORDER BY obj.title" ;
	

}
