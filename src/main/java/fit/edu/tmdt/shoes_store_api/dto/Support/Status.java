package fit.edu.tmdt.shoes_store_api.dto.Support;

public interface Status {
    String UNLOCK = "S1";
    String LOCK = "S2";
    String VERIFY = "S3";

    String ORDER_PROCESSING= "O1";
    String ORDER_SHIPPING = "O2";
    String ORDER_DELIVERED = "O3";
    String ORDER_CANCEL = "O4";
}
