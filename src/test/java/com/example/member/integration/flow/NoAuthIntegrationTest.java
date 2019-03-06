package com.example.member.integration.flow;

import com.example.member.dto.LoginDto;
import com.example.member.model.Member;
import org.junit.Assert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.example.member.FlowIntegrationTest.APP_URL_TEMPLATE;

public class NoAuthIntegrationTest  {

    private TestRestTemplate restTemplate = new TestRestTemplate();

    /*order of operation is not important*/
    public void testNoAuthenticatedFlow(){
        testGetRequestByIdWithEmptyDb();
        testGetRequestWithEmptyDb();
        testPostRequestNoAuth();
        testPostSingErrorExpected();
    }

    private void testGetRequestWithEmptyDb() {
        final ResponseEntity forEntity = restTemplate.getForEntity(APP_URL_TEMPLATE + "/members", List.class);
        final List body = (List) forEntity.getBody();
        Assert.assertTrue(body.isEmpty());
    }

    private void testGetRequestByIdWithEmptyDb() {
        final ResponseEntity forEntity = restTemplate.getForEntity(APP_URL_TEMPLATE + "/members/1", Member.class);
        Assert.assertNull(forEntity.getBody());
    }

    private void testPostRequestNoAuth() {
        final Member sendObj = Member.builder().build();
        final ResponseEntity forEntity = restTemplate.postForEntity(APP_URL_TEMPLATE + "/members", sendObj, Member.class);
        Assert.assertEquals(forEntity.getStatusCode(), HttpStatus.FORBIDDEN);
    }

    private void testPostSingErrorExpected() {
        LoginDto invalidLoginDto = new LoginDto("1", "2");
        final ResponseEntity forEntity = restTemplate.postForEntity(APP_URL_TEMPLATE + "/api/auth", invalidLoginDto, Object.class);
        Assert.assertEquals(HttpStatus.FORBIDDEN, forEntity.getStatusCode());
    }
}
