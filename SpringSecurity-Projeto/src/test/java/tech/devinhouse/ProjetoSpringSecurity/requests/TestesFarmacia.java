package tech.devinhouse.ProjetoSpringSecurity.requests;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesFarmacia {

    public URI path;
    private MockHttpServletRequest request;
    private ResultMatcher expectedResult;

    @Autowired
    private MockMvc mock;

    public String token;

    @Before
    public void setUp() throws Exception{
        String acesso = "{\"email\": \"marlon@gmail.com\", \"senha\": \"marlon\"}";
        path = new URI("/auth");

        MockHttpServletRequestBuilder request;
        request = MockMvcRequestBuilders.post(path)
                .contentType(MediaType.APPLICATION_JSON).content(acesso);

        expectedResult = MockMvcResultMatchers.status().isOk();

        String response = mock.perform(request).andExpect(expectedResult).andReturn().getResponse()
                .getContentAsString();


        JSONObject data = new JSONObject(response);

        token = data.getString("Auth");
    }
    @Test
    public void CadastrarFarmacia() throws Exception{
        String farmacia = "{" +
                "\"razao_social\": \"Cia Latino Americana de Medicamentos\"," +
                "\"cnpj\": \"84.683.481/0001-77\"," +
                "\"nome_fantasia\": \"Preço Popular\"," +
                "\"email\": \"sac@precopopular.com.br\"," +
                "\"telefone\": \"4799999999\"," +
                "\"celular\": \"47999999999\"," +
                "\"endereco\": {" +
                "\"cep\": \"89204002\"," +
                "\"logradouro\": \"R. Dr. João Colin\"," +
                "\"numero\": 2217," +
                "\"bairro\": \"América\"," +
                "\"cidade\": \"Joinville\"," +
                "\"estado\": \"SC\"," +
                "\"complemento\": \"\"," +
                "\"latitude\": \"lat\"," +
                "\"longitude\": \"lon\"" +
                "}" +
                "}";


        path = new URI("/farmacia");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(path)
                .content(farmacia)
                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isCreated();

        mock.perform(request).andExpect(expectedResult);

    }
    @Test
    public void EncontrarFarmacia() throws Exception{


        path = new URI("/farmacia");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(path)

                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);

    }
    @Test
    public void MudarFarmacia() throws Exception{
        String farmacia = "{" +
                "\"razao_social\": \"Cia Latino Americana de Medicamentos\"," +
                "\"cnpj\": \"84.683.481/0001-77\"," +
                "\"nome_fantasia\": \"Preço Popular\"," +
                "\"email\": \"sac@precopopular.com.br\"," +
                "\"telefone\": \"4799999999\"," +
                "\"celular\": \"47999999999\"," +
                "\"endereco\": {" +
                "\"cep\": \"89204002\"," +
                "\"logradouro\": \"R. Dr. João Colin\"," +
                "\"numero\": 2217," +
                "\"bairro\": \"América\"," +
                "\"cidade\": \"Joinville\"," +
                "\"estado\": \"SC\"," +
                "\"complemento\": \"\"," +
                "\"latitude\": \"lat\"," +
                "\"longitude\": \"lon\"" +
                "}" +
                "}";


        path = new URI("/farmacia/1");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(path)
                .content(farmacia)
                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isCreated();

        mock.perform(request).andExpect(expectedResult);

    }
    @Test
    public void RemoverFarmacia() throws Exception{


        path = new URI("/farmacia/1");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(path)

                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isAccepted();

        mock.perform(request).andExpect(expectedResult);

    }






}
