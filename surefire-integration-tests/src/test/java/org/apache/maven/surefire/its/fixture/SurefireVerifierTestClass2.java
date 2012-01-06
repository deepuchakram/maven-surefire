package org.apache.maven.surefire.its.fixture;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.IOException;
import org.apache.maven.it.VerificationException;

import junit.framework.TestCase;

/**
 * Contains commonly used features for most tests, encapsulating
 * common use cases.
 * <p/>
 * Also includes thread-safe access to the extracted resource
 * files, which AbstractSurefireIntegrationTestClass does not.
 * Thread safe only for running in "classes" mode.
 *
 * @author Kristian Rosenvold
 */
public abstract class SurefireVerifierTestClass2
    extends TestCase
{
    public OutputValidator executeErrorFreeTest( String sourceName, int total ){
        return unpack( sourceName ).executeTest().verifyErrorFree( total );
    }

    public SurefireLauncher unpack( String sourceName )
    {
        try
        {
            return new SurefireLauncher( this.getClass(), sourceName );
        }
        catch ( VerificationException e )
        {
            throw new SurefireVerifierException( e );
        }
        catch ( IOException e )
        {
            throw new SurefireVerifierException( e );
        }
    }
}