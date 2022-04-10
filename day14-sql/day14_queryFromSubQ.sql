SELECT 
	t.first_name,
	t.last_name,
	t.title
FROM (
 	SELECT 
 		c.customer_id,
 		c.first_name,
 		c.last_name,
 		f.title
 	FROM 
		customer c 	JOIN rental r 		ON c.customer_id = r.customer_id 
				   	JOIN inventory i 	ON r.inventory_id = i.inventory_id
					JOIN film f 		ON i.film_id = f.film_id
	) as t
ORDER BY t.last_name;
	