SELECT 
	c.first_name,
	c.last_name,
	f.title
FROM 
	customer c
JOIN
	rental r
ON
	c.customer_id = r.customer_id 
JOIN
	inventory i 
ON
	r.inventory_id = i.inventory_id
JOIN
	film f 
ON
	i.film_id = f.film_id
ORDER BY
	c.last_name;