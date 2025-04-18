SELECT
    E.EMP_NO,
    E.EMP_NAME,
    G.GRADE,
    CASE
        WHEN G.GRADE = 'S' THEN E.SAL * 0.2
        WHEN G.GRADE = 'A' THEN E.SAL * 0.15
        WHEN G.GRADE = 'B' THEN E.SAL * 0.10
        ELSE 0
    END AS BONUS   
FROM 
    HR_EMPLOYEES E JOIN (
                    SELECT EMP_NO, CASE 
                                       WHEN AVG(SCORE) >= 96 THEN 'S'
                                       WHEN AVG(SCORE) >= 90 THEN 'A'
                                       WHEN AVG(SCORE) >= 80 THEN 'B'
                                       ELSE 'C'
                                   END AS GRADE
                    FROM HR_GRADE
                    GROUP BY EMP_NO
                ) AS G ON E.EMP_NO = G.EMP_NO
ORDER BY 1