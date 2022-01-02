package Practico14.steps;

import Practico10.Lead;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import Practico14.steps.AuthenticationHelper;

import static io.restassured.RestAssured.given;

public class LeadTest {
        String leadCompany = "IBM";
        String leadName = "Pedro Lopez";
        String leadCourse = "Course A0";
        Lead newLead;
        Response newLeadResponse;
        String newLeadInformation;
        String newLeadOnlyLastname;
        String newLeadResponseWithoutBody;

        @Given("I got a lead")
        public void i_got_a_new_case() {
                newLead = new Lead(leadName, leadCompany, leadCourse);
        }

        @When("I send a request to create the lead")
        public void i_send_a_request_to_create_a_lead() {
                System.out.println("RestAssured.baseURI: " + RestAssured.baseURI);

                //create the lead
                newLeadResponse =
                        given()
                                .header("Content-type", "application/json")
                                .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN)
                                .body(newLead).log().all()
                        .when()
                                .post("/services/data/v51.0/sobjects/Lead")
                        .then()
                                .assertThat()
                                .statusCode(201)
                                .log().all()
                                .extract().response();

        }

        @Then("a lead is created")
        public void a_lead_has_been_created() {
                JsonPath jsonPath = newLeadResponse.jsonPath();

                String leadId = jsonPath.get("id");
                boolean status = jsonPath.get("success");

                //obtener la informacion del lead previamente creado...
                newLeadInformation =
                        given()
                                .header("Content-Type", "application/json")
                                .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN)
                        .when()
                                .get("services/data/v51.0/sobjects/Lead/" + leadId )
                        .then()
                                .assertThat().statusCode(200).extract().asString();

                System.out.println("Informacion del contacto obtenido: " + newLeadInformation);
//                Informacion del contacto obtenido: {"attributes":{"type":"Lead","url":"/services/data/v51.0/sobjects/Lead/00Q5f000008AsnDEAS"},"Id":"00Q5f000008AsnDEAS","IsDeleted":false,"MasterRecordId":null,"LastName":"Pedro Lopez","FirstName":null,"Salutation":null,"Name":"Pedro Lopez","Title":null,"Company":"IBM","Street":null,"City":null,"State":null,"PostalCode":null,"Country":null,"Latitude":null,"Longitude":null,"GeocodeAccuracy":null,"Address":null,"Phone":null,"MobilePhone":null,"Fax":null,"Email":null,"Website":null,"PhotoUrl":"/services/images/photo/00Q5f000008AsnDEAS","Description":null,"LeadSource":null,"Status":"Open - Not Contacted","Industry":null,"Rating":null,"AnnualRevenue":null,"NumberOfEmployees":null,"OwnerId":"0055f000003x7oxAAA","IsConverted":false,"ConvertedDate":null,"ConvertedAccountId":null,"ConvertedContactId":null,"ConvertedOpportunityId":null,"IsUnreadByOwner":true,"CreatedDate":"2022-01-02T15:41:15.000+0000","CreatedById":"0055f000003x7oxAAA","LastModifiedDate":"2022-01-02T15:41:15.000+0000","LastModifiedById":"0055f000003x7oxAAA","SystemModstamp":"2022-01-02T15:41:16.000+0000","LastActivityDate":null,"LastViewedDate":"2022-01-02T15:41:16.000+0000","LastReferencedDate":"2022-01-02T15:41:16.000+0000","Jigsaw":null,"JigsawContactId":null,"CleanStatus":"Pending","CompanyDunsNumber":null,"DandbCompanyId":null,"EmailBouncedReason":null,"EmailBouncedDate":null,"IndividualId":null,"SICCode__c":null,"ProductInterest__c":null,"Primary__c":null,"CurrentGenerators__c":null,"NumberofLocations__c":null,"Course__c":"Course A0"}
                Assert.assertTrue(leadId.startsWith("00Q"), "El id deberia comenzar con 00Q");
                Assert.assertTrue(status, "Error: el status deberia ser true");
        }

        @Then("new lead information expected is the same as lead sent")
        public void new_lead_information_expected_is_the_same_as_lead_sent() {
                JsonPath js = new JsonPath(newLeadInformation);

                String status = js.getString("Status");
                String lastName = js.getString("LastName");
                String company = js.getString("Company");
                String course = js.getString("Course__c");

                Assert.assertEquals(status, "Open - Not Contacted", "Status incorrecto");
                Assert.assertEquals(lastName, leadName, "Se esperaba otro apellido");
                Assert.assertEquals(company, leadCompany, "Se esperaba otra Company");
                Assert.assertEquals(course, leadCourse, "Se esperaba otro Course");
        }


        @Given("I got a lead with last name")
        public void i_got_a_lead_with_last_name() { newLead = new Lead(leadName); }

        @When("I send a request to create the lead with lastname")
        public void i_send_a_request_to_create_a_lead_with_lastname() {
                System.out.println("RestAssured.baseURI: " + RestAssured.baseURI);

                //create the lead
                newLeadOnlyLastname =
                        given()
                                .header("Content-type", "application/json")
                                .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN)
                                .body(newLead).log().all()
                        .when()
                                .post("/services/data/v51.0/sobjects/Lead")
                        .then()
                                .assertThat()
                                .statusCode(400)
                                .log().all()
                                .extract().asString();

                System.out.println("newLeadOnlyLastname: " + newLeadOnlyLastname);
        }

        @Then("I got an error message")
        public void i_got_an_error_message() {
//[
//    {
//        "message": "Required fields are missing: [Company, Course__c]",
//        "errorCode": "REQUIRED_FIELD_MISSING",
//        "fields": [
//            "Company",
//            "Course__c"
//        ]
//    }
//]
                JsonPath js = new JsonPath(newLeadOnlyLastname);
                String errorCode = js.getString("[0].errorCode");

                Assert.assertEquals(errorCode, "REQUIRED_FIELD_MISSING", "No se obtuvo el codigo de error correspondiente");
        }

        @When("I send a request to create the lead without body")
        public void i_send_a_request_to_create_the_lead_without_body() {
                newLeadResponseWithoutBody =
                        given()
                                .header("Content-type", "application/json")
                                .header("Authorization", "Bearer " + AuthenticationHelper.ACCESS_TOKEN)
//                                .body(newLead).log().all()
                        .when()
                                .post("/services/data/v51.0/sobjects/Lead")
                        .then()
                                .assertThat()
                                .statusCode(400)
                                .log().all()
                                .extract().asString();

                System.out.println("newLeadResponseWithoutBody: " + newLeadResponseWithoutBody);

                JsonPath js = new JsonPath(newLeadOnlyLastname);
                String errorCode = js.getString("[0].errorCode");
                String errorMessage = js.getString("[0].message");

                Assert.assertEquals(errorCode, "JSON_PARSER_ERROR", "No se obtuvo el codigo de error correspondiente");
                Assert.assertEquals(errorMessage, "The HTTP entity body is required, but this request has no entity body.", "No se obtuvo el mensaje de error correspondiente");
        }
}

