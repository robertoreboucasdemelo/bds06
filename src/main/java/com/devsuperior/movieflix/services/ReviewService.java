package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.constants.Constants;
import com.devsuperior.movieflix.dto.ReviewMinDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;



@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;

	
	@Transactional(readOnly = true)
	public List<ReviewMinDTO> findReviewByMovie(Long id) {
		Optional<Movie> movie = movieRepository.findById(id);
		Movie movieEntity = movie.orElseThrow(()-> new ResourceNotFoundException(Constants.ENTITY_NOT_FOUND));
		
		List<Review> list = repository.findByMovie(movieEntity);
		
		if (list.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ENTITY_NOT_FOUND);
		}
		return list.stream().map(item-> new ReviewMinDTO(item, item.getUser())).collect(Collectors.toList());
	}
	
	@Transactional
	public ReviewMinDTO insert(ReviewMinDTO dto) {
		try {
			
			User user = authService.authenticated();		
			Movie movieEntity = movieRepository.getOne(dto.getMovieId());
			
			Review entity = new Review();
			entity.setText(dto.getText());
			entity.setMovie(movieEntity);
			entity.setUser(user);
			
			entity = repository.save(entity);
			return new ReviewMinDTO(entity, user);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(Constants.ID_NOT_FOUND + dto.getMovieId());
		}	
		
	}

}
