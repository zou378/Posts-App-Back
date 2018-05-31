package tn.biat.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tn.biat.domain.Post;


//@CrossOrigin(origins="http://localhost:4200")
@RestController
@CrossOrigin("http://localhost:4200")
public class PostRestController {

	private static Map<Integer, Post> data;
	
	
	static {
		data = new HashMap<>();
		data.put(111, new Post(111,"Title 111","Body 111"));
		data.put(222, new Post(222,"Title 222","Body 222"));
		data.put(333, new Post(333,"Title 333","Body 333"));
	}
	
	@GetMapping(path="/api/posts")
	public List<Post> findAll(){
		return new ArrayList<Post>(data.values());
	}
	
//	@GetMapping(path="/api/posts/{id}")
//	public Post findById(@PathVariable int id){
//		return data.values().stream().filter(p -> p.getId()==id).findFirst().orElse(null);
//	}
	
	@GetMapping(path="/api/posts/{id}")
	public ResponseEntity<Post> findById(@PathVariable int id){
		
		Optional<Post> resultat =data.values().stream().filter(p -> p.getId()==id).findFirst();
		
		if (resultat.isPresent()) {
			return new ResponseEntity<Post>(resultat.get(),HttpStatus.OK); 
		}
		else  return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
		
	}
	
	
}
