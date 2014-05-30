/**
 * Created by admin on 28/05/2014.
 */
package com.redhat.gps.security;


import org.jboss.security.PicketBoxLogger;
import org.jboss.security.PicketBoxMessages;
import org.jboss.security.SimpleGroup;
import org.jboss.security.auth.spi.AbstractServerLoginModule;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Map;

public class POCPolicyMapperLoginModule extends AbstractServerLoginModule {
    /**
     * The login identity
     */
    private Principal identity;
    /**
     * The proof of login identity
     */
    private char[] credential;

    /**
     * A {@code Throwable} representing the validation error
     */
    private Throwable validateError;

    public POCPolicyMapperLoginModule() {
        PicketBoxLogger.LOGGER.info("POCPolicyMapperLoginModule called");
        policiesList = new ArrayList<IDMPolicyList>();
    }

    //Container of policies
    private ArrayList<IDMPolicyList> policiesList;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler,
                           Map<String, ?> sharedState, Map<String, ?> options) {
        super.initialize(subject, callbackHandler, sharedState, options);

    }

    @Override
    protected Group[] getRoleSets() throws LoginException {
        SimpleGroup rolesGroup = new SimpleGroup("Roles");
        ArrayList<Group> groups = new ArrayList<Group>();
        groups.add(rolesGroup);
        String tmpStr;

        for (IDMPolicyList policy : policiesList) {
            tmpStr = IDMPolicyList.IDM_ROLE_PREFIX.MANAGER_OF_.toString() + policy.getParam1();
            try {
                Principal p1 = super.createIdentity(tmpStr);
                rolesGroup.addMember(p1);
            } catch (Exception e) {
                PicketBoxLogger.LOGGER.debugFailureToCreatePrincipal(tmpStr, e);
            }
            tmpStr = IDMPolicyList.IDM_ROLE_PREFIX.MEMBER_OF.toString() + policy.getParam2();
            try {
                Principal p2 = super.createIdentity(tmpStr);
                rolesGroup.addMember(p2);
            } catch (Exception e) {
                PicketBoxLogger.LOGGER.debugFailureToCreatePrincipal(tmpStr, e);
            }
            tmpStr = IDMPolicyList.IDM_ROLE_PREFIX.OWNER_OF.toString() + policy.getParam3();
            try {
                Principal p3 = super.createIdentity(tmpStr);
                rolesGroup.addMember(p3);
            } catch (Exception e) {
                PicketBoxLogger.LOGGER.debugFailureToCreatePrincipal(tmpStr, e);
            }
        }
        Group[] roleSets = new Group[groups.size()];
        groups.toArray(roleSets);
        return roleSets;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean login() throws LoginException {

        super.loginOk = false;
        String[] info = getUsernameAndPassword();
        String username = info[0];
        String password = info[1];

        if (username == null && password == null) {
            identity = unauthenticatedIdentity;
            PicketBoxLogger.LOGGER.traceUsingUnauthIdentity(identity != null ? identity.getName() : null);
        }

        if (identity == null) {
            try {
                identity = createIdentity(username);
            } catch (Exception e) {
                LoginException le = PicketBoxMessages.MESSAGES.failedToCreatePrincipal(e.getLocalizedMessage());
                le.initCause(e);
                throw le;
            }

            /**
             * Invoke the downstream ws to validate the username and expectedPassword
             * and if non valid then throw an exception
             */

            //Test data for now
            policiesList.add(new IDMPolicyList("res1", "res2", "res3"));
            policiesList.add(new IDMPolicyList("res3", "res4", "res5"));


        }

        if (getUseFirstPass() == true) {    // Add the principal and password to the shared state map
            sharedState.put("javax.security.auth.login.name", identity);
            sharedState.put("javax.security.auth.login.password", credential);
        }
        super.loginOk = true;
        PicketBoxLogger.LOGGER.traceEndLogin(super.loginOk);
        return true;
    }

    @Override
    public boolean commit() throws LoginException {
        PicketBoxLogger.LOGGER.info("Commit called");
        return super.commit();
    }

    @Override
    protected Principal getIdentity() {
        return identity;
    }

    @Override
    protected Principal getUnauthenticatedIdentity() {
        return unauthenticatedIdentity;
    }

    protected Object getCredentials() {
        return credential;
    }

    protected String getUsername() {
        String username = null;
        if (getIdentity() != null)
            username = getIdentity().getName();
        return username;
    }

    /**
     * Called by login() to acquire the username and password strings for
     * authentication. This method does no validation of either.
     *
     * @return String[], [0] = username, [1] = password
     * @throws LoginException thrown if CallbackHandler is not set or fails.
     */
    protected String[] getUsernameAndPassword() throws LoginException {
        String[] info = {null, null};
        // prompt for a username and password
        if (callbackHandler == null) {
            throw PicketBoxMessages.MESSAGES.noCallbackHandlerAvailable();
        }

        NameCallback nc = new NameCallback(PicketBoxMessages.MESSAGES.enterUsernameMessage(), "guest");
        PasswordCallback pc = new PasswordCallback(PicketBoxMessages.MESSAGES.enterPasswordMessage(), false);
        Callback[] callbacks = {nc, pc};
        String username = null;
        String password = null;
        try {
            callbackHandler.handle(callbacks);
            username = nc.getName();
            char[] tmpPassword = pc.getPassword();
            if (tmpPassword != null) {
                credential = new char[tmpPassword.length];
                System.arraycopy(tmpPassword, 0, credential, 0, tmpPassword.length);
                pc.clearPassword();
                password = new String(credential);
            }
        } catch (IOException e) {
            LoginException le = PicketBoxMessages.MESSAGES.failedToInvokeCallbackHandler();
            le.initCause(e);
            throw le;
        } catch (UnsupportedCallbackException e) {
            LoginException le = new LoginException();
            le.initCause(e);
            throw le;
        }
        info[0] = username;
        info[1] = password;
        return info;
    }


    /**
     * Get the error associated with the validatePassword failure
     *
     * @return the Throwable seen during validatePassword, null if no
     * error occurred.
     */
    protected Throwable getValidateError() {
        return validateError;
    }

    /**
     * Set the error associated with the validatePassword failure
     *
     * @param validateError
     */
    protected void setValidateError(Throwable validateError) {
        this.validateError = validateError;
    }


    protected boolean validatePassword(String inputPassword, String expectedPassword) {
        // The downstream web service will handle this
        return true;
    }

}