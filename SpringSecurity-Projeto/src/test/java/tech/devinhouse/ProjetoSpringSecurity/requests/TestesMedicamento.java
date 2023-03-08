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
public class TestesMedicamento {

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
    public void CadastrarMedicamento() throws Exception{
        String medicamento = "{" +
                "\"nome_medicamento\":\"Paracetamol\"," +
                "\"nome_laboratorio\":\"Cimed\"," +
                "\"dosagem\":\"750mg\"," +
                "\"desc_medicamento\":\"Medicamento para redução de febre\"," +
                "\"preco_unitario\":\"7.61\"," +
                "\"tipo_medicamento\":\"Venda Livre\"" +
                "}";


        path = new URI("/medicamentos");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(path)
                .content(medicamento)
                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isCreated();

        mock.perform(request).andExpect(expectedResult);

    }
    @Test
    public void BuscarMedicamentos() throws Exception{


        path = new URI("/medicamentos");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(path)

                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);

    }
    @Test
    public void MudarMedicamento() throws Exception{
        String medicamento = "{" +
                "\"nome_medicamento\":\"Paracetamol\"," +
                "\"nome_laboratorio\":\"Cimed\"," +
                "\"dosagem\":\"750mg\"," +
                "\"desc_medicamento\":\"Medicamento para redução de febre\"," +
                "\"preco_unitario\":\"7.61\"," +
                "\"tipo_medicamento\":\"Venda Livre\"" +
                "}";


        path = new URI("/medicamentos/1");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(path)
                .content(medicamento)
                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);

    }
    @Test
    public void RemoverMedicamento() throws Exception{


        path = new URI("/medicamentos/1");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(path)

                .header("Content-Type", "application/json")
                .header("Authorization", token);

        expectedResult = MockMvcResultMatchers.status().isAccepted();

        mock.perform(request).andExpect(expectedResult);

    }






}
