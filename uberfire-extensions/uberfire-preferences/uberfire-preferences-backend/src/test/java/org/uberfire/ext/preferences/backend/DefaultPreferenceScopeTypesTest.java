/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uberfire.ext.preferences.backend;

import org.junit.Before;
import org.junit.Test;
import org.uberfire.ext.preferences.shared.impl.DefaultScopes;
import org.uberfire.ext.preferences.shared.impl.PreferenceScopeImpl;
import org.uberfire.ext.preferences.shared.impl.exception.InvalidPreferenceScopeException;
import org.uberfire.mocks.SessionInfoMock;
import org.uberfire.rpc.SessionInfo;

import static org.junit.Assert.*;

public class DefaultPreferenceScopeTypesTest {

    private DefaultPreferenceScopeTypes defaultPreferenceScopeTypes;

    @Before
    public void setup() {
        final SessionInfo sessionInfo = new SessionInfoMock();
        defaultPreferenceScopeTypes = new DefaultPreferenceScopeTypes( sessionInfo );
    }

    @Test
    public void validateTypesTest() {
        defaultPreferenceScopeTypes.validate( new PreferenceScopeImpl( DefaultScopes.GLOBAL.type(), null ) );
        defaultPreferenceScopeTypes.validate( new PreferenceScopeImpl( DefaultScopes.USER.type(), null ) );
    }

    @Test(expected = InvalidPreferenceScopeException.class)
    public void validateInvalidTypeTest() {
        defaultPreferenceScopeTypes.validate( new PreferenceScopeImpl( "invalidType", null ) );
    }

    @Test
    public void typesRequireKeyTest() {
        assertFalse( defaultPreferenceScopeTypes.typeRequiresKey( DefaultScopes.GLOBAL.type() ) );
        assertFalse( defaultPreferenceScopeTypes.typeRequiresKey( DefaultScopes.USER.type() ) );
    }

    @Test
    public void defaultKeysForTypesTest() {
        assertEquals( DefaultScopes.GLOBAL.type(), defaultPreferenceScopeTypes.getDefaultKeyFor( DefaultScopes.GLOBAL.type() ) );
        assertEquals( "admin", defaultPreferenceScopeTypes.getDefaultKeyFor( DefaultScopes.USER.type() ) );
    }

    @Test
    public void isEmptyTest() {
        assertTrue( defaultPreferenceScopeTypes.isEmpty( null ) );
        assertTrue( defaultPreferenceScopeTypes.isEmpty( "" ) );
        assertFalse( defaultPreferenceScopeTypes.isEmpty( "anyString" ) );
    }
}