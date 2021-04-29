package blyzniuk.supersmart.api.controllers;

import blyzniuk.supersmart.data.enums.ItemType;
import blyzniuk.supersmart.data.models.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ValidationControllerTest {

    @Autowired
    ValidationController validationController;

    @Autowired
    MockMvc mockMvc;

    @Test
    void validateTransaction() throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1L,1234L,0.99, ItemType.WEIGHTED));
        items.add(new Item(2L,1235L,40.99, ItemType.UNIT));
        items.add(new Item(3L,9345L,40.99, ItemType.GREEN));
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(items);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/validation/items")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json);
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .is(200))
                .andExpect(MockMvcResultMatchers.content()
                        .string("true"));
    }
}