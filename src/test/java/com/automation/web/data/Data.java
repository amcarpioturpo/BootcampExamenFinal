package com.automation.web.data;

import org.testng.annotations.DataProvider;

/**
 * Data provider class.
 * @author juan.montes
 *
 * Modified for ESPN FinalExam
 * @author alejandro.carpio
 */
public class Data {

    @DataProvider(name = "userData")
    public Object[][] inputData() {
        return new Object[][]{{new CreateRandomUser()}};
    }
}
