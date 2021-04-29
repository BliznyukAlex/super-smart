package blyzniuk.supersmart.data;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppData {
    @Getter
    @Value("${total.items.weight}")
    private Double totalItemsWeight;

    @Getter
    @Value("${total.weight.items.weight}")
    private Double totalWeightedItemsWeight;

    @Getter
    @Value("${barcode.green.false}")
    private Long barcodeGreenFalse;

    @Getter
    @Value("${min.processes.passed}")
    private Long minProcessesPassed;
}
