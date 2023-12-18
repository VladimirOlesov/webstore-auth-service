INSERT INTO orders_info (user_uuid)
VALUES ('3dad110d-4ba0-4f35-aa87-65d24a185301');

INSERT INTO order_info_books (order_info_id, book_id, title)
SELECT order_info_id, 1, 'Book Title'
FROM orders_info
WHERE user_uuid = '3dad110d-4ba0-4f35-aa87-65d24a185301'