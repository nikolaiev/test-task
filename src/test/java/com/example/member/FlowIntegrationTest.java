package com.example.member;

import com.example.member.integration.flow.AuthIntegrationTest;
import com.example.member.integration.flow.NoAuthIntegrationTest;
import com.palantir.docker.compose.DockerComposeRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FlowIntegrationTest {

    /*no sense to retrieve 8181 right now, since it's hardcoded at docker-compose.yaml*/
    public static final String APP_URL_TEMPLATE = "http://localhost:8181";
    public static final String AUTH_HEADER_NAME = "x-auth-token";

    @ClassRule
    public static DockerComposeRule docker = DockerComposeRule.builder()
            .file("docker-compose.yaml")
            .build();

    @Test
    public void testNoAuthenticatedFlow() {
        new NoAuthIntegrationTest().testNoAuthenticatedFlow();
    }


    @Test
    public void testAuthenticatedFlow() {
        new AuthIntegrationTest().testPostSingUpAndRunFlow();
    }
}
