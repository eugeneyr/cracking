CREATE TABLE "Complexes" (
  "ComplexID"   INTEGER PRIMARY KEY,
  "ComplexName" VARCHAR(64) NOT NULL
);

CREATE TABLE "Buildings" (
  "BuildingID"   INTEGER PRIMARY KEY,
  "ComplexID"    INTEGER     NOT NULL REFERENCES "Complexes"("ComplexID"),
  "BuildingName" VARCHAR(32) NOT NULL,
  "Address"      VARCHAR(64) NOT NULL
);

CREATE TABLE "Apartments" (
  "AptID"      INTEGER PRIMARY KEY,
  "UnitNumber" VARCHAR(8),
  "BuildingID" INTEGER NOT NULL REFERENCES "Buildings"("BuildingID")
);

CREATE TABLE "Tenants" (
  "TenantID" INTEGER PRIMARY KEY,
  "TenantName" VARCHAR(64) NOT NULL
);

CREATE TABLE "AptTenants" (
  "TenantID" INTEGER REFERENCES "Tenants"("TenantID"),
  "AptID" INTEGER REFERENCES "Apartments"("AptID"),
  CONSTRAINT uq_apt UNIQUE ("AptID")
);

CREATE TABLE "Requests" (
  "RequestID" INTEGER PRIMARY KEY,
  "Status" VARCHAR(16),
  "AptID" INTEGER REFERENCES "Apartments"("AptID"),
  "Description" VARCHAR(512)
);


