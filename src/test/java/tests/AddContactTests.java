package tests;

import dto.ContactDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataProviders;

import java.util.Random;

public class AddContactTests extends BaseTest{

    @BeforeClass
    public void preconditions() {
        app.getUserHelper().clickLoginOnNavBar();
        app.getUserHelper().login(user);
    }

    @AfterClass
    public void methodPostCondition() {
        app.getUserHelper().clickLogoutBtn();
        app.getHomePageHelper().navigateToHomePage();
    }

    @Test
    public void positiveAddContact() {
        Random random = new Random();
        String name = "John" + String.valueOf(random.nextInt(1000));
        String phone = String.valueOf(random.nextInt(10000)) + "56789";
        app.getContactHelper().clickAddOnNavBar();
        ContactDTO contactDTO = new ContactDTO()
                .setName(name)
                .setLastName("jhsbfh")
                .setPhone(phone)
                .setEmail("sjkfhj@mail.com")
                .setAddress("hjsbfhb")
                .setDescription("bhjfdb");
        app.getContactHelper().addContact(contactDTO);

        app.getContactHelper().pause(3000);

        Assert.assertTrue(app.getContactHelper().isContactDisplaysOnThePage(phone));
    }

    @Test(dataProvider = "addNewContact", dataProviderClass = DataProviders.class)
    public void positiveAddContactProvider(String name, String lastName, String phone,
                                           String email, String address, String description)
    {
        app.getContactHelper().clickAddOnNavBar();
        ContactDTO contactDTO = new ContactDTO()
                .setName(name)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description);
        app.getContactHelper().addContact(contactDTO);

        app.getContactHelper().pause(2000);

        Assert.assertTrue(app.getContactHelper().isContactDisplaysOnThePage(phone));
    }

    @Test(dataProvider = "addContactCSVFile", dataProviderClass = DataProviders.class)
    public void positiveAddContactProviderCSV(ContactDTO ContactDTO)
    {
        app.getContactHelper().clickAddOnNavBar();
        app.getContactHelper().addContact(ContactDTO);
        app.getContactHelper().pause(2000);
        Assert.assertTrue(app.getContactHelper().isContactDisplaysOnThePage(ContactDTO.getPhone()));
    }

}
