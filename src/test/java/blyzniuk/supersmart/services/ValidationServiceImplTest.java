package blyzniuk.supersmart.services;

import blyzniuk.supersmart.data.enums.ItemType;
import blyzniuk.supersmart.data.models.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidationServiceImplTest {

    @Autowired
    ValidationServiceImpl validationService;
    List<Item> items;

    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
    }

    @Test
    void validateTransactionPassAllProcesses() {
        items.add(new Item(1L,1234L,0.99, ItemType.WEIGHTED));
        items.add(new Item(2L,1235L,40.99, ItemType.UNIT));
        items.add(new Item(3L,9345L,40.99, ItemType.GREEN));
        Assert.assertEquals(true, validationService.validateTransaction(items));
    }

    @Test
    void validateTransactionPassProcessesXY() {
        items.add(new Item(1L,1234L,0.99, ItemType.WEIGHTED));
        items.add(new Item(2L,1235L,40.99, ItemType.UNIT));
        items.add(new Item(3L,2945L,40.99, ItemType.GREEN));
        Assert.assertEquals(true, validationService.validateTransaction(items));
    }
    @Test
    void validateTransactionPassProcessesXZ() {
        items.add(new Item(1L,1234L,10.99, ItemType.WEIGHTED));
        items.add(new Item(2L,1235L,40.99, ItemType.UNIT));
        items.add(new Item(3L,1945L,10.99, ItemType.GREEN));
        Assert.assertEquals(true, validationService.validateTransaction(items));
    }

    @Test
    void validateTransactionPassProcessesYZ() {
        items.add(new Item(1L,1234L,0.99, ItemType.WEIGHTED));
        items.add(new Item(2L,1235L,0.99, ItemType.UNIT));
        items.add(new Item(3L,1945L,0.99, ItemType.GREEN));
        Assert.assertEquals(true, validationService.validateTransaction(items));
    }

    @Test
    void validateTransactionAllFails() {
        items.add(new Item(1L,1234L,10.99, ItemType.WEIGHTED));
        items.add(new Item(3L,2934L,10.99, ItemType.GREEN));
        items.add(new Item(3L,2934L,10.99, ItemType.UNIT));
        Assert.assertEquals(false, validationService.validateTransaction(items));
    }

    @Test
    void validateTransactionFailsXY() {
        items.add(new Item(1L,1234L,10.99, ItemType.WEIGHTED));
        items.add(new Item(3L,1934L,10.99, ItemType.GREEN));
        items.add(new Item(3L,2934L,10.99, ItemType.UNIT));
        Assert.assertEquals(false, validationService.validateTransaction(items));
    }

    @Test
    void validateTransactionFailsXZ() {
        items.add(new Item(1L,1234L,0.99, ItemType.WEIGHTED));
        items.add(new Item(3L,2934L,10.99, ItemType.GREEN));
        items.add(new Item(3L,1934L,10.99, ItemType.UNIT));
        Assert.assertEquals(false, validationService.validateTransaction(items));
    }

    @Test
    void validateTransactionFailsYZ() {
        items.add(new Item(1L,1234L,10.99, ItemType.WEIGHTED));
        items.add(new Item(3L,2934L,40.99, ItemType.GREEN));
        items.add(new Item(3L,1934L,10.99, ItemType.UNIT));
        Assert.assertEquals(false, validationService.validateTransaction(items));
    }
}