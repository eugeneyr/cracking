INSERT INTO "Complexes" ("ComplexID", "ComplexName") VALUES (1, 'Whispering Oaks');
INSERT INTO "Complexes" ("ComplexID", "ComplexName") VALUES (2, 'Greenwood');
INSERT INTO "Complexes" ("ComplexID", "ComplexName") VALUES (3, 'Brookside');

INSERT INTO "Buildings" ("BuildingID", "ComplexID", "BuildingName", "Address")
VALUES (1, 1, 'Mighty Oak', '111 Oak Road');
INSERT INTO "Buildings" ("BuildingID", "ComplexID", "BuildingName", "Address")
VALUES (2, 1, 'Acorns', '113 Oak Road');

INSERT INTO "Buildings" ("BuildingID", "ComplexID", "BuildingName", "Address")
VALUES (3, 2, 'Shady Glen', '1200 Walker Ave');
INSERT INTO "Buildings" ("BuildingID", "ComplexID", "BuildingName", "Address")
VALUES (4, 2, 'Greenhill', '1220 Walker Ave');

INSERT INTO "Apartments" ("AptID", "UnitNumber", "BuildingID") VALUES (1, '101', 1);
INSERT INTO "Apartments" ("AptID", "UnitNumber", "BuildingID") VALUES (2, '102', 1);
INSERT INTO "Apartments" ("AptID", "UnitNumber", "BuildingID") VALUES (3, '201', 1);
INSERT INTO "Apartments" ("AptID", "UnitNumber", "BuildingID") VALUES (4, '202', 1);


INSERT INTO "Apartments" ("AptID", "UnitNumber", "BuildingID") VALUES (5, '1001', 2);
INSERT INTO "Apartments" ("AptID", "UnitNumber", "BuildingID") VALUES (6, '1002', 2);
INSERT INTO "Apartments" ("AptID", "UnitNumber", "BuildingID") VALUES (7, '2001', 2);
INSERT INTO "Apartments" ("AptID", "UnitNumber", "BuildingID") VALUES (8, '2002', 2);


INSERT INTO "Apartments" ("AptID", "UnitNumber", "BuildingID") VALUES (9, 'A-1', 3);
INSERT INTO "Apartments" ("AptID", "UnitNumber", "BuildingID") VALUES (10, 'A-2', 3);
INSERT INTO "Apartments" ("AptID", "UnitNumber", "BuildingID") VALUES (11, 'B-1', 3);
INSERT INTO "Apartments" ("AptID", "UnitNumber", "BuildingID") VALUES (12, 'B-2', 3);

INSERT INTO "Apartments" ("AptID", "UnitNumber", "BuildingID") VALUES (13, '1', 4);


INSERT INTO "Tenants" ("TenantID", "TenantName") VALUES (1, 'Slipping Jim');
INSERT INTO "Tenants" ("TenantID", "TenantName") VALUES (2, 'Guido Van Rossum');
INSERT INTO "Tenants" ("TenantID", "TenantName") VALUES (3, 'Charles Manson');
INSERT INTO "Tenants" ("TenantID", "TenantName") VALUES (4, 'Plinius the Elder');

INSERT INTO "AptTenants" ("TenantID", "AptID") VALUES (1, 1);
INSERT INTO "AptTenants" ("TenantID", "AptID") VALUES (1, 2);
INSERT INTO "AptTenants" ("TenantID", "AptID") VALUES (1, 3);

INSERT INTO "AptTenants" ("TenantID", "AptID") VALUES (2, 4);
INSERT INTO "AptTenants" ("TenantID", "AptID") VALUES (3, 5);
INSERT INTO "AptTenants" ("TenantID", "AptID") VALUES (3, 7);

INSERT INTO "Requests" ("RequestID", "Status", "AptID", "Description") VALUES (1, 'Open', 1, 'Clogged toilet');
INSERT INTO "Requests" ("RequestID", "Status", "AptID", "Description")
VALUES (2, 'Open', 5, 'Blood stains on walls in the living room');
INSERT INTO "Requests" ("RequestID", "Status", "AptID", "Description")
VALUES (3, 'Closed', 2, 'Dripping faucet in the kitchen');
INSERT INTO "Requests" ("RequestID", "Status", "AptID", "Description")
VALUES (4, 'Open', 2, 'Weird smell');

INSERT INTO "Requests" ("RequestID", "Status", "AptID", "Description")
VALUES (5, 'Closed', 13, 'Damaged walls');

INSERT INTO "Data" VALUES (1), (2), (3),
  (4), (5), (6),
  (7), (8), (9),
  (10), (11), (12),
  (13),
  --  (14), (15),
  (16), (17), (18),
  --  (19), (20), (21),
  (22), (23), (24);

INSERT INTO "Parent" (id, name) VALUES (1, 'Confirmed Bachelor');
INSERT INTO "Parent" (id, name) VALUES (2, 'Papa Bear');
INSERT INTO "Parent" (id, name) VALUES (3, 'Wabbit the Wabbit');
INSERT INTO "Parent" (id, name) VALUES (4, 'Rickard Stark');
INSERT INTO "Parent" (id, name) VALUES (5, 'Young Dad');

INSERT INTO "Child" (id, "parentId", name, age) VALUES (1, 2, 'Lil Bear', 3);
INSERT INTO "Child" (id, "parentId", name, age) VALUES (2, 2, 'Medium Bear', 8);
INSERT INTO "Child" (id, "parentId", name, age) VALUES (3, 3, 'Anny Wabbit', 7);
INSERT INTO "Child" (id, "parentId", name, age) VALUES (4, 3, 'Johnny Wabbit', 9);
INSERT INTO "Child" (id, "parentId", name, age) VALUES (5, 3, 'Tony Wabbit', 11);
INSERT INTO "Child" (id, "parentId", name, age) VALUES (6, 3, 'Bunny Wabbit', 12);
INSERT INTO "Child" (id, "parentId", name, age) VALUES (7, 3, 'Jesse Wabbit', 15);
INSERT INTO "Child" (id, "parentId", name, age) VALUES (8, 4, 'Arya Stark', 8);
INSERT INTO "Child" (id, "parentId", name, age) VALUES (9, 4, 'Small Tot', 3);

INSERT INTO "Table1" (id, "Column1") VALUES (1, 1), (2, 2), (3, 3), (4, 4), (5, 2), (6, 5), (7, 3), (8, 3), (9, 3);







