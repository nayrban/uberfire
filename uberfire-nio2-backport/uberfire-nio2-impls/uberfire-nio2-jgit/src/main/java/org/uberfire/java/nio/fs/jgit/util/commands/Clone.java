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

package org.uberfire.java.nio.fs.jgit.util.commands;

import java.io.File;
import java.util.Optional;

import org.eclipse.jgit.transport.CredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uberfire.java.nio.fs.jgit.util.JGitUtil;

public abstract class Clone extends GitCommand {

    Logger logger = LoggerFactory.getLogger(Clone.class);

    protected <T> Optional<T> clone(File directory,
                                    String origin,
                                    CredentialsProvider credentialsProvider) {
        JGitUtil.cloneRepository(directory,
                                 origin,
                                 true,
                                 credentialsProvider);
        if (logger.isDebugEnabled()) {
            logger.debug("{} repository successfully cloned into {}",
                         origin,
                         directory.getPath());
        }
        return Optional.empty();
    }
}
