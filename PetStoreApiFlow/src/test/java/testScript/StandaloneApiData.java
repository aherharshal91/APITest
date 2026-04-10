package testScript;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class StandaloneApiData {

    @Test
    // Pet data when state for Available
    public void getPetStoreStatusAvailable()
    {
        RestAssured.baseURI ="https://petstore.swagger.io";
        Response response = given()

                .log().all()
                .header("Accept", "*/*")


                .when()
                .queryParam("status","available")
                .get("v2/pet/findByStatus")

                .then()
                .log().all().extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.prettyPrint());


    }

    @Test
    // state for Available with 404 Not Found state code
    public void getPetStoreStatusAvailableWith404StatusCode()
    {
        RestAssured.baseURI ="https://petstore.swagger.io";


        Response response = given()
                .header("Accept", "*/*")


                .when()
                .queryParam("status","available")
                .get("v1/pet/findByStatus")
                //in place of v2 we put v1

                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),404);
        System.out.println(response.prettyPrint());

    }

    @Test
    // pet data of state for pending
    public void getPetStoreStatusPending()
    {
        RestAssured.baseURI ="https://petstore.swagger.io";


        Response response = given().header("Accept", "*/*")


                .when()
                .queryParam("status","pending")
                .get("v2/pet/findByStatus")

                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.prettyPrint());

    }

    @Test
    // pet data of state for sold
    public void getPetStoreStatusSold()
    {
        RestAssured.baseURI ="https://petstore.swagger.io";


        Response response = given().log().all()
                .header("Accept", "*/*")


                .when()
                .queryParam("status","sold")
                .get("v2/pet/findByStatus")


                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
       // System.out.println(response.prettyPrint());
        //Assert.assertEquals(response.jsonPath().getString("[0].name"), "olafFF");
        String petName = response.jsonPath().getString("name[0]");
       // Assert.assertEquals(petName, "puppy2");

    }

    @Test
    // create a new pet data
    public  void createNewPetRecord(){
        RestAssured.baseURI ="https://petstore.swagger.io";
       Response response = given().log().all().header("Content-Type","application/json")
               .body("{\n" +
                       "  \"id\": 0,\n" +
                       "  \"category\": {\n" +
                       "    \"id\": 110,\n" +
                       "    \"name\": \"ABCC\"\n" +
                       "  },\n" +
                       "  \"name\": \"doggie\",\n" +
                       "  \"photoUrls\": [\n" +
                       "    \"string\"\n" +
                       "  ],\n" +
                       "  \"tags\": [\n" +
                       "    {\n" +
                       "      \"id\": 0,\n" +
                       "      \"name\": \"string\"\n" +
                       "    }\n" +
                       "  ],\n" +
                       "  \"status\": \"available\"\n" +
                       "}")
                .when()
                .post("v2/pet")
                .then().log().all()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getContentType(),"application/json");
        Assert.assertEquals(response.jsonPath().getString("category.name"), "ABCC");

    }
@Test
//get the pet ID data
    public void getPetIdData()
    {
        RestAssured.baseURI="https://petstore.swagger.io";
        Response response = given().header("Accept","*/*")
                .log().all()
                .when()
                .get("v2/pet/9223372016900014000")

                .then()
                .log().all()
                .extract().response();

        //Assert.assertEquals(response.jsonPath().getString("id","");
    }

    @Test
    // update the pet data with valid ID
    public void updateExistingPetDataWith_Valid_ID()
    {
        RestAssured.baseURI ="https://petstore.swagger.io";
        Response response= given()
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 9223372016900016766,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"test 123\"\n" +
                        "  },\n" +
                        "  \"name\": \"cat\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"sold\"\n" +
                        "}")
                .when()
                .put("2/pet")

                .then().log().all().extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getContentType(),"application/json");
    }
    @Test
    // 404 pet does not exist then shown 404
    public void updateExistingPetDataWith_InValid_ID()
    {
        RestAssured.baseURI ="https://petstore.swagger.io";
        Response response= given()
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 110,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"test 123\"\n" +
                        "  },\n" +
                        "  \"name\": \"cat\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("2/pet")

                .then().log().all().extract().response();
        Assert.assertEquals(response.getStatusCode(),404);
        Assert.assertEquals(response.getContentType(),"text/html");
    }



    
}




