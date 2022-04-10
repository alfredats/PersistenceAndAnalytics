CREATE VIEW top_rentals as 
SELECT 
	f.title,
	COUNT(DISTINCT c.customer_id) as rental_count
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
GROUP BY
	f.title
ORDER BY 
	rental_count DESC;
	