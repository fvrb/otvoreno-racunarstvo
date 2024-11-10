package com.otvrac.backend;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class BackendApplication {

	@PersistenceContext
	private EntityManager entityManager; // This injects the EntityManager automatically

	@GetMapping("/start")
	public List<Muzej> start() {
		String queryString = "SELECT DISTINCT m FROM Muzej m";

		TypedQuery<Muzej> query = entityManager.createQuery(queryString, Muzej.class);
		query.setMaxResults(5);

		return query.getResultList();
	}

	@GetMapping("/filter")
	public ResponseEntity<List<Muzej>> filter(@RequestParam(value = "column") String column, @RequestParam(value = "term") String term) {
		String queryString = "SELECT DISTINCT m FROM Muzej m " +
							 "JOIN m.eksponati e ";

		switch(column) {
			case "nazivMuzeja":
				queryString += "WHERE nazivMuzeja LIKE :term";
				break;
			case "drzava":
				queryString += "WHERE drzava LIKE :term";
				break;
			case "grad":
				queryString += "WHERE grad LIKE :term";
				break;
			case "godinaOsnivanja":
				queryString += "WHERE CONCAT(godinaOsnivanja, '') LIKE :term";
				break;
			case "tipMuzeja":
				queryString += "WHERE tipMuzeja LIKE :term";
				break;
			case "velicinaKolekcije":
				queryString += "WHERE CONCAT(velicinaKolekcije, '') LIKE :term";
				break;
			case "posjetitelji":
				queryString += "WHERE CONCAT(posjetitelji, '') LIKE :term";
				break;
			case "izlozbeniProstor":
				queryString += "WHERE CONCAT(izlozbeniProstor, '') LIKE :term";
				break;
			case "webStranica":
				queryString += "WHERE webStranica LIKE :term";
				break;
			case "onlineSetnja":
				queryString += "WHERE (onlineSetnja = true AND :term LIKE '%Da%') " +
							   "OR (onlineSetnja = false AND :term LIKE '%Ne%')";
				break;
			case "nazivEksponata":
				queryString += "WHERE nazivEksponata LIKE :term";
				break;
			case "tipEksponata":
				queryString += "WHERE tipEksponata LIKE :term";
				break;
			default:
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		TypedQuery<Muzej> query = entityManager.createQuery(queryString, Muzej.class);
		query.setParameter("term", "%"+term+"%");

        return ResponseEntity.ok(query.getResultList());
	}

	@GetMapping("/filter/wildcard")
	public List<Muzej> filterWildcard(@RequestParam(value = "term") String term) {
		String queryString = "SELECT DISTINCT m FROM Muzej m " +
				"JOIN m.eksponati e " +
				"WHERE nazivMuzeja LIKE :term " +
				"OR drzava LIKE :term " +
				"OR grad LIKE :term " +
				"OR CONCAT(godinaOsnivanja, '') LIKE :term " +
				"OR tipMuzeja LIKE :term " +
				"OR CONCAT(velicinaKolekcije, '') LIKE :term " +
				"OR CONCAT(posjetitelji, '') LIKE :term " +
				"OR CONCAT(izlozbeniProstor, '') LIKE :term " +
				"OR webStranica LIKE :term " +
				"OR CONCAT(onlineSetnja, '') LIKE :term " +
				"OR nazivEksponata LIKE :term " +
				"OR tipEksponata LIKE :term";

		TypedQuery<Muzej> query = entityManager.createQuery(queryString, Muzej.class);
		query.setParameter("term", "%"+term+"%");

		return query.getResultList();
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
