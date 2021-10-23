package com.devsuperior.movieflix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieMinDTO;
import com.devsuperior.movieflix.dto.ReviewMinDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value="/movies")
public class MovieResource {
	
	@Autowired
	private MovieService service;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping
	public ResponseEntity<Page<MovieMinDTO>> findPaged(Pageable pageable,
			@RequestParam(value= "genreId", defaultValue= "0") Long genreId){	
		Page<MovieMinDTO> page = service.findPaged(pageable, genreId);
		return ResponseEntity.ok().body(page);		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id){	
		MovieDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);		
	}
	
	@GetMapping(value = "/{id}/reviews")
	public ResponseEntity<List<ReviewMinDTO>> findReviewByMovie(@PathVariable Long id){	
		List<ReviewMinDTO> list = reviewService.findReviewByMovie(id);
		return ResponseEntity.ok().body(list);		
	}

}
