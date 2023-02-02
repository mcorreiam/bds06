package com.devsuperior.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;
	
			
	@Transactional(readOnly = true)
	public List<ReviewDTO> findByMovie(Long id) {
		Movie movie = movieRepository.getOne(id);
		List<Review> list = repository.findAllByMovie(movie);		
		
		return list.stream().map(x -> new ReviewDTO(x)).toList();
	}


	public ReviewDTO insert(ReviewDTO dto) {
		Movie movie = movieRepository.getOne(dto.getMovieId());
		User user = authService.authenticated();
		Review review = new Review();
		review.setText(dto.getText());
		review.setMovie(movie);
		review = repository.save(review);
		review.setUser(user);
		return new ReviewDTO(review);
	}
}
