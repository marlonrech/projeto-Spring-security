package tech.devinhouse.ProjetoSpringSecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.ProjetoSpringSecurity.Model.Farmacia;
import tech.devinhouse.ProjetoSpringSecurity.Service.farmaciaService;

@RestController
@RequestMapping("/farmacia")
@CrossOrigin(origins = "http://localhost:3000 ")
public class FarmaciaController {

    @Autowired
    private farmaciaService farmaciaService;

    @PostMapping()
    public ResponseEntity cadastrarNovaFarmacia(@RequestBody Farmacia farmacia){

        return farmaciaService.cadastrarNovaFarmacia(farmacia);
    }
    @GetMapping
    public ResponseEntity  buscarFarmacias(){

        return farmaciaService.buscarFarmacias();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarFarmacia(@PathVariable Long id) {

        return farmaciaService.deletarFarmacia(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUmaFarmacia(@PathVariable Long id) {
        return farmaciaService.buscarUmaFarmacia(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> alterarFarmacia(@PathVariable Long id,@RequestBody Farmacia farmacia){
        return farmaciaService.alterarFarmacia(id, farmacia);
    }
}
