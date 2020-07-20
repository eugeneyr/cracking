ALTER TABLE employees ADD middle_name VARCHAR NULL;
UPDATE employees SET middle_name = 'Nandor' WHERE id = 1;
UPDATE employees SET middle_name = 'Nadja' WHERE id = 3;
UPDATE employees SET middle_name = 'Laszlo' WHERE id = 4;






CREATE TABLE "Data" (
  "id" INTEGER PRIMARY KEY
);


INSERT INTO "Data" VALUES
  (1), (2), (3),
  (4), (5), (6),
  (7), (8), (9),
  (10), (11), (12),
  (13),
  --  (14), (15),
  (16), (17), (18),
  --  (19), (20), (21),
  (22), (23), (24);


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
