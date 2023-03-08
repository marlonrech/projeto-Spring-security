package tech.devinhouse.ProjetoSpringSecurity.Security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tech.devinhouse.ProjetoSpringSecurity.Context.ApplicationContextLoad;
import tech.devinhouse.ProjetoSpringSecurity.Model.Usuario;
import tech.devinhouse.ProjetoSpringSecurity.Repository.usuarioRepository;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
@Component
public class JwtTokenAutenticacaoService {

    //Token Valido por 2 dias
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 2;


    private static final String SECRET = "Senhamuitofortemesmovamosporaquiok";


    private static final String TOKEN_PREFIX = "Bearer";



    private static  final String HEADER_STRING = "Authorization";



    public void addAuthentication(HttpServletResponse response, String email) throws IOException {

        //Montagem do token
        String JWT =  Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();


        String token = TOKEN_PREFIX + " " + JWT;


        response.getWriter().write("{\"Auth\": \""+token+"\"}");

    }



    public Authentication getAuthentication(HttpServletRequest request){


        String token = request.getHeader(HEADER_STRING);

        if(token != null){

            String user = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody().getSubject();

            if (user != null){
                 Usuario usuario = ApplicationContextLoad.getApplicationContext()
                         .getBean(usuarioRepository.class).findUserByLogin(user);


                 if(usuario != null){
                     return new UsernamePasswordAuthenticationToken(
                             usuario.getPassword(),
                             usuario.getSenha(),
                             usuario.getAuthorities());
                 }



            }

        }


        return null;
    }



}
