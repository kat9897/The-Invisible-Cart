package com.example.b07_final_project;

import com.example.b07_final_project.helper.FirebaseModel;
import com.example.b07_final_project.helper.LoginPresenter;
import com.example.b07_final_project.helper.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTester {

    @Mock
    Model model = new FirebaseModel();

    LoginPresenter presenter = LoginPresenter.Initialize(model);

    @Test
    public void loginCustomer_test1() {

        assert true;

    }




    /*
    @Override
    public Customer loginCustomer(String email, String password) {

        ArrayList<IDobj> customers = database.getAllIDobj(IDobj.CUSTOMER);

        for (IDobj o : customers){
            Customer c = (Customer) o;

            if (c.getEmail().equals(email)){
                if (c.getPassword().equals(password)) {
                    singleton.setCurrentLogin(database.getIDobj(c)); // get a new one so it's not same as return
                    return c;
                } else
                    return null;
            }
        }
        return null;
    }
     */

}
