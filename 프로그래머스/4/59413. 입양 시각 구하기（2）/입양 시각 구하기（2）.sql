WITH RECURSIVE HOURS AS (
    
    SELECT 0 AS HOUR
    
    UNION ALL
    
    SELECT HOUR + 1
    FROM HOURS
    WHERE HOUR < 23
)

SELECT 
  H.HOUR,
  IFNULL(COUNT(A.ANIMAL_ID), 0) AS COUNT
FROM 
    HOURS AS H
        LEFT JOIN ANIMAL_OUTS AS A ON H.HOUR = HOUR(A.DATETIME)
GROUP BY
    1
ORDER BY 
    1