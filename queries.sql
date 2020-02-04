## Part 1: Test it with SQL
    DESCRIBE techjobs.job;
## Part 2: Test it with SQL
    SELECT employer.name
    FROM techjobs.employer
    WHERE location = "Bozoville USA"
## Part 3: Test it with SQL
    DROP TABLE techjobs.job
## Part 4: Test it with SQL
    select distinct skill.id,skill.name,skill.description
    from techjobs.skill
    inner join job_skills on skill.id = skills_id
    where jobs_id is not null ORDER BY skill.name ASC;