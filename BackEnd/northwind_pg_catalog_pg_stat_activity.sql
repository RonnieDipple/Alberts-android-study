INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (13318, 'postgres', 5620, 10, 'postgres', '', '::1', null, 49668, '2019-12-27 04:15:44.063351', null, '2019-12-27 04:15:44.100326', '2019-12-27 04:15:44.141584', 'Client', 'ClientRead', 'idle', null, null, 'SELECT count(*) As count, pg_backend_pid() AS pid FROM pg_class cl JOIN pg_namespace ns ON ns.oid=relnamespace WHERE relname=''pga_job'' AND nspname=''pgagent''', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (null, null, 5580, null, null, '', null, null, null, '2019-12-27 04:15:44.075486', null, null, null, 'Activity', 'AutoVacuumMain', null, null, null, '', 'autovacuum launcher');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (null, null, 5596, 10, 'postgres', '', null, null, null, '2019-12-27 04:15:44.085676', null, null, null, 'Activity', 'LogicalLauncherMain', null, null, null, '', 'logical replication launcher');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (13318, 'postgres', 1008, 10, 'postgres', 'pgAdmin 4 - DB:postgres', '::1', null, 49844, '2019-12-27 04:16:46.727119', null, '2019-12-27 19:02:34.001205', '2019-12-27 19:02:34.011721', 'Client', 'ClientRead', 'idle', null, null, 'SELECT CASE WHEN usesuper
       THEN pg_is_in_recovery()
       ELSE FALSE
       END as inrecovery,
       CASE WHEN usesuper AND pg_is_in_recovery()
       THEN pg_is_wal_replay_paused()
       ELSE FALSE
       END as isreplaypaused
FROM pg_user WHERE usename=current_user', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (17089, 'mem:testdb', 10244, 10, 'postgres', 'pgAdmin 4 - DB:mem:testdb', '::1', null, 49890, '2019-12-27 04:16:52.184759', null, '2019-12-27 04:17:06.702936', '2019-12-27 04:17:06.725015', 'Client', 'ClientRead', 'idle', null, null, '/*pga4dash*/
SELECT ''session_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT count(*) FROM pg_stat_activity WHERE datname = (SELECT datname FROM pg_database WHERE oid = 17089)) AS "Total",
   (SELECT count(*) FROM pg_stat_activity WHERE state = ''active'' AND datname = (SELECT datname FROM pg_database WHERE oid = 17089))  AS "Active",
   (SELECT count(*) FROM pg_stat_activity WHERE state = ''idle'' AND datname = (SELECT datname FROM pg_database WHERE oid = 17089))  AS "Idle"
) t
UNION ALL
SELECT ''tps_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT sum(xact_commit) + sum(xact_rollback) FROM pg_stat_database WHERE datname = (SELECT datname FROM pg_database WHERE oid = 17089)) AS "Transactions",
   (SELECT sum(xact_commit) FROM pg_stat_database WHERE datname = (SELECT datname FROM pg_database WHERE oid = 17089)) AS "Commits",
   (SELECT sum(xact_rollback) FROM pg_stat_database WHERE datname = (SELECT datname FROM pg_database WHERE oid = 17089)) AS "Rollbacks"
) ', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (25064, 'starthere', 5676, 10, 'postgres', 'pgAdmin 4 - DB:starthere', '::1', null, 49963, '2019-12-27 04:17:06.441149', null, '2019-12-27 04:51:03.186257', '2019-12-27 04:51:03.206479', 'Client', 'ClientRead', 'idle', null, null, '/*pga4dash*/
SELECT ''session_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT count(*) FROM pg_stat_activity WHERE datname = (SELECT datname FROM pg_database WHERE oid = 25064)) AS "Total",
   (SELECT count(*) FROM pg_stat_activity WHERE state = ''active'' AND datname = (SELECT datname FROM pg_database WHERE oid = 25064))  AS "Active",
   (SELECT count(*) FROM pg_stat_activity WHERE state = ''idle'' AND datname = (SELECT datname FROM pg_database WHERE oid = 25064))  AS "Idle"
) t
UNION ALL
SELECT ''tps_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT sum(xact_commit) + sum(xact_rollback) FROM pg_stat_database WHERE datname = (SELECT datname FROM pg_database WHERE oid = 25064)) AS "Transactions",
   (SELECT sum(xact_commit) FROM pg_stat_database WHERE datname = (SELECT datname FROM pg_database WHERE oid = 25064)) AS "Commits",
   (SELECT sum(xact_rollback) FROM pg_stat_database WHERE datname = (SELECT datname FROM pg_database WHERE oid = 25064)) AS "Rollbacks"
) ', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (13318, 'postgres', 1372, 10, 'postgres', 'pgAdmin 4 - DB:postgres', '::1', null, 57435, '2019-12-27 19:02:55.883119', null, '2019-12-27 19:06:06.461941', '2019-12-27 19:06:06.484202', 'Client', 'ClientRead', 'idle', null, null, '/*pga4dash*/
SELECT ''session_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT count(*) FROM pg_stat_activity) AS "Total",
   (SELECT count(*) FROM pg_stat_activity WHERE state = ''active'')  AS "Active",
   (SELECT count(*) FROM pg_stat_activity WHERE state = ''idle'')  AS "Idle"
) t
UNION ALL
SELECT ''tps_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT sum(xact_commit) + sum(xact_rollback) FROM pg_stat_database) AS "Transactions",
   (SELECT sum(xact_commit) FROM pg_stat_database) AS "Commits",
   (SELECT sum(xact_rollback) FROM pg_stat_database) AS "Rollbacks"
) t
UNION ALL
SELECT ''ti_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT sum(tup_inserted) FROM pg_stat_database) AS "Inserts",
   (SELECT sum(tup_updated) FROM pg_stat_database) AS "Updates",
   (SELECT sum(tup_deleted) FROM pg_stat_database) AS "Deletes"
) t
UNION ALL
SELECT ''to_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT sum(tup_fetched) FROM ', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (25064, 'starthere', 10532, 10, 'postgres', 'pgAdmin 4 - DB:starthere', '::1', null, 57451, '2019-12-27 19:02:56.718171', null, '2019-12-27 19:04:25.183554', '2019-12-27 19:04:25.204675', 'Client', 'ClientRead', 'idle', null, null, '/*pga4dash*/
SELECT ''session_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT count(*) FROM pg_stat_activity WHERE datname = (SELECT datname FROM pg_database WHERE oid = 25064)) AS "Total",
   (SELECT count(*) FROM pg_stat_activity WHERE state = ''active'' AND datname = (SELECT datname FROM pg_database WHERE oid = 25064))  AS "Active",
   (SELECT count(*) FROM pg_stat_activity WHERE state = ''idle'' AND datname = (SELECT datname FROM pg_database WHERE oid = 25064))  AS "Idle"
) t
UNION ALL
SELECT ''tps_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT sum(xact_commit) + sum(xact_rollback) FROM pg_stat_database WHERE datname = (SELECT datname FROM pg_database WHERE oid = 25064)) AS "Transactions",
   (SELECT sum(xact_commit) FROM pg_stat_database WHERE datname = (SELECT datname FROM pg_database WHERE oid = 25064)) AS "Commits",
   (SELECT sum(xact_rollback) FROM pg_stat_database WHERE datname = (SELECT datname FROM pg_database WHERE oid = 25064)) AS "Rollbacks"
) ', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (13318, 'postgres', 15772, 10, 'postgres', 'pgAdmin 4 - DB:postgres', '::1', null, 57620, '2019-12-27 19:05:48.454906', null, '2019-12-27 19:08:36.821814', '2019-12-27 19:08:36.822147', 'Client', 'ClientRead', 'idle', null, null, '
SELECT
    db.oid as did, db.datname, db.datallowconn,
    pg_encoding_to_char(db.encoding) AS serverencoding,
    has_database_privilege(db.oid, ''CREATE'') as cancreate, datlastsysoid
FROM
    pg_database db
WHERE db.oid = 47707', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (47707, 'northwind', 10500, 10, 'postgres', 'pgAdmin 4 - DB:northwind', '::1', null, 57818, '2019-12-27 19:08:36.845215', null, '2019-12-27 19:44:26.465656', '2019-12-27 19:44:26.486108', 'Client', 'ClientRead', 'idle', null, null, '/*pga4dash*/
SELECT ''session_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT count(*) FROM pg_stat_activity WHERE datname = (SELECT datname FROM pg_database WHERE oid = 47707)) AS "Total",
   (SELECT count(*) FROM pg_stat_activity WHERE state = ''active'' AND datname = (SELECT datname FROM pg_database WHERE oid = 47707))  AS "Active",
   (SELECT count(*) FROM pg_stat_activity WHERE state = ''idle'' AND datname = (SELECT datname FROM pg_database WHERE oid = 47707))  AS "Idle"
) t
UNION ALL
SELECT ''tps_stats'' AS chart_name, row_to_json(t) AS chart_data
FROM (SELECT
   (SELECT sum(xact_commit) + sum(xact_rollback) FROM pg_stat_database WHERE datname = (SELECT datname FROM pg_database WHERE oid = 47707)) AS "Transactions",
   (SELECT sum(xact_commit) FROM pg_stat_database WHERE datname = (SELECT datname FROM pg_database WHERE oid = 47707)) AS "Commits",
   (SELECT sum(xact_rollback) FROM pg_stat_database WHERE datname = (SELECT datname FROM pg_database WHERE oid = 47707)) AS "Rollbacks"
) ', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (47707, 'northwind', 16632, 10, 'postgres', 'PostgreSQL JDBC Driver', '127.0.0.1', null, 65106, '2019-12-27 19:51:16.655432', '2019-12-27 19:51:16.690539', '2019-12-27 19:51:16.692335', '2019-12-27 19:51:16.692336', null, null, 'active', null, 11069, 'SELECT t.* FROM pg_catalog.pg_stat_activity t', 'client backend');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (null, null, 5564, null, null, '', null, null, null, '2019-12-27 04:15:44.049248', null, null, null, 'Activity', 'BgWriterHibernate', null, null, null, '', 'background writer');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (null, null, 5552, null, null, '', null, null, null, '2019-12-27 04:15:44.055686', null, null, null, 'Activity', 'CheckpointerMain', null, null, null, '', 'checkpointer');
INSERT INTO pg_catalog.pg_stat_activity (datid, datname, pid, usesysid, usename, application_name, client_addr, client_hostname, client_port, backend_start, xact_start, query_start, state_change, wait_event_type, wait_event, state, backend_xid, backend_xmin, query, backend_type) VALUES (null, null, 5572, null, null, '', null, null, null, '2019-12-27 04:15:44.081476', null, null, null, 'Activity', 'WalWriterMain', null, null, null, '', 'walwriter');