CREATE VIEW OrderDetailsWithTotalPrice AS
SELECT order_details.order_id, 
       order_details.item_id, 
       order_details.order_qty AS quantity, 
       items.unit_price AS price, 
       (order_details.order_qty * items.unit_price) AS total_price
FROM order_details
JOIN items ON order_details.item_id = items.item_id;
