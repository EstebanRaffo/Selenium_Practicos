package factories;

import org.testng.annotations.*;
import tests.DocusignTest;

public class DocusignFactory {

    @Factory
    public Object[] docusignFactoryTest(){
        return new Object[]{
            new DocusignTest("instancia 1"),
            new DocusignTest("instancia 2"),
            new DocusignTest("instancia 3")
        };
    }
}
