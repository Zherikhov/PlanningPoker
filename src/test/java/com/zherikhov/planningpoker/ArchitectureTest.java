package com.zherikhov.planningpoker;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.zherikhov.planningpoker")
public class ArchitectureTest {

    @ArchTest
    static final ArchRule webShouldNotDependOnImplOrPersistence =
            noClasses().that().resideInAnyPackage("..web..")
                    .should().dependOnClassesThat().resideInAnyPackage("..impl..", "..persistence..");
}
