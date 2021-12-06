package com.example.b07_final_project;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.text.TextUtils;

import com.example.b07_final_project.helper.Customer;
import com.example.b07_final_project.helper.IDobj;
import com.example.b07_final_project.helper.LoginPresenter;
import com.example.b07_final_project.helper.MVPview;
import com.example.b07_final_project.helper.Model;
import com.example.b07_final_project.helper.Owner;
import com.example.b07_final_project.helper.Singleton;
import com.example.b07_final_project.helper.Store;
import com.example.b07_final_project.owner_dashboard.SignUp_Owner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTester {

    @Mock
    Model model;

    @Mock
    Singleton singleton;

    @Mock
    MVPview view;

    @Mock
    Customer customer;

    @Mock
    Owner owner;

    @Mock
    Store store;

    private final String correctEmail = "test1@gmail.com";
    private final String incorrectEmail = "test2@gmail.com";
    private final String invalidEmail = "testgmail.com";
    private final String unregisteredEmail = "nouserfound@gmail.com";
    private final String badFormatEmail = "abcdefg";
    private final String emptyStr = "";
    private final String correctPassword = "12345678";
    private final String incorrectPassword = "abcdefg";
    private final String correctStoreName = "Store Name";
    private final String incorrectStoreName = "Not Store Name";
    private final String correctCustomerName = "Customer Name";
    private final String correctOwnerName = "Owner Name";
    private final String correctPhoneNumber = "1234567890";
    private final String incorrectPhoneNumber = "abc123";


    // Tests

    @Test
    public void AAAA_Initialize_test() { // starts with AAA to make it the first unit test (was risk of missing a bug if it was not the first)

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        assertNotNull(presenter);
    }

    @Test
    public void AAAB_getID_test() { // starts with AAA to make it the first unit test (was risk of missing a bug if it was not the first)

        LoginPresenter.Initialize(model, singleton); // to meet precondition for getID()

        LoginPresenter presenter1 = LoginPresenter.getID();
        assertNotNull(presenter1);

        LoginPresenter presenter2 = LoginPresenter.getID();
        assertNotNull(presenter2);
        assertEquals(presenter1, presenter2);
    }



    @Test
    public void loginCustomer_test_noMatchingEmail() {

        ArrayList<IDobj> customers = new ArrayList<>();
        customers.add(customer);

        when(model.getAllIDobj(IDobj.CUSTOMER)).thenReturn(customers);
        when(customer.getEmail()).thenReturn(correctEmail);
        when(customer.getPassword()).thenReturn(correctPassword);
        when(model.getIDobj(customer)).thenReturn(customer);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        Customer result = presenter.loginCustomer(incorrectEmail, correctPassword);

        verify(singleton, never()).setCurrentLogin(anyObject());
        assertEquals(result, null);
    }

    @Test
    public void loginCustomer_test_wrongPassword() {

        ArrayList<IDobj> customers = new ArrayList<>();
        customers.add(customer);

        when(model.getAllIDobj(IDobj.CUSTOMER)).thenReturn(customers);
        when(customer.getEmail()).thenReturn(correctEmail);
        when(customer.getPassword()).thenReturn(correctPassword);
        when(model.getIDobj(customer)).thenReturn(customer);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        Customer result = presenter.loginCustomer(correctEmail, incorrectPassword);

        verify(singleton, never()).setCurrentLogin(anyObject());
        assertEquals(result, null);
    }

    @Test
    public void loginCustomer_test_successfulLogin() {

        ArrayList<IDobj> customers = new ArrayList<>();
        customers.add(customer);

        when(model.getAllIDobj(IDobj.CUSTOMER)).thenReturn(customers);
        when(customer.getEmail()).thenReturn(correctEmail);
        when(customer.getPassword()).thenReturn(correctPassword);
        when(model.getIDobj(customer)).thenReturn(customer);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        Customer result = presenter.loginCustomer(correctEmail, correctPassword);

        verify(singleton).setCurrentLogin(customer);
        assertEquals(result, customer);
    }


    @Test
    public void loginOwner_test_noMatchingEmail() {

        ArrayList<IDobj> owners = new ArrayList<>();
        owners.add(owner);

        when(model.getAllIDobj(IDobj.OWNER)).thenReturn(owners);
        when(owner.getEmail()).thenReturn(correctEmail);
        when(owner.getPassword()).thenReturn(correctPassword);
        when(model.getIDobj(owner)).thenReturn(owner);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        Owner result = presenter.loginOwner(incorrectEmail, correctPassword);

        verify(singleton, never()).setCurrentLogin(anyObject());
        assertEquals(result, null);
    }

    @Test
    public void loginOwner_test_wrongPassword() {

        ArrayList<IDobj> owners = new ArrayList<>();
        owners.add(owner);

        when(model.getAllIDobj(IDobj.OWNER)).thenReturn(owners);
        when(owner.getEmail()).thenReturn(correctEmail);
        when(owner.getPassword()).thenReturn(correctPassword);
        when(model.getIDobj(owner)).thenReturn(owner);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        Owner result = presenter.loginOwner(correctEmail, incorrectPassword);

        verify(singleton, never()).setCurrentLogin(anyObject());
        assertEquals(result, null);
    }

    @Test
    public void loginOwner_test_successfulLogin() {

        ArrayList<IDobj> owners = new ArrayList<>();
        owners.add(owner);

        when(model.getAllIDobj(IDobj.OWNER)).thenReturn(owners);
        when(owner.getEmail()).thenReturn(correctEmail);
        when(owner.getPassword()).thenReturn(correctPassword);
        when(model.getIDobj(owner)).thenReturn(owner);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        Owner result = presenter.loginOwner(correctEmail, correctPassword);

        verify(singleton).setCurrentLogin(owner);
        assertEquals(result, owner);
    }

    @Test
    public void customerExists_test_true() {

        ArrayList<IDobj> customers = new ArrayList<>();
        customers.add(customer);

        when(model.getAllIDobj(IDobj.CUSTOMER)).thenReturn(customers);
        when(customer.getEmail()).thenReturn(correctEmail);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        boolean result = presenter.customerExists(correctEmail);

        assertTrue(result);
    }

    @Test
    public void customerExists_test_false() {

        ArrayList<IDobj> customers = new ArrayList<>();
        customers.add(customer);

        when(model.getAllIDobj(IDobj.CUSTOMER)).thenReturn(customers);
        when(customer.getEmail()).thenReturn(correctEmail);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        boolean result = presenter.customerExists(incorrectEmail);

        assertFalse(result);
    }

    @Test
    public void storeExists_test_true() {

        ArrayList<IDobj> stores = new ArrayList<>();
        stores.add(store);

        when(model.getAllIDobj(IDobj.STORE)).thenReturn(stores);
        when(store.getName()).thenReturn(correctStoreName);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        boolean result = presenter.storeExists(correctStoreName);

        assertTrue(result);
    }

    @Test
    public void storeExists_test_false() {

        ArrayList<IDobj> stores = new ArrayList<>();
        stores.add(store);

        when(model.getAllIDobj(IDobj.STORE)).thenReturn(stores);
        when(store.getName()).thenReturn(correctStoreName);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        boolean result = presenter.storeExists(incorrectStoreName);

        assertFalse(result);
    }

    @Test
    public void ownerExists_test_false() {

        ArrayList<IDobj> owners = new ArrayList<>();
        owners.add(owner);

        when(model.getAllIDobj(IDobj.OWNER)).thenReturn(owners);
        when(owner.getEmail()).thenReturn(correctEmail);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        boolean result = presenter.ownerExists(incorrectEmail);

        assertFalse(result);
    }

    @Test
    public void ownerExists_test_true() {

        ArrayList<IDobj> owners = new ArrayList<>();
        owners.add(owner);

        when(model.getAllIDobj(IDobj.OWNER)).thenReturn(owners);
        when(owner.getEmail()).thenReturn(correctEmail);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        boolean result = presenter.ownerExists(correctEmail);

        assertTrue(result);
    }


    @Test
    public void newCustomer_test() {

        when(model.newIDobj(IDobj.CUSTOMER)).thenReturn(customer);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        Customer result = presenter.newCustomer(correctEmail, correctCustomerName, correctPassword);

        InOrder order = Mockito.inOrder(singleton, customer);
        order.verify(customer).setEmail(correctEmail);
        order.verify(customer).setName(correctCustomerName);
        order.verify(customer).setPassword(correctPassword);
        order.verify(customer).save();
        order.verify(singleton).setCurrentLogin(customer);

        assertEquals(result, customer);
    }

    @Test
    public void newOwner_test() {

        when(model.newIDobj(IDobj.OWNER)).thenReturn(owner);
        when(model.newIDobj(IDobj.STORE)).thenReturn(store);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        Owner result = presenter.newOwner(correctEmail, correctOwnerName, correctPassword, correctPhoneNumber, correctStoreName);

        InOrder order = Mockito.inOrder(singleton, owner, store);
        order.verify(owner).setEmail(correctEmail);
        order.verify(owner).setPassword(correctPassword);
        order.verify(owner).setName(correctOwnerName);
        order.verify(owner).setPhoneNumber(correctPhoneNumber);
        order.verify(owner).save();
        order.verify(store).setName(correctStoreName);
        order.verify(store).save();
        order.verify(singleton).setCurrentLogin(owner);

        assertEquals(result, owner);
    }

    @Test // When  email  is empty
    public void customerLoginClicked() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.customerLoginClicked(view, "", correctPassword);
        verify(view).makeToast(view, "Please enter email");
    }
    @Test // When  email  is invalid
    public void customerLoginClicked1() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.customerLoginClicked(view, invalidEmail, correctPassword);
        verify(view).makeToast(view, "Please enter a valid email");
    }
    @Test // When password is empty
    public void customerLoginClicked2() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.customerLoginClicked(view, correctEmail, "");
        verify(view).makeToast(view, "Enter Password");
    }
    @Test // No customer found
    public void customerLoginClicked3() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.customerLoginClicked(view, unregisteredEmail, "xyz");
        verify(view).makeToast(view, "Incorrect customer email or password.");
    }
    @Test // Logged in
    public void customerLoginClicked4(){

        ArrayList<IDobj> customers = new ArrayList<>();
        customers.add(customer);

        when(model.getAllIDobj(IDobj.CUSTOMER)).thenReturn(customers);
        when(customer.getEmail()).thenReturn(correctEmail);
        when(customer.getPassword()).thenReturn(correctPassword);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.customerLoginClicked(view, correctEmail, correctPassword);
        verify(view).emptyTextBoxes();
        verify(view).signupOrLogin();
    }

    @Test // When  email  is empty
    public void ownerLoginClicked_emptyEmail_test() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.ownerLoginClicked(view, "", correctPassword);
        verify(view).makeToast(view, "Please enter email");
    }

    @Test // When  email  is invalid
    public void ownerLoginClicked_invalidEmail_test() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.ownerLoginClicked(view, invalidEmail, correctPassword);
        verify(view).makeToast(view, "Please enter a valid email");
    }

    @Test // When password is empty
    public void ownerLoginClicked_emptyPassword_test() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.ownerLoginClicked(view, correctEmail, "");
        verify(view).makeToast(view, "Enter Password");
    }

    @Test // No owner found
    public void ownerLoginClicked_noOwner_test() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.ownerLoginClicked(view, unregisteredEmail, "xyz");
        verify(view).makeToast(view, "Incorrect owner email or password.");
    }

    @Test // Logged in
    public void ownerLoginClicked_loginSuccess_test(){

        ArrayList<IDobj> owners = new ArrayList<>();
        owners.add(owner);

        when(model.getAllIDobj(IDobj.OWNER)).thenReturn(owners);
        when(owner.getEmail()).thenReturn(correctEmail);
        when(owner.getPassword()).thenReturn(correctPassword);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.ownerLoginClicked(view, correctEmail, correctPassword);
        verify(view).emptyTextBoxes();
        verify(view).signupOrLogin();
    }

    @Test
    public void ownerSignupClicked_emptyStoreName_test() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.ownerSignupClicked(view, correctOwnerName, correctEmail, correctPassword, correctPassword, correctPhoneNumber, "");
        verify(view).makeToast(view, "Enter a store Name");
    }

    @Test
    public void ownerSignupClicked_existingStoreName_test() {
        ArrayList<IDobj> stores = new ArrayList<>();
        stores.add(store);
        when(model.getAllIDobj(IDobj.STORE)).thenReturn(stores);
        when(store.getName()).thenReturn(incorrectStoreName);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        presenter.ownerSignupClicked(view, correctOwnerName, correctEmail, correctPassword, correctPassword, correctPhoneNumber, incorrectStoreName);
        verify(view).makeToast(view, "Store Name already exists");
    }

    @Test
    public void ownerSignupClicked_emptyEmail_test() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.ownerSignupClicked(view, correctOwnerName, "", correctPassword, correctPassword, correctPhoneNumber, correctStoreName);
        verify(view).makeToast(view, "Please enter email");
    }

    @Test
    public void ownerSignupClicked_invalidEmail_test() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.ownerSignupClicked(view, correctOwnerName, invalidEmail, correctPassword, correctPassword, correctPhoneNumber, correctStoreName);
        verify(view).makeToast(view, "Please enter a valid email");
    }

    @Test
    public void ownerSignupClicked_existingEmail_test() {
        ArrayList<IDobj> owners = new ArrayList<>();
        owners.add(owner);
        when(model.getAllIDobj(IDobj.OWNER)).thenReturn(owners);
        when(owner.getEmail()).thenReturn(incorrectEmail);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        presenter.ownerSignupClicked(view, correctOwnerName, incorrectEmail, correctPassword, correctPassword, correctPhoneNumber, correctStoreName);
        verify(view).makeToast(view, "Owner already exists");
    }

    @Test
    public void ownerSignupClicked_emptyPhoneNumber_test() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.ownerSignupClicked(view, correctOwnerName, correctEmail, correctPassword, correctPassword, "", correctStoreName);
        verify(view).makeToast(view, "Please enter Phone number");
    }

    @Test
    public void ownerSignupClicked_incorrectPhoneNumber_test() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.ownerSignupClicked(view, correctOwnerName, correctEmail, correctPassword, correctPassword, incorrectPhoneNumber, correctStoreName);
        verify(view).makeToast(view, "Please enter a valid Phone Number");
    }

    @Test
    public void ownerSignupClicked_emptyPassword_test() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.ownerSignupClicked(view, correctOwnerName, correctEmail, "", correctPassword, correctPhoneNumber, correctStoreName);
        verify(view).makeToast(view, "Please enter Password");
    }

    @Test
    public void ownerSignupClicked_incorrectPassword_test() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.ownerSignupClicked(view, correctOwnerName, correctEmail, incorrectPassword, incorrectPassword, correctPhoneNumber, correctStoreName);
        verify(view).makeToast(view, "Password must have at least 8 characters");
    }

    @Test
    public void ownerSignupClicked_incorrectConfirmPassword_test() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.ownerSignupClicked(view, correctOwnerName, correctEmail, correctPassword, incorrectPassword, correctPhoneNumber, correctStoreName);
        verify(view).makeToast(view, "Your passwords do not match");
    }

    @Test
    public void ownerSignupClicked_loginSuccess_test() {

        // for store exists
        ArrayList<IDobj> stores = new ArrayList<>();
        when(model.getAllIDobj(IDobj.STORE)).thenReturn(stores);

        // for owner exists
        ArrayList<IDobj> owners = new ArrayList<>();
        when(model.getAllIDobj(IDobj.OWNER)).thenReturn(owners);

        // for new owner
        when(model.newIDobj(IDobj.OWNER)).thenReturn(owner);
        when(model.newIDobj(IDobj.STORE)).thenReturn(store);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        presenter.ownerSignupClicked(view, correctOwnerName, correctEmail, correctPassword, correctPassword, correctPhoneNumber, correctStoreName);
        //Owner result = presenter.newOwner(correctEmail, correctOwnerName, correctPassword, correctPhoneNumber, correctStoreName);

        //assertEquals(result, owner);


        verify(view).emptyTextBoxes();
        verify(view).signupOrLogin();

        //newOwner called successfully
        InOrder order = Mockito.inOrder(singleton, owner, store);
        order.verify(owner).setEmail(correctEmail);
        order.verify(owner).setPassword(correctPassword);
        order.verify(owner).setName(correctOwnerName);
        order.verify(owner).setPhoneNumber(correctPhoneNumber);
        order.verify(owner).save();
        order.verify(store).setName(correctStoreName);
        order.verify(store).save();
        order.verify(singleton).setCurrentLogin(owner);
    }

    @Test
    public void customerSignupClicked_Test_name_empty() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.customerSignupClicked(view, "", correctEmail, correctPassword, correctPassword);
        verify(view).makeToast(view, "Please enter Name");
    }

    @Test
    public void customerSignupClicked_Test_email_empty() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.customerSignupClicked(view, correctCustomerName, "", correctPassword, correctPassword);
        verify(view).makeToast(view, "Please enter email");
    }

    @Test
    public void customerSignupClicked_Test_email_invalid() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.customerSignupClicked(view, correctCustomerName, invalidEmail, correctPassword, correctPassword);
        verify(view).makeToast(view, "Please enter a valid email");
    }

    @Test
    public void customerSignupClicked_Test_password_empty() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.customerSignupClicked(view, correctCustomerName, correctEmail, "", correctPassword);
        verify(view).makeToast(view, "Please enter Password");
    }

    @Test
    public void customerSignupClicked_Test_password_invalid() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.customerSignupClicked(view, correctCustomerName, correctEmail, incorrectPassword, correctPassword);
        verify(view).makeToast(view, "Password must have at least 8 characters");
    }

    @Test
    public void customerSignupClicked_Test_confirmPassword_invalid() {
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        presenter.customerSignupClicked(view, correctCustomerName, correctEmail, correctPassword, incorrectPassword);
        verify(view).makeToast(view, "Your passwords do not match");
    }

    @Test
    public  void customerSignup_Clicked_Test_customer_exist(){
        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);
        ArrayList<IDobj> customers = new ArrayList<>();
        customers.add(customer);
        when(model.getAllIDobj(IDobj.CUSTOMER)).thenReturn(customers);
        when(customer.getEmail()).thenReturn(correctEmail);
        presenter.customerSignupClicked(view, correctCustomerName, correctEmail, correctPassword, correctPassword);
        verify(view).makeToast(view, "Customer Already Exists");
    }

    @Test
    public void customerSignupClicked_loginSuccess_test() {

        // for customer exists
        ArrayList<IDobj> customers = new ArrayList<>();
        when(model.getAllIDobj(IDobj.CUSTOMER)).thenReturn(customers);

        // for new owner
        when(model.newIDobj(IDobj.CUSTOMER)).thenReturn(customer);

        LoginPresenter presenter = LoginPresenter.Initialize(model, singleton);

        presenter.customerSignupClicked(view, correctCustomerName, correctEmail, correctPassword, correctPassword);
        //Owner result = presenter.newOwner(correctEmail, correctOwnerName, correctPassword, correctPhoneNumber, correctStoreName);

        //assertEquals(result, owner);


        verify(view).emptyTextBoxes();
        verify(view).signupOrLogin();

        //newCustomer called successfully
        InOrder order = Mockito.inOrder(singleton, customer);
        order.verify(customer).setEmail(correctEmail);
        order.verify(customer).setName(correctCustomerName);
        order.verify(customer).setPassword(correctPassword);
        order.verify(customer).save();
        order.verify(singleton).setCurrentLogin(customer);
    }
}
