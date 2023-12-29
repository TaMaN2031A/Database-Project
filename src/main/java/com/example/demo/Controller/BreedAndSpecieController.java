package com.example.demo.Controller;
import com.example.demo.Model.Shelter;
import com.example.demo.Model.SpecieBreed;
import com.example.demo.Service.PetService;
import com.example.demo.Service.SpecieBreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Breed")
public class BreedAndSpecieController {


    @Autowired
    private SpecieBreedService speciebreedService;

    @PostMapping("/addBreed")
    public ResponseEntity<?> addBreed(@RequestBody SpecieBreed specieBreed) {
        System.out.println(specieBreed.toString());
        return speciebreedService.addspeciebreed(specieBreed);
    }

    @PostMapping("/updateBreed")
    public ResponseEntity<?> updateBreed(@RequestBody SpecieBreed specieBreed) {
        System.out.println(specieBreed.toString());

        return speciebreedService.updatespecieBreed(specieBreed);
    }
    @PostMapping("/deleteBreed/{Breed}")
    public ResponseEntity<?> deleteBreed(@PathVariable String Breed) {
        System.out.println(Breed);
        return speciebreedService.deletespecieBreed(Breed);
    }
    @GetMapping("/getBreed")
    public ResponseEntity<?> getBreed() {
        return speciebreedService.getspecieBreed();
    }

}
