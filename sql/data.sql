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
