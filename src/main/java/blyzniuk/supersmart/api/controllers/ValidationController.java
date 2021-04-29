package blyzniuk.supersmart.api.controllers;

import blyzniuk.supersmart.data.models.Item;
import blyzniuk.supersmart.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ValidationController {

    @Autowired
    ValidationService validationService;

    @PostMapping("/api/v1/validation/items")
    public Boolean validateTransaction(@RequestBody List<Item> items) {
        return validationService.validateTransaction(items);
    }
}
