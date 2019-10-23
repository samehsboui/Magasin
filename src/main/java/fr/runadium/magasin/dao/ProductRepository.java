package fr.runadium.magasin.dao;

import org.springframework.data.repository.CrudRepository;

import fr.runadium.magasin.model.Produit;

public interface ProductRepository extends CrudRepository<Produit, Long> {

}
