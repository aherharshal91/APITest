package testScript;

import Pages.storeRelatedData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class storeRelatedTest {
    storeRelatedData storeData = new storeRelatedData();

    // Test case 12 : Verify Inventory Status of pet
    @Test
    public void validateInventoryStatus_Test()
    {
        Response response = storeData.inventoryStatus();
        JsonPath js = response.jsonPath();
       String sold= js.getString("sold");
        Assert.assertEquals(response.getStatusCode(),200,"Test case 12 : Verify Inventory Status of pet");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

    }

    //Test case 13: place a order of pet from store
@Test
    public void orderPetFromStore_Test()
{
      Response response =storeData.placeOrderForPetInStore();
      JsonPath js = response.jsonPath();
      String id = js.getString("id");
      String PetStatus = js.getString("status");
      Assert.assertEquals(id,"101","//Test case 13: place a order of pet from store");
}

// Test case 14 : delete pet order which was previously placed
@Test
public void deletePetOrderFromStore_Test() {
    Response response =storeData.placeOrderForPetInStore();
    JsonPath js = response.jsonPath();
    String id = js.getString("id");
    Response response1 = storeData.store_executeDeleteApi(id);
    JsonPath js1 = response1.jsonPath();

    Assert.assertEquals(response1.getStatusCode(),200,"Test case 14 : delete pet order which was previously placed");
    Assert.assertEquals(js1.getInt("message"),101,"Validate the deleted data with response body confirmation ");
}

// Test case 15 : validate after 404 status code shown when we recall place order API after delete order
@Test
    public void status_404_DeletePetOrderFromStoreTest()
{
    Response response =storeData.placeOrderForPetInStore();
    JsonPath js = response.jsonPath();
    String id = js.getString("status");
    Response response1 = storeData.status_404_store_executeDeleteApi(id);
    Assert.assertEquals(response1.getStatusCode(),404,"Test case 15 : validate after 404 status code shown when we recall place order API after delete order");

}
}
