package com.cvnhan.androidcr.mvp.model.local;

import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.internal.Table;

/**
 * Created by NhanCao on 13-Sep-15.
 * details: https://github.com/realm/realm-java/blob/master/examples/migrationExample/src/main/java/io/realm/examples/realmmigrationexample/model/Migration.java
 */


public class YKMADBMigration implements RealmMigration {
    public static final int DBVERSION = 0;

    @Override
    public long execute(Realm realm, long version) {

        return version;
    }

    private long getIndexForProperty(Table table, String name) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).equals(name)) {
                return i;
            }
        }
        return -1;
    }
}