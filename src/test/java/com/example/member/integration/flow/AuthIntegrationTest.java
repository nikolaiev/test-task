package com.example.member.integration.flow;

import com.example.member.dto.LoginDto;
import com.example.member.model.Member;
import org.junit.Assert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.member.FlowIntegrationTest.APP_URL_TEMPLATE;
import static com.example.member.FlowIntegrationTest.AUTH_HEADER_NAME;

public class AuthIntegrationTest {

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private String jwt;

    /*order of operation is important*/
    public void testPostSingUpAndRunFlow() {
        testPostSingUp();
        testPostSingIn();
        final String memberId = testAddMember();
        final Member member = testGetMemberById(memberId);
        testGetAllMembers();
        testUpdateMemberById(member);
        testDeleteMemberById(memberId);
    }

    private void testDeleteMemberById(String memberId) {
        final ResponseEntity forEntityBeforeDelete = restTemplate.getForEntity(APP_URL_TEMPLATE + "/members", List.class);
        final List listBeforeDelete = (List) forEntityBeforeDelete.getBody();
        int sizeBeforeDelete = listBeforeDelete.size();

        /*Deleting*/
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(AUTH_HEADER_NAME, this.jwt);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        final ResponseEntity<Object> forEntity = restTemplate.exchange(APP_URL_TEMPLATE + "/members/" + memberId, HttpMethod.DELETE, httpEntity, Object.class);
        Assert.assertEquals(forEntity.getStatusCode(), HttpStatus.OK);

        final ResponseEntity forEntityAfterDelete = restTemplate.getForEntity(APP_URL_TEMPLATE + "/members", List.class);
        final List listAfterDelete = (List) forEntityAfterDelete.getBody();
        final int sizeAfterDelete = listAfterDelete.size();

        Assert.assertEquals(sizeBeforeDelete, sizeAfterDelete + 1);
    }

    private void testUpdateMemberById(Member member) {
        final String initialFirstName = member.getFirstName();
        final String expectedUpdatedFirstName = initialFirstName + "123";
        member.setFirstName(expectedUpdatedFirstName);

        final ResponseEntity forEntity = performRequestWithJwt(APP_URL_TEMPLATE + "/members", HttpMethod.PUT, member, Member.class);
        Assert.assertEquals(forEntity.getStatusCode(), HttpStatus.OK);
        Member updatedMember = (Member) forEntity.getBody();
        Assert.assertEquals(expectedUpdatedFirstName, updatedMember.getFirstName());
        Assert.assertEquals(expectedUpdatedFirstName, testGetMemberById(member.getId()).getFirstName());
    }

    private void testGetAllMembers() {
        final ResponseEntity forEntity = restTemplate.getForEntity(APP_URL_TEMPLATE + "/members", List.class);
        final List body = (List) forEntity.getBody();
        Assert.assertEquals(1, body.size());
    }

    private void testPostSingUp() {
        LoginDto loginDto = new LoginDto("vlad", "nikolaiev");
        final ResponseEntity forEntity = restTemplate.postForEntity(APP_URL_TEMPLATE + "/api/signup", loginDto, Object.class);
        Assert.assertNotNull(forEntity.getBody());
        Assert.assertEquals(forEntity.getStatusCode(), HttpStatus.OK);
    }

    private Member testGetMemberById(String memberId) {
        final Member member = restTemplate.getForEntity(APP_URL_TEMPLATE + "/members/" + memberId, Member.class).getBody();
        Assert.assertNotNull(member);
        Assert.assertEquals(member.getId(), memberId);
        return member;
    }

    private String testAddMember() {
        Member member = Member.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .birthday(new Date())
                .postalCode(123)
                .picture("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg=="
                        .getBytes())
                .build();
        final ResponseEntity forEntity = performRequestWithJwt(APP_URL_TEMPLATE + "/members", HttpMethod.POST, member, Member.class);
        Assert.assertEquals(forEntity.getStatusCode(), HttpStatus.OK);
        return ((Member) forEntity.getBody()).getId();
    }

    private void testPostSingIn() {
        LoginDto loginDto = new LoginDto("vlad", "nikolaiev");
        final ResponseEntity forEntity = restTemplate.postForEntity(APP_URL_TEMPLATE + "/api/auth", loginDto, Object.class);
        final String token = ((Map) forEntity.getBody()).get("token").toString();
        Assert.assertNotNull(token);
        Assert.assertEquals(HttpStatus.OK, forEntity.getStatusCode());
        this.jwt = token;
    }

    private <T> ResponseEntity<T> performRequestWithJwt(String url, HttpMethod post, Object objectToSend, Class<T> respClass) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(AUTH_HEADER_NAME, this.jwt);
        HttpEntity<?> httpEntity = new HttpEntity<>(objectToSend, headers);
        return restTemplate.exchange(url, post, httpEntity, respClass);
    }
}
