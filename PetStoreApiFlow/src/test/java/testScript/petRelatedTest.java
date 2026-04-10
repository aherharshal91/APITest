package testScript;

import Pages.petRelatedData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.naming.Name;
import java.io.InputStream;
import java.util.Objects;

public class petRelatedTest {

    petRelatedData petData = new petRelatedData();

    //Test case :1 verify Available pet status with petID and pet Name
    @Test
    public void testAvailablePet()
    {
       Response response = petData.getAvailableStateData();
        Assert.assertEquals(response.getStatusCode(),200);
        JsonPath js =  response.jsonPath();
        String Name = js.getString("[4].name");
        Assert.assertEquals(Name, "cat");

        String Name1 = js.getString("[6].name");
        Assert.assertEquals(Name1, "hello kity");

        String id= js.getString("[6].id");
        Assert.assertEquals(id,"2312","Test case :1 verify Available pet status with petID and pet Name");

    }

    // Test case 2: Verify Pending pet status
    @Test
    public void testPendingPet()
    {
        Response response = petData.getPendingStateData();
        Assert.assertEquals(response.getStatusCode(),200);
        JsonPath js = response.jsonPath();
        String Name = js.getString("[3].name");
        Assert.assertEquals(Name, "tommy");
        String ID =js.getString("[3].id");
        Assert.assertEquals(ID,"12346","Test case 2: Verify Pending pet status");
    }
 // Test case 3: verify 404 status invalid path for pending status
    @Test
    public void testInvalid_404_PendingPet()
    {
        Response response = petData.getInvalid_400_PendingStateData();
        Assert.assertEquals(response.getStatusCode(),404,"Test case 3: verify 404 status invalid path for pending status");
    }

    // Test case 4: verify sold pet status
    @Test
    public void testSoldPet()
    {
        Response response = petData.getSoldStateData();
        Assert.assertEquals(response.getStatusCode(),200);
        JsonPath js = response.jsonPath();
        String Name = js.getString("category.name[1]");
        Assert.assertEquals(Name,"Dog","Test case 4: verify sold pet status");
    }

    // Test case 4: verify create/add New pet record using API
    @Test
    public void testCreateNewPetRecordInStore()
    {
        Response response=petData.postNewPetData("Sweety", "Tiger");
        Assert.assertEquals(response.getStatusCode(),200,"Test case 4: verify create/add New pet record using API");



    }
// Test case 6: verify 405 method not allow status code to be shown for Post - New pet data created
    @Test
    public void testInvalid_405_CreateNewPetRecordInStore()
    {
        Response response=petData.postInvalid_405_NewPetData("Invalid", "Test Invalid");
        Assert.assertEquals(response.getStatusCode(),405, "Test case 6: verify 405 method not allow status code to be shown for Post - New pet data created");

    }

    // Test case 7: verify create multiple new pet data using dataProvider
    @Test (dataProvider = "DogData")
    public void testCreateNewPetRecordInStoreUsingDataProvider(String name, String dogName)
    {
        Response response=petData.postNewPetData(name, dogName);
        Assert.assertEquals(response.getStatusCode(),200,"Test case 7: verify create multiple new pet data using dataProvider");

    }

    // Test case 8: verify update pet record using API
    @Test
    public void testUpdatePetRecord()
    {
        petData.postNewPetData("Sweety", "Indian");
        Response response = petData.updatePetData("UpdatedCharlie", "Pasha", 122344);
        Assert.assertEquals(response.getStatusCode(),200);
        JsonPath js = response.jsonPath();
        String Name =js.getString("name");
        Assert.assertEquals(Name,"Pasha","Test case 8: verify update pet record using API " );
    }


    // Test case 9: Verify Update Pet using Create pet record API response and set in Update pet API
    @Test
    public void chaining_UpdatePetOfNewlyCreatedPet()
    {
        Response response= petData.postNewPetData("Dolly","Helly");
        JsonPath js = response.jsonPath();
        int petId = js.getInt("id");
       Response response1= petData.updatePetData("UpdateDolly", "UpdatedHelly", petId);
        JsonPath js1 = response1.jsonPath();
        String Name =js1.getString("name");
        Assert.assertEquals(Name,"UpdatedHelly","Test case 9: Verify Update Pet using Create pet record API response and set in Update pet API " );

    }

    // Test case 10: verify user able to delete pet data using create pet data response code 200
@Test
    public void chaining_DeleteNewlyCreatedPet()
{
    Response response= petData.postNewPetData("Dolly","Helly");
    JsonPath js = response.jsonPath();
    String petId = js.getString("id");
    Response response1 =petData.executeDeleteApi(petId);
    JsonPath js1 = response1.jsonPath();
    Assert.assertEquals(response1.getStatusCode(),200, "Test case 10: verify user able to delete pet data using create pet data response code 405");
    String message = js1.getString("message");
    Assert.assertEquals(message,petId,"validate created ID and deleted ID are same");
}

//Test case 11: verify user able to create record and upload the image in respective petid
@Test
public void testUploadImage() {
    Response response = petData.postNewPetData("Charle", "Dolly");
    JsonPath js = response.jsonPath();
    String petId = js.getString("id");
    Response response1 = petData.setImageOfPet(petId);
    Assert.assertEquals(response1.getStatusCode(), 200, "Test case 11: verify user able to create record and upload the image in respective petid");

}












//verify dataProvider to pass a value into testcase
@DataProvider(name = "DogData")

    public Object[][] getData()
{

    return new Object[][] {{"Peta", "Dog no 1"},{"Jira", "Dog no 2"},{"Charlie", "Dog no 3"},{"Sweety", "Dog no 4"}};
}

}
