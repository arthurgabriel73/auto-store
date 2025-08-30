package com.autostore.user.acceptance.config;


import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
class CucumberTest {

    @Test
    void testSuite() {
        File bddResourcesDirectory = new File("src/test/resources/features");
        assertTrue(bddResourcesDirectory.exists());
    }

}
