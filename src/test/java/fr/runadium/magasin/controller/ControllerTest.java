package fr.runadium.magasin.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import fr.runadium.magasin.model.Produit;

public class ControllerTest {

	RestTemplate restTemplate = new RestTemplate();
	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}


	
	 @Test
	    public void testGetAllProducts() {
	        HttpHeaders headers = new HttpHeaders();
	        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

	        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/products",
	            HttpMethod.GET, entity, String.class);

	        assertNotNull(response.getBody());
	    }
	 
	 @Test
	    public void testGetProductById() {
	        Produit product = restTemplate.getForObject(getRootUrl() + "/products/1", Produit.class);
	        System.out.println(product.getMarque());
	        assertNotNull(product);
	    }

	    /**
	     * Here we test that we can create a product using the POST method
	     */
	    @Test
	    public void testCreateProduct() {
	        Produit product = new Produit();
	        product.setMarque("BMW");
	       

	        ResponseEntity<Produit> postResponse = restTemplate.postForEntity(getRootUrl() + "/products", product, Produit.class);
	        assertNotNull(postResponse);
	        assertNotNull(postResponse.getBody());
	    }

	    /**
	     * Here we test that we can update a product's information using the PUT method
	     */
	    @Test
	    public void testUpdateProduct() {
	        int id = 1;
	        Produit product = restTemplate.getForObject(getRootUrl() + "/products/" + id, Produit.class);
	        product.setMarque("BMW");
	      
	        restTemplate.put(getRootUrl() + "/products/" + id, product);

	        Produit updatedProduct = restTemplate.getForObject(getRootUrl() + "/products/" + id, Produit.class);
	        assertNotNull(updatedProduct);
	    }
	    
}
