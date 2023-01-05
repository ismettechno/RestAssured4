import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ZippoTest {

    @Test
    public void test(){

         given()
                 // hazırlık işlemlerini yapacağız (token,send body, parametreler)
                 .when()
                 // link i ve metodu veriyoruz
                 .then()
                //  assertion ve verileri ele alma extract
         ;
    }

    @Test
    public void statusCodeTest(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()  // log().all() bütün respons u gösterir
                .statusCode(200) // status kontrolü
        ;
    }

    @Test
    public void contentTypeTest(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()  // log().all() bütün respons u gösterir
                .statusCode(200) // status kontrolü
                .contentType(ContentType.JSON) // dönen sonuç JSON tipinde mi
        ;
    }

    @Test
    public void checkCountryInResponseBody(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()  // log().all() bütün respons u gösterir
                .statusCode(200) // status kontrolü
                .body("country", equalTo("United States")) // body.country == United States ?
        ;
    }


//    pm                              RestAssured
//    body.country                    body("country",
//    body.'post code'                body("post code",
//    body.places[0].'place name'     body("places[0].'place name'")
//    body.places.'place name'        body("places.'place name'")   -> bütün place name leri verir
//                                    bir index verilmezse dizinin bütün elemanlarında arar


    @Test
    public void checkstateInResponseBody(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()  // log().all() bütün respons u gösterir
                .statusCode(200) // status kontrolü
                .body("places[0].state", equalTo("California")) // // birebir eşit mi
        ;
    }

    @Test
    public void bodyJsonPathTest3(){

        given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                //.log().body()  // log().all() bütün respons u gösterir
                .statusCode(200) // status kontrolü
                .body("places.'place name'", hasItem("Dörtağaç Köyü"))   // verile path deki liste bu item e sahip mi, contains
        ;
    }

    @Test
    public void bodyArrayHasSizeTest(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()  // log().all() bütün respons u gösterir
                .statusCode(200) // status kontrolü
                .body("places", hasSize(1)) // palace ın size 1 e eşit mi
        ;
    }

    @Test
    public void combiningTest(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()  // log().all() bütün respons u gösterir
                .statusCode(200) // status kontrolü
                .body("places", hasSize(1)) // palace ın size 1 e eşit mi
                .body("places.state", hasItem("California"))  // verilen path deki list bu item e sahip mi
                .body("places[0].'place name'", equalTo("Beverly Hills")) // verilen path deki değer buna eşit mi
        ;
    }
}
