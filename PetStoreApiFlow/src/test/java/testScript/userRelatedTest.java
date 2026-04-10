package testScript;

import Pages.userRelatedData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class userRelatedTest {

    userRelatedData userdata = new userRelatedData();

    //Test case 16 :verify 200 status code shown when we enter one record
    @Test
    public void singleUser_createUserWishList()
    {
      Response response =userdata.createUserListInStore();
      JsonPath js= response.jsonPath();
      String message = js.getString("message");
      Assert.assertEquals(message,"ok");
      Assert.assertEquals(response.getStatusCode(), 200,"Test case 16 :verify 200 status code shown when we enter one record");

    }
    // Test case 17: verify 400 bad request shown as per swagger when we pass 2 record at a time

 @Test
    public void multiUserCreateWishList()
 {
      Response response=userdata.arrayListOfUserCreateWishList();
      JsonPath js = response.jsonPath();
      String message= js.getString("message");
      Assert.assertEquals(message,"bad input");
      Assert.assertEquals(response.getStatusCode(), 400,"Test case 17: verify 400 bad request shown as per swagger when we pass 2 record at a time");

 }

 // Test case no 18 : validate user login into system with status code 200
 @Test
    public void validateUserLoginIntoTheSystem()
 {
 Response response =userdata.userLoginIntoSystem();
 JsonPath js =response.jsonPath();
 String Message= js.getString("message");
 Assert.assertEquals(response.getStatusCode(), 200, "Test case no 18 : validate user login into system with status code 200");
 String responseBody = response.getBody().asString();
 System.out.println("Response Body: " + responseBody);
 }

 // Test case 19 : validate user logout from system
 @Test
    public void validateUserLogOutFromSystem()
 {
   Response response=  userdata.userLogoutFromSystem();
   JsonPath js = response.jsonPath();
   Assert.assertEquals(response.getStatusCode(),200,"Test case 19 : validate user logout from system ");
 }


 // Test case 20 : validate user login into system using spec   ification Builder feature

    @Test
    public void login()
    {
        Response response =userdata.LoginIntoSystem();
        JsonPath js =response.jsonPath();
        Assert.assertEquals(response.getStatusCode(), 200, "Test case 20 : validate user login into system using spcification Builder feature");

    }
}
