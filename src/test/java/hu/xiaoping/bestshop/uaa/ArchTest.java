package hu.xiaoping.bestshop.uaa;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("hu.xiaoping.bestshop.uaa");

        noClasses()
            .that()
                .resideInAnyPackage("hu.xiaoping.bestshop.uaa.service..")
            .or()
                .resideInAnyPackage("hu.xiaoping.bestshop.uaa.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..hu.xiaoping.bestshop.uaa.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
