package Pages;

import Base.BaseService;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;


public class petRelatedData extends BaseService
{

    public Response getAvailableStateData()
    {
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("status", "available");
        return  executeGetApi(queryParam,"v2/pet/findByStatus");

    }

    public Response getPendingStateData()
    {
        Map<String,Object> queryParam = new HashMap<>();
        queryParam.put("status", "pending");
        return executeGetApi(queryParam,"v2/pet/findByStatus");
    }
    public Response getInvalid_400_PendingStateData()
    {
        Map<String,Object> queryParam = new HashMap<>();
        queryParam.put("status", "pending");
        return executeGetApi(queryParam,"v2/pet/findByStatu");
    }

    public Response getSoldStateData()
    {
        Map<String,Object> queryParam = new HashMap<>();
        queryParam.put("status", "sold");
        return executeGetApi(queryParam,"v2/pet/findByStatus");
    }

    public Response postNewPetData(String Name, String DogName)
    {
        String body ="{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 110,\n" +
                "    \"name\": \""+Name+"\"\n" +
                "  },\n" +
                "  \"name\": \""+DogName+"\",\n" +
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
                "}";
        return executePostApi(body,"v2/pet");

    }
    public Response postInvalid_405_NewPetData(String Name, String DogName)
    {
        String body ="{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 110,\n" +
                "    \"name\": \""+Name+"\"\n" +
                "  },\n" +
                "  \"name\": \""+DogName+"\",\n" +
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
                "}";
        return executeInvalid_405_PostApi(body,"v2/pet");

    }

    public Response updatePetData(String Name, String DogName, int id)
    {
        String UpdateBody = "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \""+Name+"\"\n" +
                "  },\n" +
                "  \"name\": \""+DogName+"\",\n" +
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
                "}";
        return executeUpdateApi(UpdateBody,"v2/pet");

    }


    public Response setImageOfPet(String petid)

    {
        String PetId = petid;
        return uploadImage(PetId);
    }



}

