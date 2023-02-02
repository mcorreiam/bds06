package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.UserRepository;

public class ReviewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo requerido")
	private String text;
	private Long movieId;
	private UserDTO user;	

	@Autowired
	UserRepository userRepository;
	
	public ReviewDTO() {
	}
	
	public ReviewDTO(Review review) {
		id = review.getId();
		text = review.getText();
		movieId = review.getMovie().getId();
		user = new UserDTO(
				review.getUser().getId(), 
				review.getUser().getName(), 
				review.getUser().getEmail()
				);
		
//		Long id, String title, String subTitle, Integer year, String imgUrl, Long genreId
	}

	public ReviewDTO(Long id, String text, UserDTO user, Long movieId) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
		this.movieId = movieId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movie) {
		this.movieId = movie;
	}

	@Override
	public String toString() {
		return "ReviewDTO [id=" + id + ", text=" + text + ", user=" + user + ", movie=" + movieId + "]";
	}
	
	
	
}
