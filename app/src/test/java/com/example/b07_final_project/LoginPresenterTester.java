package com.example.b07_final_project;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.b07_final_project.helper.Customer;
import com.example.b07_final_project.helper.FirebaseModel;
import com.example.b07_final_project.helper.IDobj;
import com.example.b07_final_project.helper.LoginPresenter;
import com.example.b07_final_project.helper.MVPview;
import com.example.b07_final_project.helper.Model;
import com.example.b07_final_project.helper.Owner;
import com.example.b07_final_project.helper.Singleton;
import com.example.b07_final_project.helper.Store;

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
    private final String correctPassword = "12345678";
    private final String incorrectPassword = "abcdefg";
    private final String correctStoreName = "Store Name";
    private final String incorrectStoreName = "Not Store Name";
    private final String correctCustomerName = "Customer Name";
    private final String correctOwnerName = "Owner Name";
    private final String correctPhoneNumber = "1112223334";


    // Tests

    @Test
    public void AAA_getID_test() { // starts with AAA to make it the first unit test (was risk of missing a bug if it was not the first)

        LoginPresenter.Initialize(model, singleton);

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


    //  when(.()).thenReturn();
    //  verify().();
    //  verify(, never()).(anyObject());


    /*
    @Override
    public Owner newOwner(String email, String name, String password, String phoneNumber, String storename) {

        Owner owner = (Owner) database.newIDobj(IDobj.OWNER);
        Store store = (Store) database.newIDobj(IDobj.STORE);

        database.addRelation(owner, store);

        owner.setEmail(email);
        owner.setPassword(password);
        owner.setName(name);
        owner.setPhoneNumber(phoneNumber);

        owner.save();

        store.setName(storename);
        store.save();

        singleton.setCurrentLogin(owner);

        return owner;
    }





     */

}
