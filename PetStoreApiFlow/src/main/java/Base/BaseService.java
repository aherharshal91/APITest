package Base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseService {


    public Response executeGetApi(Map<String,Object> queryParam, String endPoint)
    {
        RestAssured.baseURI ="https://petstore.swagger.io";
        Response response = given()

                .log().all()
                .header("Accept", "*/*")

                .when()
                .queryParams(queryParam)
                .get(endPoint)

                .then()
                .log().all().extract().response();
        return response;
    }

    public Response executePostApi(Object body, String endpoint)
    {
        RestAssured.baseURI ="https://petstore.swagger.io";
        Response response= given()
                .log().all().header("Content-Type","application/json")
                .body(body)

                .when()
                .post(endpoint)
                .then().log().all()
                .extract().response();
        return response;

    }
    public Response executeInvalid_405_PostApi(Object body, String endpoint)
    {
        RestAssured.baseURI ="https://petstore.swagger.io";
        Response response= given()
                .log().all().header("Content-Type","application/json")
                .body(body)

                .when()
                .post()
                .then().log().all()
                .extract().response();
        return response;

    }

    public Response executeUpdateApi(Object body, String endpoint)
    {
        RestAssured.baseURI ="https://petstore.swagger.io";
        Response response= given()
                .log().all().header("Content-Type","application/json")
                .body(body)

                .when()
                .put(endpoint)
                .then().log().all()
                .extract().response();
        return response;

    }

public Response executeDeleteApi(String id)
{
    RestAssured.baseURI ="https://petstore.swagger.io";
    Response response = given()

            .log().all()
            .header("Accept", "*/*")


            .when()
            .delete("v2/pet/"+id)

            .then()
            .log().all().extract().response();
    return response;
}
public Response uploadImage(String PetID)
{


    RestAssured.baseURI="https://petstore.swagger.io";
    Response response = given()
            .log().all()
            .pathParam("id", PetID)
            .header("Accept", "*/*")
            .header("Content-Type", "multipart/form-data")
            .multiPart("file", new File("/Users/harshal.aher/Documents/QA.jpg"))

            .when()
            .post("v2/pet/{id}/uploadImage")

            .then().log().all()
            .extract().response();
    return response;
}

public Response inventory_Status(String endPoint)
{
    RestAssured.baseURI= "https://petstore.swagger.io";
   Response response = given().log().all()
            .header("Accept","*/*")
            .when()
            .get(endPoint)

            .then()
            .log().all()
            .extract().response();
return response;

}

public Response postOrderForPetInStore(Object body, String endpoint)
{
    RestAssured.baseURI ="https://petstore.swagger.io";
    Response response =given().log().all()
            .header("Accept","*/*")
            .header("Content-Type","application/json")
            .body(body)

            .when()
            .post(endpoint)
            .then().log().all()
            .extract().response();
    return response;
}

    public Response store_executeDeleteApi(String ID)
    {
        String PetID = ID;
        RestAssured.baseURI ="https://petstore.swagger.io";
        Response response = given()

                .log().all()

                .header("Accept", "*/*")
                .when()
                .delete("v2/store/order/"+PetID)

                .then()
                .log().all().extract().response();
        return response;
    }

    public Response status_404_store_executeDeleteApi(String ID)
    {
        String PetID = ID;
        RestAssured.baseURI ="https://petstore.swagger.io";
        Response response = RestAssured.given()

                .log().all()

                .header("Accept", "*/*")
                .when()
                .delete("v2/pet"+PetID)

                .then()
                .log().all().extract().response();
        return response;
    }

    public Response userLoginIntoSystemData(String endpoint)
    {
        RestAssured.baseURI ="https://petstore.swagger.io";
        Response response = given().log().all()
                .queryParam("username","harshal")
                .queryParam("password", "1234")
                .header("Accept","*/*")
                .when()
                .get(endpoint)

                .then().log().all()
                .extract().response();
        return response;
    }

    public Response userLogOutFromSystemData(String endpoint)
    {
        RestAssured.baseURI ="https://petstore.swagger.io";
        Response response= given().log().all()
                .header("Accept", "*/*")
                .when()
                .get(endpoint)
                .then().log().all()
                .extract().response();
        return response;
    }




    // RequestSpecBuilder and ResponseSpecbuilder code



        RequestSpecification req = new RequestSpecBuilder().setContentType(ContentType.JSON)
                .setBaseUri("https://petstore.swagger.io")
                .addHeader("Accept", "*/*")
                .build();



       ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

       public Response loginIntoSystemUsingSpecBuilder(String Endpoint)
       {
         RequestSpecification res1 = given().log().all()
                   .spec(req);
           Response response = res1.when().get(Endpoint)
                   .then().spec(res).extract().response();
           return response;
       }


}
