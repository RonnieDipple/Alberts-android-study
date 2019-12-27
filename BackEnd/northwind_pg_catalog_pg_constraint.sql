INSERT INTO pg_catalog.pg_constraint (oid, conname, connamespace, contype, condeferrable, condeferred, convalidated, conrelid, contypid, conindid, conparentid, confrelid, confupdtype, confdeltype, confmatchtype, conislocal, coninhcount, connoinherit, conkey, confkey, conpfeqop, conppeqop, conffeqop, conexclop, conbin) VALUES (13033, 'cardinal_number_domain_check', 13018, 'c', false, false, true, 0, 13032, 0, 0, 0, ' ', ' ', ' ', true, 0, false, null, null, null, null, null, null, {OPEXPR :opno 525 :opfuncid 150 :opresulttype 16 :opretset false :opcollid 0 :inputcollid 0 :args ({COERCETODOMAINVALUE :typeId 23 :typeMod -1 :collation 0 :location 195} {CONST :consttype 23 :consttypmod -1 :constcollid 0 :constlen 4 :constbyval true :constisnull false :location 204 :constvalue 4 [ 0 0 0 0 0 0 0 0 ]}) :location 201});
INSERT INTO pg_catalog.pg_constraint (oid, conname, connamespace, contype, condeferrable, condeferred, convalidated, conrelid, contypid, conindid, conparentid, confrelid, confupdtype, confdeltype, confmatchtype, conislocal, coninhcount, connoinherit, conkey, confkey, conpfeqop, conppeqop, conffeqop, conexclop, conbin) VALUES (13045, 'yes_or_no_check', 13018, 'c', false, false, true, 0, 13044, 0, 0, 0, ' ', ' ', ' ', true, 0, false, null, null, null, null, null, null, {SCALARARRAYOPEXPR :opno 98 :opfuncid 67 :useOr true :inputcollid 100 :args ({RELABELTYPE :arg {COERCETODOMAINVALUE :typeId 1043 :typeMod 7 :collation 100 :location 133} :resulttype 25 :resulttypmod -1 :resultcollid 100 :relabelformat 2 :location -1} {ARRAYCOERCEEXPR :arg {ARRAY :array_typeid 1015 :array_collid 100 :element_typeid 1043 :elements ({CONST :consttype 1043 :consttypmod -1 :constcollid 100 :constlen -1 :constbyval false :constisnull false :location 143 :constvalue 7 [ 28 0 0 0 89 69 83 ]} {CONST :consttype 1043 :consttypmod -1 :constcollid 100 :constlen -1 :constbyval false :constisnull false :location 150 :constvalue 6 [ 24 0 0 0 78 79 ]}) :multidims false :location -1} :elemexpr {RELABELTYPE :arg {CASETESTEXPR :typeId 1043 :typeMod -1 :collation 0} :resulttype 25 :resulttypmod -1 :resultcollid 100 :relabelformat 2 :location -1} :resulttype 1009 :resulttypmod -1 :resultcollid 100 :coerceformat 2 :location -1}) :location 139});