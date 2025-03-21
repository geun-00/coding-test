SELECT ID
FROM ECOLI_DATA
WHERE PARENT_ID IN (
                    SELECT ID
                    FROM ECOLI_DATA
                    WHERE PARENT_ID IN (
                        
                        SELECT ID
                        FROM ECOLI_DATA
                        WHERE ISNULL(PARENT_ID)
                    )
)