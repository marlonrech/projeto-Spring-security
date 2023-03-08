package tech.devinhouse.ProjetoSpringSecurity.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.ProjetoSpringSecurity.Model.Usuario;
import tech.devinhouse.ProjetoSpringSecurity.Service.usuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:3000 ")
public class UsuarioController {

    @Autowired
    private usuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity cadastrarUsuario(@RequestBody Usuario usuario){
        return usuarioService.cadastrarNovoUsuario(usuario);
    }

    @GetMapping("/login")
    public ResponseEntity buscarPorEmailEsenha(@RequestParam ("email") String email, @RequestParam("senha") String senha){
        return usuarioService.buscarUsuario(email,senha);
    }
}
