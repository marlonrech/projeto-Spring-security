package tech.devinhouse.ProjetoSpringSecurity.Service;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tech.devinhouse.ProjetoSpringSecurity.DAO.*;

import tech.devinhouse.ProjetoSpringSecurity.Model.Endereco;
import tech.devinhouse.ProjetoSpringSecurity.Model.Farmacia;
import tech.devinhouse.ProjetoSpringSecurity.Repository.enderecoRepository;
import tech.devinhouse.ProjetoSpringSecurity.Repository.farmaciaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class farmaciaService {

    @Autowired
    private farmaciaRepository farmaciaRepository;
    @Autowired
    private enderecoRepository enderecoRepository;


    public ResponseEntity cadastrarNovaFarmacia(Farmacia farmacia) {

        Endereco endereco = new Endereco();
        FarmaciaDAO farmaciaDao = new FarmaciaDAO();


        endereco.setNumero(farmacia.getEndereco().getNumero());
        endereco.setLogradouro(farmacia.getEndereco().getLogradouro());
        endereco.setComplemento(farmacia.getEndereco().getComplemento());
        endereco.setBairro(farmacia.getEndereco().getBairro());
        endereco.setCidade(farmacia.getEndereco().getCidade());
        endereco.setEstado(farmacia.getEndereco().getEstado());
        endereco.setCep(farmacia.getEndereco().getCep());
        endereco.setLongitude(farmacia.getEndereco().getLongitude());
        endereco.setLatitude(farmacia.getEndereco().getLatitude());
        farmacia.setEndereco(endereco);

        farmaciaDao.setDados(farmacia);

        farmaciaRepository.save(farmacia);
        enderecoRepository.save(endereco);

        return new ResponseEntity<>(new FarmaciaDAO(Response.SC_CREATED, "Farmácia Adicionada!", farmaciaDao.getDados()), HttpStatus.CREATED);



    }

    public ResponseEntity buscarFarmacias() {
        FarmaciasDAO farmaciasDAO = new FarmaciasDAO();
        List<Farmacia> buscarFarmacias = farmaciaRepository.findAll();

        farmaciasDAO.setDados(buscarFarmacias);
        if (buscarFarmacias.isEmpty()){
            return new ResponseEntity<>(new ErroResponse(Response.SC_NOT_FOUND,"Erro!",HttpStatus.NOT_FOUND,"Não há farmácias a listar!"),HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(new FarmaciasDAO(Response.SC_OK,"Lista de Farmácias encontradas!",farmaciasDAO.getDados()),HttpStatus.OK);
        }
    }

    public ResponseEntity deletarFarmacia(Long id) {

        if(farmaciaRepository.existsById(id)){
            farmaciaRepository.deleteById(id);
            return ResponseEntity.accepted().body("Farmácia deletadaID deletado: "+id);

        }else{
            return new ResponseEntity<>(new ErroResponse(Response.SC_NOT_FOUND,"Erro!",HttpStatus.NOT_FOUND,"Não existe farmácia com o id: "+id),HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity buscarUmaFarmacia(Long id) {
        FarmaID farmaID = new FarmaID();

        Optional<Farmacia> buscarUmaFarmacia = farmaciaRepository.findById(id);
        farmaID.setDados(buscarUmaFarmacia);
        return new ResponseEntity<>(new FarmaID(Response.SC_OK,"Farmacia Encontrada!",farmaID.getDados()),HttpStatus.OK);
    }

    public ResponseEntity alterarFarmacia(Long id, Farmacia farmacia) {

        FarmaciaDAO farmaciaDAO = new FarmaciaDAO();

        Farmacia alterarFarmacia = farmaciaRepository.findById(id).get();
        alterarFarmacia.setRazao_social(farmacia.getRazao_social());
        alterarFarmacia.setCnpj(farmacia.getCnpj());
        alterarFarmacia.setNome_fantasia(farmacia.getNome_fantasia());
        alterarFarmacia.setEmail(farmacia.getEmail());
        alterarFarmacia.setTelefone(farmacia.getTelefone());
        alterarFarmacia.setCelular(farmacia.getCelular());
        alterarFarmacia.getEndereco().setCep(farmacia.getEndereco().getCep());
        alterarFarmacia.getEndereco().setNumero(farmacia.getEndereco().getNumero());
        alterarFarmacia.getEndereco().setLogradouro(farmacia.getEndereco().getLogradouro());
        alterarFarmacia.getEndereco().setComplemento(farmacia.getEndereco().getComplemento());
        alterarFarmacia.getEndereco().setBairro(farmacia.getEndereco().getBairro());
        alterarFarmacia.getEndereco().setCidade(farmacia.getEndereco().getCidade());
        alterarFarmacia.getEndereco().setEstado(farmacia.getEndereco().getEstado());
        alterarFarmacia.getEndereco().setLongitude(farmacia.getEndereco().getLongitude());
        alterarFarmacia.getEndereco().setLatitude(farmacia.getEndereco().getLatitude());
        farmaciaDAO.setDados(alterarFarmacia);

        farmaciaRepository.save(alterarFarmacia);
        return new ResponseEntity<>(new FarmaciaDAO(Response.SC_CREATED, "Farmacia Atualizada!", farmaciaDAO.getDados()), HttpStatus.CREATED);

    }
}
