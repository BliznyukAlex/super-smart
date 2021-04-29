package blyzniuk.supersmart.data.models;

import blyzniuk.supersmart.data.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Long id;
    private Long barcode;
    private Double weight;
    private ItemType type;
}
