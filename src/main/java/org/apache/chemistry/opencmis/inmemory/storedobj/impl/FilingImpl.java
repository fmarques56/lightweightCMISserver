/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.chemistry.opencmis.inmemory.storedobj.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.chemistry.opencmis.inmemory.storedobj.api.Fileable;
import org.apache.chemistry.opencmis.inmemory.storedobj.api.MultiFiling;
import org.apache.chemistry.opencmis.utils.FilePersistence;

public class FilingImpl extends StoredObjectImpl implements Fileable, MultiFiling {

    private List<String> parentIds = new ArrayList<String>(1);

    FilingImpl() {
        super();
    }

    @Override
    public List<String> getParentIds() {
        return parentIds;
    }

    @Override
    public boolean hasParent() {
        return !(null == parentIds || parentIds.isEmpty());
    }

    @Override
    public String getPathSegment() {
        StringBuilder stb = new StringBuilder();
        if (hasParent() && getParentIds().get(0) != FilePersistence.rootId) {
            String parentPathSegment = ((FolderImpl) getStore().getObjectById( getParentIds().get(0))).getPathSegment();
            stb.append(parentPathSegment);
            stb.append("/");
        }
        stb.append(super.getName());
        return stb.toString();
    }

    @Override
    public void addParentId(String parentId) {
        parentIds.add(parentId);
    }

    @Override
    public void removeParentId(String parentId) {
        parentIds.remove(parentId);
    }
}
