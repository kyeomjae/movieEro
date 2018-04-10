package com.army.movieEro.sjMovies.model.dao;

import java.util.List;

import com.army.movieEro.sjMovies.model.vo.MovieDetailVo;
import com.army.movieEro.sjMovies.model.vo.MovieInfoVo;
import com.army.movieEro.sjMovies.model.vo.MovieReviewVo;
import com.army.movieEro.sjMovies.model.vo.MovieVisualVo;

public interface MovieDao {
	public List<MovieInfoVo> loadMovieList();
	
	public List<MovieInfoVo> loadMovieRating();
	
	public List<MovieInfoVo> loadMovieLecnt();
	
	public List<MovieDetailVo> loadSummary(String MVInfoSeq);
	
	public List<MovieInfoVo> loadSpecInfo(String MVInfoSeq);
	
	public List<MovieVisualVo> loadTrailer(String MVInfoSeq);
	
	public List<MovieVisualVo> loadStillcut(String MVInfoSeq);
	
	public List<MovieReviewVo> loadReview(String MVInfoSeq);
}