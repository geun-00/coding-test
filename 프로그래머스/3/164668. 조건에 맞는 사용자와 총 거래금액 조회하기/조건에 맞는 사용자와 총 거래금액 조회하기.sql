SELECT 
    B.USER_ID, 
    B.NICKNAME, 
    SUM(PRICE) AS TOTAL_SALES
FROM
    USED_GOODS_BOARD A
        JOIN USED_GOODS_USER B ON A.WRITER_ID = B.USER_ID
WHERE
    A.STATUS = 'DONE'
GROUP BY 
    B.USER_ID HAVING TOTAL_SALES >= 700000
ORDER BY
    3