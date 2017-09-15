-- 4.1 Multiple Apartments:
-- Write a SQL query to get the list of tenants who are renting more than one apartment.

SELECT
  t."TenantID",
  t."TenantName"
FROM "Tenants" t JOIN "AptTenants" a_t ON t."TenantID" = a_t."TenantID"
GROUP BY t."TenantID", t."TenantName"
HAVING COUNT(a_t."AptID") > 1;


-- 4.2 Open Requests:

SELECT
  b."BuildingID",
  b."BuildingName",
  count(r."RequestID")
FROM "Buildings" AS b LEFT OUTER JOIN "Apartments" a ON b."BuildingID" = a."BuildingID"
  LEFT OUTER JOIN "Requests" r ON a."AptID" = r."AptID" AND r."Status" = 'Open'
GROUP BY b."BuildingID", b."BuildingName";

-- 4.3 Close All Requests:

UPDATE "Requests"
SET "Status" = 'Closed'
WHERE "AptID" IN (SELECT "AptID"
                  FROM "Apartments"
                  WHERE "BuildingID" = 1);

-- From the onsite interview with HouseCanary:
-- Gaps in IDs:

SELECT
  d2.id + 1 prev_id,
  d1.id
FROM "Data" d1 JOIN "Data" d2 ON d1.id > d2.id + 1
WHERE NOT exists(SELECT d3.id
                 FROM "Data" d3
                 WHERE d3.id > d2.id AND d3.id < d1.id);

SELECT
  d2.id + 1 prev_id,
  d1.id
FROM "Data" d1 JOIN "Data" d2 ON d1.id > d2.id + 1
  LEFT OUTER JOIN "Data" d3 ON d3.id > d2.id AND d3.id < d1.id
WHERE d3.id IS NULL;

-- The list of parents who don't have children younger than 4
SELECT
  "Parent".id,
  "Parent".name
FROM "Parent"
  LEFT OUTER JOIN "Child" ON "Parent".id = "Child"."parentId" AND "Child".age < 4
WHERE "Child".id IS NULL;

-- The list of duplicate Column1 values
SELECT "Table1"."Column1", COUNT("Table1"."Column1") FROM "Table1" GROUP BY "Column1" HAVING COUNT("Table1"."Column1") > 1;


-- The list of parents who don't have children younger than 4 but have children 
SELECT
  "Parent".id,
  "Parent".name
FROM "Parent"
  LEFT OUTER JOIN "Child" ON "Parent".id = "Child"."parentId" AND "Child".age < 4
WHERE "Child".id IS NULL;
