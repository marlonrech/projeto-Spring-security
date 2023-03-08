package tech.devinhouse.ProjetoSpringSecurity.Service;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import tech.devinhouse.ProjetoSpringSecurity.DAO.ErroResponse;
import tech.devinhouse.ProjetoSpringSecurity.DAO.usuarioDAO;
import tech.devinhouse.ProjetoSpringSecurity.Model.Usuario;
import tech.devinhouse.ProjetoSpringSecurity.Repository.usuarioRepository;

@Service
public class usuarioService implements UserDetailsService {

    @Autowired
    private usuarioRepository usuarioRepository;

    public ResponseEntity cadastrarNovoUsuario(@RequestBody Usuario usuario){
        usuarioDAO usuarioDAO = new usuarioDAO();
        usuarioDAO.setDados(usuario);
        usuarioRepository.save(usuario);
        return new ResponseEntity<>(new usuarioDAO(Response.SC_CREATED, "Usuario Adicionado!", usuarioDAO.getDados()), HttpStatus.CREATED);
    }


    public ResponseEntity buscarUsuario(@RequestParam("email") String email,@RequestParam("senha") String senha) {
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);
        usuarioDAO usuarioDAO = new usuarioDAO();
        usuarioDAO.setDados(usuario);

        if(usuarioDAO.getDados() == null){
            return new ResponseEntity<>(new ErroResponse(Response.SC_NOT_FOUND,"Erro!",HttpStatus.NOT_FOUND,"Dados inválidos"),HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(new usuarioDAO(Response.SC_OK,"Usuario Encontrado!",usuarioDAO.getDados()),HttpStatus.OK);
        }


    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUserByLogin(email);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new User(usuario.getEmail(), usuario.getSenha(), usuario.getAuthorities());

    }
}
