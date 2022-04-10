SELECT f.title, l.name 
FROM sakila.film as f JOIN sakila.`language` as l 
ON f.language_id = l.language_id 
WHERE f.title like 'a%';