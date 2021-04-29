package blyzniuk.supersmart.services;

import blyzniuk.supersmart.data.AppData;
import blyzniuk.supersmart.data.enums.ItemType;
import blyzniuk.supersmart.data.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    AppData appData;

    @Override
    public Boolean validateTransaction(List<Item> items) {
        int validationPassed = 0;
        if(items == null || items.isEmpty()){
            return false;
        }
        if(processX(items)){
            validationPassed++;
        }
        if(processY(items.stream().filter(item -> item.getType().equals(ItemType.WEIGHTED)).collect(Collectors.toList()))){
            validationPassed++;
        }
        if(validationPassed>0){
            if(processZ(items.stream().filter(item -> item.getType().equals(ItemType.GREEN)).collect(Collectors.toList()))){
                validationPassed++;
            }
        }
        return validationPassed >= appData.getMinProcessesPassed();
    }

    private Boolean processX(List<Item> items) {
        Double totalWeight = weightCount(items);
        return totalWeight.compareTo(appData.getTotalItemsWeight()) > 0;
    }

    private Boolean processY(List<Item> items) {
        if(items == null || items.isEmpty()) {
            return true;
        } else {
            Double totalWeight = weightCount(items);
            return totalWeight.compareTo(appData.getTotalWeightedItemsWeight())<0;
        }
    }

    private Boolean processZ(List<Item> items) {
        if (items != null && !items.isEmpty()) {
            for (Item item : items) {
                Long barcodeStart = Long.parseLong(item.getBarcode().toString().substring(0, appData.getBarcodeGreenFalse().toString().length()));
                if (barcodeStart == appData.getBarcodeGreenFalse()) {
                    return false;
                }
            }
        }
        return true;
    }

    private Double weightCount(List<Item> items){
        Double totalWeight = items.stream().mapToDouble(Item::getWeight).sum();
        return totalWeight;
    }
}
