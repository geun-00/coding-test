SELECT 
    ID, EMAIL, FIRST_NAME, LAST_NAME
FROM 
    DEVELOPER_INFOS
WHERE 
    # "Python" IN (SKILL_1, SKILL_2, SKILL_3)
    SKILL_1 = "Python" or SKILL_2 = "Python" or SKILL_3 = "Python" 
ORDER BY 
    1