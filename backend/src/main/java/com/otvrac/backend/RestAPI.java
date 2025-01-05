package com.otvrac.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/muzeji")
public class RestAPI {

    @Autowired
    private MuzejRepository muzejRepository;

    @PostMapping
    public ResponseEntity<?> createMuseum(@RequestBody Muzej muzej) {
        Muzej museumSaved = muzejRepository.save(muzej);
        return ResponseEntity.ok(new ApiResponse<Muzej>("OK", "Muzej spremljen", museumSaved));
    }

    @GetMapping
    public ResponseEntity<?> getAllMuseums() {
        List<Muzej> museums = muzejRepository.findAll();
        return ResponseEntity.ok(new ApiResponse<List<Muzej>>("OK", "Muzeji dohvaćeni", museums));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMuseumById(@PathVariable Integer id) {
        Optional<Muzej> museum = muzejRepository.findById(id);
        if(museum.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<Muzej>("Not Found", "Muzej nije pronađen", null));

        return ResponseEntity.ok(new ApiResponse<Muzej>("OK", "Muzej dohvaćen", museum.get()));
    }

    @GetMapping("/drzava/{country}")
    public ResponseEntity<?> getMuseumByCountry(@PathVariable String country) {
        List<Muzej> museums = muzejRepository.findByDrzava(country);
        if(museums.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<List<Muzej>>("Not found", "Muzeji nisu pronađeni", null));

        return ResponseEntity.ok(new ApiResponse<List<Muzej>>("OK", "Muzeji dohvaćeni", museums));
    }

    @GetMapping("/grad/{city}")
    public ResponseEntity<?> getMuseumByCity(@PathVariable String city) {
        List<Muzej> museums = muzejRepository.findByGrad(city);
        if(museums.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<List<Muzej>>("Not found", "Muzeji nisu pronađeni", null));

        return ResponseEntity.ok(new ApiResponse<List<Muzej>>("OK", "Muzeji dohvaćeni", museums));
    }

    @GetMapping("/tip/{type}")
    public ResponseEntity<?> getMuseumByType(@PathVariable String type) {
        List<Muzej> museums = muzejRepository.findByTipMuzeja(type);
        if(museums.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<List<Muzej>>("Not found", "Muzeji nisu pronađeni", null));

        return ResponseEntity.ok(new ApiResponse<List<Muzej>>("OK", "Muzeji dohvaćeni", museums));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMuseum(@PathVariable Integer id, @RequestBody Muzej updatedMuseum) {
        Optional<Muzej> museumOpt = muzejRepository.findById(id);
        if(museumOpt.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<Muzej>("Not found", "Muzej nije pronađen", null));

        Muzej museum = museumOpt.get();

        if(updatedMuseum.getNazivMuzeja() != null) museum.setNazivMuzeja(updatedMuseum.getNazivMuzeja());
        if(updatedMuseum.getDrzava() != null) museum.setDrzava(updatedMuseum.getDrzava());
        if(updatedMuseum.getGrad() != null) museum.setGrad(updatedMuseum.getGrad());
        if(updatedMuseum.getGodinaOsnivanja() != null) museum.setGodinaOsnivanja(updatedMuseum.getGodinaOsnivanja());
        if(updatedMuseum.getTipMuzeja() != null) museum.setTipMuzeja(updatedMuseum.getTipMuzeja());
        if(updatedMuseum.getVelicinaKolekcije() != null) museum.setVelicinaKolekcije(updatedMuseum.getVelicinaKolekcije());
        if(updatedMuseum.getPosjetitelji() != null) museum.setPosjetitelji(updatedMuseum.getPosjetitelji());
        if(updatedMuseum.getIzlozbeniProstor() != null) museum.setIzlozbeniProstor(updatedMuseum.getIzlozbeniProstor());
        if(updatedMuseum.getWebStranica() != null) museum.setWebStranica(updatedMuseum.getWebStranica());
        if(updatedMuseum.getOnlineSetnja() != null) museum.setOnlineSetnja(updatedMuseum.getOnlineSetnja());

        Muzej savedMuseum = muzejRepository.save(museum);

        return ResponseEntity.ok(new ApiResponse<Muzej>("OK", "Muzej izmijenjen", savedMuseum));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMuseum(@PathVariable Integer id) {
        Optional<Muzej> museum = muzejRepository.findById(id);

        if(museum.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<Muzej>("Not found", "Muzej nije pronađen", null));

        muzejRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<Muzej>("OK", "Muzej obrisan", null));
    }
}
