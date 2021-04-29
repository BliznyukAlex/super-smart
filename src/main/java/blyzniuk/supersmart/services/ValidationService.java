package blyzniuk.supersmart.services;

import blyzniuk.supersmart.data.models.Item;

import java.util.List;

public interface ValidationService {
    Boolean validateTransaction(List<Item> items);
}
