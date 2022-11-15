package org.sonar.samples.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class EntityManagerCheckerTest {

    @Test
    void test() {
        CheckVerifier.newVerifier()
                .onFile("src/test/files/EntityManagerCheckSample.java")
                .withCheck(new EntityManagerCheck())
                .verifyIssues();
    }
}
