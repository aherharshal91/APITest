package Pages;

import Base.BaseService;
import io.restassured.response.Response;

public class userRelatedData extends BaseService {

    public Response createUserListInStore()
    {
        String body = "[\n" +
                "  {\n" +
                "    \"id\": 0,\n" +
                "    \"username\": \"Jonluis\",\n" +
                "    \"firstName\": \"Luis\",\n" +
                "    \"lastName\": \"Jon\",\n" +
                "    \"email\": \"jon@gmail.com\",\n" +
                "    \"password\": \"Test123\",\n" +
                "    \"phone\": \"123445666\",\n" +
                "    \"userStatus\": 0\n" +
                "  }\n" +
                "\n" +
                "]";
         return executePostApi(body,"v2/user/createWithList");

    }

    public Response arrayListOfUserCreateWishList()
    {
        String body = "[\n" +
                "  {\n" +
                "    \"id\": 0,\n" +
                "    \"username\": \"Jonluis\",\n" +
                "    \"firstName\": \"Luis\",\n" +
                "    \"lastName\": \"Jon\",\n" +
                "    \"email\": \"jon@gmail.com\",\n" +
                "    \"password\": \"Test123\",\n" +
                "    \"phone\": \"123445666\",\n" +
                "    \"userStatus\": 0\n" +
                "  }\n" +
                "{\n" +
                "    \"id\": 0,\n" +
                "    \"username\": \"petermil\",\n" +
                "    \"firstName\": \"peter\",\n" +
                "    \"lastName\": \"mike\",\n" +
                "    \"email\": \"peter@gmail.com\",\n" +
                "    \"password\": \"Test1234\",\n" +
                "    \"phone\": \"123456789\",\n" +
                "    \"userStatus\": 0\n" +
                "  }\n" +
                "]";
        return executePostApi(body,"v2/user/createWithList");
    }

    public Response userLoginIntoSystem()
    {
       return userLoginIntoSystemData("v2/user/login");
    }

    public Response userLogoutFromSystem()
    {
       return userLogOutFromSystemData("v2/user/logout");
    }

    public Response LoginIntoSystem()
    {
        return loginIntoSystemUsingSpecBuilder("v2/user/login");
    }
}
