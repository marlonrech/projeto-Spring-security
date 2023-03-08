package tech.devinhouse.ProjetoSpringSecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.ProjetoSpringSecurity.Model.Medicamento;
import tech.devinhouse.ProjetoSpringSecurity.Service.medicamentoService;

@RestController
@RequestMapping("/medicamentos")
@CrossOrigin(origins = "http://localhost:3000 ")
public class MedicamentoController {

    @Autowired
    private medicamentoService medicamentoService;

    @PostMapping
    public ResponseEntity cadastrarMedicamento( @RequestBody Medicamento medicamento) {
        return medicamentoService.cadastrarNovoMedicamento(medicamento);
    }

    @GetMapping
    public ResponseEntity buscarMedicamentos() {
        return medicamentoService.buscarMedicamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarUmMedicamento(@PathVariable Long id) {
        return medicamentoService.buscarUmMedicamento(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletarUmMedicamento(@PathVariable Long id){
        return medicamentoService.deletarUmMedicamento(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity alterarMedicamento(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        return medicamentoService.alterarMedicamento(id,medicamento);
    }

}
