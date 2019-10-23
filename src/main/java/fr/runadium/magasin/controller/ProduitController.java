package fr.runadium.magasin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.runadium.magasin.dao.ProductRepository;
import fr.runadium.magasin.model.Produit;

@RestController
@RequestMapping("/products")
public class ProduitController {

	@Autowired
	private ProductRepository produitRepository;

	
	/***********/ 
	// Get all products
	/**********/
	@GetMapping("/all")
	public List<Produit> getAllProducts() {
		List<Produit> products = new ArrayList<>();
		produitRepository.findAll().forEach(products::add);
		return products;
	}
	
	
	/***********/ 
	// Get one product
	/**********/ 
	
	@GetMapping("/{id}")
	public Optional<Produit> getOneProduct(@PathVariable("id") long id) {
		return produitRepository.findById(id);
	}
	
	/***********/ 
	// Delete one product
	/**********/ 
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {
		
		produitRepository.deleteById(id);
		return new ResponseEntity<>("product has been deleted!", HttpStatus.OK);
	}
	
	/***********/ 
	// Update one product
	/**********/ 
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Produit> updateProduct(@PathVariable("id") long id, @RequestBody Produit produit) {
		System.out.println("Update product with ID = " + id + "...");

		Optional<Produit> produitData = produitRepository.findById(id);

		if (produitData.isPresent()) {
			Produit p = produitData.get();
			p.setMarque(produit.getMarque());
			return new ResponseEntity<>(produitRepository.save(p), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	/***********/ 
	// Create one product
	/**********/
	
	@PostMapping(value = "/create")
	public Produit createProduct(@RequestBody Produit produit) {

		return produitRepository.save(produit);
	}
	
	
}