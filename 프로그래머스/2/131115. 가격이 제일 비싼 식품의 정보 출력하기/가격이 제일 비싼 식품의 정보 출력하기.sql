SELECT *
FROM FOOD_PRODUCT
WHERE PRODUCT_ID = (SELECT PRODUCT_ID 
                    FROM FOOD_PRODUCT 
                    ORDER BY PRICE DESC
                    LIMIT 1
                   )