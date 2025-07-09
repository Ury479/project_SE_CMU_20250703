CREATE Customers;
SELECT Customers.customer_id, Orders.order_id
FROM Customers
INNER JOIN Orders ON Customers.customer_id = Orders.customer_id;