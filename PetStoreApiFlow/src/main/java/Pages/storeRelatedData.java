package Pages;

import Base.BaseService;
import io.restassured.response.Response;

public class storeRelatedData extends BaseService {

    public Response inventoryStatus()
    {

        return inventory_Status("v2/store/inventory");
    }

    public Response placeOrderForPetInStore()
    {
        String body ="{\n" +
                "  \"id\": 101,\n" +
                "  \"petId\": 12345,\n" +
                "  \"quantity\": 1,\n" +
                "  \"shipDate\": \"2024-08-30T06:54:57.632Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";
       return postOrderForPetInStore(body,"v2/store/order");
    }

    
}
