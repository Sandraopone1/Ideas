package com.sandra.ideas.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.sandra.ideas.models.Idea;
import com.sandra.ideas.repositories.IdeaRepository;

@Service
public class IdeaService {
	
private IdeaRepository ideaRepository;

	
	public IdeaService(IdeaRepository ideaRepository) {
		this.ideaRepository = ideaRepository;
	
	}
	
	public void create(Idea idea) {
		ideaRepository.save(idea);
	}
	
	public ArrayList<Idea> all(){
		return (ArrayList<Idea>) ideaRepository.findAll();
	}
	public Idea findById(Long id) {
		return ideaRepository.findById(id).orElse(null);
	}
	
	public void destroy(Long id) {
		ideaRepository.deleteById(id);
	}
	
	public void update(Idea idea) {
		ideaRepository.save(idea);
	}
	

}
