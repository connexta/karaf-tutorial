package com.connexta.itest;

import com.connexta.api.Greeting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.MavenArtifactUrlReference;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import javax.inject.Inject;
import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.ops4j.pax.exam.CoreOptions.*;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.configureConsole;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class TestGreetingService {
    private static String NAME = "Test";
    private static String EXPECTED = "Hello, " + NAME + "!";

    @Inject
    private Greeting greeting;

    @Configuration
    public Option[] config() {
        MavenArtifactUrlReference distro = maven()
                .groupId("com.connexta")
                .artifactId("distribution")
                .versionAsInProject()
                .type("zip");

        return options(karafDistributionConfiguration()
                        .frameworkUrl(distro)
                        .unpackDirectory(new File("target/exam")),
                configureConsole().ignoreLocalConsole(),
                junitBundles()
        );
    }

    @Test
    public void testGreetingService() {
        String result = greeting.hello(NAME);
        assertThat(result, equalTo(EXPECTED));
    }
}
