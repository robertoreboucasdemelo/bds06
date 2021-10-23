package com.devsuperior.movieflix.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

public class MovieDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;	
	private String title;
	private String subTitle;
	private Integer year;
	private String imgUrl;
	private String synopsis;
	private List<ReviewMinDTO> reviews = new ArrayList<>();
	private GenreMinDTO genre;
	
	public MovieDTO() {
	}
	
	public MovieDTO(Long id, String title, String subTitle, Integer year, String imgUrl, String synopsis, GenreMinDTO genre) {
		this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.year = year;
		this.imgUrl = imgUrl;
		this.synopsis = synopsis;
		this.genre = genre;
	}
	
	public MovieDTO(Movie entity) {
		id = entity.getId();
		title = entity.getTitle();
		subTitle = entity.getSubTitle();
		year = entity.getYear();
		imgUrl = entity.getImgUrl();
		synopsis = entity.getSynopsis();
	}
	
	public MovieDTO(Movie entity, Genre genreEntity) {
		this(entity);
		genre = new GenreMinDTO(genreEntity);
	}
	
	public MovieDTO(Movie entity,  Set<Review> reviews) {
		this(entity);
		reviews.forEach(item-> this.reviews.add(new ReviewMinDTO(item)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public GenreMinDTO getGenre() {
		return genre;
	}

	public void setGenre(GenreMinDTO genre) {
		this.genre = genre;
	}

	public List<ReviewMinDTO> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewMinDTO> reviews) {
		this.reviews = reviews;
	}

	
	
	

	
	
	
}
