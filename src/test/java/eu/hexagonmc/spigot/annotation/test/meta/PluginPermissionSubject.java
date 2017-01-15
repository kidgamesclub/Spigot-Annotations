/**
 *
 * Copyright (C) 2017  HexagonMc <https://github.com/HexagonMC>
 * Copyright (C) 2017  Zartec <zartec@mccluster.eu>
 *
 *     This file is part of Spigot-Annotations.
 *
 *     Spigot-Annotations is free software:
 *     you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Spigot-Annotations is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Spigot-Annotations.
 *     If not, see <http://www.gnu.org/licenses/>.
 */
package eu.hexagonmc.spigot.annotation.test.meta;

import com.google.common.truth.FailureStrategy;
import com.google.common.truth.Subject;
import com.google.common.truth.SubjectFactory;
import com.google.common.truth.Truth;
import eu.hexagonmc.spigot.annotation.meta.PluginPermission;

public class PluginPermissionSubject extends Subject<PluginPermissionSubject, PluginPermission> {

    private static final SubjectFactory<PluginPermissionSubject, PluginPermission> METADATA_SUBJECT_FACTORY;

    static {
        METADATA_SUBJECT_FACTORY = new SubjectFactory<PluginPermissionSubject, PluginPermission>() {

            @Override
            public PluginPermissionSubject getSubject(FailureStrategy failureStrategy, PluginPermission target) {
                return new PluginPermissionSubject(failureStrategy, target);
            }
        };
    }

    public PluginPermissionSubject(FailureStrategy failureStrategy, PluginPermission actual) {
        super(failureStrategy, actual);
    }

    public static PluginPermissionSubject assertThat(PluginPermission permission) {
        return Truth.assertAbout(METADATA_SUBJECT_FACTORY).that(permission);
    }

    public void setEmptyNameThrows() {
        try {
            actual().setName("");
            fail("set empty name throws");
        } catch (IllegalArgumentException e) {
            // ignore
        }
    }

    public void nameEquals(PluginPermission other) {
        if (!actual().getName().equals(other.getName())) {
            fail("name equals", other);
        }
    }

    public void nameNotEquals(PluginPermission other) {
        if (actual().getName().equals(other.getName())) {
            fail("name not equals", other);
        }
    }

    public void addEmptyChildThrows() {
        try {
            actual().addChild("", true);
            fail("add empty child throws");
        } catch (IllegalArgumentException e) {
            // ignore
        }
    }

    public void addDuplicateChildThrows() {
        try {
            actual().addChild("test", true);
            actual().addChild("test", true);
            fail("add duplicate child throws");
        } catch (IllegalArgumentException e) {
            // ignore
        } finally {
            actual().removeChild("test");
        }
    }

    public void childsEquals(PluginPermission other) {
        if (!actual().getChilds().equals(other.getChilds())) {
            fail("childs equals", other);
        }
    }

    public void childsNotEquals(PluginPermission other) {
        if (actual().getChilds().equals(other.getChilds())) {
            fail("childs not equals", other);
        }
    }
}
