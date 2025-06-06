SELECT
    BOOK_ID,
    DATE_FORMAT(PUBLISHED_DATE, '%Y-%m-%d') AS PUBLISHED_DATE
FROM
    BOOK
WHERE
    # YEAR(PUBLISHED_DATE) = 2021 AND # YEAR 사용
    PUBLISHED_DATE LIKE '2021%' # LIKE 사용
    AND CATEGORY = '인문'
ORDER BY
    PUBLISHED_DATE