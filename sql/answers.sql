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

UPDATE "Requests" set "Status" = 'Closed' where "AptID" in (select "AptID" from "Apartments" where "BuildingID" = 1);

