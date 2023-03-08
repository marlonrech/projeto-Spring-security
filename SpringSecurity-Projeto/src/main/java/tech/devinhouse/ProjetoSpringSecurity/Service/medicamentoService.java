package tech.devinhouse.ProjetoSpringSecurity.Service;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tech.devinhouse.ProjetoSpringSecurity.DAO.ErroResponse;
import tech.devinhouse.ProjetoSpringSecurity.DAO.MedID;
import tech.devinhouse.ProjetoSpringSecurity.DAO.MedicamentoDAO;
import tech.devinhouse.ProjetoSpringSecurity.DAO.MedicamentosDAO;
import tech.devinhouse.ProjetoSpringSecurity.Model.Medicamento;
import tech.devinhouse.ProjetoSpringSecurity.Repository.medicamentoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class medicamentoService {
    @Autowired
    private medicamentoRepository medicamentoRepository;

    public ResponseEntity cadastrarNovoMedicamento(@RequestBody Medicamento medicamento){
        MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
        medicamentoDAO.setDados(medicamento);
        medicamentoRepository.save(medicamento);
        return new ResponseEntity<>(new MedicamentoDAO(Response.SC_CREATED, "Medicamento Adicionado", medicamentoDAO.getDados()), HttpStatus.CREATED);

    }

    public ResponseEntity buscarMedicamentos() {
        MedicamentosDAO medicamentosDAO = new MedicamentosDAO();
        List<Medicamento> medicamentoList = medicamentoRepository.findAll();
        medicamentosDAO.setDados(medicamentoList);
        return new ResponseEntity<>(new MedicamentosDAO(Response.SC_OK, "Lista de Medicamentos!", medicamentosDAO.getDados()), HttpStatus.OK);
    }

    public ResponseEntity buscarUmMedicamento(Long id) {
        MedID medID = new MedID();

        if (medicamentoRepository.existsById(id)){
            Optional<Medicamento> buscarUmMedicamento = medicamentoRepository.findById(id);
            medID.setDados(buscarUmMedicamento);
            return new ResponseEntity<>(new MedID(Response.SC_OK, "Medicamento Encontrado",medID.getDados()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ErroResponse(Response.SC_NOT_FOUND, "Erro ao encontrar medicamento", HttpStatus.NOT_FOUND, "Não existe medicamento com o id: " + id), HttpStatus.NOT_FOUND);
        }


    }
    public ResponseEntity deletarUmMedicamento(@PathVariable Long id) {

        if(medicamentoRepository.existsById(id)){
            medicamentoRepository.deleteById(id);
            return ResponseEntity.accepted().body("Medicamento Deletado ID: "+id);

        }else{
            return new ResponseEntity<>(new ErroResponse(Response.SC_NOT_FOUND,"Erro ao encontrar medicamento",HttpStatus.NOT_FOUND,"Não há medicamento com o id: "+id),HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity alterarMedicamento(Long id, Medicamento medicamento) {
            Medicamento alterarMedicamento = medicamentoRepository.findById(id).get();
            MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

            alterarMedicamento.setNome_medicamento(medicamento.getNome_medicamento());
            alterarMedicamento.setNome_laboratorio(medicamento.getNome_laboratorio());
            alterarMedicamento.setDosagem(medicamento.getDosagem());
            alterarMedicamento.setDesc_medicamento(medicamento.getDesc_medicamento());
            alterarMedicamento.setPreco_unitario(medicamento.getPreco_unitario());
            alterarMedicamento.setTipo_medicamento(medicamento.getTipo_medicamento());

            medicamentoDAO.setDados(alterarMedicamento);
            medicamentoRepository.save(alterarMedicamento);

            return new ResponseEntity<>(new MedicamentoDAO(Response.SC_OK, "Medicamento Atualizado!", medicamentoDAO.getDados()), HttpStatus.OK);




    }
}
