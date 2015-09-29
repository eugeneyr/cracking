CREATE DATABASE cracking;


CREATE TABLE "Complexes" (
  "ComplexID"   INTEGER PRIMARY KEY,
  "ComplexName" VARCHAR(64) NOT NULL
);

CREATE TABLE "Buildings" (
  "BuildingID"   INTEGER PRIMARY KEY,
  "ComplexID"    INTEGER     NOT NULL REFERENCES "Complexes" ("ComplexID"),
  "BuildingName" VARCHAR(32) NOT NULL,
  "Address"      VARCHAR(64) NOT NULL
);

CREATE TABLE "Apartments" (
  "AptID"      INTEGER PRIMARY KEY,
  "UnitNumber" VARCHAR(8),
  "BuildingID" INTEGER NOT NULL REFERENCES "Buildings" ("BuildingID")
);

CREATE TABLE "Tenants" (
  "TenantID"   INTEGER PRIMARY KEY,
  "TenantName" VARCHAR(64) NOT NULL
);

CREATE TABLE "AptTenants" (
  "TenantID" INTEGER REFERENCES "Tenants" ("TenantID"),
  "AptID"    INTEGER REFERENCES "Apartments" ("AptID"),
  CONSTRAINT uq_apt UNIQUE ("AptID")
);

CREATE TABLE "Requests" (
  "RequestID"   INTEGER PRIMARY KEY,
  "Status"      VARCHAR(16),
  "AptID"       INTEGER REFERENCES "Apartments" ("AptID"),
  "Description" VARCHAR(512)
);

-- From the problems from the onsite interview with HouseCanary (courtesy of Victor Lipchenko)
CREATE TABLE "Data" (
  "id" INTEGER PRIMARY KEY
);

CREATE TABLE "Parent" (
  "id"   INTEGER PRIMARY KEY,
  "name" VARCHAR(64)
);

CREATE TABLE "Child" (
  "id"       INTEGER PRIMARY KEY,
  "parentId" INTEGER NOT NULL REFERENCES "Parent" ("id"),
  "name"     VARCHAR(32),
  "age"      INTEGER
);

CREATE TABLE "Table1" (
  "id"      INTEGER PRIMARY KEY,
  "Column1" INTEGER
);

